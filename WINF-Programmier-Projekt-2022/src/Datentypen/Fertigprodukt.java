package Datentypen;
/**
 * Klasse welche ein Fertigprodukt fuer die Datenbank und das Programm definieren soll.
 * @author AllStars
 *
 */
public class Fertigprodukt implements Produkt {
    /**
     * String-Array mit den Attributnamen.
     */
    String[] tabelleneintraege = {"Name" ,"CPU", "Grafikkarte","Hauptspeicher", "Festplatte","ID", "Lagerplatz"};
    /**
     * Name des Produkttyps in der Datenbank.
     */
    String PRODUKTTYP = "FERTIGPRODUKT";
    /**
     * Getter fuer Tabelleneintraege.
     */
	@Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
	 /**
     * Getter fuer den Produkttyp als String.
     */
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
    /**
     * Getter fuer die Mindestmenge.
     */
	@Override
	public int getMindestmenge() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
     * Setter fuer die Mindestmenge.
     */
	@Override
	public void setMindestmenge(int mindestmenge) {
		// TODO Auto-generated method stub
		
	}
}
