package shop.Bestellverwaltung.domain;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import shop.Kundenverwaltung.domain.Kunde;

/**
 * @author Julian Kohlhaas
 * 
 */

@XmlRootElement
public class Bestellung {
	
	private Long id;
	private Kunde kunde;
	private Date bestelldatum;
	private double gesamtpreis;
	private boolean versendet;
	private URI kundeUri;
	private List<Bestellposition> bestellpositionen;
	
	public Bestellung() {
		super();
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public shop.Kundenverwaltung.domain.Kunde getKunde() {
		return kunde;
	}

	public void setKunde(shop.Kundenverwaltung.domain.Kunde kunde) {
		this.kunde = kunde;
	}

	public Date getBestelldatum() {
		return bestelldatum;
	}

	public void setBestelldatum(Date bestelldatum) {
		this.bestelldatum = bestelldatum;
	}

	public double getGesamtpreis() {
		return gesamtpreis;
	}

	public void setGesamtpreis(double gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}

	public boolean isVersendet() {
		return versendet;
	}

	public void setVersendet(boolean versendet) {
		this.versendet = versendet;
	}
	
	public URI getKundeUri() {
		return kundeUri;
	}

	public void setKundeUri(URI kundeUri) {
		this.kundeUri = kundeUri;
	}
	
	public List<Bestellposition> getBestellpositionen() {
		if (bestellpositionen == null) {
			return null;
		}
		
		return Collections.unmodifiableList(bestellpositionen);
	}
	
	public void setBestellpositionen(List<Bestellposition> bestellpositionen) {
		if (this.bestellpositionen == null) {
			this.bestellpositionen = bestellpositionen;
			return;
		}
		
		// Wiederverwendung der vorhandenen Collection
		this.bestellpositionen.clear();
		if (bestellpositionen != null) {
			this.bestellpositionen.addAll(bestellpositionen);
		}
	}
	
	public Bestellung addBestellposition(Bestellposition bestellposition) {
		if (bestellpositionen == null) {
			bestellpositionen = new ArrayList<>();
		}
		bestellpositionen.add(bestellposition);
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bestelldatum == null) ? 0 : bestelldatum.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gesamtpreis);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		result = prime * result + (versendet ? 1231 : 1237);
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
		final Bestellung other = (Bestellung) obj;
		if (bestelldatum == null) {
			if (other.bestelldatum != null)
				return false;
		} 
			else if (!bestelldatum.equals(other.bestelldatum))
			return false;
		if (Double.doubleToLongBits(gesamtpreis) != Double
				.doubleToLongBits(other.gesamtpreis))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} 
			else if (!id.equals(other.id))
			return false;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		} 
			else if (!kunde.equals(other.kunde))
			return false;
		if (versendet != other.versendet)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bestellung [id=" + id + ", kunde=" + kunde + "kundeUri=" + kundeUri
				+ ", bestelldatum=" + bestelldatum + "]";
	}

}
