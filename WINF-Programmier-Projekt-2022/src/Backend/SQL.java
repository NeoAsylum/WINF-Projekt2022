package Backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JOptionPane;
import Datentypen.Fertigprodukt;
import Datentypen.Produkt;
import UI.UI;

public class SQL {

    static Connection conn;
    static Properties props = new Properties();

    public static void setup() {
        try {
            props.loadFromXML(new FileInputStream("file.txt"));
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
            Hauptklasse.frame = new UI(QueryOutputHandling.nonsenseQuery());
            Hauptklasse.frame.setVisible(true);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }

    public static void update(String query) {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
        Hauptklasse.frame.setSuchTable(QueryOutputHandling.nonsenseQuery());
    }

    public static int getLagerplatzIDFertigprodukt(boolean zweiterdurchlauf) {
        try {
            Statement stmt = conn.createStatement();
            Fertigprodukt p = new Fertigprodukt();
            if (zweiterdurchlauf) {
                ResultSet rs = stmt.executeQuery(
                        "SELECT ID FROM LAGERPLATZ WHERE TYP='" + p.produktTyp() + "';");
                System.out.println(Arrays.deepToString(queryToStringArray("SELECT ID FROM LAGERPLATZ WHERE TYP='" + p.produktTyp() + "';", new String[]{"ID"},
                        "Suche")));
                rs.last();
                System.out.println(rs.getInt(1));
                return rs.getInt(1);
                
            } else {
                stmt.executeUpdate("INSERT INTO LAGERPLATZ (TYP)" + System.lineSeparator()
                        + "VALUES ('" + p.produktTyp() + "');");
                return getLagerplatzIDFertigprodukt(true);
            }
        } catch (SQLException e) {
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
        return 0;
    }

    public static int getLagerplatzID(Produkt p) {
        try {
            Statement stmt = conn.createStatement();
            if (!(p.produktTyp().equals("FERTIGPRODUKT"))) {
                ResultSet rs = stmt.executeQuery(
                        "SELECT ID FROM LAGERPLATZ WHERE TYP='" + p.produktTyp() + "';");
                rs.first();
                return rs.getInt(1);
            } else {
                return getLagerplatzIDFertigprodukt(false);
            }
        } catch (SQLException e) {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO LAGERPLATZ (TYP)" + System.lineSeparator()
                        + "VALUES ('" + p.produktTyp() + "');");
                return getLagerplatzID(p);
            } catch (SQLException e1) {
                Hauptklasse.log.log(Level.SEVERE, e.getMessage());
            }
            // TODO Automatisch generierter Erfassungsblock
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
        return 0;
    }

    public static Object[][] queryToStringArray(String query, String[] tabelleneintrage,
            String oberflaeche) throws SQLException {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int counter = 2;
            String result = "";

            // Tabelle Spalten benennen
            for (int i = 0; i < tabelleneintrage.length; i++) {
                result += tabelleneintrage[i] + "<<";
            }
            result += "<<##";
            result += " " + "<<" + " " + "<<" + " " + "<<" + "##";

            // Alle Spalten holen
            while (rs.next()) {
                for (int i = 0; i < tabelleneintrage.length; i++) {
                    result += rs.getString(tabelleneintrage[i]) + "<<";
                }
                result += "<<##";
                counter++;
            }
            return QueryOutputHandling.queryOutputToStringArray(counter, result,
                    tabelleneintrage.length + 2);
        } catch (SQLSyntaxErrorException e) {
            Hauptklasse.frame.setSuchTable(QueryOutputHandling.nonsenseQuery());
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
        }
        return null;
    }
}
