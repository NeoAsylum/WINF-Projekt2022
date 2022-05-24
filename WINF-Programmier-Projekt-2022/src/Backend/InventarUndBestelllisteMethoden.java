package Backend;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InventarUndBestelllisteMethoden {

    public static Object[][] inventarisierung() {
    
        // Querys für alle PRodukttypen erstellen.
        Object[][] grafikkarten = NurSQL.queryToStringArray(
                "SELECT NAME, ID, LAGERPLATZ FROM GRAFIKKARTE;",
                new String[] { "Name", "ID", "Lagerplatz" });
        Object[][] cpus = NurSQL.queryToStringArray("SELECT NAME, ID, LAGERPLATZ FROM CPU;",
                new String[] { "Name", "ID", "Lagerplatz" });
        Object[][] fertigprodukte = NurSQL.queryToStringArray(
                "SELECT NAME, ID, LAGERPLATZ FROM Fertigprodukt;",
                new String[] { "Name", "ID", "Lagerplatz" });
        Object[][] hauptspeicher = NurSQL.queryToStringArray(
                "SELECT NAME, ID, LAGERPLATZ FROM Hauptspeicher;",
                new String[] { "Name", "ID", "Lagerplatz" });
        Object[][] festplatten = NurSQL.queryToStringArray(
                "SELECT NAME, ID, LAGERPLATZ FROM Festplatte;",
                new String[] { "Name", "ID", "Lagerplatz" });
    
        // Alle Produkttypen in die Spalten einfügen.
        grafikkarten = InventarUndBestelllisteMethoden.produktTypArrayhinzufuegen("Grafikkarte", grafikkarten);
        grafikkarten[0][0] = "Typ";
        cpus = InventarUndBestelllisteMethoden.produktTypArrayhinzufuegen("CPU", cpus);
        fertigprodukte = InventarUndBestelllisteMethoden.produktTypArrayhinzufuegen("Fertigprodukt", fertigprodukte);
        hauptspeicher = InventarUndBestelllisteMethoden.produktTypArrayhinzufuegen("Hauptspeicher", festplatten);
        festplatten = InventarUndBestelllisteMethoden.produktTypArrayhinzufuegen("Festplatte", festplatten);
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
     * Eine Methode welche eine Query ueber eine ganze Tabelle erstellt und dann die
     * Zahl der Produkte zu ihren Namen zu mappt.
     * 
     * @param tabellenname Der Name der Tabelle in der SQL Datenbank.
     * @return Eine Map mit Namen und Stueckzahlen fuer alle Objekte der Tabelle.
     */
    public static Map<Object, Long> queryNachNamenStueckzahlen(String tabellenname) {
        Object[][] arr = NurSQL.queryToStringArray("SELECT Name FROM " + tabellenname + ";",
                new String[] { "Name" });
        Object[] two = Arrays.copyOfRange(SQLZuUI.getColumnInArray(arr, 0), 1, SQLZuUI.getColumnInArray(arr, 0).length);
        System.out.println(Arrays.toString(two));
        return InventarUndBestelllisteMethoden.stueckzahlenInArray(two);
    }

    /**
     * Methode welche aus einem Array eine Map mit Stueckzahlen erzeugt.
     * 
     * @param names Namen.
     * @return Map mit Stueckzahlen.
     */
    public static Map<Object, Long> stueckzahlenInArray(Object[] names) {
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

}
