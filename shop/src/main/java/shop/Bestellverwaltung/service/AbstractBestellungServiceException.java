package shop.Bestellverwaltung.service;

import shop.util.AbstractShopException;

/**
 * @author <a href="mailto:koju1020@HS-Karlsruhe.de">Julian Kohlhaas</a>  
 */

public abstract class AbstractBestellungServiceException extends AbstractShopException {

	private static final long serialVersionUID = -2980151275917486252L;
	
	public AbstractBestellungServiceException(String msg) {
		super(msg);
	}
	
	public AbstractBestellungServiceException(String msg, Throwable t) {
		super(msg, t);
	}

}
