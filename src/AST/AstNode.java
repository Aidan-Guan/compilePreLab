package AST;

import TokenUtils.Ident;

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
    public int nextLabel = -1;

    public ArrayList<Integer> arrayInfo = new ArrayList<>();
    public Ident arraySym;
    public int dep;

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

    public static boolean hasLoopControl(AstNode parent) {
        boolean flg = false;

        for (AstNode child: parent.children) {
            if (child.value!=null && (child.value.equals("continue") || child.value.equals("break"))) {
                return true;
            }
            else if (child.value==null) {
                if (hasLoopControl(child))
                    flg = true;
            }
        }

        return flg;
    }

    public void copyNextLabel(AstNode node){
//        System.out.println(node.nextLabel);
        if(node.children.size() == 7){
            AstNode stmtNode = node.children.get(6);
            AstNode newNode = stmtNode.children.get(0);

            if(newNode.value!=null && newNode.value.equals("if")){
                stmtNode.nextLabel = nextLabel;
                copyNextLabel(newNode);
            }
        }
    }
}
