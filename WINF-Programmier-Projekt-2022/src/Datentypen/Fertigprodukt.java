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
    
	@Override
	public int getMindestmenge() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setMindestmenge(int mindestmenge) {
		// TODO Auto-generated method stub
		
	}
}
