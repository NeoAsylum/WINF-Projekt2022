package Datentypen;

public abstract class Produkt extends EinObjekt {
    int mindestmenge;
    String Hersteller;
    int Lagerplatz;
    public String[] tabelleneintraege;

    public abstract String[] getTabelleneintraege();

    public abstract String produktTyp();
}
