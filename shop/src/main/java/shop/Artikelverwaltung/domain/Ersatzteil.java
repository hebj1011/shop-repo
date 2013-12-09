package shop.Artikelverwaltung.domain;

//import javax.persistence.Cacheable;
//import javax.persistence.Column;
//import javax.persistence.DiscriminatorValue;
//import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

//import static shop.Artikelverwaltung.domain.AbstractArtikel.ERSATZTEIL;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">Bjoern Hetzel</a>
 */

//@Entity
//@Cacheable
//@DiscriminatorValue(ERSATZTEIL)
@XmlRootElement
public class Ersatzteil extends AbstractArtikel {

	private static final long serialVersionUID = -9125274988659035043L;
	
//	@Column(length = 256)
	private String datenblatt;
	
	public String getDatenblatt() {
		return datenblatt;
	}
	public void setDatenblatt(String datenblatt) {
		this.datenblatt = datenblatt;
	}
	
	@Override
	public String toString() {
		return "Ersatzteil [" + super.toString() + ", Datenblatt:" + datenblatt + "]";
	}

}
