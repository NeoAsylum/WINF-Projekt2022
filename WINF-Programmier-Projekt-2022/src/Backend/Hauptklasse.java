package Backend;

import UI.UI;
import java.util.logging.Logger;

public class Hauptklasse {

	static UI frame;

	public static final Logger log = Logger.getLogger(Hauptklasse.class.getName());

	public static void main(String[] args) {
		SQL.setup();
	}

}
