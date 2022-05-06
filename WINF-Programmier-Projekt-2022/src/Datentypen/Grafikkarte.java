package Datentypen;

public class Grafikkarte extends Produkt {
    int vram;
    private String[] tabelleneintraege = {"Name", "VRAM",  "Hersteller", "ID", "LAGERPLATZ" };
    final static String PRODUKTTYP = "GRAFIKKARTE";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
