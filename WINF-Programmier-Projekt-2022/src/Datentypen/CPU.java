package Datentypen;

/**
 * Klasse welche einen CPU fuer die Datenbank und das Programm definieren soll.
 * @author AllStars
 *
 */
public class CPU implements Produkt {
    /**
     * String-Array mit den Attributnamen.
     */
    String[] tabelleneintraege = { "Name", "Hersteller", "MHz","ID", "Lagerplatz"};
    /**
     * Name des Produkttyps in der Datenbank.
     */
    String PRODUKTTYP = "cpu";
    /**
     * Mindestmenge auf einem Lagerplatz.
     */
    private int mindestmenge = 2;
    
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
