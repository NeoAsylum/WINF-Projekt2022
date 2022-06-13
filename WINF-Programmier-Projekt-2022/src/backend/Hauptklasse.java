package backend;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import sql.NurSQL;
import sql.sqlSetup;
import stuff.International;
import ui.Login;
import ui.UI;

/**
 * Hauptklasse zum Starten des Programmes.
 * 
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
   * Dies ist die Main-Methode. Sie führt die Applikation aus.
   * 
   * @param args Argumente fuer das Ausfuehren des Programmes.
   */
  public static void main(String[] args) {
    if (args.length < 1) {
      sqlSetup.setupSql();
    } else if (Arrays.stream(args).collect(Collectors.toList()).contains("no login")) {
      sqlSetup.setupSql();
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
  public static void setupUi() {
    if (frame != null) {
      disposeOfFrame();
    }
    frame = new UI();
    frame.addActionListenersToUi();
    frame.setVisible(true);
  }

  /**
   * Getter fuer den Uebersetzer.
   * 
   * @return Der Uebersetzer.
   */
  public static International getUebersetzer() {
    return uebersetzer;
  }

  /**
   * Setter fuer den Uebersetzer.
   * 
   * @param ue Der Uebersetzer.
   */
  public static void setUebersetzer(International ue) {
    uebersetzer = ue;
  }

  /**
   * Getter fuer das UI.
   * 
   * @return Gibt das UI zurueck.
   */
  public static UI getUI() {
    return frame;
  }

}
