package shop.Kundenverwaltung.service;
//
//import java.util.ArrayList;
////import java.util.HashSet;
//import java.util.List;
////import java.util.Set;
//
////import shop.Bestellverwaltung.domain.Bestellung;
//import shop.Kundenverwaltung.domain.Kunde;
//import shop.Kundenverwaltung.domain.Adresse;
//
///**
// * Emulation des Anwendungskerns
// */
public final class Mock {
//	//private static final int MAX_ID = 99;
//	private static final int MAX_KUNDEN = 8;
//	//private static final int MAX_BESTELLUNGEN = 4;
//
//	public static Kunde findKundeById(Long id) {
////		if (id > MAX_ID) {
////			return null;
////		}
//		
//		final Kunde kunde = new Kunde(); //id % 2 == 1 ?
//		kunde.setId(id);
//		kunde.setVorname("Vorname" + id);
//		kunde.setNachname("Nachname" + id);
//		kunde.setEmail("" + id + "@hska.de");
//		
//		final Adresse adresse = new Adresse();
//		adresse.setId(id + 1);        // andere ID fuer die Adresse
//		adresse.setPlz("12345");
//		adresse.setOrt("Testort");
//		adresse.setStrasse("Muhlweg");
//		adresse.setHausnummer("256");
//		adresse.setKunde(kunde);
//		kunde.setAdresse(adresse);
//		
//		return kunde;
//	}
//
//	public static List<Kunde> findAllKunden() {
//		final int anzahl = MAX_KUNDEN;
//		final List<Kunde> kunden = new ArrayList<>(anzahl);
//		for (int i = 1; i <= anzahl; i++) {
//			final Kunde kunde = findKundeById(Long.valueOf(i));
//			kunden.add(kunde);			
//		}
//		return kunden;
//	}
//
//	public static List<Kunde> findKundenByNachname(String nachname) {
//		final int anzahl = nachname.length();
//		final List<Kunde> kunden = new ArrayList<>(anzahl);
//		for (int i = 1; i <= anzahl; i++) {
//			final Kunde kunde = findKundeById(Long.valueOf(i));
//			kunde.setNachname(nachname);
//			kunden.add(kunde);			
//		}
//		return kunden;
//	}
//	
//
////	public static List<Bestellung> findBestellungenByKunde(Kunde kunde) {
////		// Beziehungsgeflecht zwischen Kunde und Bestellungen aufbauen
////		final int anzahl = kunde.getId().intValue() % MAX_BESTELLUNGEN + 1;  // 1, 2, 3 oder 4 Bestellungen
////		final List<Bestellung> bestellungen = new ArrayList<>(anzahl);
////		for (int i = 1; i <= anzahl; i++) {
////			final Bestellung bestellung = findBestellungById(Long.valueOf(i));
////			bestellung.setKunde(kunde);
////			bestellungen.add(bestellung);			
////		}
////		kunde.setBestellungen(bestellungen);
////		
////		return bestellungen;
////	}
//
////	public static Bestellung findBestellungById(Long id) {
////		if (id > MAX_ID) {
////			return null;
////		}
////
////		final Kunde kunde = findKundeById(id + 1);  // andere ID fuer den Kunden
////
////		final Bestellung bestellung = new Bestellung();
////		bestellung.setId(id);
////		bestellung.setAusgeliefert(false);
////		bestellung.setKunde(kunde);
////		
////		return bestellung;
////	}
//
//	public static Kunde createKunde(Kunde kunde) {
//		// Neue IDs fuer Kunde und zugehoerige Adresse
//		// Ein neuer Kunde hat auch keine Bestellungen
//		//final String nachname = kunde.getNachname();
//		Long id = Long.valueOf(kunde.hashCode());
//		if (id < 0) {
//			id = id * (-1);
//		}
//		kunde.setId(id);
//		final Adresse adresse = kunde.getAdresse();
//		adresse.setId(id + 1);
//		adresse.setKunde(kunde);
//		kunde.setBestellungen(null);
//		
//		System.out.println("Neuer Kunde: " + kunde);
//		return kunde;
//	}
//
//	public static void updateKunde(Kunde kunde) {
//		System.out.println("Aktualisierter Kunde: " + kunde);
//	}
//
//	public static void deleteKunde(Long kundeId) {
//		System.out.println("Kunde mit ID=" + kundeId + " geloescht");
//	}
//
	private Mock() { /**/ }
}
