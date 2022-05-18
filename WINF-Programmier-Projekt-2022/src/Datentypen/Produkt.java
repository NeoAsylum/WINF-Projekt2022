package Datentypen;

public interface Produkt extends EinObjekt {
	
	public int getMindestmenge();
	
	public void setMindestmenge(int mindestmenge);
  
    public String[] getTabelleneintraege();

    public String produktTyp();
}
