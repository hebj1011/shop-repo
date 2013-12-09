package shop.Bestellverwaltung.service;

import java.util.List;
import java.util.Locale;

import shop.Bestellverwaltung.domain.Bestellung;
import shop.Kundenverwaltung.domain.Kunde;

/**
 * @author <a href="mailto:koju1020@HS-Karlsruhe.de">Julian Kohlhaas</a>  
 */

public interface BestellungService {
	
	Bestellung findBestellungById(Long id);
	List<Bestellung> findBestellungenByKunde(Kunde kunde);
	Bestellung createBestellung(Bestellung bestellung, Kunde kunde, Locale locale);
	Bestellung createBestellung(Bestellung bestellung);

}
