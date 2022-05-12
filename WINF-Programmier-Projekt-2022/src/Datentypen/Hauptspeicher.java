package Datentypen;

public class Hauptspeicher extends Produkt {
    private String[] tabelleneintraege = { "Name", "Groesse","ID", "Lagerplatz"};
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
