package shop.Rechnungsverwaltung.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//import shop.bestellverwaltung.domain.Bestellung;
import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Artikelverwaltung.domain.Ersatzteil;
import shop.Artikelverwaltung.domain.Fahrrad;
//import shop.Artikelverwaltung.domain.Sicherheitszubehoer;
import shop.Artikelverwaltung.domain.Unterklasse;
import shop.Artikelverwaltung.domain.Farbe;
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
		
		Rechnung rechnung = null;
		/*
		 * TODO Datenbankabfrage einbauen 
		 */
		return rechnung;
	}



	private Mock() {}
}