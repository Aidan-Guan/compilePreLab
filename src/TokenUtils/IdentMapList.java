package TokenUtils;

import ErrorSolution.ErrorSolu;
import LexicalAnalysis.Token;

import java.util.ArrayList;
import java.util.HashMap;

public class IdentMapList {
    static ArrayList<HashMap<String, Ident>> identMapList = new ArrayList<>();
    static int mapListIndex = 0;

    public static HashMap<String, Ident> getCurrentMap() {
        if (mapListIndex == 0) {
            ErrorSolu.error();
        }
        return identMapList.get(mapListIndex-1);
    }

    public static HashMap<String, Ident> getGlobalMap() {
        if (mapListIndex == 0) ErrorSolu.error();

        return identMapList.get(0);
    }

    public static void addMap(HashMap<String, Ident> newMap) {
        identMapList.add(newMap);
        mapListIndex++;
    }

    public static void removeFisrtMap() {
        mapListIndex--;
        identMapList.remove(mapListIndex);
    }

    public Ident getIdentInCurrMap(String str) {
        HashMap<String, Ident> currMap = identMapList.get(mapListIndex-1);
        return currMap.get(str);
    }

    public Ident getIdentInAllMap(String str) {
        for (int i=mapListIndex-1; i>=0; i--) {
            Ident tarIdent = identMapList.get(i).get(str);
            if (tarIdent!=null) {
                return tarIdent;
            }
        }
        return null;
    }


}
