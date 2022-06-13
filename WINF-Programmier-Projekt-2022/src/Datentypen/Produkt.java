package Datentypen;

/**
 * Klasse welche ein Produktinterfacve fuer die Datenbank und das Programm definieren soll.
 * @author AllStars
 *
 */
public interface Produkt extends EinObjekt {
    /**
     * Getter fuer die Mindestmenge.
     */
	public int getMindestmenge();
	/**
     * Setter fuer die Mindestmenge.
     */
	public void setMindestmenge(int mindestmenge);
	/**
     * Getter fuer Tabelleneintraege.
     */
    public String[] getTabelleneintraege();
    /**
     * Getter fuer den Produkttyp als String.
     */
    public String produktTyp();

}
