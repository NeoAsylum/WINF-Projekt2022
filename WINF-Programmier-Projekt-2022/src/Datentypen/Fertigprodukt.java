package Datentypen;

public class Fertigprodukt implements Produkt {
    String[] tabelleneintraege = {"Name" ,"CPU", "Grafikkarte","Hauptspeicher", "Festplatte","ID", "Lagerplatz"};
    String PRODUKTTYP = "FERTIGPRODUKT";
    
    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
