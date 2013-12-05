package shop.Rechnungsverwaltung.service;

/**
 * @author <a href="mailto:hosi1015@HS-Karlsruhe.de">Simon Holzmayer</a>
 */
public class EmailExistsException extends RechnungServiceException {
	private static final long serialVersionUID = 4867667611097919943L;
	private static final String MESSAGE_KEY = "rechnung.emailExists";
	private final String email;
	
	public EmailExistsException(String email) {
		super("Die Email-Adresse " + email + " existiert bereits");
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getMessageKey() {
		return MESSAGE_KEY;
	}
}
