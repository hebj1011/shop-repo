package shop.Artikelverwaltung.domain;

import java.util.List;

public class Ersatzteil extends Artikel {
	public Ersatzteil(Long artikelnummer, String name, String beschreibung,
			String mengeneinheit, Double einzelpreis, Integer bestand,
			Boolean lieferbar, Unterklasse unterklasse,
			List<Fahrrad> zugeh�rigkeit, String datenblatt) {
		super(artikelnummer, name, beschreibung, mengeneinheit, einzelpreis,
				bestand, lieferbar);
		this.unterklasse = unterklasse;
		this.zugeh�rigkeit = zugeh�rigkeit;
		this.datenblatt = datenblatt;
	}
	
	private Unterklasse unterklasse;
	private List<Fahrrad> zugeh�rigkeit;
	private String datenblatt;
	public Unterklasse getUnterklasse() {
		return unterklasse;
	}
	public void setUnterklasse(Unterklasse unterklasse) {
		this.unterklasse = unterklasse;
	}
	public List<Fahrrad> getZugeh�rigkeit() {
		return zugeh�rigkeit;
	}
	public void setZugeh�rigkeit(List<Fahrrad> zugeh�rigkeit) {
		this.zugeh�rigkeit = zugeh�rigkeit;
	}
	public String getDatenblatt() {
		return datenblatt;
	}
	public void setDatenblatt(String datenblatt) {
		this.datenblatt = datenblatt;
	}

}
