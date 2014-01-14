package shop.Kundenverwaltung.domain;

import static shop.util.Constants.KEINE_ID;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.TemporalType.DATE;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jboss.logging.Logger;

import shop.Bestellverwaltung.domain.Bestellung;
import shop.util.persistence.AbstractAuditable;


/**
 * @author <a href="mailto:lade1011@HS-Karlsruhe.de">Denis Langer</a>
 */
@ScriptAssert(lang = "javascript",
              script = "_this.password != null && !_this.password.equals(\"\")"
                       + " && _this.password.equals(_this.passwordWdh)",
              message = "{kunde.password.notEqual}",
              groups = { Default.class, PasswordGroup.class })
@XmlRootElement
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
// Alternativen bei @Inheritance
//   strategy=SINGLE_TABLE (=default), TABLE_PER_CLASS, JOINED
// Alternativen bei @DiscriminatorColumn
//   discriminatorType=STRING (=default), CHAR, INTEGER
@Entity
//Zu email wird unten ein UNIQUE Index definiert
@Table(name = "kunde", indexes = @Index(columnList = "nachname"))
@Inheritance
@DiscriminatorColumn(name = "art", length = 1)
@NamedQueries({
	@NamedQuery(name  = Kunde.FIND_KUNDEN,
                query = "SELECT k"
				        + " FROM   Kunde k"),
	@NamedQuery(name  = Kunde.FIND_KUNDEN_ORDER_BY_ID,
		        query = "SELECT   k"
				        + " FROM  Kunde k"
		                + " ORDER BY k.id"),
	@NamedQuery(name  = Kunde.FIND_IDS_BY_PREFIX,
		        query = "SELECT   k.id"
		                + " FROM  Kunde k"
		                + " WHERE CONCAT('', k.id) LIKE :" + Kunde.PARAM_KUNDE_ID_PREFIX
		                + " ORDER BY k.id"),
	@NamedQuery(name  = Kunde.FIND_KUNDEN_BY_NACHNAME,
	            query = "SELECT k"
				        + " FROM   Kunde k"
	            		+ " WHERE  UPPER(k.nachname) = UPPER(:" + Kunde.PARAM_KUNDE_NACHNAME + ")"),
	@NamedQuery(name  = Kunde.FIND_NACHNAMEN_BY_PREFIX,
   	            query = "SELECT   DISTINCT k.nachname"
				        + " FROM  Kunde k "
	            		+ " WHERE UPPER(k.nachname) LIKE UPPER(:"
	            		+ Kunde.PARAM_KUNDE_NACHNAME_PREFIX + ")"),
   	@NamedQuery(name  = Kunde.FIND_KUNDE_BY_EMAIL,
   	            query = "SELECT DISTINCT k"
   			            + " FROM   Kunde k"
   			            + " WHERE  k.email = :" + Kunde.PARAM_KUNDE_EMAIL),
    @NamedQuery(name  = Kunde.FIND_KUNDEN_BY_PLZ,
	            query = "SELECT k"
				        + " FROM  Kunde k"
			            + " WHERE k.adresse.plz = :" + Kunde.PARAM_KUNDE_ADRESSE_PLZ),
	@NamedQuery(name = Kunde.FIND_KUNDEN_BY_DATE,
			    query = "SELECT k"
			            + " FROM  Kunde k"
			    		+ " WHERE k.seit = :" + Kunde.PARAM_KUNDE_SEIT)
//	@NamedQuery(name = Kunde.FIND_PRIVATKUNDEN_FIRMENKUNDEN,
//			    query = "SELECT k"
//			            + " FROM  Kunde k"
//			    		+ " WHERE TYPE(k) IN (Privatkunde, Firmenkunde)")
})
@NamedEntityGraphs({
	@NamedEntityGraph(name = Kunde.GRAPH_BESTELLUNGEN,
					  attributeNodes = @NamedAttributeNode("bestellungen")),
	@NamedEntityGraph(name = Kunde.GRAPH_WARTUNGSVERTRAEGE,
					  attributeNodes = @NamedAttributeNode("wartungsvertraege"))
})
public class Kunde extends AbstractAuditable {
	private static final long serialVersionUID = 5685115602958386843L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	//Pattern mit UTF-8 (statt Latin-1 bzw. ISO-8859-1) Schreibweise fuer Umlaute:
	private static final String NAME_PATTERN = "[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF]+";
	private static final String PREFIX_ADEL = "(o'|von|von der|von und zu|van)?";
	
