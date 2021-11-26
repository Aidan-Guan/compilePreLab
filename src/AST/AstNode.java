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
}
