package Backend;

import UI.UI;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Hauptklasse {

    private static UI frame;
    public static International uebersetzer = new International();

    public static final Logger log = Logger.getLogger(Hauptklasse.class.getName());

    /**
     * Dies ist die Main-Methode.
     * 
     * @param args
     */
    public static void main(String[] args) {
        TreeMap tm=new TreeMap();
        if (args.length < 1) {
            SQL.setup();
        } else if (Arrays.stream(args).collect(Collectors.toList()).contains("no login")) {
            SQL.setup();
        }
        System.out.println("blub");
        System.out.println(Arrays.deepToString(SQL.queryToStringArray(
                "SELECT " + "Name, VRAM, Hersteller "
                        + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';",
                new String[] { "Name", "VRAM", "Hersteller" }))+",,");
    }
    
    public static UI getUI() {
        return frame;
    }
    
    public static void setUI(UI newFrame) {
        frame=newFrame;
    }

}
