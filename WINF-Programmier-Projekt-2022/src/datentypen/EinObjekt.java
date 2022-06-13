package datentypen;

/**
 * Klasse welche ein Object fuer die Datenbank und das Programm definiert.
 * 
 * @author AllStars
 *
 */
public interface EinObjekt {
  String id = "";
  String name = "";

  /**
   * Getter fuer die ID.
   * 
   * @return Gibt die ID zurueck.
   */
  default String getId() {
    return id;
  }

  /**
   * Getter fuer den Namen.
   * 
   * @return Gibt den Namen zurueck.
   */
  default String getName() {
    return name;
  }
}
