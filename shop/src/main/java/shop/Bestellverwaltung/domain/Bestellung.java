package shop.Bestellverwaltung.domain;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
//import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

import shop.Kundenverwaltung.domain.Kunde;

/**
 * @author <a href="mailto:koju1020@HS-Karlsruhe.de">Julian Kohlhaas</a>  
 */

@XmlRootElement
@Entity
@Table(indexes = {
		@Index(columnList = "kunde_fk"),
		@Index(columnList = "erzeugt")
	})
	@NamedQueries({
		@NamedQuery(name  = Bestellung.FIND_BESTELLUNGEN_BY_KUNDE,
	                query = "SELECT b"
				            + " FROM   Bestellung b"
							+ " WHERE  b.kunde = :" + Bestellung.PARAM_KUNDE),
		@NamedQuery(name  = Bestellung.FIND_KUNDE_BY_ID,
	 			    query = "SELECT b.kunde"
	                        + " FROM   Bestellung b"
	  			            + " WHERE  b.id = :" + Bestellung.PARAM_ID)
	})
	@NamedEntityGraphs({
		@NamedEntityGraph(name = Bestellung.GRAPH_LIEFERUNGEN,
						  attributeNodes = @NamedAttributeNode("lieferungen"))
	})
	@Cacheable
public class Bestellung implements Serializable  {
		
	private static final long serialVersionUID = 4279475449855483352L;
	
	
	private static final String PREFIX = "Bestellung.";
	public static final String FIND_BESTELLUNGEN_BY_KUNDE = PREFIX + "findBestellungenByKunde";
	public static final String FIND_KUNDE_BY_ID = PREFIX + "findBestellungKundeById";
	
	public static final String PARAM_KUNDE = "kunde";
	public static final String PARAM_ID = "id";
	
	public static final String GRAPH_LIEFERUNGEN = PREFIX + "lieferungen";
	
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "kunde_fk", nullable = false, insertable = false, updatable = false)
	@XmlTransient
	private Kunde kunde;
	
	private Date bestelldatum;
	private double gesamtpreis;
	
	private boolean versendet;
	
	@Transient
	private URI kundeUri;
	
	@OneToMany(fetch = EAGER, cascade = { PERSIST, REMOVE })
	@JoinColumn(name = "bestellung_fk", nullable = false)
	@OrderColumn(name = "idx", nullable = false)
	@NotEmpty(message = "{bestellung.bestellpositionen.notEmpty}")
	@Valid
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
