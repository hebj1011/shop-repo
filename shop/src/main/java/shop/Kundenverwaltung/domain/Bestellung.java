package shop.Kundenverwaltung.domain;

public class Bestellung {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Bestellung(String id) {
		super();
		this.id = id;
	}
}
