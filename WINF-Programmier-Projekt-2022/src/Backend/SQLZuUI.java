package Backend;

/**
 * Eine Klasse welche die Ausgaben der SQL-Querys verarbeitet.
 * 
 * @author Team 4
 *
 */
public class SQLZuUI {

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
            Hauptklasse.getUI().setSuchTable(NurSQL.queryToStringArray(query, tabelleneintraege));
        } else if (oberflaeche.equals("Einlagerung")) {
            Hauptklasse.getUI()
                    .setEinlagerungTable(NurSQL.queryToStringArray(query, tabelleneintraege));
            Hauptklasse.getUI()
                    .setEinlagerungTable(NurSQL.queryToStringArray(query, tabelleneintraege));

        } else if (oberflaeche.equals("Bestellliste")) {
            Hauptklasse.getUI().formatieren(NurSQL.queryToStringArray(query, tabelleneintraege));
        }
    }

    /**
     * Methode welche eine Spalte von einem zweidimensionalen Array zurueckgibt.
     * 
     * @param array Der betroffene Array.
     * @param index Der Index der Spalte.
     * @return Die Spalte als Array.
     */
    public static Object[] getColumnInArray(Object[][] array, int index) {
        Object[] column = new Object[array.length];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }
        return column;
    }
}
