package shop.Artikelverwaltung.domain;

public class Sicherheitszubehoer extends Artikel {
	
	public Sicherheitszubehoer(Long artikelnummer, String name,
			String beschreibung, String mengeneinheit, Double einzelpreis,
			Integer bestand, Boolean lieferbar, Boolean tuev,
			String zertifikat, String groesse) {
		super(artikelnummer, name, beschreibung, mengeneinheit, einzelpreis,
				bestand, lieferbar);
		this.tuev = tuev;
		this.zertifikat = zertifikat;
		this.groesse = groesse;
	}
	
	private Boolean tuev;
	private String zertifikat;
	private String groesse;
	public Boolean getTuev() {
		return tuev;
	}
	public void setTuev(Boolean tuev) {
		this.tuev = tuev;
	}
	public String getZertifikat() {
		return zertifikat;
	}
	public void setZertifikat(String zertifikat) {
		this.zertifikat = zertifikat;
	}
	public String getGroesse() {
		return groesse;
	}
	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}

}
