package AST;

import java.util.ArrayList;

public class AstNode {
    public String type;
    public String value;
    public ArrayList<AstNode> children = new ArrayList<>();
    public AstNode parent;

    public int condBlock = -1;

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
