package Backend;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Eine Klasse welche die Ausgaben der SQL-Querys verarbeitet.
 * 
 * @author Team 4
 *
 */
public class QueryOutputHandling {


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
            Hauptklasse.getUI().setSuchTable(SQL.queryToStringArray(query, tabelleneintraege));
        } else if (oberflaeche.equals("Einlagerung")) {
            Hauptklasse.getUI()
                    .setEinlagerungTable(SQL.queryToStringArray(query, tabelleneintraege));
            Hauptklasse.getUI()
                    .setEinlagerungTable(SQL.queryToStringArray(query, tabelleneintraege));

        } else if (oberflaeche.equals("Bestellliste")) {
            Hauptklasse.getUI().formatieren(SQL.queryToStringArray(query, tabelleneintraege));
        }
    }

    /**
     * Eine Methode welche eine Query ueber eine ganze Tabelle erstellt und dann die
     * Zahl der Produkte zu ihren Namen zu mappt.
     * 
     * @param tabellenname Der Name der Tabelle in der SQL Datenbank.
     * @return Eine Map mit Namen und Stueckzahlen fuer alle Objekte der Tabelle.
     */
    public static Map<Object, Long> queryNachNamenStueckzahlen(String tabellenname) {
        Object[][] arr = SQL.queryToStringArray("SELECT Name FROM " + tabellenname + ";",
                new String[] { "Name" });
        Object[] two = Arrays.copyOfRange(getColumn(arr, 0), 1, getColumn(arr, 0).length);
        System.out.println(Arrays.toString(two));
        return stueckzahlen(two);
    }

    /**
     * Methode welche eine Spalte von einem zweidimensionalen Array zurueckgibt.
     * 
     * @param array Der betroffene Array.
     * @param index Der Index der Spalte.
     * @return Die Spalte als Array.
     */
    public static Object[] getColumn(Object[][] array, int index) {
        Object[] column = new Object[array.length];
        for (int i = 0; i < column.length; i++) {
            column[i] = array[i][index];
        }
        return column;
    }

    /**
     * Methode welche aus einem Array eine Map mit Stueckzahlen erzeugt.
     * 
     * @param names Namen.
     * @return Map mit Stueckzahlen.
     */
    public static Map<Object, Long> stueckzahlen(Object[] names) {
        List<Object> a = Arrays.asList(names);
        Map<Object, Long> mapMitStueckzahlen = a.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return mapMitStueckzahlen;
    }

    /**
     * Eine nutzlose Query welche einfach die Suchtabelle leeren oder mit nichts
     * fuellen soll.
     * 
     * @return Gibt einen leeren Array zurueck, da die Query keine ergebnisse
     *         zurueckgeben sollte.
     */
    public static Object[][] nonsenseQuery() {
        System.out.println(Arrays.deepToString(SQL.queryToStringArray(
                "SELECT " + "Name, VRAM, Hersteller "
                        + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';",
                new String[] { "Name", "VRAM", "Hersteller" }))+",,");
        return SQL.queryToStringArray(
                "SELECT " + "Name, VRAM, Hersteller "
                        + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';",
                new String[] { "Name", "VRAM", "Hersteller" });
    }
}
