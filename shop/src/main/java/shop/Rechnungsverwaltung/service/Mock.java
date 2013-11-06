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
		
		Rechnung rechnung = new Rechnung();
		rechnung.setId(rechnungsnummer);
		rechnung.setGesamtpreis(156616.6);
		rechnung.setVersandkosten(156.2);
	
		return rechnung;
	}



	private Mock() {}
}