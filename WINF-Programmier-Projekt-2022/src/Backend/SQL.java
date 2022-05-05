package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.logging.Level;

import UI.UI;

public class SQL {

	static Connection conn;
	static String url = "jdbc:mysql://3.69.96.96:3306/";
	static String dbName = "db4";
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String userName = "db4";
	static String password = "!db4.hfts22?";

	public static void setup() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url + dbName, userName, password);
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
			int rs = stmt.executeUpdate(query);

			
		} catch (SQLSyntaxErrorException e) {
			QueryOutputHandling.nonsenseQuery();
			Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
		}
	}
	
	
	public static Object[][] queryToStringArray(String query, String[] tabelleneintrage) throws SQLException {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int counter = 2;
			String result = "";

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
			return QueryOutputHandling.queryOutputToStringArray(counter, result, tabelleneintrage.length + 2);
		} catch (SQLSyntaxErrorException e) {
			QueryOutputHandling.nonsenseQuery();
			Hauptklasse.log.log(Level.SEVERE, "Problem:", e);
		}
		return null;
	}
}
