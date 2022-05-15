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
            Hauptklasse.frame.addActionListenersToUi();
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
     * @param sqlQueryText
     */
    public static void update(String sqlQueryText) {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlQueryText);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
        Hauptklasse.frame.setSuchTable(QueryOutputHandling.nonsenseQuery());
    }

    /**
     * Methode welche die LagerplatzID fuer ein Fertigprodukt ausgibt.
     * 
     * @param istZweiterDurchlauf Ist wahr wenn die Methode rekursiv das zweite Mal
     *                            aufgerufen wird.
     * @return
     */
    public static int getLagerplatzIDFertigprodukt(boolean istZweiterDurchlauf) {
        try {
            Statement stmt = conn.createStatement();
            Fertigprodukt p = new Fertigprodukt();
            if (istZweiterDurchlauf) {
                ResultSet rs = stmt.executeQuery(
                        "SELECT ID FROM LAGERPLATZ WHERE TYP='" + p.produktTyp() + "';");
                rs.last();
                return rs.getInt(1);
            } else {
                stmt.executeUpdate("INSERT INTO LAGERPLATZ (TYP)" + System.lineSeparator()
                        + "VALUES ('" + p.produktTyp() + "');");
                return getLagerplatzIDFertigprodukt(true);
            }
        } catch (SQLException e) {
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
        return -1;
    }

    /**
     * Methode welche die LagerplatzID fuer ein Produkt ausgibt.
     * 
     * @param produktTyp Der Produkttyp des Produktes.
     * @return
     */
    public static int getLagerplatzID(Produkt produktTyp) {
        try {
            Statement stmt = conn.createStatement();
            if (!(produktTyp.produktTyp().equals("FERTIGPRODUKT"))) {
                ResultSet rs = stmt.executeQuery(
                        "SELECT ID FROM LAGERPLATZ WHERE TYP='" + produktTyp.produktTyp() + "';");
                rs.first();
                return rs.getInt(1);
            } else {
                return getLagerplatzIDFertigprodukt(false);
            }
        } catch (SQLException e) {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO LAGERPLATZ (TYP)" + System.lineSeparator()
                        + "VALUES ('" + produktTyp.produktTyp() + "');");
                return getLagerplatzID(produktTyp);
            } catch (SQLException e1) {
                Hauptklasse.log.log(Level.SEVERE, e.getMessage());
            }
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
        return -1;
    }

    /**
     * Diese Methode macht aus den Daten in der Datenbank ein Object[][] Array,
     * damit dieses für die JTable verarbeitet werden kann.
     * 
     * @param queryText    Die Query welche ausgefuehrt werden soll.
     * @param spaltenNamen Spaltennamen fuer die Tabelle
     * @return
     */
    public static Object[][] queryToStringArray(String queryText, String[] spaltenNamen) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(queryText);
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
