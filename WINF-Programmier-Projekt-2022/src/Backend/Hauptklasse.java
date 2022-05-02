package Backend;

import java.awt.EventQueue;
import UI.UI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Arrays;

import Datentypen.Grafikkarte;
import Datentypen.Produkt;
import Datentypen.CPU;

public class Hauptklasse {

    static Connection conn = null;
    static String url = "jdbc:mysql://3.69.96.96:3306/";
    static String dbName = "db4";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String userName = "db4";
    static String password = "!db4.hfts22?";
    static Tabelleneintraege tab = new Tabelleneintraege();
    static UI frame;

    public static void main(String[] args) {
        establishConnection();
    }

    public static void establishConnection() {
        Object[][] ergebnis = {};
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connected to the database");
            query(new CPU());
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Diese Methode führt SQL-Queries durch
     * @param p
     * @param query
     * @throws SQLException
     */
    public static void aQuery(Produkt p, String query) throws SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connected to the database");
            String result = "";
            Statement stmt = conn.createStatement();
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            int counter = 2;

            // Tabelle Spalten benennen
            for (int i = 0; i < p.getTabelleneintraege().length; i++) {
                result += p.getTabelleneintraege()[i] + "<<";
            }
            result += "x<<##";
            result += " " + "<<" + " " + "<<" + " " + "<<" + "##";

            // Alle Spalten holen
            while (rs.next()) {
                for (int i = 0; i < p.getTabelleneintraege().length; i++) {
                    result += rs.getString(p.getTabelleneintraege()[i]) + "<<";
                }
                result += "x<<##";
                counter++;
                System.out.println(result);
            }
            // Alle Daten in Array parsen
            Object[][] ergebnis = new Object[counter][p.getTabelleneintraege().length + 2];
            System.out.println(counter+"  "+p.getTabelleneintraege().length + 1);
            ergebnis[0] = result.split("##")[0].split("<<");
            for (int i = 0; i < counter; i++) {
                ergebnis[i] = result.split("##")[i].split("<<");
            }
            System.out.println("Disconnected from database");
            if (frame == null) {
                frame = new UI(ergebnis);
                frame.setVisible(true);
                System.out.println("aa");
            } else {
                frame.setGrakaTable(ergebnis);
            }
            conn.close();
        } catch (SQLSyntaxErrorException e) {
            System.out.println("SQLSyntaxErrorException!!!");
            query(p);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void query(Produkt p) throws SQLException {
        aQuery(new Grafikkarte(), "SELECT " + "Name, VRAM, Hersteller "
                + "FROM GRAFIKKARTEN WHERE HERSTELLER='ABCDEFG';");
        /*
         * String result = ""; Statement stmt = conn.createStatement(); ResultSet rs; //
         * Tabelle Spalten benennen for (int i = 0; i < p.getTabelleneintraege().length;
         * i++) { result += p.getTabelleneintraege()[i] + "<<"; } result += "##"; //
         * Query Spalten benennen String query = ""; for (int i = 0; i <
         * p.getTabelleneintraege().length; i++) { query += p.getTabelleneintraege()[i]
         * + ", "; } query = query.substring(0, query.length() - 2); rs =
         * stmt.executeQuery("SELECT " + query + " FROM " + p.produktTyp());
         * 
         * int counter = 1; // Alle Spalten holen while (rs.next()) { for (int i = 0; i
         * < p.getTabelleneintraege().length; i++) { result +=
         * rs.getString(p.getTabelleneintraege()[i]) + "<<"; } result += "##";
         * counter++; } // Alle Daten in Array parsen Object[][] ergebnis = new
         * Object[counter][p.getTabelleneintraege().length + 1]; ergebnis[0] =
         * result.split("##")[0].split("<<"); for (int i = 1; i < counter; i++) {
         * ergebnis[i] = result.split("##")[i].split("<<");
         * ergebnis[i][p.getTabelleneintraege().length - 1] = "x"; }
         * System.out.println("Disconnected from database"); return ergebnis;
         */
    }

    public static void uI() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new UI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
