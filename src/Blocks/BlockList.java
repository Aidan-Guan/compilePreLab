package Blocks;

import java.util.ArrayList;

public class BlockList {
    public static ArrayList<Block> blockArrayList = new ArrayList<>();

    public static Block getMainBlock() {
        for (Block item: blockArrayList) {
            if (item.regNum == -1) return item;
        }
        return null;
    }
}
