package Datentypen;

public class Festplatte implements Produkt {
    String[] tabelleneintraege = { "Name", "Hersteller", "Groesse", "Betriebssystem","ID", "Lagerplatz"};
    String PRODUKTTYP = "FESTPLATTE";
    private int mindestmenge = 5;
    
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
