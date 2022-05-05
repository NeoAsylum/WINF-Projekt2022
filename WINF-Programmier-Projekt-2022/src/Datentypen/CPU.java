package Datentypen;

public class CPU extends Produkt {
    int taktfrequenz;
    private String[] tabelleneintraege = { "Name", "Hersteller", "MHz","ID" };
    final static String PRODUKTTYP = "cpu";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
