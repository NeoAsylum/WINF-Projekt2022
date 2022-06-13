package Datentypen;
/**
 * Klasse welche eine Festplatte fuer die Datenbank und das Programm definieren soll.
 * @author AllStars
 *
 */
public class Festplatte implements Produkt {
    /**
     * String-Array mit den Attributnamen.
     */
    String[] tabelleneintraege = { "Name", "Hersteller", "Groesse", "Betriebssystem","ID", "Lagerplatz"};
    /**
     * Name des Produkttyps in der Datenbank.
     */
    String PRODUKTTYP = "FESTPLATTE";
    /**
     * Mindestmenge auf einem Lagerplatz.
     */
    private int mindestmenge = 5;
    
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
		return mindestmenge;
	}
	/**
     * Setter fuer die Mindestmenge.
     */
	@Override
	public void setMindestmenge(int mindestmenge) {
		this.mindestmenge = mindestmenge;
	}

}
