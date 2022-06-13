package Datentypen;
/**
 * Klasse welche einen CPU fuer die Datenbank und das Programm definieren soll.
 * @author AllStars
 *
 */
public class Hauptspeicher implements Produkt {
    /**
     * String-Array mit den Attributnamen.
     */
    String[] tabelleneintraege = { "Name", "Groesse","ID", "Lagerplatz"};
    /**
     * Name des Produkttyps in der Datenbank.
     */
    String PRODUKTTYP = "HAUPTSPEICHER";
    /**
     * Mindestmenge auf einem Lagerplatz.
     */
    private int mindestmenge = 10;
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
