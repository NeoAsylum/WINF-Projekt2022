package Backend;

import UI.UI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Hauptklasse {

	static Connection conn;
	static UI frame;

	public static final Logger log = Logger.getLogger(Hauptklasse.class.getName());

	public static void main(String[] args) {
			SQL.setup();
	}

	

	public static Object[][] result(int counter, String result, int width) {
		Object[][] ergebnis = new Object[counter][width];
		ergebnis[0] = result.split("##")[0].split("<<");
		for (int i = 0; i < counter; i++) {
			ergebnis[i] = result.split("##")[i].split("<<");
		}
		return ergebnis;
	}

	public static void queryToUI(String query, String oberflaeche, String[] tabelleneintraege) {
		try {
			if (oberflaeche.equals("Suche")) {
				frame.setSuchTable(SQL.queryToStringArray(query, tabelleneintraege));
			} else if (oberflaeche.equals("Einlagerung")) {
				frame.setEinlagerungTable(SQL.queryToStringArray(query, tabelleneintraege));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	public static Object[][] nonsenseQuery() {
		try {
			return SQL.queryToStringArray(
					"SELECT " + "Name, VRAM, Hersteller " + "FROM GRAFIKKARTEN WHERE HERSTELLER='ABCDEFG';",
					new String[] { "Name", "VRAM", "Hersteller" });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
