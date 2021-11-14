package Blocks;

import Grammar.GrammarAnal;

import static Grammar.GrammarAnal.*;

public class Block {
    public int regNum;
    public String blockStr = "";
    public Block compBlock = null; //当前块没有if等判断，此处记录直接的下一跳块


    public Block() {
        regNum = regIndex;
        blockStr = "";
        regIndex ++;
        BlockList.blockArrayList.add(this);
    }

    public Block(Block compBlock) {
        if (regIndex == 20) {
            System.out.println("here");
        }
        regNum = regIndex;
        blockStr = "";
        regIndex ++;
        this.compBlock = compBlock;
        BlockList.blockArrayList.add(this);
    }

    public Block(boolean isMain) {
        regNum = -1;
        blockStr = "";
        BlockList.blockArrayList.add(this);
    }

    public String out() {
        return "%x"+regNum;
    }

    public String outputBlockStr() {
        blockStr = "x"+regNum+":\n" + blockStr;
        return blockStr;
    }
}
