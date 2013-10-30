package shop.Kundenverwaltung.domain;

public class Adresse {
	private String Land;
	private int Postleitzahl;
	private String Wohnort;
	private String Straﬂe;
	private int Hausnummer;
	
	public Adresse(String land, int postleitzahl, String wohnort,
			String straﬂe, int hausnummer) {
		super();
		Land = land;
		Postleitzahl = postleitzahl;
		Wohnort = wohnort;
		Straﬂe = straﬂe;
		Hausnummer = hausnummer;
	}

	public String getLand() {
		return Land;
	}

	public void setLand(String land) {
		Land = land;
	}

	public int getPostleitzahl() {
		return Postleitzahl;
	}

	public void setPostleitzahl(int postleitzahl) {
		Postleitzahl = postleitzahl;
	}

	public String getWohnort() {
		return Wohnort;
	}

	public void setWohnort(String wohnort) {
		Wohnort = wohnort;
	}

	public String getStraﬂe() {
		return Straﬂe;
	}

	public void setStraﬂe(String straﬂe) {
		Straﬂe = straﬂe;
	}

	public int getHausnummer() {
		return Hausnummer;
	}

	public void setHausnummer(int hausnummer) {
		Hausnummer = hausnummer;
	}
	
}
