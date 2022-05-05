package Backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;

import UI.UI;

public class SQL {

    static Connection conn;
    static String url = "jdbc:mysql://3.69.96.96:3306/";
    static String dbName = "db4";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String userName = "db4";
    static String password = "!db4.hfts22?";
    static Properties props = new Properties();

    public static void setup() {
        try {
            // Properties props = new Properties();
            props.loadFromXML(new FileInputStream("file.txt"));

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            Class.forName(driver);

            conn = DriverManager.getConnection(props.getProperty(url) + dbName, userName,
                    password);
            Hauptklasse.frame = new UI(QueryOutputHandling.nonsenseQuery());
            Hauptklasse.frame.setVisible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String query) throws SQLException {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLSyntaxErrorException e) {
            QueryOutputHandling.nonsenseQuery();
            Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
        }
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
            result += oberflaeche.equals("Suche") ? "Delete<<##" : "<<##";
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
            QueryOutputHandling.nonsenseQuery();
            Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
        }
        return null;
    }
}
