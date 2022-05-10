package Datentypen;

public class Fertigprodukt extends Produkt {
    private String[] tabelleneintraege = { "CPU", "Grafikkarte","Hauptspeicher", "Festplatte","ID", "Lagerplatz", "Menge" };
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
