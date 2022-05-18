package Datentypen;

public class Festplatte implements Produkt {
    String[] tabelleneintraege = { "Name", "Hersteller", "Groesse", "Betriebssystem","ID", "Lagerplatz"};
    String PRODUKTTYP = "FESTPLATTE";
    
    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
