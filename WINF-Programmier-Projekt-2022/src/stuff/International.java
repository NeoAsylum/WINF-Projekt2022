package stuff;

import java.nio.file.FileSystems;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Uebersetzer fuer Teile des UIs.
 * 
 * @author AllStars.
 *
 */
public class International {
  /**
   * Instanz der Locale.
   */
  Locale current = Locale.getDefault();
  /**
   * Das ResourceBundle.
   */
  ResourceBundle rb = ResourceBundle
      .getBundle("sprachen" + FileSystems.getDefault().getSeparator() + "sprache", current);

  /**
   * Methode welche anhand von Codes die Sprache festlegt und das ResourceBundle
   * laedt.
   * 
   * @param localcode
   */
  public void setSprache(String localcode) {
    if (localcode == "Deutsch" || localcode == "de-DE") {
      localcode = "de";
    }
    if (localcode == "English" || localcode == "en-EN") {
      localcode = "en";
    }
    if (localcode == "Español" || localcode == "es-ES") {
      localcode = "es";
    }

    current = new Locale(localcode);
    rb = ResourceBundle.getBundle("sprachen" + FileSystems.getDefault().getSeparator() + "sprache",
        current);
  }

  /**
   * Methode welche Strings uebersetzt.
   * 
   * @param wort Wort welches uebersetzt werden soll.
   * @return Gibt das uebersetzte Wort zurueck.
   */
  public String getUebersetzung(String wort) {
    return rb.getString(wort);
  }
}
