package shop.Artikelverwaltung.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Artikelverwaltung.domain.Ersatzteil;
import shop.Artikelverwaltung.domain.Fahrrad;
//import shop.Artikelverwaltung.domain.Sicherheitszubehoer;


/**
 * Emulation des Anwendungskerns
 */


public final class Mock {
	private static final int MAX_ARTIKELNUMMER = 99;
	private static final int MAX_ARTIKEL = 8;

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
		}
		else if (artikel.getClass().equals(Fahrrad.class)) {
			final Fahrrad fahrrad = (Fahrrad) artikel;
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
	

	public static AbstractArtikel createArtikel(AbstractArtikel artikel) {
		// Neue IDs fuer Artikel und zugehoerigem Preis + Bestand
		// Ein neuer Artikel geh�rt auch zu keinen Bestellungen
		final String name = artikel.getName();
		artikel.setArtikelnummer(Long.valueOf(name.length()));
		final Double einzelpreis = artikel.getEinzelpreis();
		artikel.setEinzelpreis(einzelpreis);
		artikel.setBestand(artikel.getBestand());
		
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