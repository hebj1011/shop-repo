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
	private String beschreibung;
	private String mengeneinheit;
	private Double einzelpreis;
	private Integer bestand;
	private Boolean lieferbar;
	
	public Artikel(Long artikelnummer, String name, String beschreibung,
			String mengeneinheit, Double einzelpreis, Integer bestand,
			Boolean lieferbar) {
		super();
		this.artikelnummer = artikelnummer;
		this.name = name;
		this.beschreibung = beschreibung;
		this.mengeneinheit = mengeneinheit;
		this.einzelpreis = einzelpreis;
		this.bestand = bestand;
		this.lieferbar = lieferbar;
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
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public String getMengeneinheit() {
		return mengeneinheit;
	}
	public void setMengeneinheit(String mengeneinheit) {
		this.mengeneinheit = mengeneinheit;
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
	public Boolean getLieferbar() {
		return lieferbar;
	}
	public void setLieferbar(Boolean lieferbar) {
		this.lieferbar = lieferbar;
	}

}