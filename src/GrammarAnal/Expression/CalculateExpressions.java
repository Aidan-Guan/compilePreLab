package GrammarAnal.Expression;

import AST.AstNode;
import ErrorSolution.ErrorSolu;
import LexicalAnalysis.Token;

import java.security.spec.ECParameterSpec;

import static GrammarAnal.Grammar.TokensToAST.*;

public class CalculateExpressions {

    public static void Exp (AstNode parent) {
        AstNode NodeExp = new AstNode("<Exp>");

        addExp(NodeExp);

        addChild(NodeExp, parent);
    }


    public static void constExp (AstNode parent) {
        AstNode NodeConstExp = new AstNode("<ConstExp>");

        addExp(NodeConstExp);

        addChild(NodeConstExp, parent);
    }

    public static void addExp(AstNode parent) {
        AstNode NodeAddExp = new AstNode("<AddExp>");

        mulExp(NodeAddExp);

        while (currentSym.value.equals("+") || currentSym.value.equals("-")) {
            addChild(currentSym, NodeAddExp);
            getNextSym();

            mulExp(NodeAddExp);
        }

        addChild(NodeAddExp, parent);
    }


    public static void mulExp(AstNode parent) {
        AstNode NodeMulExp = new AstNode("<MulExp>");

        unaryExp(NodeMulExp);

        while (currentSym.value.equals("*") || currentSym.value.equals("/") || currentSym.value.equals("%")) {
            addChild(currentSym, NodeMulExp);
            getNextSym();

            unaryExp(NodeMulExp);
        }

        addChild(NodeMulExp, parent);
    }


    public static void unaryExp(AstNode parent) {
        AstNode NodeUnaryExp = new AstNode("<UnaryExp>");

        Token tmp = showFutureSym(1);

        if (currentSym.value.equals("+") || currentSym.value.equals("-") || currentSym.value.equals("!")) {
            unaryOp(NodeUnaryExp);
            unaryExp(NodeUnaryExp);
        }
        else if (currentSym.type.equals("IDENT") && tmp.value.equals("(")) {
            addChild(currentSym, NodeUnaryExp);
            getNextSym();

            if (!currentSym.value.equals("(")) ErrorSolu.error();
            addChild(currentSym, NodeUnaryExp);
            getNextSym();

            FuncParams(NodeUnaryExp);

            if (!currentSym.value.equals(")")) ErrorSolu.error();
            addChild(currentSym, NodeUnaryExp);
            getNextSym();
        }
        else if (currentSym.type.equals("IDENT") || currentSym.type.equals("NUMBER")) {
            primaryExp(NodeUnaryExp);
        }
        else {ErrorSolu.error();}

        addChild(NodeUnaryExp, parent);
    }


    public static void FuncParams(AstNode parent) {
        AstNode NodeFuncParams = new AstNode("<FuncParams>");

        if (currentSym.value.equals(")")) {
            addChild(NodeFuncParams, parent);
            return;
        }

        Exp(NodeFuncParams);

        while (currentSym.value.equals(",")) {
            addChild(currentSym, NodeFuncParams);
            Exp(NodeFuncParams);
        }

        addChild(NodeFuncParams, parent);
    }


    public static void unaryOp(AstNode parent) {
        AstNode NodeUnaryOp = new AstNode("<UnaryOp>");

        if (currentSym.value.equals("+") || currentSym.value.equals("-") || currentSym.value.equals("!")) {
            addChild(currentSym, NodeUnaryOp);
            getNextSym();
        }
        else {
            ErrorSolu.error();
        }

        addChild(NodeUnaryOp, parent);
    }


    public static void primaryExp(AstNode parent) {
        AstNode NodePrimaryExp = new AstNode("<PrimaryExp>");

        if (currentSym.value.equals("(")) {
            addChild(currentSym, NodePrimaryExp);
            getNextSym();

            Exp(NodePrimaryExp);

            if (!currentSym.value.equals(")")) ErrorSolu.error();
            addChild(currentSym, NodePrimaryExp);
            getNextSym();

        }
        else if (currentSym.type.equals("NUMBER")) {
            addChild(currentSym, NodePrimaryExp);
            getNextSym();
        }
        else if (currentSym.type.equals("IDENT")) {
            LVal(NodePrimaryExp);
        }

        addChild(NodePrimaryExp, parent);
    }

    static void LVal(AstNode parent) {
        AstNode NodeLVal = new AstNode("<LVal>");
        if (!currentSym.type.equals("IDENT")) ErrorSolu.error();

        addChild(currentSym, NodeLVal);
        getNextSym();

        addChild(NodeLVal, parent);
    }
}
