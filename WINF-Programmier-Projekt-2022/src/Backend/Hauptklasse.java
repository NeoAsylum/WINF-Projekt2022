package Backend;

import UI.UI;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Hauptklasse {

    private static UI frame;
    private static International uebersetzer = new International();
    public static final Logger log = Logger.getLogger(Hauptklasse.class.getName());

    /**
     * Dies ist die Main-Methode.
     * 
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            SQLSetup.setup();
        } else if (Arrays.stream(args).collect(Collectors.toList()).contains("no login")) {
            SQLSetup.setup();
        }
    }

    public static UI getUI() {
        return frame;
    }

    public static void setUI(UI newFrame) {
        frame = newFrame;
    }

    public static International getUebersetzer() {
        return uebersetzer;
    }

    public static void setUebersetzer(International ue) {
        uebersetzer = ue;
    }

}
