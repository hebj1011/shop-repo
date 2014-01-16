package shop.Artikelverwaltung.service;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">BjoernHetzel</a>
 */
public class NameExistsException extends AbstractArtikelServiceException {
	private static final long serialVersionUID = 4867667611097919943L;
	
	private static final String MESSAGE_KEY = "kunde.emailExists";
	private final String name;
	
	public NameExistsException(String name) {
		super("Der Name " + name + " existiert bereits");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getMessageKey() {
		return MESSAGE_KEY;
	}
}
