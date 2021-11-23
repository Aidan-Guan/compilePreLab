package TokenUtils;

import java.awt.*;
import java.net.IDN;
import java.util.ArrayList;

public class Ident {
    public String name; // 名称
    public IdentType identType; // 变量种类
    public ValueType valueType; // 值种类
    public int regInex; // 寄存器号
    public ArrayList<Ident> funcParams; // 函数参数
    public boolean isAssigned; // 仅仅被定义
    public boolean isDeclared = false;

    /**
     * 创建函数
     * @param name
     * @param identType
     * @param valueType
     * @param funcParams
     */
    public Ident (String name, IdentType identType, ValueType valueType, ArrayList<Ident> funcParams) {
        this.name = name;
        this.identType = identType;
        this.valueType = valueType;
        this.funcParams = funcParams;
    }

    /**
     * 创建变量
     * @param name
     * @param identType
     * @param valueType
     * @param regInex
     */
    public Ident (String name, IdentType identType, ValueType valueType, int regInex) {
        this.name = name;
        this.identType = identType;
        this.valueType = valueType;
        this.regInex = regInex;
    }

    public String out() {
        return "%x"+regInex;
    }
}
