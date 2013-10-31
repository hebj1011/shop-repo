package shop.Kundenverwaltung.domain;

public class Adresse {
	private String land;
	private int postleitzahl;
	private String wohnort;
	private String strasse;
	private int hausnummer;
	
	public Adresse(String land, int postleitzahl, String wohnort,
			String strasse, int hausnummer) {
		super();
		this.land = land;
		this.postleitzahl = postleitzahl;
		this.wohnort = wohnort;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
	}

	public String getLand() {
		return this.land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public int getPostleitzahl() {
		return this.postleitzahl;
	}

	public void setPostleitzahl(int postleitzahl) {
		this.postleitzahl = postleitzahl;
	}

	public String getWohnort() {
		return this.wohnort;
	}

	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}

	public String getStraﬂe() {
		return this.strasse;
	}

	public void setStraﬂe(String strasse) {
		this.strasse = strasse;
	}

	public int getHausnummer() {
		return this.hausnummer;
	}

	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}
	
}