	public static final String NACHNAME_PATTERN = PREFIX_ADEL + NAME_PATTERN + "(-" + NAME_PATTERN + ")?";
	private static final int NACHNAME_LENGTH_MIN = 2;
	private static final int NACHNAME_LENGTH_MAX = 32;
	private static final int VORNAME_LENGTH_MAX = 32;
	private static final int EMAIL_LENGTH_MAX = 128;
	private static final String RABATT_MAX = "0.5";
	private static final int PASSWORD_LENGTH_MAX = 256;
	
	
	private static final String PREFIX = "Kunde.";
	public static final String FIND_KUNDEN = PREFIX + "findKunden";
	public static final String FIND_KUNDEN_ORDER_BY_ID = PREFIX + "findKundenOrderById";
	public static final String FIND_IDS_BY_PREFIX = PREFIX + "findIdsByPrefix";
	public static final String FIND_KUNDEN_BY_NACHNAME = PREFIX + "findKundenByNachname";
	public static final String FIND_NACHNAMEN_BY_PREFIX = PREFIX + "findNachnamenByPrefix";
	public static final String FIND_KUNDE_BY_EMAIL = PREFIX + "findKundeByEmail";
	public static final String FIND_KUNDEN_BY_PLZ = PREFIX + "findKundenByPlz";
	public static final String FIND_KUNDEN_BY_DATE = PREFIX + "findKundenByDate";
	
	public static final String PARAM_KUNDE_ID = "kundeId";
	public static final String PARAM_KUNDE_ID_PREFIX = "idPrefix";
	public static final String PARAM_KUNDE_NACHNAME = "nachname";
	public static final String PARAM_KUNDE_NACHNAME_PREFIX = "nachnamePrefix";
	public static final String PARAM_KUNDE_ADRESSE_PLZ = "plz";
	public static final String PARAM_KUNDE_SEIT = "seit";
	public static final String PARAM_KUNDE_EMAIL = "email";
	
	public static final String GRAPH_BESTELLUNGEN = PREFIX + "bestellungen";
	public static final String GRAPH_WARTUNGSVERTRAEGE = PREFIX + "wartungsvertraege";

	@Id
	@GeneratedValue
	@Basic(optional = false)
	private Long id = KEINE_ID;

	@NotNull(message = "{kunde.nachname.notNull}")
	@Size(min = NACHNAME_LENGTH_MIN, max = NACHNAME_LENGTH_MAX, message = "{kunde.nachname.length}")
	@Pattern(regexp = NACHNAME_PATTERN, message = "{kunde.nachname.pattern}")
	private String nachname;

	@Size(max = VORNAME_LENGTH_MAX, message = "{kunde.vorname.length}")
	private String vorname;
	
	@Temporal(DATE)
	@Past(message = "{kunde.seit.past}")
	private Date seit;

	@DecimalMax(value = RABATT_MAX, message = "{kunde.rabatt.max}")
	@Digits(integer = 1, fraction = 4, message = "{kunde.rabatt.digits}")
	private BigDecimal rabatt = BigDecimal.ZERO;
	
	@Digits(integer = 10, fraction = 2, message = "{kunde.umsatz.digits}")
	private BigDecimal umsatz = BigDecimal.ZERO;
	
	@Email(message = "{kunde.email.pattern}")
	@NotNull(message = "{kunde.email.notNull}")
	@Size(max = EMAIL_LENGTH_MAX, message = "{kunde.email.length}")
	@Column(unique = true)
	private String email;
	
	@Basic(optional = false)
	private boolean newsletter = false;
	
	@Size(max = PASSWORD_LENGTH_MAX, message = "{kunde.password.length}")
	private String password;
	
	@Transient
	private String passwordWdh;
	
	@AssertTrue(groups = PasswordGroup.class, message = "{kunde.password.notEqual}")
	public boolean isPasswordEqual() {
		if (password == null) {
			return passwordWdh == null;
		}
		return password.equals(passwordWdh);
	}
	
	@OneToOne(cascade = { PERSIST, REMOVE }, mappedBy = "kunde")
	@Valid
	@NotNull(message = "{kunde.adresse.notNull}")
	private Adresse adresse;

	// Default: fetch=LAZY
	@OneToMany
	@JoinColumn(name = "kunde_fk", nullable = false)
	@OrderColumn(name = "idx", nullable = false)
	@XmlTransient
	private List<Bestellung> bestellungen;
	
