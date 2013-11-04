package shop.Kundenverwaltung.domain;

public class Adresse {
	private String land;
	private String postleitzahl;
	private String wohnort;
	private String strasse;
	private String hausnummer;
	
	public Adresse(String land, String postleitzahl, String wohnort,
			String strasse, String hausnummer) {
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

	public String getPostleitzahl() {
		return this.postleitzahl;
	}

	public void setPostleitzahl(String postleitzahl) {
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

	public String getHausnummer() {
		return this.hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	
}
