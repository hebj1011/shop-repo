package shop.Bestellverwaltung.service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Locale;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import shop.Bestellverwaltung.domain.Bestellung;
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
	public Bestellung findBestellungById(Long id) {	
		return bs.findBestellungById(id);
	}

	@Override
	public List<Bestellung> findBestellungenByKunde(Kunde kunde) {
		return bs.findBestellungenByKunde(kunde);
	}

	@Override
	public Bestellung createBestellung(Bestellung bestellung, Kunde kunde,
			Locale locale) {
		LOGGER.warn("Geschenkverpackung leider noch nicht vorrätig...sorryyyy");
		
		return bs.createBestellung(bestellung, kunde, locale);
	}

}
