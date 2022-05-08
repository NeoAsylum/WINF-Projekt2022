package Datentypen;

public class Grafikkarte extends Produkt {
    private String[] tabelleneintraege = {"Name", "VRAM",  "Hersteller", "ID", "LAGERPLATZ" };
    private String PRODUKTTYP = "GRAFIKKARTE";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
