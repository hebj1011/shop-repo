package shop.Artikelverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">Bjoern Hetzel</a>
 */

@XmlRootElement
public class Fahrrad extends AbstractArtikel {
	
	private static final long serialVersionUID = -3177911520687689458L;
	
	private Unterklasse unterklasse;
	private Set<Farbe> farbe;
	
	public Unterklasse getUnterklasse() {
		return unterklasse;
	}
	public void setUnterklasse(Unterklasse unterklasse) {
		this.unterklasse = unterklasse;
	}
	public Set<Farbe> getFarbe() {
		return farbe;
	}
	public void setFarbe(Set<Farbe> farbe) {
		this.farbe = farbe;
	}
	
	@Override
	public String toString() {
		return "Fahrrad [" + super.toString() + ", Unterklasse=" + unterklasse.toString() + ", Farbe:" + farbe + "]";
	}
}
