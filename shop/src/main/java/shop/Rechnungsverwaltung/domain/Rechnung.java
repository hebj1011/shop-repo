package shop.Rechnungsverwaltung.domain;

import java.net.URI;
import java.util.Date;
import java.util.List;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Kundenverwaltung.domain.Adresse;


public class Rechnung {
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private List<AbstractArtikel> artikel;
	private Adresse adresseRechnung;
	private Adresse adresseLieferung;
	private Enum<Zahlungsmittel> Zahlungsmittel;
	private Date datumRechnung;
	private Date datumZahlung;
	private double versandkosten;
	private Boolean bezahlt; 

	public Rechnung(
			Long id,
			List<AbstractArtikel> artikel,
			Adresse adresseRechnung,
			Adresse adresseLieferung,
			Enum<shop.Rechnungsverwaltung.domain.Zahlungsmittel> zahlungsmittel,
			Date datumRechnung, Date datumZahlung, double versandkosten,
			Boolean bezahlt) {
		super();
		this.id = id;
		this.artikel = artikel;
		this.adresseRechnung = adresseRechnung;
		this.adresseLieferung = adresseLieferung;
		Zahlungsmittel = zahlungsmittel;
		this.datumRechnung = datumRechnung;
		this.datumZahlung = datumZahlung;
		this.versandkosten = versandkosten;
		this.bezahlt = bezahlt;
	}
	

	public Rechnung(Long id) {
		/*
		 * TODO Muss später entfernt werden; Nur zu Testzwecken
		 */
		super();
		this.id = id;
		this.adresseRechnung = adresseRechnung;
	}

	public Boolean getBezahlt() {
		return bezahlt;
	}

	public Adresse getAdresseRechnung() {
		return adresseRechnung;
	}

	public void setAdresseRechnung(Adresse adresseRechnung) {
		this.adresseRechnung = adresseRechnung;
	}

	public Adresse getAdresseLieferung() {
		return adresseLieferung;
	}

	public void setAdresseLieferung(Adresse adresseLieferung) {
		this.adresseLieferung = adresseLieferung;
	}

	public void setBezahlt(Boolean bezahlt) {
		this.bezahlt = bezahlt;
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

	public Rechnung getRechnung() {
		return this;
	}

	/**
	 * @param kundeUri
	 */
	public void setRechnungUri(URI kundeUri) {
		// TODO @ALL:Wieso RechnungUri-setzen?! Wird die nicht gegeben?!
		
	}

	
}
