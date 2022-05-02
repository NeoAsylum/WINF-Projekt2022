package Backend;

import UI.Suche;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import Datentypen.Grafikkarte;
import Datentypen.Produkt;

public class Hauptklasse {

    static Connection conn;
    static String url = "jdbc:mysql://3.69.96.96:3306/";
    static String dbName = "db4";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String userName = "db4";
    static String password = "!db4.hfts22?";
    static Suche frame;

    public static void main(String[] args) {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connected to the database");
            // nonsense Query but works, has no results
            produktQuery(new Grafikkarte(), "SELECT " + "Name, VRAM, Hersteller "
                    + "FROM GRAFIKKARTEN WHERE HERSTELLER='ABCDEFG';", "Suche");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Diese Methode führt SQL-Queries durch
     * 
     * @param p     Produkt welches gefiltert wird
     * @param query String welcher Query enthält
     * @throws SQLException
     */
    public static void produktQuery(Produkt p, String query, String oberflaeche)
            throws SQLException {
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
            System.out.println(counter + "  " + p.getTabelleneintraege().length + 1);
            ergebnis[0] = result.split("##")[0].split("<<");
            for (int i = 0; i < counter; i++) {
                ergebnis[i] = result.split("##")[i].split("<<");
            }
            System.out.println("Disconnected from database");
            // Wenn Frame nicht initialisiert wird er initialisiert.
            if (frame == null) {
                frame = new Suche(ergebnis);
                frame.setVisible(true);
                System.out.println("aa");
            } else {
                if (oberflaeche.equals("Suche")) {
                    frame.setSuchTable(ergebnis);
                } else if (oberflaeche.equals("Einlagerung")) {
                    System.out.println("huhu");
                    frame.setEinlagerungTable(ergebnis);
                }

            }
            conn.close();
        } catch (SQLSyntaxErrorException e) {
            System.out.println("SQLSyntaxErrorException!!!");
            // nonsense Query but works
            produktQuery(new Grafikkarte(), "SELECT " + "Name, VRAM, Hersteller "
                    + "FROM GRAFIKKARTEN WHERE HERSTELLER='ABCDEFG';", "Suche");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
