package Backend;

import UI.UI;
import java.util.logging.Logger;

public class Hauptklasse {

	public static UI frame;
    public static International uebersetzer = new International();

	public static final Logger log = Logger.getLogger(Hauptklasse.class.getName());
	
	/**
	 * Dies ist die Main-Methode.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	    SQL.setup();
	}
	
	
	
	
	

}
