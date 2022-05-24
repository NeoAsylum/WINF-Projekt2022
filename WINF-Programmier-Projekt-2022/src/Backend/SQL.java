package Backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import Datentypen.Produkt;
import UI.UI;

public class SQL {

    static Connection conn;
    static Properties props = new Properties();

    /**
     * Setup Methode welche SQL-Connection aufsetzt, frame/UI initialisiert und
     * Properties lädt/die Sprache anhand der vorher gespeicherten Sprache festlegt.
     */
    public static void setup() {
        try {
            props.loadFromXML(new FileInputStream("resources/properties.txt"));
            /*
             * props.setProperty("Sprache", Locale.getDefault().toLanguageTag());
             * props.storeToXML(new FileOutputStream("resources/properties.txt"),
             * "Written in " + new Datum().datum + ", " + new Datum().zeit);
             */
            Hauptklasse.getUebersetzer().setSprache(props.getProperty("Sprache"));
            System.out.println(Locale.getDefault().toLanguageTag());
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
            e1.printStackTrace();
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
            e1.printStackTrace();
        }
        try {
            Class.forName(props.getProperty("driver"));
            conn = DriverManager.getConnection(
                    props.getProperty("url") + props.getProperty("dbName"),
                    props.getProperty("userName"), props.getProperty("password"));
            Hauptklasse.setUI(new UI());
            Hauptklasse.getUI().addActionListenersToUi();
            Hauptklasse.getUI().setVisible(true);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Diese Methode sendet ein Update-Query zum SQL-Server.
     * 
     * @param sqlQueryText Der Text der Query welche zum Server geschickt werden
     *                     soll.
     */
    public static void update(String sqlQueryText) {
        Statement stmt;
        try {
            stmt = conn.createStatement();
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
            stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            Hauptklasse.log.log(Level.SEVERE, e.getMessage(), e);
        }

        return makeAQuery("SELECT " + "Name, VRAM, Hersteller "
                + "FROM GRAFIKKARTE WHERE HERSTELLER='ABCDEFG';");
    }

    /**
     * Methode welche die LagerplatzID fuer ein Produkt ausgibt.
     * 
     * @param produktTyp Der Produkttyp des Produktes.
     * @param name       Der Name des Produktes welches eingelagert werden soll.
     * @return
     */
    public static int einlagern(Produkt produktTyp, String name) {
        try {
            ResultSet rs = makeAQuery("SELECT ID FROM LAGERPLATZ WHERE TYP='"
                    + produktTyp.produktTyp() + "' AND Name='" + name + "';");
            rs.first();
            anzahlImLagerHochzaehlen(rs.getInt(1), produktTyp.produktTyp(), name);
            return rs.getInt(1);
        } catch (SQLException e) {
            Hauptklasse.log.log(Level.SEVERE, "es muss ein neuer Lagerplatz zugewiesen werden", e);
            try {
                ResultSet rs = makeAQuery("SELECT ID FROM LAGERPLATZ WHERE Menge=0;");
                rs.first();
                update("UPDATE LAGERPLATZ SET TYP='" + produktTyp.produktTyp() + "',name='"
                        + name.toString() + "' WHERE ID=" + rs.getInt(1) + ";");
                return einlagern(produktTyp, name);
            } catch (SQLException e1) {
                System.out.println("There is no more free space!!!");
                Hauptklasse.log.log(Level.SEVERE, e.getMessage(), e1);
            }
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
        return -1;
    }

    /**
     * Methode welche die Stueckzahl im Lager hochzaehlt.
     * 
     * @param lagerplatzID Die ID des Lagerplatzes.
     * @param tablename    Der Name der Tabelle in der SQL Datenbank.
     * @param name         Der Name des PRoduktes als Identifikator.
     */
    public static void anzahlImLagerHochzaehlen(int lagerplatzID, String tablename, String name) {
        update("UPDATE LAGERPLATZ SET Name='" + name + "', Menge=Menge+1 WHERE ID=" + lagerplatzID
                + ";");
    }

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
            anzahlImLagerrunterzaehlen(Integer.parseInt((String) array[i][1]), tablename,
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
    public static void anzahlImLagerrunterzaehlen(int lagerplatzID, String tablename,
            String name) {
        System.out.println("lagerplatzID: " + lagerplatzID + "tablename " + tablename);
        update("UPDATE LAGERPLATZ SET Name='" + name + "', Menge=Menge-1 WHERE ID=" + lagerplatzID
                + ";");
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
            Hauptklasse.getUI().setSuchTable(QueryOutputHandling.nonsenseQuery());
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
        } catch (SQLException e) {
            Hauptklasse.getUI().setSuchTable(QueryOutputHandling.nonsenseQuery());
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
        }
        
        return null;
    }

    /**
     * Eine Methode welche all unsere Lagerplaetze leer nochmal anlegt.
     */
    public static void erstelleAlleLagerplaetze() {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            for (int raum = 0; raum < 5; raum++) {
                for (int regal = 0; regal < 5; regal++) {
                    for (int fach = 0; fach < 20; fach++) {
                        System.out.println("raum+ " + raum + "regal" + regal + "fach" + fach);
                        stmt.executeUpdate("INSERT INTO LAGERPLATZ (RAUM,REGAL,FACH) VALUES("
                                + raum + "," + regal + "," + fach + ");");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
        Hauptklasse.getUI().setSuchTable(QueryOutputHandling.nonsenseQuery());
    }

}
