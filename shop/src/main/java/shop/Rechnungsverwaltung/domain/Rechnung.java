package shop.Rechnungsverwaltung.domain;

import java.util.Date;
import java.util.List;

import shop.Artikelverwaltung.domain.AbstractArtikel;


public class Rechnung {
	private List<AbstractArtikel> artikel;
	private String adresseRechnung;
	private String adresseLieferung;
	private Enum<Zahlungsmittel> Zahlungsmittel;
	private Date datumRechnung;
	private Date datumZahlung;
	private double versandkosten;
	private Boolean bezahlt; 

	public Boolean getBezahlt() {
		return bezahlt;
	}

	public void setBezahlt(Boolean bezahlt) {
		this.bezahlt = bezahlt;
	}

	public String getAdresseRechnung() {
		return adresseRechnung;
	}

	public void setAdresseRechnung(String adresseRechnung) {
		this.adresseRechnung = adresseRechnung;
	}

	public String getAdresseLieferung() {
		return adresseLieferung;
	}

	public void setAdresseLieferung(String adresseLieferung) {
		this.adresseLieferung = adresseLieferung;
	}

	public Enum<Zahlungsmittel> getZahlungsmittel() {
		return Zahlungsmittel;
	}

	public void setZahlungsmittel(Enum<Zahlungsmittel> zahlungsmittel) {
		Zahlungsmittel = zahlungsmittel;
	}

	public Date getDatumRechnung() {
		return datumRechnung;
	}

	public void setDatumRechnung(Date datumRechnung) {
		this.datumRechnung = datumRechnung;
	}

	public Date getDatumZahlung() {
		return datumZahlung;
	}

	public void setDatumZahlung(Date datumZahlung) {
		this.datumZahlung = datumZahlung;
	}

	public double getVersandkosten() {
		return versandkosten;
	}

	public void setVersandkosten(double versandkosten) {
		this.versandkosten = versandkosten;
	}

	public Rechnung(List<AbstractArtikel> artikel, String adresseRechnung,
			String adresseLieferung, Enum<Zahlungsmittel> zahlungsmittel, Date datumRechnung,
			Date datumZahlung, double versandkosten) {
		super();
		this.artikel = artikel;
		this.adresseRechnung = adresseRechnung;
		this.adresseLieferung = adresseLieferung;
		Zahlungsmittel = zahlungsmittel;
		this.datumRechnung = datumRechnung;
		this.datumZahlung = datumZahlung;
		this.versandkosten = versandkosten;
	}

	public List<AbstractArtikel> getArtikel() {
		return artikel;
	}

	public void setArtikel(List<AbstractArtikel> artikel) {
		this.artikel = artikel;
	}

	public double betrag() {
		double betrag = 0;
		for (int i = 0; i < this.artikel.size(); i++) {
			AbstractArtikel art = this.artikel.get(i);
			betrag += art.getEinzelpreis();
		}
		return betrag;
	}
	
	
}
