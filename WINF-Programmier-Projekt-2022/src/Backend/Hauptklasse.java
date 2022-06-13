package Backend;

import UI.Login;
import UI.UI;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import SQL.NurSQL;
import SQL.SQLSetup;
import Stuff.International;

/**
 * Hauptklasse zum Starten des Programmes.
 * @author AllStars
 *
 */
public class Hauptklasse {

    /**
     * JFrame mit UI.
     */
    private static UI frame;
    /**
     * Instanz der Login-Klasse.
     */
    private static Login login;
    /**
     * Instanz fuer den Uebersetzer.
     */
    private static International uebersetzer = new International();
    /**
     * Instanz fuer den Logger.
     */
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

    /**
     * Methode welche das frame entsorgt.
     */
    private static void disposeOfFrame() {
        frame.dispose();
    }

    /**
     * Query welche die Suchtabelle mit 'nichts' fuellt.
     */
    public static void nonsenseQuery() {
        frame.setSuchTable(NurSQL.nonsenseQuery());
    }

    /**
     * Setup fuer UI.
     */
    public static void setupUI() {
        if (frame != null) {
            disposeOfFrame();
        }
        frame = new UI();
        frame.addActionListenersToUi();
        frame.setVisible(true);
    }

    /**
     * Getter fuer den Uebersetzer.
     * @return Der Uebersetzer.
     */
    public static International getUebersetzer() {
        return uebersetzer;
    }

    /**
     * Setter fuer den Uebersetzer.
     * @param ue Der Uebersetzer.
     */
    public static void setUebersetzer(International ue) {
        uebersetzer = ue;
    }

    /**
     * Getter fuer das UI.
     * @return
     */
    public static UI getUI() {
        return frame;
    }

}
