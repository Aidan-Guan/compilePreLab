package Grammar;

import Blocks.Block;
import Blocks.BlockList;
import Token.*;

import static Grammar.GrammarAnal.*;

import java.io.IOException;

public class Expression {
    static ExpValue Exp(Block currBlock) throws IOException {
        return AddExp(currBlock);
    }

    static ExpValue AddExp(Block currBlock) throws IOException {
        ExpValue expValue = MulExp(currBlock);
        while(GrammarAnal.currentSym.value.equals("+") || GrammarAnal.currentSym.value.equals("-")){
            if(GrammarAnal.currentSym.value.equals("+")){
                GrammarAnal.getNextSym();
                expValue = Tools.addOperation(currBlock, expValue, MulExp(currBlock));
            }
            else {
                GrammarAnal.getNextSym();
                expValue = Tools.subOperation(currBlock, expValue, MulExp(currBlock));
            }
        }
        return expValue;
    }

    static ExpValue MulExp(Block currBlock) throws IOException {
        ExpValue expValue = UnaryExp(currBlock);
        while(GrammarAnal.currentSym.value.equals("*") || GrammarAnal.currentSym.value.equals("/") || currentSym.value.equals("%")){
            if(GrammarAnal.currentSym.value.equals("*")){
                GrammarAnal.getNextSym();
                expValue = Tools.mulOperation(currBlock, expValue, UnaryExp(currBlock));
            }
            else if(GrammarAnal.currentSym.value.equals("/")){
                GrammarAnal.getNextSym();
                expValue = Tools.divOperation(currBlock, expValue, UnaryExp(currBlock));
            }
            else {
                GrammarAnal.getNextSym();
                expValue = Tools.modOperation(currBlock, expValue, UnaryExp(currBlock));
            }
        }
        return expValue;
    }

    static ExpValue UnaryExp(Block currBlock) throws IOException {
        if (currentSym.value.equals("+") || currentSym.value.equals("-") || currentSym.value.equals("!")) {
            String sign = currentSym.value;
            getNextSym();
            ExpValue expValue = UnaryExp(currBlock);
            if (sign.equals("-")) {
                return Tools.subOperation(currBlock, new ExpValue(0, false), expValue);
            }
            else if (sign.equals("+")){
                return expValue;
            }
            else if (sign.equals("!")) {
                expValue = Tools.notOperation(currBlock, expValue);
                return Tools.zextOperation(currBlock, expValue);
            }
        }
        else if (currentSym.value.equals("(") || currentSym.type.equals("NUMBER")) {
            return PrimaryExp(currBlock);
        }
        else if (currentSym.type.equals("IDENT")) {
            Token funcName = currentSym;
            Token nextSym = showNextSym();
            if (nextSym.value.equals("(")) {
                getNextSym();
                getNextSym();

                if (!currentSym.value.equals(")")) {
                    ExpValue param = FuncAnal.FuncRParams(currBlock);
                    ExpValue expValue = FuncAnal.resolveFunc(currBlock, funcName, param);
                    if (!currentSym.value.equals(")")) {error();}
                    getNextSym();
                    return expValue;
                }
                else {
                    ExpValue param = FuncAnal.FuncRParams(currBlock);
                    ExpValue expValue = FuncAnal.resolveFunc(currBlock, funcName, param);
                    getNextSym();
                    return expValue;
                }
            }
            else {
                return PrimaryExp(currBlock);
            }
        }
        return null;
    }

    private static ExpValue PrimaryExp(Block currBlock) throws IOException {
        if (currentSym.value.equals("(")) {
            getNextSym();
            ExpValue expValue = Exp(currBlock);
            if (!currentSym.value.equals(")")) {error();}
            getNextSym();
            return expValue;
        }
        else if (currentSym.type.equals("NUMBER")) {
            ExpValue expValue = new ExpValue(Integer.parseInt(currentSym.value),false);
            getNextSym();
            return expValue;
        }
        else {
            if (!currentSym.type.equals("IDENT")) {error();}
            Ident tarIdent = identMap.get(currentSym.value);
//            Integer varReg = varMap.getOrDefault(currentSym.value, -1);
            if (tarIdent==null || currentSym.isConst) {error();}

            ExpValue expValue = new ExpValue(tarIdent.regNum,true);
            currBlock.blockStr += Tools.load(regIndex++, expValue.out());
            getNextSym();
            return new ExpValue(regIndex-1, true);
        }
    }


