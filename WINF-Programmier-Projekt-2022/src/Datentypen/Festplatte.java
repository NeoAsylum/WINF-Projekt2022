package Datentypen;

public class Festplatte extends Produkt {
    int groesse;
    String betriebssystem;
    private String[] tabelleneintraege = { "Name", "Hersteller", "Groesse", "Betriebssystem" };
    final static String PRODUKTTYP = "FESTPLATTE";


    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    } 
    
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
