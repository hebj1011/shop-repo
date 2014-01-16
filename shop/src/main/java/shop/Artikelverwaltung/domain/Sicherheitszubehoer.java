package shop.Artikelverwaltung.domain;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import static shop.Artikelverwaltung.domain.AbstractArtikel.SICHERHEITSZUBEHOER;

@Entity
@DiscriminatorValue(SICHERHEITSZUBEHOER)
@Cacheable
@XmlRootElement
public class Sicherheitszubehoer extends AbstractArtikel {

	private static final long serialVersionUID = -4684331848134556129L;
	@Basic(optional = false)
	private Boolean tuev;
	
	@Column(length = 10)
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
