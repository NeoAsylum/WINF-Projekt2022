package Datentypen;

public class Hauptspeicher implements Produkt {
    String[] tabelleneintraege = { "Name", "Groesse","ID", "Lagerplatz"};
    String PRODUKTTYP = "HAUPTSPEICHER";
    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
