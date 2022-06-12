package Backend;

import UI.Login;
import UI.UI;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import SQL.NurSQL;
import SQL.SQLSetup;
import Stuff.International;

public class Hauptklasse {

    private static UI frame;
    private static Login login;
    private static International uebersetzer = new International();
    public static final Logger log = Logger.getLogger(Hauptklasse.class.getName());

    /**
     * Dies ist die Main-Methode.
     * Sie führt die Applikation aus.
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            SQLSetup.setupSQL();
        } else if (Arrays.stream(args).collect(Collectors.toList()).contains("no login")) {
            SQLSetup.setupSQL();
        }
        login = new Login();
        login.setVisible(true);
       
    }

    private static void disposeOfFrame() {
        frame.dispose();
    }

    public static void nonsenseQuery() {
        frame.setSuchTable(NurSQL.nonsenseQuery());
    }

    public static void setupUI() {
        if (frame != null) {
            disposeOfFrame();
        }
        frame = new UI();
        frame.addActionListenersToUi();
        frame.setVisible(true);
    }

    public static International getUebersetzer() {
        return uebersetzer;
    }

    public static void setUebersetzer(International ue) {
        uebersetzer = ue;
    }

    public static UI getUI() {
        return frame;
    }

}
