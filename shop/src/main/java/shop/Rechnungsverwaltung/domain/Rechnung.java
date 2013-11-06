package shop.Rechnungsverwaltung.domain;

//import java.net.URI;
import java.net.URI;
import java.util.Date;
import java.util.List;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Kundenverwaltung.domain.Adresse;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rechnung {
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private List<AbstractArtikel> artikel;
	//private Adresse adresseRechnung;
	//private Adresse adresseLieferung;
	//private Enum<Zahlungsmittel> Zahlungsmittel;
	//private Date datumRechnung;
	//private Date datumZahlung;
	private double versandkosten;
	private Boolean bezahlt;
	private double gesamtpreis;
	
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

	

	
}
