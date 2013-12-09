package shop.Artikelverwaltung.domain;

//import static shop.Artikelverwaltung.domain.AbstractArtikel.FAHRRAD;
//
//import javax.persistence.Cacheable;
//import javax.persistence.Column;
//import javax.persistence.DiscriminatorValue;
//import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Set;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">Bjoern Hetzel</a>
 */

//@Entity
//@Cacheable
//@DiscriminatorValue(FAHRRAD)
@XmlRootElement
public class Fahrrad extends AbstractArtikel {

	private static final long serialVersionUID = 7087800972131210358L;
	
//	@Column(length = 1)
	private Set<Farbe> farbe;
	
	public Set<Farbe> getFarbe() {
		return farbe;
	}
	public void setFarbe(Set<Farbe> farbe) {
		this.farbe = farbe;
	}
	
	@Override
	public String toString() {
		return "Fahrrad [" + super.toString() + ", Farbe:" + farbe + "]";
	}
}
