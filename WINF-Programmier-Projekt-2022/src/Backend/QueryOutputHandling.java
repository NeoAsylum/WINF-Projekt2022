package Backend;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static Object[][] produktTypArrayhinzufuegen(String hinzuzufuegen, Object[][] array) {
        Object[][] result = new Object[array.length][array[0].length + 1];
        for (int i = 0; i < array.length; i++) {
            result[i][0] = hinzuzufuegen;
            for (int a = 1; a < array[0].length + 1; a++) {
                result[i][a] = array[i][a - 1];
            }
        }
        return result;
    }

    public static Object[][] inventarisierung() {

        // Querys für alle PRodukttypen erstellen.
        Object[][] grafikkarten = SQL.queryToStringArray(
                "SELECT NAME, ID, LAGERPLATZ FROM GRAFIKKARTE;",
                new String[] { "Name", "ID", "Lagerplatz" });
        Object[][] cpus = SQL.queryToStringArray("SELECT NAME, ID, LAGERPLATZ FROM CPU;",
                new String[] { "Name", "ID", "Lagerplatz" });
        Object[][] fertigprodukte = SQL.queryToStringArray(
                "SELECT NAME, ID, LAGERPLATZ FROM Fertigprodukt;",
                new String[] { "Name", "ID", "Lagerplatz" });
        Object[][] hauptspeicher = SQL.queryToStringArray(
                "SELECT NAME, ID, LAGERPLATZ FROM Hauptspeicher;",
                new String[] { "Name", "ID", "Lagerplatz" });
        Object[][] festplatten = SQL.queryToStringArray(
                "SELECT NAME, ID, LAGERPLATZ FROM Festplatte;",
                new String[] { "Name", "ID", "Lagerplatz" });

        // Alle Produkttypen in die Spalten einfügen.
        grafikkarten = produktTypArrayhinzufuegen("Grafikkarte", grafikkarten);
        grafikkarten[0][0] = "Typ";
        cpus = produktTypArrayhinzufuegen("CPU", cpus);
        fertigprodukte = produktTypArrayhinzufuegen("Fertigprodukt", fertigprodukte);
        hauptspeicher = produktTypArrayhinzufuegen("Hauptspeicher", festplatten);
        festplatten = produktTypArrayhinzufuegen("Festplatte", festplatten);
        System.out.println(Arrays.deepToString(festplatten));

        // Die erste Zeile mit den Spaltennamen abschneiden
        grafikkarten = Arrays.copyOfRange(grafikkarten, 1, grafikkarten.length);

        cpus = Arrays.copyOfRange(cpus, 1, cpus.length);
        fertigprodukte = Arrays.copyOfRange(fertigprodukte, 1, fertigprodukte.length);
        hauptspeicher = Arrays.copyOfRange(hauptspeicher, 1, hauptspeicher.length);
        festplatten = Arrays.copyOfRange(festplatten, 1, festplatten.length);

        // Alle Arrays zu Einem kombinieren.
        Object[][] result = Stream
                .of(Arrays.stream(grafikkarten), Arrays.stream(cpus),
                        Arrays.stream(fertigprodukte), Arrays.stream(festplatten),
                        Arrays.stream(hauptspeicher))
                .flatMap(s -> s).sorted((a, b) -> (((String) a[3]).compareTo((String) b[3])))
                .toArray(Object[][]::new);
        result = Stream.of(Arrays.stream(new Object[][] { { "Typ", "Name", "ID", "Lagerplatz" } }),
                Arrays.stream(result)).flatMap(s -> s).toArray(Object[][]::new);
        System.out.println(Arrays.deepToString(result) + ";;");
        return result;
    }

    /**
     * Eine nutzlose Query welche einfach die Suchtabelle leeren oder mit nichts
     * fuellen soll.
     * 
     * @return Gibt einen leeren Array zurueck, da die Query keine ergebnisse
     *         zurueckgeben sollte.
     */
    public static Object[][] nonsenseQuery() {
        return SQL.queryToStringArray(
                "SELECT " + "Name, VRAM, Hersteller "
                        + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';",
                new String[] { "Name", "VRAM", "Hersteller" });
    }
}
