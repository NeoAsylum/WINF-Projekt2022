package Backend;

import java.sql.SQLException;

public class QueryOutputHandling {


    public static void einlagern() {
        
    }
    
   /**
    * Ruft die Methoden auf welche das jeweilige UI setzen mittels 
    * eines Arrays mit Tabelleneinträgen.
    * 
    * @param query
    * @param oberflaeche
    * @param tabelleneintraege
    */
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

   /**
    * Eine nutzlose Query welche einfach die Suchtabelle
    * leeren oder mit nichts fuellen soll.
    * 
    * @return
    */
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
