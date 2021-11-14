package Blocks;

import Grammar.GrammarAnal;

import static Grammar.GrammarAnal.*;

public class Block {
    public int regNum;
    public String blockStr = "";

    public Block() {
        regNum = regIndex;
        blockStr = "";
        regIndex ++;
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
