package Datentypen;

public class Grafikkarte extends Produkt {
    int vram;
    private String[] tabelleneintraege = {"Name", "VRAM",  "Hersteller" };
    final static String PRODUKTTYP = "GRAFIKKARTEN";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
