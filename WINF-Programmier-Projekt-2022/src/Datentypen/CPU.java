package Datentypen;

public class Prozessor extends Produkt {
    int taktfrequenz;
    private String[] tabelleneintraege = { "Name", "Hersteller", "Taktfrequenz" };
    final static String PRODUKTTYP = "PROZESSOR";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
