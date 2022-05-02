package Backend;

import java.awt.EventQueue;
import UI.UI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static void main(String[] args) {
        uI(establishConnection());
    }

    public static Object[][] establishConnection() {
        Object[][] ergebnis = {};
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connected to the database");
            ergebnis = Query(new CPU());
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ergebnis;

    }

    public static Object[][] Query(Produkt p) throws SQLException {
        String result = "";
        Statement stmt = conn.createStatement();
        ResultSet rs;
        // Tabelle Spalten benennen
        for (int i = 0; i < p.getTabelleneintraege().length; i++) {
            result += p.getTabelleneintraege()[i] + "<<";
        }
        result += "##";
        // Query Spalten benennen
        String query = "";
        for (int i = 0; i < p.getTabelleneintraege().length; i++) {
            query += p.getTabelleneintraege()[i] + ", ";
        }
        query = query.substring(0, query.length() - 2);
        rs = stmt.executeQuery("SELECT " + query + " FROM " + p.produktTyp());
        int counter = 1;
        // Alle Spalten holen
        while (rs.next()) {
            for (int i = 0; i < p.getTabelleneintraege().length; i++) {
                result += rs.getString(p.getTabelleneintraege()[i]) + "<<";
            }
            result += "##";
            counter++;
        }
        // Alle Daten in Array parsen
        Object[][] ergebnis = new Object[counter][p.getTabelleneintraege().length+1];
        ergebnis[0] = result.split("##")[0].split("<<");
        for (int i = 1; i < counter; i++) {
            ergebnis[i] = result.split("##")[i].split("<<");
            ergebnis[i][p.getTabelleneintraege().length-1]= "x";
        }
        System.out.println("Disconnected from database");

        return ergebnis;
    }

    public static void uI(Object[][] input) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UI frame = new UI(input);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
