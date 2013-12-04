package shop.Rechnungsverwaltung.domain;

import java.util.Date;
import java.util.List;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Kundenverwaltung.domain.Adresse;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rechnung {
	@Min(0)
	@Max(9)
	private Long id;

	@Valid
	private List<AbstractArtikel> artikel;
	
	@Valid
	private Adresse adresseRechnung;

	@Valid
	private Adresse adresseLieferung;

	@NotNull
	@Valid
	private Enum<Zahlungsmittel> Zahlungsmittel;

	@Past
	@NotNull
	private Date datumRechnung;

	@NotNull
	private Date datumZahlung;

	@DecimalMin("0.00")
	@NotNull
	private double versandkosten;

	private Boolean bezahlt;

	@DecimalMin("0.00")
	private double gesamtpreis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<AbstractArtikel> getArtikel() {
		return artikel;
	}

	public void setArtikel(List<AbstractArtikel> artikel) {
		this.artikel = artikel;
	}

	public double getVersandkosten() {
		return versandkosten;
	}

	public void setVersandkosten(double versandkosten) {
		this.versandkosten = versandkosten;
	}

	public Boolean getBezahlt() {
		return bezahlt;
	}

	public void setBezahlt(Boolean bezahlt) {
		this.bezahlt = bezahlt;
	}

	public double getGesamtpreis() {
		return gesamtpreis;
	}

	public void setGesamtpreis(double gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
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

}