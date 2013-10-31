package shop.Kundenverwaltung.domain;

import java.util.List;

public class Kunde {
	private String nachname;
	private String vorname;
	private Adresse adresse;
	private List<Bestellung> bestellung;
	
	public Kunde(String nachname, String vorname, Adresse adresse,
			List<Bestellung> bestellung) {
		super();
		this.nachname = nachname;
		this.vorname = vorname;
		this.adresse = adresse;
		this.bestellung = bestellung;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Bestellung> getBestellung() {
		return bestellung;
	}

	public void setBestellung(List<Bestellung> bestellung) {
		this.bestellung = bestellung;
	}
	
	
}
