package TokenUtils;

import ErrorSolution.Error;

import java.util.ArrayList;
import java.util.HashMap;

public class IdentMapList {
    static ArrayList<HashMap<String, Ident>> identMapList = new ArrayList<>();
    static int mapListIndex = 0;

    public static HashMap<String, Ident> getCurrentMap() {
        if (mapListIndex == 0) {
            Error.error();
        }
        return identMapList.get(mapListIndex-1);
    }



}