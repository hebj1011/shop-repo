package shop.Artikelverwaltung.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">Bjoern Hetzel</a>
 */

@XmlRootElement
public class Ersatzteil extends AbstractArtikel {
	
	private static final long serialVersionUID = 6258156986876418100L;
	
	private Unterklasse unterklasse;
	private List<Fahrrad> zugehörigkeit;
	private String datenblatt;
	
	public Ersatzteil(Long artikelnummer, String name, Double einzelpreis,
			Integer bestand, Unterklasse unterklasse,
			List<Fahrrad> zugehörigkeit, String datenblatt) {
		super(artikelnummer, name, einzelpreis, bestand);
		this.unterklasse = unterklasse;
		this.zugehörigkeit = zugehörigkeit;
		this.datenblatt = datenblatt;
	}
	
	public Unterklasse getUnterklasse() {
		return unterklasse;
	}
	public void setUnterklasse(Unterklasse unterklasse) {
		this.unterklasse = unterklasse;
	}
	public List<Fahrrad> getZugehörigkeit() {
		return zugehörigkeit;
	}
	public void setZugehörigkeit(List<Fahrrad> zugehörigkeit) {
		this.zugehörigkeit = zugehörigkeit;
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
				+ ", Zugehörigkeit= " + zugehörigkeit.toString() + ", Datenblatt:" + datenblatt + "]";
	}

}
