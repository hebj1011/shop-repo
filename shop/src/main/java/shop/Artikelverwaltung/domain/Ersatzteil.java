package shop.Artikelverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">Bjoern Hetzel</a>
 */

@XmlRootElement
public class Ersatzteil extends AbstractArtikel {
	
	private static final long serialVersionUID = 6258156986876418100L;
	
	private Unterklasse unterklasse;
	private String datenblatt;
	
	public Unterklasse getUnterklasse() {
		return unterklasse;
	}
	public void setUnterklasse(Unterklasse unterklasse) {
		this.unterklasse = unterklasse;
	}
	public String getDatenblatt() {
		return datenblatt;
	}
	public void setDatenblatt(String datenblatt) {
		this.datenblatt = datenblatt;
	}
	
	@Override
	public String toString() {
		return "Ersatzteil [" + super.toString() + ", Unterklasse=" + unterklasse.toString() 
				+  ", Datenblatt:" + datenblatt + "]";
	}

}
