package Token;

import java.util.ArrayList;
import java.util.HashMap;


public class IdentMapList {
    static int mapIndex = 0;
    static ArrayList<HashMap<String, Ident>> identMapList = new ArrayList<>();

    public static void mapGenerate() {
        HashMap<String, Ident> identMap = new HashMap<String, Ident>();
        identMapList.add(identMap);
        mapIndex++;
    }

    public static void removeTopMap() {
//        HashMap<String, Ident> tarMap = identMapList.get(mapIndex-1);
        identMapList.remove(mapIndex-1);
        mapIndex--;
    }

    public static HashMap<String, Ident> getCurrMap() {
        return identMapList.get(mapIndex-1);
    }

    public static Ident getTarIdentInAllBlocks(String identName) {
        for (int i = mapIndex-1; i>=0; i--) {
            Ident tarIdent = identMapList.get(i).get(identName);
            if (tarIdent != null) {
                return tarIdent;
            }
        }
        return null;
    }

    public static Ident getTarIdentInCurrBlock(String identName) {
        return identMapList.get(mapIndex-1).get(identName);
    }

    public static int getListLength() {
        return mapIndex;
    }
}
