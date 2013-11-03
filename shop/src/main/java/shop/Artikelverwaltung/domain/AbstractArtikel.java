package shop.Artikelverwaltung.domain;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">Bjoern Hetzel</a>
 */

@XmlRootElement
@XmlSeeAlso({ Ersatzteil.class, Fahrrad.class, Sicherheitszubehoer.class })
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
	@Type(value = Ersatzteil.class, name = AbstractArtikel.ERSATZTEIL),
	@Type(value = Fahrrad.class, name = AbstractArtikel.FAHRRAD),
	@Type(value = Sicherheitszubehoer.class, name = AbstractArtikel.SICHERHEITSZUBEHOER) })

public abstract class AbstractArtikel implements Serializable {
	
	private static final long serialVersionUID = 7401524595142572933L;
	
	public static final String ERSATZTEIL = "E";
	public static final String FAHRRAD = "F";
	public static final String SICHERHEITSZUBEHOER = "S";
	
	private Long artikelnummer;
	private String name;
	private Double einzelpreis;
	private Integer bestand;
	
	/* TODO
	 * hier Bestellungen draus machen wenn angelegt
	 */
	@XmlTransient
	private List<String> bestellungen;
	
	private URI bestellungenUri;
	
	public AbstractArtikel(Long artikelnummer, String name, Double einzelpreis,
			Integer bestand) {
		super();
		this.artikelnummer = artikelnummer;
		this.name = name;
		this.einzelpreis = einzelpreis;
		this.bestand = bestand;
	}
	
	public Long getArtikelnummer() {
		return artikelnummer;
	}
	public void setArtikelnummer(Long artikelnummer) {
		this.artikelnummer = artikelnummer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getEinzelpreis() {
		return einzelpreis;
	}
	public void setEinzelpreis(Double einzelpreis) {
		this.einzelpreis = einzelpreis;
	}
	public Integer getBestand() {
		return bestand;
	}
	public void setBestand(Integer bestand) {
		this.bestand = bestand;
	}
	/* TODO
	 * hier auch List<Bestellungen> wenn angelegt
	 */
	public List<String> getBestellungen() {
		return bestellungen;
	}
	public void setBestellungen(List<String> bestellungen) {
		this.bestellungen = bestellungen;
	}
	public URI getBestellungenUri() {
		return bestellungenUri;
	}
	public void setBestellungenUri(URI bestellungenUri) {
		this.bestellungenUri = bestellungenUri;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		AbstractArtikel other = (AbstractArtikel) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "AbstractArtikel [Artikelnummer=" + artikelnummer + ", name=" + name + ", bestellungenUri=" + bestellungenUri + "]";
	}

}