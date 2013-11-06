package shop.Rechnungsverwaltung.domain;

import java.net.URI;
import java.util.Date;
import java.util.List;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Kundenverwaltung.domain.Adresse;
import shop.Kundenverwaltung.domain.Kunde;


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
	private Kunde kunde;
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
			Kunde kunde,
			Enum<shop.Rechnungsverwaltung.domain.Zahlungsmittel> zahlungsmittel,
			Date datumRechnung, Date datumZahlung, double versandkosten,
			Boolean bezahlt) {
		super();
		this.id = id;
		this.artikel = artikel;
		this.adresseRechnung = adresseRechnung;
		this.adresseLieferung = adresseLieferung;
		this.kunde = kunde;
		Zahlungsmittel = zahlungsmittel;
		this.datumRechnung = datumRechnung;
		this.datumZahlung = datumZahlung;
		this.versandkosten = versandkosten;
		this.bezahlt = bezahlt;
	}

	public Boolean getBezahlt() {
		return bezahlt;
	}

	public void setBezahlt(Boolean bezahlt) {
		this.bezahlt = bezahlt;
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
	public void setKundeUri(URI kundeUri) {
		// TODO @ALL:Wieso KundeUri-setzen?!
		
	}
	
	
	@Override
	public String toString() {
		return "Rechnung [id=" + id + ", artikel=" + artikel
				+ ", adresseRechnung=" + adresseRechnung
				+ ", adresseLieferung=" + adresseLieferung + ", kunde=" + kunde
				+ ", Zahlungsmittel=" + Zahlungsmittel + ", datumRechnung="
				+ datumRechnung + ", datumZahlung=" + datumZahlung
				+ ", versandkosten=" + versandkosten + ", bezahlt=" + bezahlt
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((Zahlungsmittel == null) ? 0 : Zahlungsmittel.hashCode());
		result = prime
				* result
				+ ((adresseLieferung == null) ? 0 : adresseLieferung.hashCode());
		result = prime * result
				+ ((adresseRechnung == null) ? 0 : adresseRechnung.hashCode());
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result + ((bezahlt == null) ? 0 : bezahlt.hashCode());
		result = prime * result
				+ ((datumRechnung == null) ? 0 : datumRechnung.hashCode());
		result = prime * result
				+ ((datumZahlung == null) ? 0 : datumZahlung.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		long temp;
		temp = Double.doubleToLongBits(versandkosten);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rechnung other = (Rechnung) obj;
		if (Zahlungsmittel == null) {
			if (other.Zahlungsmittel != null)
				return false;
		} else if (!Zahlungsmittel.equals(other.Zahlungsmittel))
			return false;
		if (adresseLieferung == null) {
			if (other.adresseLieferung != null)
				return false;
		} else if (!adresseLieferung.equals(other.adresseLieferung))
			return false;
		if (adresseRechnung == null) {
			if (other.adresseRechnung != null)
				return false;
		} else if (!adresseRechnung.equals(other.adresseRechnung))
			return false;
		if (artikel == null) {
			if (other.artikel != null)
				return false;
		} else if (!artikel.equals(other.artikel))
			return false;
		if (bezahlt == null) {
			if (other.bezahlt != null)
				return false;
		} else if (!bezahlt.equals(other.bezahlt))
			return false;
		if (datumRechnung == null) {
			if (other.datumRechnung != null)
				return false;
		} else if (!datumRechnung.equals(other.datumRechnung))
			return false;
		if (datumZahlung == null) {
			if (other.datumZahlung != null)
				return false;
		} else if (!datumZahlung.equals(other.datumZahlung))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		} else if (!kunde.equals(other.kunde))
			return false;
		if (Double.doubleToLongBits(versandkosten) != Double
				.doubleToLongBits(other.versandkosten))
			return false;
		return true;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	

	
}
