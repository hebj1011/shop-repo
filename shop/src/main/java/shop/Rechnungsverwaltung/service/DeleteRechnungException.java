package shop.Rechnungsverwaltung.service;

import shop.Rechnungsverwaltung.domain.Rechnung;


/**
 * Exception, die ausgel&ouml;st wird, wenn ein Kunde gel&ouml;scht werden soll, aber mindestens eine Bestellung hat
 * @author <a href="mailto:hosi1015@HS-Karlsruhe.de">Simon Holzmayer</a>
 */
public class DeleteRechnungException extends RechnungServiceException {
	private static final long serialVersionUID = 2237194289969083093L;
	
	private static final String MESSAGE_KEY = "kunde.deleteMitBestellung";
	private final Long kundeId;
	
	public DeleteRechnungException(Rechnung rechnung) {
		super("Kunde mit ID=" + rechnung.getId() + " kann nicht geloescht werden.");
		this.kundeId = rechnung.getId();
	}

	public Long getKundeId() {
		return kundeId;
	}

	@Override
	public String getMessageKey() {
		return MESSAGE_KEY;
	}
}
