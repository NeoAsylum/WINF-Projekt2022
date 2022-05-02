package Datentypen;

public class CPU extends Produkt {
    int taktfrequenz;
    private String[] tabelleneintraege = { "Name", "Hersteller", "MHz" };
    final static String PRODUKTTYP = "cpus";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}