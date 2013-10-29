package shop.Artikelverwaltung.domain;

public class Fahrrad extends Artikel {
	private String rahmentyp;
	private String rahmenmaterial;
	private Double rahmenhoehe;
	private String gangschaltung;
	private Double gewicht;
	private String bremse_vorne;
	private String bremse_hinten;
	private String bereifung;
	private String felgen;
	private String sattel;
	private String farbe;
	private String unterklasse;
	
	public Fahrrad(Long artikelnummer, String name, String beschreibung,
			String mengeneinheit, Double einzelpreis, Integer bestand,
			Boolean lieferbar, String rahmentyp, String rahmenmaterial,
			Double rahmenhoehe, String gangschaltung, Double gewicht,
			String bremse_vorne, String bremse_hinten, String bereifung,
			String felgen, String sattel, String farbe, String unterklasse) {
		super(artikelnummer, name, beschreibung, mengeneinheit, einzelpreis,
				bestand, lieferbar);
		this.rahmentyp = rahmentyp;
		this.rahmenmaterial = rahmenmaterial;
		this.rahmenhoehe = rahmenhoehe;
		this.gangschaltung = gangschaltung;
		this.gewicht = gewicht;
		this.bremse_vorne = bremse_vorne;
		this.bremse_hinten = bremse_hinten;
		this.bereifung = bereifung;
		this.felgen = felgen;
		this.sattel = sattel;
		this.farbe = farbe;
		this.unterklasse = unterklasse;
	}

	public String getRahmentyp() {
		return rahmentyp;
	}
	public void setRahmentyp(String rahmentyp) {
		this.rahmentyp = rahmentyp;
	}
	public String getRahmenmaterial() {
		return rahmenmaterial;
	}
	public void setRahmenmaterial(String rahmenmaterial) {
		this.rahmenmaterial = rahmenmaterial;
	}
	public Double getRahmenhoehe() {
		return rahmenhoehe;
	}
	public void setRahmenhoehe(Double rahmenhoehe) {
		this.rahmenhoehe = rahmenhoehe;
	}
	public String getGangschaltung() {
		return gangschaltung;
	}
	public void setGangschaltung(String gangschaltung) {
		this.gangschaltung = gangschaltung;
	}
	public Double getGewicht() {
		return gewicht;
	}
	public void setGewicht(Double gewicht) {
		this.gewicht = gewicht;
	}
	public String getBremse_vorne() {
		return bremse_vorne;
	}
	public void setBremse_vorne(String bremse_vorne) {
		this.bremse_vorne = bremse_vorne;
	}
	public String getBremse_hinten() {
		return bremse_hinten;
	}
	public void setBremse_hinten(String bremse_hinten) {
		this.bremse_hinten = bremse_hinten;
	}
	public String getBereifung() {
		return bereifung;
	}
	public void setBereifung(String bereifung) {
		this.bereifung = bereifung;
	}
	public String getFelgen() {
		return felgen;
	}
	public void setFelgen(String felgen) {
		this.felgen = felgen;
	}
	public String getSattel() {
		return sattel;
	}
	public void setSattel(String sattel) {
		this.sattel = sattel;
	}
	public String getFarbe() {
		return farbe;
	}
	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}
	public String getUnterklasse() {
		return unterklasse;
	}
	public void setUnterklasse(String unterklasse) {
		this.unterklasse = unterklasse;
	}
}
