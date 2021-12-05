package AST;

import java.util.ArrayList;

public class AstNode {
    public String type;
    public String value;
    public ArrayList<AstNode> children = new ArrayList<>();
    public AstNode parent;

    public int tBlock = -1;
    public int fBlock = -1;
    public int condBlock = -1;
    public int breakBlock = -1;
    public int continueBlock = -1;
    public ArrayList<Integer> loopLabel;

    /**
     * 创建方法
     * @param type
     * @param value
     */
    public AstNode (String type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * 创建方法
     * @param type
     */
    public AstNode (String type) {
        this.type = type;
    }


    public int getContinueBlock () {
        return this.loopLabel.get(0);
    }


    public int getBreakBlock () {
        return this.loopLabel.get(2);
    }

    public static void copyWhile(AstNode parent) {
        for (AstNode child: parent.children) {
            child.loopLabel = parent.loopLabel;
            copyWhile(child);
        }
    }
}
