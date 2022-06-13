package backend;

import datentypen.Produkt;
import sql.NurSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Eine Klasse welche Methoden zum Prozess des Einlagerns enthaelt.
 * 
 * @author AllStars
 *
 */
public class EinlagernMethoden {

  /**
   * Methode welche die Stueckzahl im Lager hochzaehlt.
   * 
   * @param tablename    Der Name der Tabelle in der SQL Datenbank.
   * @param name         Der Name des PRoduktes als Identifikator.
   * @param lagerplatzId Die ID des Lagerplatzes.
   */
  public static void anzahlImLagerHochzaehlen(int lagerplatzId, String tablename, String name) {
    NurSQL.update(
        "UPDATE LAGERPLATZ SET Name='" + name + "', Menge=Menge+1 WHERE ID=" + lagerplatzId + ";");
  }

  /**
   * Methode welche die LagerplatzID fuer ein Produkt ausgibt.
   * 
   * @param produktTyp Der Produkttyp des Produktes.
   * @param name       Der Name des Produktes welches eingelagert werden soll.
   * @return Gibt den Lagerplatz zurueck.
   */
  public static int einlagern(Produkt produktTyp, String name) {
    try {
      ResultSet rs = NurSQL.makeAQuery("SELECT ID FROM LAGERPLATZ WHERE TYP='"
          + produktTyp.produktTyp() + "' AND Name='" + name + "';");
      rs.first();
      anzahlImLagerHochzaehlen(rs.getInt(1), produktTyp.produktTyp(), name);
      return rs.getInt(1);
    } catch (SQLException e) {
      Hauptklasse.log.log(Level.SEVERE, "es muss ein neuer Lagerplatz zugewiesen werden", e);
      try {
        ResultSet rs = NurSQL.makeAQuery("SELECT ID FROM LAGERPLATZ WHERE Menge=0;");
        rs.first();
        NurSQL.update("UPDATE LAGERPLATZ SET TYP='" + produktTyp.produktTyp() + "',name='"
            + name.toString() + "' WHERE ID=" + rs.getInt(1) + ";");
        return einlagern(produktTyp, name);
      } catch (SQLException e1) {
        System.out.println("There is no more free space!!!");
        Hauptklasse.log.log(Level.SEVERE, e.getMessage(), e1);
      }
      Hauptklasse.log.log(Level.SEVERE, e.getMessage());
    }
    return -1;
  }

}
