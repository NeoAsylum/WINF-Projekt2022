package Datentypen;

public class Fertigprodukt extends Produkt {

    int groesse;
    String betriebssystem;
    private String[] tabelleneintraege = { "Name", "Hersteller", "Betriebssystem" };
    final static String PRODUKTTYP = "FESTPLATTE";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }

    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }

}
