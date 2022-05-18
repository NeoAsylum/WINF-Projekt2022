package Datentypen;

public class CPU implements Produkt {
    String[] tabelleneintraege = { "Name", "Hersteller", "MHz","ID", "Lagerplatz"};
    String PRODUKTTYP = "cpu";
    
    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
