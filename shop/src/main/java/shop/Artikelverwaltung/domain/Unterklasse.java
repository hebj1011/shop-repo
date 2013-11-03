package shop.Artikelverwaltung.domain;

public class Unterklasse {
	
	public Unterklasse(Long klassenId, String name, String beschreibung) {
		super();
		this.klassenId = klassenId;
		this.name = name;
		this.beschreibung = beschreibung;
	}
	
	private Long klassenId;
	private String name;
	private String beschreibung;
	
	public Long getKlassenId() {
		return klassenId;
	}
	public void setKlassenId(Long klassenId) {
		this.klassenId = klassenId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	@Override
	public String toString() {
		return "Unterklasse [" + "ID=" + klassenId + ", Name:" + name
				+ ", Beschreibung:" + beschreibung + "]";
	}

}
