package shop.Bestellverwaltung.domain;

import java.util.Date;

import shop.Kundenverwaltung.domain.Kunde;

/**
 * @author Julian Kohlhaas
 * 
 */
public class Bestellung {
	
	private Long id ;
	private shop.Kundenverwaltung.domain.Kunde kunde ;
	private Date bestelldatum ;
	private double gesamtpreis ;
	private boolean versendet ;
	
	public Bestellung(Long id, Kunde kunde, Date bestelldatum,
			double gesamtpreis, boolean versendet) {
		super();
		this.id = id;
		this.kunde = kunde;
		this.bestelldatum = bestelldatum;
		this.gesamtpreis = gesamtpreis;
		this.versendet = versendet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public shop.Kundenverwaltung.domain.Kunde getKunde() {
		return kunde;
	}

	public void setKunde(shop.Kundenverwaltung.domain.Kunde kunde) {
		this.kunde = kunde;
	}

	public Date getBestelldatum() {
		return bestelldatum;
	}

	public void setBestelldatum(Date bestelldatum) {
		this.bestelldatum = bestelldatum;
	}

	public double getGesamtpreis() {
		return gesamtpreis;
	}

	public void setGesamtpreis(double gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}

	public boolean isVersendet() {
		return versendet;
	}

	public void setVersendet(boolean versendet) {
		this.versendet = versendet;
	}
	

}
