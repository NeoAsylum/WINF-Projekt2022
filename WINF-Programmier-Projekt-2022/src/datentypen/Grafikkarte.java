package datentypen;

/**
 * Klasse welche eine Grafikkarte fuer die Datenbank und das Programm definieren
 * soll.
 * 
 * @author AllStars
 *
 */
public class Grafikkarte implements Produkt {
  /**
   * String-Array mit den Attributnamen.
   */
  String[] tabelleneintraege = { "Name", "VRAM", "Hersteller", "ID", "Lagerplatz" };
  /**
   * Name des Produkttyps in der Datenbank.
   */
  String produkttyp = "GRAFIKKARTE";
  /**
   * Mindestmenge auf einem Lagerplatz.
   */
  private int mindestmenge = 15;

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
    return produkttyp;
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
