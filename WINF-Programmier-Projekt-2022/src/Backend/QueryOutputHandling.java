package Backend;

import java.sql.SQLException;

public class QueryOutputHandling {


    public static void einlagern() {
        
    }
    
    public static void queryToUI(String query, String oberflaeche, String[] tabelleneintraege) {
        try {
            if (oberflaeche.equals("Suche")) {
                Hauptklasse.frame.setSuchTable(
                        SQL.queryToStringArray(query, tabelleneintraege, oberflaeche));
            } else if (oberflaeche.equals("Einlagerung")) {
                Hauptklasse.frame.setEinlagerungTable(
                        SQL.queryToStringArray(query, tabelleneintraege, oberflaeche));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Object[][] nonsenseQuery() {
        try {
            return SQL.queryToStringArray(
                    "SELECT " + "Name, VRAM, Hersteller "
                            + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';",
                    new String[] { "Name", "VRAM", "Hersteller" },"Suche");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
