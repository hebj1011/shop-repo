package shop.Artikelverwaltung.domain;

import static shop.util.Constants.KEINE_ID;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
//import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.Inheritance;
//import javax.persistence.JoinColumn;
//import javax.persistence.NamedAttributeNode;
//import javax.persistence.NamedEntityGraph;
//import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQuery;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.OrderColumn;
//import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
//import javax.persistence.Transient;
//import javax.validation.Valid;
//import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Past;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
//import javax.validation.groups.Default;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;
//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.ScriptAssert;
import org.jboss.logging.Logger;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">Bjoern Hetzel</a>
 */

@Entity
@Table(name = "artikel", indexes = @Index(columnList = "name"))
@Inheritance
@DiscriminatorColumn(name = "art", length = 1)
@NamedQueries({
	@NamedQuery(name  = AbstractArtikel.FIND_VERFUEGBARE_ARTIKEL,
            	query = "SELECT      a"
            	        + " FROM     AbstractArtikel a"
						+ " WHERE    a.ausgesondert = FALSE"
                        + " ORDER BY a.id ASC"),
	@NamedQuery(name  = AbstractArtikel.FIND_ARTIKEL_BY_NAME,
            	query = "SELECT      a"
                        + " FROM     AbstractArtikel a"
						+ " WHERE    a.name LIKE :" + AbstractArtikel.PARAM_NAME
						+ "          AND a.ausgesondert = FALSE"
			 	        + " ORDER BY a.id ASC"),
   	@NamedQuery(name  = AbstractArtikel.FIND_ARTIKEL_MAX_PREIS,
            	query = "SELECT      a"
                        + " FROM     AbstractArtikel a"
						+ " WHERE    a.einzelpreis < :" + AbstractArtikel.PARAM_PREIS
			 	        + " ORDER BY a.id ASC")
})


@XmlRootElement
@XmlSeeAlso({ Ersatzteil.class, Fahrrad.class, Sicherheitszubehoer.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
	@Type(value = Ersatzteil.class, name = AbstractArtikel.ERSATZTEIL),
	@Type(value = Fahrrad.class, name = AbstractArtikel.FAHRRAD),
	@Type(value = Sicherheitszubehoer.class, name = AbstractArtikel.SICHERHEITSZUBEHOER) 
	})

public abstract class AbstractArtikel implements Serializable {
	
	private static final long serialVersionUID = -4773398465078397947L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	public static final String ERSATZTEIL = "E";
	public static final String FAHRRAD = "F";
	public static final String SICHERHEITSZUBEHOER = "S";
	
	private static final int NAME_LENGTH_MAX = 32;
	
	private static final String PREFIX = "AbstractArtikel.";
	public static final String FIND_VERFUEGBARE_ARTIKEL = PREFIX + "findVerfuegbareArtikel";
	public static final String FIND_ARTIKEL_BY_NAME = PREFIX + "findArtikelByName";
	public static final String FIND_ARTIKEL_MAX_PREIS = PREFIX + "findArtikelByMaxPreis";

	public static final String PARAM_NAME = "name";
	public static final String PARAM_PREIS = "einzelpreispreis";
		
	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;
	
	@NotNull(message = "{artikel.name.notNull}")
	@Size(max = NAME_LENGTH_MAX, message = "{artikel.name.length}")
	private String name = "";
	
	@Digits(integer = 10, fraction = 2, message = "{artikel.einzelpreispreis.digits}")
	private BigDecimal einzelpreis;
	
	@Min(0)
	@Max(100000)
	private Integer bestand;
	
	@Basic(optional = false)
	private boolean ausgesondert;
	
	@Basic(optional = false)
	@Temporal(TIMESTAMP)
	@XmlTransient
	private Date erzeugt;

	@Basic(optional = false)
	@Temporal(TIMESTAMP)
	@XmlTransient
	private Date aktualisiert;
	
	@PrePersist
	protected void prePersist() {
		erzeugt = new Date();
		aktualisiert = new Date();
	}
	
	@PostPersist
	protected void postPersist() {
		LOGGER.debugf("Neuer Artikel mit ID=%d", id);
	}
	
	@PreUpdate
	protected void preUpdate() {
		aktualisiert = new Date();
	}
	
	public void setValues(AbstractArtikel a) {
		name = a.name;
		einzelpreis = a.einzelpreis;
		bestand = a.bestand;
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getEinzelpreis() {
		return einzelpreis;
	}
	public void setEinzelpreis(BigDecimal einzelpreis) {
		this.einzelpreis = einzelpreis;
	}
	public Integer getBestand() {
		return bestand;
	}
	public void setBestand(Integer bestand) {
		this.bestand = bestand;
	}
	
	public boolean isAusgesondert() {
		return ausgesondert;
	}

	public void setAusgesondert(boolean ausgesondert) {
		this.ausgesondert = ausgesondert;
	}
	public Date getErzeugt() {
		return erzeugt == null ? null : (Date) erzeugt.clone();
	}

	public void setErzeugt(Date erzeugt) {
		this.erzeugt = erzeugt == null ? null : (Date) erzeugt.clone();
	}

	public Date getAktualisiert() {
		return aktualisiert == null ? null : (Date) aktualisiert.clone();
	}

	public void setAktualisiert(Date aktualisiert) {
		this.aktualisiert = aktualisiert == null ? null : (Date) aktualisiert.clone();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((einzelpreis == null) ? 0 : einzelpreis.hashCode());
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
		final AbstractArtikel other = (AbstractArtikel) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		}
		else if (!name.equals(other.name)) {
			return false;
		}
		if (einzelpreis == null) {
			if (other.einzelpreis != null) {
				return false;
			}
		}
		else if (!einzelpreis.equals(other.einzelpreis)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Artikel [ID=" + id + ", name=" + name
		       + ", einzelpreis=" + einzelpreis + ", ausgesondert=" + ausgesondert
		       + ", erzeugt=" + erzeugt
			   + ", aktualisiert=" + aktualisiert + "]";
	}

}
