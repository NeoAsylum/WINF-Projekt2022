package SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import Backend.Hauptklasse;

/**
 * Eine Klasse welche die Ausgaben der SQL-Querys verarbeitet.
 * 
 * @author AllStars
 *
 */
public class SQLOutputHandling {

    /**
     * Ruft die Methoden auf welche das jeweilige UI setzen mittels eines Arrays mit
     * Tabelleneinträgen.
     * 
     * @param query             Die auszufuehrende Query.
     * @param oberflaeche       Die Oberflaeche in welcher die Query auszufuehren
     *                          ist.
     * @param tabelleneintraege Die Spalten der Tabelle.
     */
    public static void queryToUI(String query, String oberflaeche, String[] tabelleneintraege) {
        if (oberflaeche.equals("Suche")) {
            Hauptklasse.getUI()
                    .setSuchTable(SQLOutputHandling.queryToStringArray(query, tabelleneintraege));
        } else if (oberflaeche.equals("Einlagerung")) {
            Hauptklasse.getUI().setEinlagerungTable(
                    SQLOutputHandling.queryToStringArray(query, tabelleneintraege));
            Hauptklasse.getUI().setEinlagerungTable(
                    SQLOutputHandling.queryToStringArray(query, tabelleneintraege));

        } else if (oberflaeche.equals("Bestellliste")) {
            Hauptklasse.getUI()
                    .formatierenBestellliste(SQLOutputHandling.queryToStringArray(query, tabelleneintraege));
        }
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
            ResultSet rs = NurSQL.makeAQuery(queryText);

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
            Hauptklasse.nonsenseQuery();
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
        } catch (SQLException e) {
            Hauptklasse.nonsenseQuery();
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
        }

        return null;
    }
}
