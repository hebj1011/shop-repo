package shop.Artikelverwaltung.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Artikelverwaltung.domain.Ersatzteil;
import shop.Artikelverwaltung.domain.Fahrrad;
import shop.Artikelverwaltung.domain.Farbe;
import shop.Artikelverwaltung.domain.Sicherheitszubehoer;
import shop.Artikelverwaltung.domain.Unterklasse;


/**
 * Emulation des Anwendungskerns
 */


public final class Mock {
	private static final int MAX_ARTIKELNUMMER = 10000;
	private static final int MAX_ARTIKEL = 8;

	public static AbstractArtikel findArtikelByID(Long artikelnummer) {
		if (artikelnummer > MAX_ARTIKELNUMMER) {
			return null;
		}
		
		AbstractArtikel artikel = null;
		final Integer art1 = 1000;
		final Integer art2 = 5000;
		final Long klassenIdNr = (long) 123456579;
		
		if (artikelnummer < art1)
			artikel = new Fahrrad();
		else if (artikelnummer > art1 && artikelnummer < art2)
			artikel = new Ersatzteil();
		else
			artikel = new Sicherheitszubehoer();
		
		artikel.setArtikelnummer(artikelnummer);
		artikel.setName("Artikel" + artikelnummer);
		artikel.setEinzelpreis(0.00);
		artikel.setBestand(0);
		
		final Unterklasse unterklasse = new Unterklasse();
		unterklasse.setKlassenId(klassenIdNr);        
		unterklasse.setName("Testunterklasse");
		unterklasse.setBeschreibung("Testbeschreibung");
		unterklasse.setArtikel(artikel);
		artikel.setUnterklasse(unterklasse);
		
		if (artikel.getClass().equals(Ersatzteil.class)) {
			final Ersatzteil ersatzteil = (Ersatzteil) artikel;
			ersatzteil.setDatenblatt("Testdatenblatt");
		}
		else if (artikel.getClass().equals(Fahrrad.class)) {
			final Fahrrad fahrrad = (Fahrrad) artikel;
			final Set<Farbe> farbe = new HashSet<>();
			farbe.add(Farbe.SCHWARZ);
			farbe.add(Farbe.ROT);
			fahrrad.setFarbe(farbe);
		}
		else if (artikel.getClass().equals(Sicherheitszubehoer.class)) {
			final Sicherheitszubehoer sicherheit = (Sicherheitszubehoer) artikel;
			sicherheit.setTuev(true);
			sicherheit.setGroesse("Gross");
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
		// Ein neuer Artikel gehört auch zu keinen Bestellungen
		final String name = artikel.getName();
		artikel.setArtikelnummer(Long.valueOf(name.length()));
		final Unterklasse unterklasse = artikel.getUnterklasse();
		unterklasse.setKlassenId((Long.valueOf(name.length())) + 1);
		unterklasse.setArtikel(artikel);
		
		System.out.println("Neuer Artikel: " + artikel);
		return artikel;
	}

	public static void updateArtikel(AbstractArtikel artikel) {
		System.out.println("Aktualisierter Artikel: " + artikel);
	}

	public static void deleteArtikel(Long artikelnummer) {
		System.out.println("Artikel mit ID=" + artikelnummer + " geloescht");
	}

	private Mock() { }
}
