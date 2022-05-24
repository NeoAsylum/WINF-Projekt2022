package Backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.logging.Level;

import javax.swing.JOptionPane;

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
            stmt = SQLSetup.conn.createStatement();
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
            stmt = SQLSetup.conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            Hauptklasse.log.log(Level.SEVERE, e.getMessage(), e);
        }
    
        return makeAQuery("SELECT " + "Name, VRAM, Hersteller "
                + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';");
    }

    /**
     * Diese Methode macht aus den Daten in der Datenbank ein Object[][] Array,
     * damit dieses für die JTable verarbeitet werden kann.
     * 
     * @param queryText    Die Query welche ausgefuehrt werden soll.
     * @param spaltenNamen Spaltennamen fuer die Tabelle
     * @return Gibt den Array mit Spalten fuer Attribute und Zeilen fuer Elemente
     *         aus.
     */
    public static Object[][] queryToStringArray(String queryText, String[] spaltenNamen) {
        try {
            ResultSet rs = makeAQuery(queryText);
            
            Hauptklasse.log.info(queryText);
    
            rs.last();
            Object[][] arr = new Object[rs.getRow() + 1][spaltenNamen.length];
            rs.beforeFirst();
    
            // Spalten benennen
            for (int i = 0; i < spaltenNamen.length; i++) {
                arr[0][i] = spaltenNamen[i];
            }
    
            // Reihen holen
            for (int i = 1; rs.next(); i++) {
                for (int j = 0; j < spaltenNamen.length; j++) {
                    arr[i][j] = rs.getString(spaltenNamen[j]);
                }
            }
            return arr;
        } catch (SQLSyntaxErrorException e) {
            Hauptklasse.getUI().setSuchTable(NurSQL.nonsenseQuery());
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
        } catch (SQLException e) {
            Hauptklasse.getUI().setSuchTable(NurSQL.nonsenseQuery());
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
        }
        
        return null;
    }

    /**
     * Eine nutzlose Query welche einfach die Suchtabelle leeren oder mit nichts
     * fuellen soll.
     * 
     * @return Gibt einen leeren Array zurueck, da die Query keine ergebnisse
     *         zurueckgeben sollte.
     */
    public static Object[][] nonsenseQuery() {
        return queryToStringArray(
                "SELECT " + "Name, VRAM, Hersteller "
                        + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';",
                new String[] { "Name", "VRAM", "Hersteller" });
    }

}