    static Block LOrExp(Block currentBlock, Block tBlock) throws IOException {
        Block fBlock;

        //TODO: 按照后面有没有或分
        if (GrammarAnal.isHaveOr()) {
            fBlock = new Block(currentBlock.compBlock);
        }
        else if (GrammarAnal.isHaveElse()) {
            fBlock = new Block(currentBlock.compBlock);
        }
        else {
            fBlock = currentBlock.compBlock;

        }

//        //TODO: 按照后面有没有else进行区分
//        if (!GrammarAnal.isHaveElse()) {
//            if (currentBlock.compBlock != null) {
//                fBlock = currentBlock.compBlock;
//            }
//            else {
//                fBlock = new Block(currentBlock);
//                currentBlock = fBlock;
//            }
//        }
//        else {
//            fBlock = new Block(currentBlock.compBlock);
//        }
//

        ExpValue expValue = LAndExp(currentBlock, fBlock, tBlock);


        while (currentSym.value.equals("||")) {
            getNextSym();
            fBlock = LOrExp(fBlock, tBlock);
        }

        return fBlock;
    }

    static ExpValue LAndExp(Block currentBlock, Block fBlock, Block tBlock) throws IOException {
        ExpValue expValue = EqExp(currentBlock);

        Block newTBlock = new Block(currentBlock.compBlock);
        if (!currentSym.value.equals("&&")) {
            currentBlock.blockStr += "\tbr i1 " + expValue.out()+", label "+tBlock.out()+", label "+fBlock.out()+"\n";
        }
        else {
            currentBlock.blockStr += "\tbr i1 " + expValue.out() + ", label " + newTBlock.out() + ", label " + fBlock.out() + "\n";
        }

        while (currentSym.value.equals("&&")) {
            getNextSym();
            expValue = LAndExp(newTBlock, fBlock, tBlock);
        }

        return expValue;
    }

    static ExpValue EqExp(Block block) throws IOException {
        ExpValue expValue1 = RelExp(block);
        ExpValue expValue = expValue1;

        if (!(currentSym.value.equals("==")||currentSym.value.equals("!=")) &&  currentSym.value.equals("(") && (showNextSym().value.equals("{")||showNextSym().type.equals("IDENT"))) {
            block.blockStr += "\t%x" + regIndex + " = icmp ne i32 " + expValue.out() +", 0\n";
            expValue = new ExpValue(regIndex, true);
            regIndex++;
            return expValue;
        }

        while (currentSym.value.equals("==") || currentSym.value.equals("!=")) {
            String op = currentSym.value;
            getNextSym();
            ExpValue expValue2 = EqExp(block);
            switch (op) {
                case "==" -> {
                    block.blockStr += "\t%x" + regIndex + " = icmp eq i32 " + expValue1.out() + ", " + expValue2.out() + "\n";
                }
                case "!=" -> {
                    block.blockStr += "\t%x" + regIndex + " = icmp ne i32 " + expValue1.out() + ", " + expValue2.out() + "\n";
                }
            }
            expValue = new ExpValue(regIndex, true);
            regIndex++;
        }

        return expValue;
    }

    static ExpValue RelExp(Block block) throws IOException {
        ExpValue expValue1 = AddExp(block);
        ExpValue expValue = expValue1;

        while (currentSym.value.equals("<") || currentSym.value.equals(">") || currentSym.value.equals("<=") || currentSym.value.equals(">=")) {
            String op = currentSym.value;
            getNextSym();
            ExpValue expValue2 = RelExp(block);

            switch (op) {
                case "<" -> {
                    block.blockStr += ("\t%x" + regIndex + " = icmp slt i32 " + expValue1.out() + ", " + expValue2.out() + "\n");
                }
                case ">" -> {
                    block.blockStr += ("\t%x" + regIndex + " = icmp sgt i32 " + expValue1.out() + ", " + expValue2.out() + "\n");
                }
                case "<=" -> {
                    block.blockStr += ("\t%x" + regIndex + " = icmp sle i32 " + expValue1.out() + ", " + expValue2.out() + "\n");
                }
                case ">=" -> {
                    block.blockStr += ("\t%x" + regIndex + " = icmp sge i32 " + expValue1.out() + ", " + expValue2.out() + "\n");
                }
            }

            expValue = new ExpValue(regIndex, true);
            regIndex++;

        }
        return expValue;
    }

}
