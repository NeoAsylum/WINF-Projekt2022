package Datentypen;

public class Hauptspeicher extends Produkt {
    private String[] tabelleneintraege = { "Name", "Groesse","ID", "LAGERPLATZ"};
    private String PRODUKTTYP = "HAUPTSPEICHER";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }

    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
