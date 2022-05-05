package Datentypen;

public class Fertigprodukt extends Produkt {

    int groesse;
    String betriebssystem;
    private String[] tabelleneintraege = { "CPU", "GRAFIKKARTE", "ID", "FESTPLATTE" };
    final static String PRODUKTTYP = "FERTIGPRODUKT";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }

    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }

}
