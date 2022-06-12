package SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import Backend.Hauptklasse;

public class NurSQL {

    /**
     * Diese Methode sendet ein Update-Query zum SQL-Server.
     * 
     * @param sqlQueryText Der Text der Query welche zum Server geschickt werden
     *                     soll.
     */
    public static void update(String sqlQueryText) {
        Statement stmt;
        try {
            stmt = SQLSetup.getConn().createStatement();
            Hauptklasse.log.info(sqlQueryText + " update Query");
            stmt.executeUpdate(sqlQueryText);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Methode welche mit einem String eine Query erstellt.
     * 
     * @param query Die zu stellende Query.
     * @return Der Rueckgabewert als ResultSet.
     */
    public static ResultSet makeAQuery(String query) {
        Statement stmt;
        try {
            stmt = SQLSetup.getConn().createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            Hauptklasse.log.log(Level.SEVERE, e.getMessage(), e);
        }
    
        return makeAQuery("SELECT " + "Name, VRAM, Hersteller "
                + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';");
    }

    /**
     * Eine nutzlose Query welche einfach die Suchtabelle leeren oder mit nichts
     * fuellen soll.
     * 
     * @return Gibt einen leeren Array zurueck, da die Query keine ergebnisse
     *         zurueckgeben sollte.
     */
    public static Object[][] nonsenseQuery() {
        return SQLOutputHandling.queryToStringArray(
                "SELECT " + "Name, VRAM, Hersteller "
                        + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';",
                new String[] { "Name", "VRAM", "Hersteller" });
    }

}
