package shop.Bestellverwaltung.service;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mail.Session;

import org.jboss.logging.Logger;

import shop.util.interceptor.Log;

/**
 * @author <a href="mailto:koju1020@HS-Karlsruhe.de">Julian Kohlhaas</a>  
 */

@ApplicationScoped
@Log
public class BestellungObserver implements Serializable {

	private static final long serialVersionUID = -5478102250135441607L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Inject
	private transient Session session;
	
	@Inject
	@AbsenderMail
	private String absenderMail;
	
	@Inject
	@AbsenderName
	private String absenderName;

}
