package shop.Artikelverwaltung.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.util.interceptor.Log;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">Bjoern Hetzel</a>
 */
@Dependent
@Log
public class ArtikelService implements Serializable {
	private static final long serialVersionUID = 3076865030092242363L;
	
	@Inject
	private transient EntityManager em;
	
	/**
	 * Suche nach verfuegbaren Artikeln.
	 * @return Liste der verfuegbaren Artikel.
	 */
	public List<AbstractArtikel> findVerfuegbareArtikel() {
		return em.createNamedQuery(AbstractArtikel.FIND_VERFUEGBARE_ARTIKEL, AbstractArtikel.class)
				 .getResultList();
	}

	
	/**
	 * Suche den Artikel zu gegebener ID.
	 * @param id ID des gesuchten Artikels.
	 * @return Der gefundene Artikel, null sonst.
	 * @exception ConstraintViolationException zu @NotNull, falls kein Artikel gefunden wurde
	 */
	@NotNull(message = "{artikel.notFound.id}")
	public AbstractArtikel findArtikelById(Long id) {
		return em.find(AbstractArtikel.class, id);
	}
	
	/**
	 * Suche die Artikel zu gegebenen IDs. 
	 * @param ids Liste der IDs
	 * @return Liste der gefundenen Artikel
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{artikel.notFound.ids}")
	public List<AbstractArtikel> findArtikelByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return Collections.emptyList();
		}
		
		/*
		 * SELECT a
		 * FROM   Artikel a
		 * WHERE  a.id = ? OR a.id = ? OR ...
		 */
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<AbstractArtikel> criteriaQuery = builder.createQuery(AbstractArtikel.class);
		final Root<AbstractArtikel> a = criteriaQuery.from(AbstractArtikel.class);

		final Path<Long> idPath = a.get("id");
		//final Path<String> idPath = a.get(Artikel_.id);   // Metamodel-Klassen funktionieren nicht mit Eclipse
		
		Predicate pred = null;
		if (ids.size() == 1) {
			// Genau 1 id: kein OR notwendig
			pred = builder.equal(idPath, ids.get(0));
		}
		else {
			// Mind. 2x id, durch OR verknuepft
			final Predicate[] equals = new Predicate[ids.size()];
			int i = 0;
			for (Long id : ids) {
				equals[i++] = builder.equal(idPath, id);
			}
			
			pred = builder.or(equals);
		}
		
		criteriaQuery.where(pred);
		
		return em.createQuery(criteriaQuery)
		         .getResultList();
	}

	
	@NotNull(message = "{artikel.notFound.name}")
	public AbstractArtikel findArtikelByName(String name) {
		try {
			return em.createNamedQuery(AbstractArtikel.FIND_ARTIKEL_BY_NAME, AbstractArtikel.class)
					 .setParameter(AbstractArtikel.PARAM_NAME, name)
					 .getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * Suche Artikel, die preiswerter als ein bestimmter Preis sind
	 * @param preis Maximaler Preis
	 * @return Liste der Artikel mit einem geringeren Preis als die angegebene Obergrenze
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{kunde.notFound.maxPreis}")
	public List<AbstractArtikel> findArtikelByMaxPreis(BigDecimal einzelpreis) {
		return em.createNamedQuery(AbstractArtikel.FIND_ARTIKEL_MAX_PREIS, AbstractArtikel.class)
				 .setParameter(AbstractArtikel.PARAM_PREIS, einzelpreis)
				 .getResultList();
	}
	
	public <T extends AbstractArtikel> T createArtikel(T artikel) {
		if (artikel == null) {
			return artikel;
		}
	
		em.persist(artikel);
		return artikel;		
	}
	
	public <T extends AbstractArtikel> T updateArtikel(T artikel) {
		if (artikel == null) {
			return null;
		}
		
		em.detach(artikel);
		
		final AbstractArtikel tmp = findArtikelByName(artikel.getName());  // Kein Aufruf als Business-Methode
		if (tmp != null) {
			em.detach(tmp);
			if (tmp.getId().longValue() != artikel.getId().longValue()) {
				// anderes Objekt mit gleichem Attributwert fuer email
				throw new NameExistsException(artikel.getName());
			}
		}


		em.merge(artikel);
		return artikel;
	}
	
	public void deleteArtikel(AbstractArtikel artikel) {
		if (artikel == null) {
			return;
		}

		em.remove(artikel);
	}
}
