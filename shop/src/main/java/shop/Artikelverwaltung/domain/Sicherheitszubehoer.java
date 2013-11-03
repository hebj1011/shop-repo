package shop.Artikelverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sicherheitszubehoer extends AbstractArtikel {
	
	private static final long serialVersionUID = 254184158546556162L;
	
	private Boolean tuev;
	private String groesse;
	private Unterklasse sUnterklasse;
	
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
	
	@Override
	public String toString() {
		return "Sicherheitszubehoer [" + super.toString() + ", Tuev:" + tuev + ", Groesse=" + groesse.toString() + ", Unterklasse=" + sUnterklasse.toString() + "]";
	}

}
