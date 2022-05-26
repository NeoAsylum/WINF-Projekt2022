package Datentypen;

import javax.swing.JOptionPane;

import Backend.AuslagernMethoden;
import SQL.NurSQL;
import SQL.SQLOutputHandling;
import UI.UI;

public interface Produkt extends EinObjekt {
	
	public int getMindestmenge();
	
	public void setMindestmenge(int mindestmenge);
  
    public String[] getTabelleneintraege();

    public String produktTyp();

}
