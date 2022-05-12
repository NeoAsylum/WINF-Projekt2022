package Backend;

import UI.Login;
import UI.UI;

import java.util.logging.Logger;

public class Hauptklasse {

	static UI frame;

	public static final Logger log = Logger.getLogger(Hauptklasse.class.getName());
	
	/**
	 * Dies ist die Main-Methode.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		
		Login login = new Login();	
		login.setVisible(true);
		
		
		
	}
	
	
	

}
