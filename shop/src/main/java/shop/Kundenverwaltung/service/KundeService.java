package shop.Kundenverwaltung.service;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.jboss.logging.Logger;

import shop.Kundenverwaltung.domain.Kunde;
import shop.util.interceptor.Log;
import shop.util.Mock;

@Log
public class KundeService implements Serializable {
	private static final long serialVersionUID = 3188789767052580247L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@PostConstruct
	private void postConstruct() {
		LOGGER.debugf("CDI-faehiges Bean %s wurde erzeugt", this);
	}
	
	@PreDestroy
	private void preDestroy() {
		LOGGER.debugf("CDI-faehiges Bean %s wird geloescht", this);
	}

	public Kunde findKundeById(Long id) {
		if (id == null) {
			return null;
		}
		// TODO Datenbanzugriffsschicht statt Mock
		final Kunde kunde = Mock.findKundeById(id);
		return kunde;
	}
	
	public List<Kunde> findAllKunden() {
		// TODO Datenbanzugriffsschicht statt Mock
		final List<Kunde> kunden = Mock.findAllKunden();
		return kunden;
	}
	
	/**
	 */
	public List<Kunde> findKundenByNachname(String nachname) {
		// TODO Datenbanzugriffsschicht statt Mock
		List<Kunde> kunden = Mock.findKundenByNachname(nachname);
		return kunden;
	}

	public <T extends Kunde> T createKunde(T kunde) {
		if (kunde == null) {
			return kunde;
		}

		// Pruefung, ob die Email-Adresse schon existiert
		// TODO Datenbanzugriffsschicht statt Mock
		//TODO Mock
		if (Mock.findKundeByEmail(kunde.getEmail()) != null) {
			throw new EmailExistsException(kunde.getEmail());
		}

		//kunde = Mock.createKunde(kunde);

	return kunde;
	}
//TODO Fehler beheben
	public <T extends Kunde> T updateKunde(T kunde) {
		if (kunde == null) {
			return null;
		}

		// Pruefung, ob die Email-Adresse schon existiert
		//TODO Fehler beheben
		final Kunde vorhandenerKunde = Mock.findKundeByEmail(kunde.getEmail());

		// Gibt es die Email-Adresse bei einem anderen, bereits vorhandenen Kunden?
		if (vorhandenerKunde.getId().longValue() != kunde.getId().longValue()) {
			throw new EmailExistsException(kunde.getEmail());
		}
		
		// TODO Datenbanzugriffsschicht statt Mock
		Mock.updateKunde(kunde);
		
		return kunde;
	}

	public void deleteKunde(Long kundeId) {
		final Kunde kunde = findKundeById(kundeId);
		if (kunde == null) {
			return;
		}

		// Gibt es Bestellungen?
		if (!kunde.getBestellungen().isEmpty()) {
			throw new KundeDeleteBestellungException(kunde);
		}
		
		// TODO Datenbanzugriffsschicht statt Mock
		//TODO Mock konfigurieren
		Mock.deleteKunde(kunde);
	}
}
