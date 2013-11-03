/*TODO 
 * Herausfinden warum Syntaxfehler
 * @XmlAccessorType(FIELD)
 */
package shop.Artikelverwaltung.domain;

/*TODO
 * einbinden wenn benötigt
 * import static javax.xml.bind.annotation.XmlAccessType.FIELD;
 * import javax.xml.bind.annotation.XmlAccessorType;
 */

public abstract class Artikel {
	
	private Long artikelnummer;
	private String name;
	private Double einzelpreis;
	private Integer bestand;
	
	public Artikel(Long artikelnummer, String name, Double einzelpreis,
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

}