package Backend;

import java.util.Arrays;

import SQL.NurSQL;

/**
 * Eine Klasse welche Methoden zum Prozess des Auslagerns enthaelt.
 * @author AllStars
 *
 */
public class AuslagernMethoden {

    /**
     * Eine Methode welche die 'anzahlImLagerHochzaehlen()' Methode auf alle Objekte
     * einer Tabelle anwendet.
     * 
     * @param array     Der Array.
     * @param tablename Der Name der Tabelle in der SQL-Datenbank.
     */
    public static void anhandEinesArraysAlleRunterzaehlen(Object[][] array, String tablename) {
        array = Arrays.copyOfRange(array, 1, array.length);
        System.out.println(Arrays.deepToString(array));
        for (int i = 0; i < array.length; i++) {
            AuslagernMethoden.anzahlImLagerRunterzaehlen(Integer.parseInt((String) array[i][1]), tablename,
                    (String) array[i][0]);
        }
    }

    /**
     * Methode welche die Stueckzahl im Lager runterzaehlt.
     * 
     * @param lagerplatzID Die ID des Lagerplatzes.
     * @param tablename    Der Name der Tabelle in der SQL Datenbank.
     * @param name         Der Name des PRoduktes als Identifikator.
     */
    public static void anzahlImLagerRunterzaehlen(int lagerplatzID, String tablename,
            String name) {
        System.out.println("lagerplatzID: " + lagerplatzID + "tablename " + tablename);
        NurSQL.update("UPDATE LAGERPLATZ SET Name='" + name + "', Menge=Menge-1 WHERE ID=" + lagerplatzID
                + ";");
    }

}
