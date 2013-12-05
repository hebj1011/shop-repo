package shop.Rechnungsverwaltung.domain;

import java.util.Date;
import java.util.List;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Kundenverwaltung.domain.Adresse;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jboss.logging.Logger;

import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 * @author Simon Holzmayer
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@XmlRootElement

@Entity
@Table(name = "rechnung") //, indexes = @Index(columnList = "nachname")
@Inheritance
//@DiscriminatorColumn(name = "art", length = 1)
@NamedQueries({
	@NamedQuery(name = Rechnung.FIND_RECHNUNGEN,
			query="SELECT k"
					+ " FROM Rechnung r"),
	@NamedQuery(name = Rechnung.FIND_RECHNUNGEN_ORDER_BY_ID,
			query="SELECT k "
					+ " FROM Rechnung r"
					+ " ORDER BY r.id")
})
@NamedEntityGraphs({ //TODO Was ist das?!
	@NamedEntityGraph (name = Rechnung.GRAPH_RECHNUNGEN,
					   attributeNodes = @NamedAttributeNode("bestellungen"))
})
public class Rechnung {
	
	private static final String PREFIX = "Rechnung.";
	public static final String FIND_RECHNUNGEN = PREFIX + "findRechnungen";
	public static final String FIND_RECHNUNGEN_ORDER_BY_ID = PREFIX + "findRechnungenOrderById";
	
	
	public static final String GRAPH_RECHNUNGEN = PREFIX + "bestellungen";
	
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