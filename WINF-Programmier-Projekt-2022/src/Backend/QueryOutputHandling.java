package Backend;

public class QueryOutputHandling {

    public static void einlagern() {

    }

    /**
     * Ruft die Methoden auf welche das jeweilige UI setzen mittels eines Arrays mit
     * Tabelleneinträgen.
     * 
     * @param query
     * @param oberflaeche
     * @param tabelleneintraege
     */
    public static void queryToUI(String query, String oberflaeche, String[] tabelleneintraege) {
        if (oberflaeche.equals("Suche")) {
            Hauptklasse.frame
                    .setSuchTable(SQL.queryToStringArray(query, tabelleneintraege));
        } else if (oberflaeche.equals("Einlagerung")) {
            Hauptklasse.frame.setEinlagerungTable(
                    SQL.queryToStringArray(query, tabelleneintraege));
            
        }else if (oberflaeche.equals("Bestellliste")) {
            Hauptklasse.frame.setBestellTable(
                    SQL.queryToStringArray(query, tabelleneintraege));
        }
    }

    /**
     * Eine nutzlose Query welche einfach die Suchtabelle leeren oder mit nichts
     * fuellen soll.
     * 
     * @return
     */
    public static Object[][] nonsenseQuery() {
        return SQL.queryToStringArray(
                "SELECT " + "Name, VRAM, Hersteller "
                        + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';",
                new String[] { "Name", "VRAM", "Hersteller" });
    }
}
