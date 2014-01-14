package shop.Bestellverwaltung.service;

import java.lang.invoke.MethodHandles;
import java.util.List;
//import java.util.Locale;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Bestellverwaltung.domain.Bestellung;
import shop.Bestellverwaltung.domain.Lieferung;
import shop.Kundenverwaltung.domain.Kunde;

/**
 * @author <a href="mailto:koju1020@HS-Karlsruhe.de">Julian Kohlhaas</a>  
 */

@Decorator
@Dependent
public class BestellungServiceMitGeschenkverpackung implements
		BestellungService {
	
private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Inject
	@Delegate
	@Any
	private BestellungService bs;

	@Override
	public Bestellung findBestellungById(Long id, FetchType fetch) {
		return bs.findBestellungById(id, fetch);
	}

	@Override
	public Kunde findKundeById(Long id) {
		return bs.findKundeById(id);
	}

	@Override
	public List<Bestellung> findBestellungenByKunde(Kunde kunde) {
		return bs.findBestellungenByKunde(kunde);
	}

	@Override
	public List<Bestellung> findBestellungenByIds(List<Long> ids, FetchType fetch) {
		return bs.findBestellungenByIds(ids, fetch);
	}

	@Override
	public Bestellung createBestellung(Bestellung bestellung, Long kundeId) {
		LOGGER.warn("Geschenkverpackung noch nicht implementiert");
		return bs.createBestellung(bestellung, kundeId);
	}

	@Override
	public Bestellung createBestellung(Bestellung bestellung, Kunde kunde) {
		LOGGER.warn("Geschenkverpackung noch nicht implementiert");
		return bs.createBestellung(bestellung, kunde);
	}

	@Override
	public List<AbstractArtikel> ladenhueter(int anzahl) {
		return bs.ladenhueter(anzahl);
	}

	@Override
	public List<Lieferung> findLieferungen(String nr) {
		return bs.findLieferungen(nr);
	}

	@Override
	public Lieferung createLieferung(Lieferung lieferung,
			List<Bestellung> bestellungen) {
		return bs.createLieferung(lieferung, bestellungen);
	}

}
