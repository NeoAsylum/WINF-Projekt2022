package Datentypen;

public class Hauptspeicher implements Produkt {
    String[] tabelleneintraege = { "Name", "Groesse","ID", "Lagerplatz"};
    String PRODUKTTYP = "HAUPTSPEICHER";
    private int mindestmenge = 10;
    
    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }
    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
    
	@Override
	public int getMindestmenge() {
		return mindestmenge;
	}
	@Override
	public void setMindestmenge(int mindestmenge) {
		this.mindestmenge = mindestmenge;
	}

}
