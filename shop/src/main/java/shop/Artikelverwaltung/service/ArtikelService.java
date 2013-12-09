package shop.Artikelverwaltung.service;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
//import java.math.BigDecimal;
//import java.util.Collections;
//import java.util.List;

import shop.util.Mock;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Path;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.jboss.logging.Logger;

//import com.google.common.base.Strings;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.util.interceptor.Log;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */

@Dependent
@Log
public class ArtikelService implements Serializable {
	private static final long serialVersionUID = 3076865030092242363L;
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
//	@Inject
//	private transient EntityManager em;
	
	@PostConstruct
	private void postConstruct() {
		LOGGER.debugf("CDI-faehiges Bean %s wurde erzeugt", this);
	}
	
	@PreDestroy
	private void preDestroy() {
		LOGGER.debugf("CDI-faehiges Bean %s wird geloescht", this);
	}
	
//	/**
//	 * Suche nach verfuegbaren Artikeln.
//	 * @return Liste der verfuegbaren Artikel.
//	 */
//	public List<AbstractArtikel> findVerfuegbareArtikel() {
//		return em.createNamedQuery(AbstractArtikel.FIND_VERFUEGBARE_ARTIKEL, AbstractArtikel.class)
//				 .getResultList();
//	}
//
//	
//	/**
//	 * Suche den Artikel zu gegebener ID.
//	 * @param id ID des gesuchten Artikels.
//	 * @return Der gefundene Artikel, null sonst.
//	 */
//	public AbstractArtikel findArtikelById(Long id) {
//		return em.find(AbstractArtikel.class, id);
//	}
//	
//	/**
//	 * Suche die Artikel zu gegebenen IDs. 
//	 * @param ids Liste der IDs
//	 * @return Liste der gefundenen Artikel
//	 */
//	public List<AbstractArtikel> findArtikelByIds(List<Long> ids) {
//		if (ids == null || ids.isEmpty()) {
//			return Collections.emptyList();
//		}
//		
//		/*
//		 * SELECT a
//		 * FROM   Artikel a
//		 * WHERE  a.id = ? OR a.id = ? OR ...
//		 */
//		final CriteriaBuilder builder = em.getCriteriaBuilder();
//		final CriteriaQuery<AbstractArtikel> criteriaQuery = builder.createQuery(AbstractArtikel.class);
//		final Root<AbstractArtikel> a = criteriaQuery.from(AbstractArtikel.class);
//
//		final Path<Long> idPath = a.get("id");
//		//final Path<String> idPath = a.get(Artikel_.id);   // Metamodel-Klassen funktionieren nicht mit Eclipse
//		
//		Predicate pred = null;
//		if (ids.size() == 1) {
//			// Genau 1 id: kein OR notwendig
//			pred = builder.equal(idPath, ids.get(0));
//		}
//		else {
//			// Mind. 2x id, durch OR verknuepft
//			final Predicate[] equals = new Predicate[ids.size()];
//			int i = 0;
//			for (Long id : ids) {
//				equals[i++] = builder.equal(idPath, id);
//			}
//			
//			pred = builder.or(equals);
//		}
//		
//		criteriaQuery.where(pred);
//		
//		return em.createQuery(criteriaQuery)
//		         .getResultList();
//	}
//
//	
//	/**
//	 * Suche die Artikel mit gleicher Bezeichnung
//	 * @param bezeichnung Gemeinsame Bezeichnung der gesuchten Artikel
//	 * @return Liste der gefundenen Artikel
//	 */
//	public List<AbstractArtikel> findArtikelByName(String name) {
//		if (Strings.isNullOrEmpty(name)) {
//			return findVerfuegbareArtikel();
//		}
//		
//		return em.createNamedQuery(AbstractArtikel.FIND_ARTIKEL_BY_NAME, AbstractArtikel.class)
//				 .setParameter(AbstractArtikel.PARAM_NAME, "%" + name + "%")
//				 .getResultList();
//	}
//	
//	/**
//	 * Suche Artikel, die preiswerter als ein bestimmter Preis sind
//	 * @param preis Maximaler Preis
//	 * @return Liste der Artikel mit einem geringeren Preis als die angegebene Obergrenze
//	 */
//	public List<AbstractArtikel> findArtikelByMaxPreis(BigDecimal preis) {
//		return em.createNamedQuery(AbstractArtikel.FIND_ARTIKEL_MAX_PREIS, AbstractArtikel.class)
//				 .setParameter(AbstractArtikel.PARAM_PREIS, preis)
//				 .getResultList();
//	}
	
	@NotNull(message = "{artikel.notFound.id}")
	public AbstractArtikel findArtikelById(Long id) {
		if (id == null) {
			return null;
		}
		// TODO Datenbanzugriffsschicht statt Mock
		return Mock.findArtikelById(id);
	}
	
//	public List<AbstractArtikel> findAllArtikel() {
//		// TODO Datenbanzugriffsschicht statt Mock
//		return Mock.findAllArtikel();
//	}
	
	
	public <T extends AbstractArtikel> T createArtikel(T artikel) {
		if (artikel == null) {
			return artikel;
		}
		// TODO Datenbanzugriffsschicht statt Mock
		artikel = Mock.createArtikel(artikel);

		return artikel;
	}
	
	public <T extends AbstractArtikel> T updateArtikel(T artikel) {
		if (artikel == null) {
			return null;
		}

		// TODO Datenbanzugriffsschicht statt Mock
		Mock.updateArtikel(artikel);
		
		return artikel;
	}

	public void deleteArtikel(Long artikelId) {
		AbstractArtikel artikel = findArtikelById(artikelId);  // Kein Aufruf als Business-Methode
		if (artikel == null) {
			return;
		}
		
		// TODO Datenbanzugriffsschicht statt Mock
		Mock.deleteArtikel(artikel);
	}
}
