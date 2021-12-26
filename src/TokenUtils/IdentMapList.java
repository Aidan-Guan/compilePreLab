package TokenUtils;

import ErrorSolution.ErrorSolu;
import LexicalAnalysis.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class IdentMapList {

    static ArrayList<IdentMap> identMapList = new ArrayList<>();
    static int mapListIndex = 0;

    /**
     * 获取栈顶符号表，即当前快
     * @return
     */
    public static HashMap<String, Ident> getCurrentMap() {
        if (mapListIndex == 0) {
            ErrorSolu.error();
        }
        return identMapList.get(mapListIndex-1).map;
    }

    /**
     * 获取全局变量符号表
     * @return
     */
    public static HashMap<String, Ident> getGlobalMap() {
        if (mapListIndex == 0) ErrorSolu.error();

        return identMapList.get(0).map;
    }

    /**
     * 添加新符号表 不命名
     * @param newMap
     */
    public static void addMap(HashMap<String, Ident> newMap) {
        IdentMap map = new IdentMap(newMap);
        identMapList.add(map);
        mapListIndex++;
    }

    /**
     * 命名新建符号表
     * @param mapName
     * @param newMap
     */
    public static void addMap(String mapName, HashMap<String, Ident> newMap) {
        IdentMap map = new IdentMap(mapName, newMap);
        identMapList.add(map);
        mapListIndex++;
    }



    /**
     * 删除栈顶符号表
     */
    public static void removeFisrtMap() {
        mapListIndex--;
        identMapList.remove(mapListIndex);
    }


    /**
     * 在所有符号表中获取变量
     * @param str
     * @return
     */
    public static Ident getIdentInAllMap(String str) {
        for (int i=mapListIndex-1; i>=0; i--) {
            Ident tarIdent = identMapList.get(i).map.get(str);
            if (tarIdent!=null) {
                return tarIdent;
            }
        }
        return null;
    }


//    public static Ident getIdentInCurrMap(String str) {
//        HashMap<String, Ident> currMap = identMapList.get(mapListIndex-1);
//        return currMap.get(str);
//    }

}
