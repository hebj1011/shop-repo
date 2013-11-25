package shop.Artikelverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sicherheitszubehoer extends AbstractArtikel {

	private static final long serialVersionUID = -4684331848134556129L;
	private Boolean tuev;
	private String groesse;

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
	
	@Override
	public String toString() {
		return "Sicherheitszubehoer [" + super.toString() + ", Tuev:" + tuev + ", Groesse=" + groesse.toString() + "]";
	}

}
