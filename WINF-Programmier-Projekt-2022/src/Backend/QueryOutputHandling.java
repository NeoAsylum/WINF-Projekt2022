package Backend;

import java.sql.SQLException;

public class QueryOutputHandling {

	public static Object[][] queryOutputToStringArray(int counter, String result, int width) {
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
				Hauptklasse.frame.setSuchTable(SQL.queryToStringArray(query, tabelleneintraege));
			} else if (oberflaeche.equals("Einlagerung")) {
				Hauptklasse.frame.setEinlagerungTable(SQL.queryToStringArray(query, tabelleneintraege));
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
			e.printStackTrace();
		}
		return null;
	}
}
