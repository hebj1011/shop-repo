package shop.Artikelverwaltung.domain;

public class Sicherheitszubehoer extends Artikel {
	
	private Boolean tuev;
	private String groesse;
	private Unterklasse sUnterklasse;
	
	public Sicherheitszubehoer(Long artikelnummer, String name,
			Double einzelpreis, Integer bestand, Boolean tuev, String groesse,
			Unterklasse sUnterklasse) {
		super(artikelnummer, name, einzelpreis, bestand);
		this.tuev = tuev;
		this.groesse = groesse;
		this.sUnterklasse = sUnterklasse;
	}
	
	public Boolean getTuev() {
		return tuev;
	}
	public void setTuev(Boolean tuev) {
		this.tuev = tuev;
	}
	public String getGroesse() {
		return groesse;
	}
	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}
	public Unterklasse getsUnterklasse() {
		return sUnterklasse;
	}
	public void setsUnterklasse(Unterklasse sUnterklasse) {
		this.sUnterklasse = sUnterklasse;
	}
	

}
