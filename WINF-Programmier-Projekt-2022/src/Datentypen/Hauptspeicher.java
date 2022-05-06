package Datentypen;

public class Hauptspeicher extends Produkt {
    int groesse;
    public String[] tabelleneintraege = { "Name", "Groesse","ID", "LAGERPLATZ"};
    final static String PRODUKTTYP = "HAUPTSPEICHER";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }

    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
