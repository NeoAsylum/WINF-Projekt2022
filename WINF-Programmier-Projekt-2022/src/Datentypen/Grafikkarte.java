package Datentypen;

public class Grafikkarte implements Produkt {
    String[] tabelleneintraege = {"Name", "VRAM",  "Hersteller", "ID", "Lagerplatz"};
    String PRODUKTTYP = "GRAFIKKARTE";
    
    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
   
}
