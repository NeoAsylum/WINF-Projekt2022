package Datentypen;

public class CPU implements Produkt {
    String[] tabelleneintraege = { "Name", "Hersteller", "MHz","ID", "Lagerplatz"};
    String PRODUKTTYP = "cpu";
    private int mindestmenge = 2;
    
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
