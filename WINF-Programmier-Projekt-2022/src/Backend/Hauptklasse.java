package Backend;

import UI.Login;
import UI.UI;

import java.time.LocalTime;
import java.util.logging.Logger;

public class Hauptklasse {

	public static UI frame;

	public static final Logger log = Logger.getLogger(Hauptklasse.class.getName());
	
	/**
	 * Dies ist die Main-Methode.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	    
	    SQL.setup();
	    //QueryOutputHandling.queryNachNamenStueckzahlen("cpu");
        //QueryOutputHandling.stueckzahlen(new String[]{"a","a","b","c"});
		//Login login = new Login();	
		//login.setVisible(true);
	    //SQL.erstelleAlleLagerplaetze();
		//Login login = new Login();	
		//login.setVisible(true);		
	}
	
	
	
	
	

}
