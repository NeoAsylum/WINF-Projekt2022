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
            Hauptklasse.log.info(sqlQueryText+" update Query");
            stmt.executeUpdate(sqlQueryText);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
        Hauptklasse.frame.setSuchTable(QueryOutputHandling.nonsenseQuery());
    }

    /**
     * Methode welche die LagerplatzID fuer ein Produkt ausgibt.
     * 
     * @param produktTyp Der Produkttyp des Produktes.
     * @return
     */
    public static int einlagern(Produkt produktTyp, String name) {
        try {
            Statement stmt = conn.createStatement();
            
                ResultSet rs = stmt.executeQuery("SELECT ID FROM LAGERPLATZ WHERE TYP='"
                        + produktTyp.produktTyp() + "' AND NAME='" + name + "';");
                rs.first();
                anzahlImLagerHochzaehlen(rs.getInt(1),produktTyp.produktTyp(),name);  
                return rs.getInt(1);
           
        } catch (SQLException e) {
            Hauptklasse.log.log(Level.SEVERE, "fuck", e);
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT ID FROM LAGERPLATZ WHERE Menge=0;");
                rs.first();
                stmt.executeUpdate("UPDATE LAGERPLATZ SET TYP='" + produktTyp.produktTyp()
                        + "', name='"+name + "' WHERE ID=" + rs.getInt(1) + ";");
                return einlagern(produktTyp, name);
            } catch (SQLException e1) {
                System.out.println("There is no more free space!!!");
                Hauptklasse.log.log(Level.SEVERE, e.getMessage(), e1);
            }
            Hauptklasse.log.log(Level.SEVERE, e.getMessage());
        }
        return -1;
    }

    public static void anzahlImLagerHochzaehlen(int lagerplatzID, String tablename, String name) {
        update("UPDATE LAGERPLATZ SET Name='" + name + "', Menge=Menge+1 WHERE ID=" + lagerplatzID
                + ";");
    }
    
    public static void anzahlImLagerrunterzaehlen(int lagerplatzID, String tablename, String name) {
        update("UPDATE LAGERPLATZ SET Name='" + name + "', Menge=Menge-1 WHERE ID=" + lagerplatzID
                + ";");
    }


    /**
     * Methode welche fuer ein Produkt die Stueckzahl im Lager ausgibt.
     * 
     * @param id        ID des gesuchten Produktes.
     * @param tablename Name der betroffenen Tabelle/Produkttyp.
     * @return
     */
    public static int stueckzahlImLager(int id, String tablename) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt
                    .executeQuery("SELECT LAGERPLATZ FROM " + tablename + " WHERE ID=" + id + ";");
            rs.last();
            rs = stmt.executeQuery("SELECT MENGE FROM LAGERPLATZ WHERE ID=" + rs.getInt(1) + ";");
            return rs.getInt(1);
        } catch (SQLException e) {
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
        Hauptklasse.frame.setSuchTable(QueryOutputHandling.nonsenseQuery());
    }

}
