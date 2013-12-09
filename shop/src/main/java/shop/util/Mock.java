package shop.util;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import org.jboss.logging.Logger;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Artikelverwaltung.domain.Ersatzteil;
import shop.Artikelverwaltung.domain.Fahrrad;
import shop.Artikelverwaltung.domain.Sicherheitszubehoer;
import shop.Bestellverwaltung.domain.Bestellung;
import shop.Kundenverwaltung.domain.Kunde;
import shop.Kundenverwaltung.domain.Adresse;

/**
 * Emulation der Datenbankzugriffsschicht
 * @author <a href="mailto:lade1011@HS-Karlsruhe.de">Denis Langer</a>
 */
public final class Mock {
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());

	private static final int MAX_ID = 99;
	private static final int MAX_KUNDEN = 8;
	private static final int MAX_BESTELLUNGEN = 4;
	private static final int JAHR = 2001;
	private static final int MONAT = 0; // bei Calendar werden die Monate von 0 bis 11 gezaehlt
	private static final int TAG = 31;  // bei Calendar die Monatstage ab 1 gezaehlt
	private final int fahval = 200;
	private final int ersval = 300;
	private final int sichval = 400;

	public static Kunde findKundeById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final Kunde kunde = new Kunde(); //id % 2 == 1 ? new Privatkunde() : new Firmenkunde();
		kunde.setId(id);
		kunde.setNachname("Nachname");
		kunde.setEmail("" + id + "@hska.de");
		final GregorianCalendar seitCal = new GregorianCalendar(JAHR, MONAT, TAG);
		final Date seit = seitCal.getTime();
		kunde.setSeit(seit);
		
		final Adresse adresse = new Adresse();
		adresse.setId(id + 1);        // andere ID fuer die Adresse
		adresse.setPlz("12345");
		adresse.setOrt("Testort");
		adresse.setKunde(kunde);
		kunde.setAdresse(adresse);
		
//		if (kunde.getClass().equals(Privatkunde.class)) {
//			final Privatkunde privatkunde = (Privatkunde) kunde;
//			final Set<HobbyType> hobbies = new HashSet<>();
//			hobbies.add(HobbyType.LESEN);
//			hobbies.add(HobbyType.REISEN);
//			privatkunde.setHobbies(hobbies);
//		}
		
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
	
	public static Kunde findKundeByEmail(String email) {
		if (email.startsWith("x")) {
			return null;
		}
		
		final Kunde kunde = new Kunde();//email.length() % 2 == 1 ? new Privatkunde() : new Firmenkunde();
		kunde.setId(Long.valueOf(email.length()));
		kunde.setNachname("Nachname");
		kunde.setEmail(email);
		final GregorianCalendar seitCal = new GregorianCalendar(JAHR, MONAT, TAG);
		final Date seit = seitCal.getTime();
		kunde.setSeit(seit);
		
		final Adresse adresse = new Adresse();
		adresse.setId(kunde.getId() + 1);        // andere ID fuer die Adresse
		adresse.setPlz("12345");
		adresse.setOrt("Testort");
		adresse.setKunde(kunde);
		kunde.setAdresse(adresse);
		
//		if (kunde.getClass().equals(Privatkunde.class)) {
//			final Privatkunde privatkunde = (Privatkunde) kunde;
//			final Set<HobbyType> hobbies = new HashSet<>();
//			hobbies.add(HobbyType.LESEN);
//			hobbies.add(HobbyType.REISEN);
//			privatkunde.setHobbies(hobbies);
//		}
		
		return kunde;
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

	public static Kunde createKunde(Kunde kunde) {
		// Neue IDs fuer Kunde und zugehoerige Adresse
		// Ein neuer Kunde hat auch keine Bestellungen
		final String nachname = kunde.getNachname();
		kunde.setId(Long.valueOf(nachname.length()));
		final Adresse adresse = kunde.getAdresse();
		adresse.setId((Long.valueOf(nachname.length())) + 1);
		adresse.setKunde(kunde);
		kunde.setBestellungen(null);
		
		LOGGER.infof("Neuer Kunde: %s", kunde);
		return kunde;
	}

	public static void updateKunde(Kunde kunde) {
		LOGGER.infof("Aktualisierter Kunde: %s", kunde);
	}

	public static void deleteKunde(Kunde kunde) {
		LOGGER.infof("Geloeschter Kunde: %s", kunde);
	}

	public static Bestellung createBestellung(Bestellung bestellung, Kunde kunde) {
		LOGGER.infof("Neue Bestellung: %s fuer Kunde: %s", bestellung, kunde);
		return bestellung;
	}
	
	public static Bestellung createBestellung(Bestellung bestellung) {
		
		LOGGER.infof("Neue Bestellung: %s " + bestellung);
		return bestellung;
	}
	
	public AbstractArtikel findArtikelById(Long id) {
		if (id == null) {
			return null;
		}
		
		final AbstractArtikel artikel;
		if (fahval < id && id < ersval)
			artikel = new Fahrrad();
		else if (ersval < id && id < sichval)
			artikel = new Ersatzteil();
		else
			artikel = new Sicherheitszubehoer();
		
		artikel.setId(id);
		artikel.setName("Name: " + id);
		
		return artikel;	
	}
	
	public static <T extends AbstractArtikel> T createArtikel(T artikel) {
		// Neue IDs fuer Artikel
		final String name = artikel.getName();
		artikel.setId(Long.valueOf(name.length()));
		artikel.setEinzelpreis(null);
		
		LOGGER.infof("Neuer Artikel: %s", artikel);
		return artikel;
	}

	public static void updateArtikel(AbstractArtikel artikel) {
		LOGGER.infof("Aktualisierter Artikel: %s", artikel);
	}

	public static void deleteArtikel(AbstractArtikel artikel) {
		LOGGER.infof("Geloeschter Artikel: %s", artikel);
	}

	private Mock() { /**/ }
}
