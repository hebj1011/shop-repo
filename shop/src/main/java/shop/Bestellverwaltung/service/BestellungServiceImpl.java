package shop.Bestellverwaltung.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import shop.Bestellverwaltung.domain.Bestellung;
import shop.Kundenverwaltung.domain.Kunde;
import shop.util.Mock;
//import shop.Bestellverwaltung.service.Mock;
import shop.util.interceptor.Log;

/**
 * @author <a href="mailto:koju1020@HS-Karlsruhe.de">Julian Kohlhaas</a>  
 */
@Dependent
@Log
public class BestellungServiceImpl implements BestellungService, Serializable {

	private static final long serialVersionUID = -1849298236445138031L;
	
	@Inject
	@NeueBestellung
	private transient Event<Bestellung> event;

	@Override
	@NotNull(message = "{bestellung.notFound.id}")
	public Bestellung findBestellungById(Long id) {

		return Mock.findBestellungById(id);
	}

	@Override
	@Size(min =1, message = "{bestellung.notFound.kunde}")
	public List<Bestellung> findBestellungenByKunde(Kunde kunde) {

		return Mock.findBestellungenByKunde(kunde);
	}

	@Override
	public Bestellung createBestellung(Bestellung bestellung, Kunde kunde,
			Locale locale) {

		bestellung = Mock.createBestellung(bestellung, kunde);
		event.fire(bestellung);
		
		return bestellung;
	}
}
