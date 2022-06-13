package Datentypen;

/**
 * Klasse welche ein Object fuer die Datenbank und das Programm definiert.
 * @author AllStars
 *
 */
public interface EinObjekt {
    String id="";
    String name="";
    /**
     * Getter fuer die ID.
     * @return
     */
    default String getID() {
        return id;
    }
    /**
     * Getter fuer den Namen.
     * @return
     */
    default String getName() {
        return name;
    }
}
