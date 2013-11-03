package shop.Artikelverwaltung.service;

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


/**
 * Emulation des Anwendungskerns
 */


public final class Mock {
	private static final int MAX_ARTIKELNUMMER = 99;
	private static final int MAX_ARTIKEL = 8;
	//private static final int MAX_BESTELLUNGEN = 4;

	public static AbstractArtikel findArtikelByID(Long artikelnummer) {
		if (artikelnummer > MAX_ARTIKELNUMMER) {
			return null;
		}
		
		final AbstractArtikel artikel = artikelnummer % 2 == 1 ? new Ersatzteil() : new Fahrrad();
		artikel.setArtikelnummer(artikelnummer);
		artikel.setName("Artikel:" + artikelnummer);
		artikel.setEinzelpreis(99.99);
		artikel.setBestand(10);
		
		if (artikel.getClass().equals(Ersatzteil.class)) {
			final Ersatzteil ersatzteil = (Ersatzteil) artikel;
			final Unterklasse unterklasse = new Unterklasse((long)1,"Licht","Macht hell");
			final String datenblatt = "Hier finden sie die Infomationen!";
		}
		else if (artikel.getClass().equals(Fahrrad.class)) {
			final Fahrrad fahrrad = (Fahrrad) artikel;
			final Unterklasse unterklasse = new Unterklasse((long)5,"Damenräder","Hier finden sie alle Damenräder");
			final Set<Farbe> farbe = new HashSet<>();
			farbe.add(Farbe.SCHWARZ);
			fahrrad.setFarbe(farbe);
		}
		
		return artikel;
	}

	public static List<AbstractArtikel> findAllArtikel() {
		final int anzahl = MAX_ARTIKEL;
		final List<AbstractArtikel> alleArtikel = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractArtikel artikel = findArtikelByID(Long.valueOf(i));
			alleArtikel.add(artikel);			
		}
		return alleArtikel;
	}

	public static List<AbstractArtikel> findArtikelByName(String name) {
		final int anzahl = name.length();
		final List<AbstractArtikel> alleArtikel = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractArtikel artikel = findArtikelByID(Long.valueOf(i));
			artikel.setName(name);
			alleArtikel.add(artikel);			
		}
		return alleArtikel;
	}
	
/* TODO auskommentieren wenn bestellungen angelegt sind
	public static List<Bestellung> findBestellungenByArtikelnummer(AbstractArtikel artikel) {
		// Beziehungsgeflecht zwischen Kunde und Bestellungen aufbauen
		final int anzahl = artikel.getArtikelnummer().intValue() % MAX_BESTELLUNGEN + 1;  // 1, 2, 3 oder 4 Bestellungen
		final List<Bestellung> bestellungen = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Bestellung bestellung = findBestellungByArtikelnummer(Long.valueOf(i));
			bestellung.setArtikel(artikel);
			bestellungen.add(bestellung);			
		}
		artikel.setBestellungen(bestellungen);
		
		return bestellungen;
	}

	public static Bestellung findBestellungByArtikelnummer(Long artikelnummer) {
		if (artikelnummer > MAX_ARTIKELNUMMER) {
			return null;
		}

		final AbstractArtikel artikel = findArtikelByID(artikelnummer + 1);  // andere ID fuer den Kunden

		final Bestellung bestellung = new Bestellung();
		bestellung.setId(id);
		bestellung.setAusgeliefert(false);
		bestellung.setArtikel(artikel);
		
		return bestellung;
	}
*/
	public static AbstractArtikel createArtikel(AbstractArtikel artikel) {
		// Neue IDs fuer Artikel und zugehoerigem Preis + Bestand
		// Ein neuer Artikel gehört auch zu keinen Bestellungen
		final String name = artikel.getName();
		artikel.setArtikelnummer(Long.valueOf(name.length()));
		final Double Einzelpreis = artikel.getEinzelpreis();
		artikel.setBestellungen(null);
		
		System.out.println("Neuer Artikel: " + artikel);
		return artikel;
	}

	public static void updateArtikel(AbstractArtikel artikel) {
		System.out.println("Aktualisierter Artikel: " + artikel);
	}

	public static void deleteArtikel(Long artikelnummer) {
		System.out.println("Artikel mit ID=" + artikelnummer + " geloescht");
	}

	private Mock() {}
}