package Backend;

import java.nio.file.FileSystems;
import java.util.Locale;
import java.util.ResourceBundle;

public class International {
    Locale current = Locale.getDefault();
    ResourceBundle rb = ResourceBundle.getBundle("sprachen"+FileSystems.getDefault().getSeparator()+"sprache",current);
    
    public void setSprache(String localcode) {
        if(localcode=="Deutsch") {
            localcode="de";
        }
        if(localcode=="English") {
            localcode="en";
        }
        current = new Locale(localcode);
        rb = ResourceBundle.getBundle("sprachen"+FileSystems.getDefault().getSeparator()+"sprache",current);
    }
    
    public String getUebersetzung(String wort) {
        return rb.getString(wort);
    }
}
