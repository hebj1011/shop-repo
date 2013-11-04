package shop.util.rest;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">Bjoern Hetzel</a>
 */
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -866705588853138386L;

	public NotFoundException(String msg) {
		super(msg);
	}
	
	public NotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
}