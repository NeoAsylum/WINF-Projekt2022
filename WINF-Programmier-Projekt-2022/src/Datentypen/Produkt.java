package Datentypen;

public abstract class Produkt extends EinObjekt {
    int mindestmenge;
    String Hersteller;
    int lagerplatz;
    public String[] tabelleneintraege;

    public abstract String[] getTabelleneintraege();

    public abstract String produktTyp();
}
