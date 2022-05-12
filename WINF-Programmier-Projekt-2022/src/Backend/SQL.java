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
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.JOptionPane;
import Datentypen.Fertigprodukt;
import Datentypen.Produkt;
import UI.UI;

public class SQL {

    static Connection conn;
    static Properties props = new Properties();

    /**
     * Setup Methode welche SQL-Connection aufsetzt und frame/UI initialisiert.
     */
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
            Hauptklasse.frame = new UI();
            Hauptklasse.frame.addActionListeners();
            Hauptklasse.frame.setVisible(true);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Diese Methode sendet ein Update-Query zum SQL-Server
     * 
     * @param query
     */
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

    public static void anzahlImLagerHochz�hlen(int id, String tablename) {
        update("UPDATE " + tablename + " SET MENGE = " + (stueckzahlImLager(id,tablename)+1) +"WHERE ID="+id);
    }

    public static int stueckzahlImLager(int id, String tablename) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT MENGE FROM " + tablename + " WHERE ID='" + id + "';");
            rs.last();
            return rs.getInt(1);
        } catch (SQLException e) {
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
        return -1;
    }

    public static int getLagerplatzIDFertigprodukt(boolean zweiterdurchlauf) {
        try {
            Statement stmt = conn.createStatement();
            Fertigprodukt p = new Fertigprodukt();
            if (zweiterdurchlauf) {
                ResultSet rs = stmt.executeQuery(
                        "SELECT ID FROM LAGERPLATZ WHERE TYP='" + p.produktTyp() + "';");
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
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
        return 0;
    }

    /**
     * Diese Methode macht aus den Daten in der Datenbank ein Object[][] Array,
     * damit dieses f�r die JTable verarbeitet werden kann.
     * 
     * @param query
     * @param tabelleneintraege
     * @param oberflaeche
     * @return
     */
    public static Object[][] queryToStringArray(String query, String[] tabelleneintraege,
            String oberflaeche) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.last();
            Object[][] arr = new Object[rs.getRow() + 1][tabelleneintraege.length];
            rs.beforeFirst();

            // Spalten benennen
            for (int i = 0; i < tabelleneintraege.length; i++) {
                arr[0][i] = tabelleneintraege[i];
            }

            // Reihen holen
            for (int i = 1; rs.next(); i++) {

                for (int j = 0; j < tabelleneintraege.length; j++) {
                    arr[i][j] = rs.getString(tabelleneintraege[j]);
                }
            }

            return arr;

        } catch (SQLSyntaxErrorException e) {
            Hauptklasse.frame.setSuchTable(QueryOutputHandling.nonsenseQuery());
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
        } catch (SQLException e) {
            Hauptklasse.frame.setSuchTable(QueryOutputHandling.nonsenseQuery());
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
        }
        return null;
    }

}
