package shop.Bestellverwaltung.service;

/*
import java.util.ArrayList;
import java.util.Collection;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Bestellverwaltung.domain.Bestellung;
import shop.Kundenverwaltung.domain.Kunde;
import shop.Kundenverwaltung.domain.Adresse;
*/

public final class Mock {
/*	
	private static final int MAX_ID = 99;
	private static final int MAX_KUNDEN = 8;
	private static final int MAX_BESTELLUNGEN = 4;

	public static Kunde findKundeById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final Kunde kunde = new Kunde();
		kunde.setId(id);
		kunde.setNachname("Nachname" + id);
		kunde.setEmail("" + id + "@hska.de");
		
		final Adresse adresse = new Adresse();
		adresse.setId(id + 1);        // andere ID fuer die Adresse
		adresse.setPlz("76596");
		adresse.setOrt("Forbach");
		adresse.setStrasse("Hansstrasse");
		adresse.setHausnummer("77");
		adresse.setKunde(kunde);
		kunde.setAdresse(adresse);
		
		return kunde;
	}

	public static List<Kunde> findAllKunden() {
		final int anzahl = MAX_KUNDEN;
		final List<Kunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Kunde kunde = findKundeById(Long.valueOf(i));
			kunden.add(kunde);			
		}
		return kunden;
	}

	public static List<Kunde> findKundenByNachname(String nachname) {
		final int anzahl = nachname.length();
		final List<Kunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Kunde kunde = findKundeById(Long.valueOf(i));
			kunde.setNachname(nachname);
			kunden.add(kunde);			
		}
		return kunden;
	}
	

	public static List<Bestellung> findBestellungenByKunde(Kunde kunde) {
		// Beziehungsgeflecht zwischen Kunde und Bestellungen aufbauen
		final int anzahl = kunde.getId().intValue() % MAX_BESTELLUNGEN + 1;  // 1, 2, 3 oder 4 Bestellungen
		final List<Bestellung> bestellungen = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Bestellung bestellung = findBestellungById(Long.valueOf(i));
			bestellung.setKunde(kunde);
			bestellungen.add(bestellung);			
		}
		kunde.setBestellungen(bestellungen);
		
		return bestellungen;
	}

	public static Bestellung findBestellungById(Long id) {
		if (id > MAX_ID) {
			return null;
		}

		final Kunde kunde = findKundeById(id + 1);  // andere ID fuer den Kunden

		final Bestellung bestellung = new Bestellung();
		bestellung.setId(id);
		bestellung.setVersendet(false);
		bestellung.setKunde(kunde);
		
		return bestellung;
	}
	
	public static Collection<AbstractArtikel> findArtikelByID(List<Long> artikelIds) {
		// TODO Auto-generated method stub
		return null;
	}


	public static Bestellung createBestellung(Bestellung bestellung, Long kundeId) {
		// TODO Auto-generated method stub
		final Long id = bestellung.getId();
		bestellung.setId(id);
		
		System.out.println("Neue Bestellung:  " + bestellung);
		return bestellung;		
	}

	public static Bestellung createBestellung(Bestellung bestellung) {
		
		System.out.println("Neue Bestellung:  " + bestellung);
		return bestellung;
	}
	
	public static Bestellung createBestellung(Bestellung bestellung, Kunde kunde) {
		
		System.out.println("Neue Bestellung:  " + bestellung);
		return bestellung;
	}
*/
	private Mock() { /**/ }

}
