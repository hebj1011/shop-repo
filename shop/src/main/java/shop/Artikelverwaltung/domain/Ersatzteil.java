package shop.Artikelverwaltung.domain;

import java.util.List;

public class Ersatzteil extends Artikel {
	
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
	

}