	@Transient
	private URI bestellungenUri;

	@OneToMany
	@JoinColumn(name = "kunde_fk", nullable = false)
	@OrderColumn(name = "idx", nullable = false)
	@XmlTransient
	private List<Wartungsvertrag> wartungsvertraege;

	@PostPersist
	protected void postPersist() {
		LOGGER.debugf("Neuer Kunde mit ID=%d", id);
	}
	
	@PostLoad
	protected void postLoad() {
		passwordWdh = password;
	}
	
	public void setValues(Kunde k) {
		nachname = k.nachname;
		vorname = k.vorname;
		umsatz = k.umsatz;
		rabatt = k.rabatt;
		seit = k.seit;
		email = k.email;
		password = k.password;
		passwordWdh = k.password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public Date getSeit() {
		return seit == null ? null : (Date) seit.clone();
	}
	public void setSeit(Date seit) {
		this.seit = seit == null ? null : (Date) seit.clone();
	}

	// Parameter, z.B. DateFormat.MEDIUM, Locale.GERMANY
	// MEDIUM fuer Format dd.MM.yyyy
	public String getSeitAsString(int style, Locale locale) {
		Date temp = seit;
		if (temp == null) {
			temp = new Date();
		}
		final DateFormat f = DateFormat.getDateInstance(style, locale);
		return f.format(temp);
	}
	
	// Parameter, z.B. DateFormat.MEDIUM, Locale.GERMANY
	// MEDIUM fuer Format dd.MM.yyyy
	public void setSeit(String seitStr, int style, Locale locale) {
		final DateFormat f = DateFormat.getDateInstance(style, locale);
		try {
			this.seit = f.parse(seitStr);
		}
		catch (ParseException e) {
			throw new RuntimeException("Kein gueltiges Datumsformat fuer: " + seitStr, e);
		}
	}

	public BigDecimal getUmsatz() {
		return umsatz;
	}

	public void setUmsatz(BigDecimal umsatz) {
		this.umsatz = umsatz;
	}
	
	public BigDecimal getRabatt() {
		return rabatt;
	}
	public void setRabatt(BigDecimal rabatt) {
		this.rabatt = rabatt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordWdh() {
		return passwordWdh;
	}

	public void setPasswordWdh(String passwordWdh) {
		this.passwordWdh = passwordWdh;
	}

	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	public List<Bestellung> getBestellungen() {
		if (bestellungen == null) {
			return null;
		}		
		return Collections.unmodifiableList(bestellungen);
	}
	
	public void setBestellungen(List<Bestellung> bestellungen) {
		if (this.bestellungen == null) {
			this.bestellungen = bestellungen;
			return;
		}
		
		// Wiederverwendung der vorhandenen Collection
		this.bestellungen.clear();
		if (bestellungen != null) {
			this.bestellungen.addAll(bestellungen);
		}
	}
	
	public Kunde addBestellung(Bestellung bestellung) {
		if (bestellungen == null) {
			bestellungen = new ArrayList<>();
		}
		bestellungen.add(bestellung);
		return this;
	}

	public URI getBestellungenUri() {
		return bestellungenUri;
	}

	public void setBestellungenUri(URI bestellungenUri) {
		this.bestellungenUri = bestellungenUri;
	}

	public List<Wartungsvertrag> getWartungsvertraege() {
		if (wartungsvertraege == null) {
			return null;
		}
		
		return Collections.unmodifiableList(wartungsvertraege);
	}

	public void setWartungsvertraege(List<Wartungsvertrag> wartungsvertraege) {
		if (this.wartungsvertraege == null) {
			this.wartungsvertraege = wartungsvertraege;
			return;
		}
		
		// Wiederverwendung der vorhandenen Collection
		this.wartungsvertraege.clear();
		if (wartungsvertraege != null) {
			this.wartungsvertraege.addAll(wartungsvertraege);
		}
	}
	
	public Kunde addWartungsvertrag(Wartungsvertrag wartungsvertrag) {
		if (wartungsvertraege == null) {
			wartungsvertraege = new ArrayList<>();
		}
		wartungsvertraege.add(wartungsvertrag);
		return this;
	}


	@Override
	public String toString() {
		return "Kunde [id=" + id
			   + ", nachname=" + nachname + ", vorname=" + vorname
			   + ", seit=" + getSeitAsString(DateFormat.MEDIUM, Locale.GERMANY)
			   + ", umsatz=" + umsatz
			   + ", email=" + email
			   + ", password=" + password + ", passwordWdh=" + passwordWdh
			   + ", bestellungenUri=" + bestellungenUri
			   + ", " + super.toString() + "]";
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	/**
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Kunde other = (Kunde) obj;
		
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		}
		else if (!email.equals(other.email)) {
			return false;
		}
		
		return true;
	}
}






//package shop.Kundenverwaltung.domain;
//
//import java.io.Serializable;
//import java.net.URI;
//import java.util.Date;
//import java.util.List;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Past;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;
//
//import org.hibernate.validator.constraints.Email;
//
//import shop.Bestellverwaltung.domain.Bestellung;
//
///**
// * @author <a href="mailto:lade1011@HS-Karlsruhe.de">Denis Langer</a>
// */
//@XmlRootElement
//public class Kunde implements Serializable  {
//	
//	private static final long serialVersionUID = 1887403048204895170L;
//	
//	private static final String NAME_PATTERN = "[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF]+";
//	private static final String NACHNAME_PREFIX = "(o'|von|von der|von und zu|van)?";
//	
//	public static final String VORNAME_PATTERN = NAME_PATTERN + "(-" + NAME_PATTERN + ")?";
//	public static final String NACHNAME_PATTERN = NACHNAME_PREFIX + NAME_PATTERN + "(-" + NAME_PATTERN + ")?";
//	private static final int VORNAME_LENGTH_MIN = 2;
//	private static final int VORNAME_LENGTH_MAX = 32;
//	private static final int NACHNAME_LENGTH_MIN = 2;
//	private static final int NACHNAME_LENGTH_MAX = 32;
//	private static final int EMAIL_LENGTH_MAX = 128;
//		
//	private Long id;
//	
//	@NotNull(message = "{kunde.vorname.notNull}")
//	@Size(min = VORNAME_LENGTH_MIN, max = VORNAME_LENGTH_MAX,
//	      message = "{kunde.vorname.length}")
//	@Pattern(regexp = VORNAME_PATTERN, message = "{kunde.vorname.pattern}")
//	private String vorname;
//	
//	@NotNull(message = "{kunde.nachname.notNull}")
//	@Size(min = NACHNAME_LENGTH_MIN, max = NACHNAME_LENGTH_MAX,
//	      message = "{kunde.nachname.length}")
//	@Pattern(regexp = NACHNAME_PATTERN, message = "{kunde.nachname.pattern}")
//	private String nachname;
//	
//	@Email(message = "{kunde.email.pattern}")
//	@NotNull(message = "{kunde.email.notNull}")
//	@Size(max = EMAIL_LENGTH_MAX, message = "{kunde.email.length}")
//	private String email;
//	
//	@Valid
//	@NotNull(message = "{kunde.adresse.notNull}")
//	private Adresse adresse;
//	
//	@Past(message = "{kunde.seit.past}")
//	private Date seit;
//	
//	@XmlTransient
//	private List<Bestellung> bestellungen;
//	
//	private URI bestellungenUri;
//
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getVorname() {
//		return vorname;
//	}
//	public void setVorname(String vorname) {
//		this.vorname = vorname;
//	}
//	public String getNachname() {
//		return nachname;
//	}
//	public void setNachname(String nachname) {
//		this.nachname = nachname;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public Adresse getAdresse() {
//		return adresse;
//	}
//	public void setAdresse(Adresse adresse) {
//		this.adresse = adresse;
//	}
//	public List<Bestellung> getBestellungen() {
//		return bestellungen;
//	}
//	public void setBestellungen(List<Bestellung> bestellungen) {
//		this.bestellungen = bestellungen;
//	}
//
//	public URI getBestellungenUri() {
//		return bestellungenUri;
//	}
//	public void setBestellungenUri(URI bestellungenUri) {
//		this.bestellungenUri = bestellungenUri;
//	}
//	public Date getSeit() {
//		return seit;
//	}
//	public void setSeit(Date seit) {
//		this.seit = seit;
//	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		return result;
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		final Kunde other = (Kunde) obj;
//		if (email == null) {
//			if (other.email != null)
//				return false;
//		}
//		else if (!email.equals(other.email))
//			return false;
//		return true;
//	}
//	
//	@Override
//	public String toString() {
//		return "Kunde [id=" + id + ", Vorname=" + vorname + ", nachname=" + nachname + ", email=" + email
//			   + ", seit=" + seit + ", bestellungenUri=" + bestellungenUri + "]";
//	}
//}
