package shop.Artikelverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">Bjoern Hetzel</a>
 */

@XmlRootElement
public class Ersatzteil extends AbstractArtikel {

	private static final long serialVersionUID = -9125274988659035043L;
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
