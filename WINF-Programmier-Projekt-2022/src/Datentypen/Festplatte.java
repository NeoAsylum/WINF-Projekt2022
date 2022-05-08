package Datentypen;

public class Festplatte extends Produkt {
    private String[] tabelleneintraege = { "Name", "Hersteller", "Groesse", "Betriebssystem","ID", "LAGERPLATZ" };
    private String PRODUKTTYP = "FESTPLATTE";


    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    } 
    
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
