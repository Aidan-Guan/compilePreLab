package TokenUtils;

import java.util.HashMap;

public class IdentMap {
    public String mapName;
    public HashMap<String, Ident> map = new HashMap<>();

    public IdentMap(String name, HashMap<String, Ident> theMap) {
        mapName = name;
        map = theMap;
    }

    public IdentMap(HashMap<String, Ident> theMap) {
        map = theMap;
        mapName = "";
    }
}
