package shop.Rechnungsverwaltung.service;

import shop.util.AbstractShopException;

/**
 * @author <a href="mailto:hosi1015@HS-Karlsruhe.de">Simon Holzmayer</a>
 */
public abstract class RechnungServiceException extends AbstractShopException {
	private static final long serialVersionUID = -2849585609393128387L;

	public RechnungServiceException(String msg) {
		super(msg);
	}
	

	public RechnungServiceException(String msg, Throwable t) {
		super(msg, t);
	}
}
