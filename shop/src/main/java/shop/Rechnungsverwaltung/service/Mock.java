package shop.Rechnungsverwaltung.service;


//import shop.Kundenverwaltung.domain.Adresse;
import shop.Rechnungsverwaltung.domain.Rechnung;


/**
 * Emulation des Anwendungskerns
 */


public final class Mock {
	private static final int MAX_RECHNUNGSNUMMER = 999999;
	
	public static Rechnung findRechnungByID(Long rechnungsnummer) {
		if (rechnungsnummer > MAX_RECHNUNGSNUMMER) {
			return null;
		}
		
		final Rechnung rechnung = new Rechnung();
		rechnung.setId(rechnungsnummer);
		
		/*
		 * TODO ADD DB ABFRAGE
		 * ignoriere vorerst checkStyle-Fehler in Z. 28 & 29
		 */
		double gesPreis = 156616.6;
		double versKosten = 4.90;
		/*
		 * Ende 
		 */
		rechnung.setGesamtpreis(gesPreis);
		rechnung.setVersandkosten(versKosten);
	
		return rechnung;
	}



	private Mock() { }
}
