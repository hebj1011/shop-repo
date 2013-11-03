package shop.Artikelverwaltung.domain;

public class Unterklasse {
	
	public Unterklasse(Long klassen_id, String name, String beschreibeung) {
		super();
		this.klassen_id = klassen_id;
		this.name = name;
		this.beschreibeung = beschreibeung;
	}
	
	private Long klassen_id;
	private String name;
	private String beschreibeung;
	
	public Long getKlassen_id() {
		return klassen_id;
	}
	public void setKlassen_id(Long klassen_id) {
		this.klassen_id = klassen_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBeschreibeung() {
		return beschreibeung;
	}
	public void setBeschreibeung(String beschreibeung) {
		this.beschreibeung = beschreibeung;
	}

}
