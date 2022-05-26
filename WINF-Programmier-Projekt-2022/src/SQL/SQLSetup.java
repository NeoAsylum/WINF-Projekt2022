package SQL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.JOptionPane;

import Backend.Hauptklasse;
import UI.UI;

public class SQLSetup {

    static Connection conn;
    static Properties props = new Properties();

    /**
     * Setup Methode welche SQL-Connection aufsetzt, frame/UI initialisiert und
     * Properties lädt/die Sprache anhand der vorher gespeicherten Sprache festlegt.
     */
    public static void setupSQL() {
        try {
            props.loadFromXML(new FileInputStream("resources/properties.txt"));
            /*
             * props.setProperty("Sprache", Locale.getDefault().toLanguageTag());
             * props.storeToXML(new FileOutputStream("resources/properties.txt"),
             * "Written in " + new Datum().datum + ", " + new Datum().zeit);
             */
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

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Eine Methode welche all unsere Lagerplaetze leer nochmal anlegt. Nur zum
     * neuanlegen der Lagerplaetze verwenden.
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
        Hauptklasse.nonsenseQuery();
    }

}
