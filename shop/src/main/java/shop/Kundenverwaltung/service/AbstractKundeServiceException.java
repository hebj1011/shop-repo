package shop.Kundenverwaltung.service;

import shop.util.AbstractShopException;

/**
 * @author <a href="mailto:lade1011@HS-Karlsruhe.de">Denis Langer</a>
 */
public abstract class AbstractKundeServiceException extends AbstractShopException {
	private static final long serialVersionUID = -2849585609393128387L;

	public AbstractKundeServiceException(String msg) {
		super(msg);
	}
	
	public AbstractKundeServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}
