package Datentypen;

public class CPU extends Produkt {
    private String[] tabelleneintraege = { "Name", "Hersteller", "MHz","ID", "Lagerplatz", "Menge"};
    private String PRODUKTTYP = "cpu";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
