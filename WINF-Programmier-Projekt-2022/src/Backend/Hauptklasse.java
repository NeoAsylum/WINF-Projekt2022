package Backend;

import UI.UI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Arrays;
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
			setup();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Problem:", e);
		}
	}

	public static void setup() {
		try {
			frame = new UI(nonsenseQuery());
			frame.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Object[][] result(int counter, String result, int width) {
		Object[][] ergebnis = new Object[counter][width];
		ergebnis[0] = result.split("##")[0].split("<<");
		for (int i = 0; i < counter; i++) {
			ergebnis[i] = result.split("##")[i].split("<<");
		}
		System.out.print(Arrays.deepToString(ergebnis));
		return ergebnis;
	}

	public static void queryToUI(String query, String oberflaeche, String[] tabelleneintraege) {
		try {
			if (oberflaeche.equals("Suche")) {
				frame.setSuchTable(queryToStringArray(query, tabelleneintraege));
			} else if (oberflaeche.equals("Einlagerung")) {
				frame.setEinlagerungTable(queryToStringArray(query, tabelleneintraege));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
			return result(counter, result, tabelleneintrage.length + 2);
		} catch (SQLSyntaxErrorException e) {
			nonsenseQuery();
			log.log(Level.SEVERE, "Problem:", e);
		}
		return null;
	}

	public static Object[][] nonsenseQuery() throws SQLException {
		return queryToStringArray(
				"SELECT " + "Name, VRAM, Hersteller " + "FROM GRAFIKKARTEN WHERE HERSTELLER='ABCDEFG';",
				new String[] { "Name", "VRAM", "Hersteller" });
	}

}
