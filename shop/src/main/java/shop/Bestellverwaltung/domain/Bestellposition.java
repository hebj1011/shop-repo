package shop.Bestellverwaltung.domain;

import java.net.URI;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import shop.Artikelverwaltung.domain.AbstractArtikel;

@XmlRootElement
public class Bestellposition implements Serializable {
	
	private static final long serialVersionUID = -7656682410415623796L;
	
	private long id;
	private AbstractArtikel artikel;
	private URI artikelUri;
	private short anzahl;
	
	public Bestellposition() {
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public AbstractArtikel getArtikel() {
		return artikel;
	}
	public void setArtikel(AbstractArtikel artikel) {
		this.artikel = artikel;
	}
	public URI getArtikelUri() {
		return artikelUri;
	}
	public void setArtikelUri(URI artikelUri) {
		this.artikelUri = artikelUri;
	}
	public short getAnzahl() {
		return anzahl;
	}
	public void setAnzahl(short anzahl) {
		this.anzahl = anzahl;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anzahl;
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result
				+ ((artikelUri == null) ? 0 : artikelUri.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		final Bestellposition other = (Bestellposition) obj;
		if (anzahl != other.anzahl)
			return false;
		if (artikel == null) {
			if (other.artikel != null)
				return false;
		} 
		else if (!artikel.equals(other.artikel))
			return false;
		if (artikelUri == null) {
			if (other.artikelUri != null)
				return false;
		} 
		else if (!artikelUri.equals(other.artikelUri))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
