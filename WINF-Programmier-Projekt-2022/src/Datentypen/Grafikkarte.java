package Datentypen;

public class Grafikkarte implements Produkt {
    String[] tabelleneintraege = {"Name", "VRAM",  "Hersteller", "ID", "Lagerplatz"};
    String PRODUKTTYP = "GRAFIKKARTE";
    private int mindestmenge = 15;
    
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
