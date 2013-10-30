package shop.Kundenverwaltung.domain;

public class Kunde {
	private String Nachname;
	private String Vorname;
	private Adresse Adresse;
	private Bestellung Bestellung;
	
	public Kunde(String nachname, String vorname, Adresse adresse,
			Bestellung bestellung) {
		super();
		Nachname = nachname;
		Vorname = vorname;
		this.Adresse = adresse;
		this.Bestellung = bestellung;
	}

	public String getNachname() {
		return Nachname;
	}

	public void setNachname(String nachname) {
		Nachname = nachname;
	}

	public String getVorname() {
		return Vorname;
	}

	public void setVorname(String vorname) {
		Vorname = vorname;
	}

	public Adresse getAdresse() {
		return Adresse;
	}

	public void setAdresse(Adresse adresse) {
		Adresse = adresse;
	}

	public Bestellung getBestellung() {
		return Bestellung;
	}

	public void setBestellung(Bestellung bestellung) {
		Bestellung = bestellung;
	}
	
	
}
