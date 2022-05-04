package Backend;

import UI.UI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hauptklasse {

    static Connection conn;
    static String url = "jdbc:mysql://3.69.96.96:3306/";
    static String dbName = "db4";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String userName = "db4";
    static String password = "!db4.hfts22?";
    static UI frame;
    public static final Logger log = Logger.getLogger(Hauptklasse.class.getName());

    public static void main(String[] args) {

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
            // nonsense Query but works, has no results
            produktQuery("SELECT " + "Name, VRAM, Hersteller "
                    + "FROM GRAFIKKARTEN WHERE HERSTELLER='ABCDEFG';", "Suche",new String[]{"Name", "VRAM",  "Hersteller" });
            conn.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Problem:", e);
        }
    }

    /**
     * Diese Methode führt SQL-Queries durch
     * 
     * @param p     Produkt welches gefiltert wird
     * @param query String welcher Query enthält
     * @throws SQLException
     */
    public static void produktQuery(String query, String oberflaeche, String[] tabelleneintrage)
            throws SQLException {
        log.info("Query: " + query + " in " + oberflaeche);
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
            String result = "";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int counter = 2;

            // Tabelle Spalten benennen
            for (int i = 0; i < tabelleneintrage.length; i++) {
                result += tabelleneintrage[i] + "<<";
            }
            result += "Delete<<##";
            result += " " + "<<" + " " + "<<" + " " + "<<" + "##";

            // Alle Spalten holen
            while (rs.next()) {
                for (int i = 0; i < tabelleneintrage.length; i++) {
                    result += rs.getString(tabelleneintrage[i]) + "<<";
                }
                result += "<<##";
                counter++;
            }
            // Alle Daten in Array parsen
            Object[][] ergebnis = new Object[counter][tabelleneintrage.length + 2];
            ergebnis[0] = result.split("##")[0].split("<<");
            for (int i = 0; i < counter; i++) {
                ergebnis[i] = result.split("##")[i].split("<<");
            }
            // Wenn Frame nicht initialisiert wird er initialisiert.
            if (frame == null) {
                frame = new UI(ergebnis);
                frame.setVisible(true);
            } else {
                if (oberflaeche.equals("Suche")) {
                    frame.setSuchTable(ergebnis);
                } else if (oberflaeche.equals("Einlagerung")) {
                    frame.setEinlagerungTable(ergebnis);
                }

            }
            conn.close();
        } catch (SQLSyntaxErrorException e) {
            // nonsense Query but works
            produktQuery("SELECT " + "Name, VRAM, Hersteller "
                    + "FROM GRAFIKKARTEN WHERE HERSTELLER='ABCDEFG';", "Suche",new String[]{"Name", "VRAM",  "Hersteller" });
            log.log(Level.SEVERE, "Problem:", e);
        } catch (ClassNotFoundException e) {
            log.log(Level.SEVERE, "Problem:", e);
        }
    }
}
