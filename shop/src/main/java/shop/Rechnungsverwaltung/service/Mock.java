package shop.Rechnungsverwaltung.service;


import shop.Kundenverwaltung.domain.Adresse;
import shop.Rechnungsverwaltung.domain.Rechnung;


/**
 * Emulation des Anwendungskerns
 */


public final class Mock {
	private static final int MAX_RECHNUNGSNUMMER = 99;
	
	public static Rechnung findRechnungByID(Long rechnungsnummer) {
		if (rechnungsnummer > MAX_RECHNUNGSNUMMER) {
			return null;
		}
		
		Rechnung rechnung = new Rechnung(Long.parseLong("26"));
		/*
		 * TODO Datenbankabfrage einbauen 
		 */
		return rechnung;
	}



	private Mock() {}
}