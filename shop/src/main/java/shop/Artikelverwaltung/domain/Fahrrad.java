package shop.Artikelverwaltung.domain;

public class Fahrrad extends Artikel {
	private String rahmenmaterial;
	private String bremse;
	private String sattel;
	private Unterklasse unterklasse;
	
	public Fahrrad(Long artikelnummer, String name, Double einzelpreis,
			Integer bestand, String rahmenmaterial, String bremse,
			String sattel, Unterklasse unterklasse) {
		super(artikelnummer, name, einzelpreis, bestand);
		this.rahmenmaterial = rahmenmaterial;
		this.bremse = bremse;
		this.sattel = sattel;
		this.unterklasse = unterklasse;
	}
	
	public String getRahmenmaterial() {
		return rahmenmaterial;
	}
	public void setRahmenmaterial(String rahmenmaterial) {
		this.rahmenmaterial = rahmenmaterial;
	}
	public String getBremse() {
		return bremse;
	}
	public void setBremse(String bremse) {
		this.bremse = bremse;
	}
	public String getSattel() {
		return sattel;
	}
	public void setSattel(String sattel) {
		this.sattel = sattel;
	}
	public Unterklasse getUnterklasse() {
		return unterklasse;
	}
	public void setUnterklasse(Unterklasse unterklasse) {
		this.unterklasse = unterklasse;
	}	
	
}
