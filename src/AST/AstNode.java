package AST;

import java.util.ArrayList;

public class AstNode {
    public String type;
    public String value;
    public ArrayList<AstNode> children = new ArrayList<>();

    /**
     * 创建方法
     * @param type
     * @param value
     */
    public AstNode (String type, String value) {
        this.type = type;
        this.value = value;
    }
}