package Datentypen;

public class Fertigprodukt extends Produkt {
    private String[] tabelleneintraege = { "CPU", "GRAFIKKARTE","Hauptspeicher", "FESTPLATTE","ID", "LAGERPLATZ", "Menge" };
    private String PRODUKTTYP = "FERTIGPRODUKT";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }

    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }

}
