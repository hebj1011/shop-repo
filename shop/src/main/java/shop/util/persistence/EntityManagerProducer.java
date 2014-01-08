package shop.util.persistence;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="mailto:koju1020@HS-Karlsruhe.de">Julian Kohlhaas</a>  
 */

@Dependent
public class EntityManagerProducer {
	@PersistenceContext
	@Produces
	private EntityManager em;
}
