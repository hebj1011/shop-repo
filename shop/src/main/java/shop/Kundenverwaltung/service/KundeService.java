package shop.Kundenverwaltung.service;

import static shop.util.Constants.LOADGRAPH;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.collect.ImmutableMap;

import shop.Bestellverwaltung.domain.Bestellposition;
import shop.Bestellverwaltung.domain.Bestellposition_;
import shop.Bestellverwaltung.domain.Bestellung;
import shop.Bestellverwaltung.domain.Bestellung_;
import shop.Kundenverwaltung.domain.Kunde;
import shop.Kundenverwaltung.domain.Kunde_;
import shop.Kundenverwaltung.domain.Adresse_;
import shop.Kundenverwaltung.domain.Wartungsvertrag;
import shop.util.interceptor.Log;


/**
 * @author <a href="mailto:hosi1015@HS-Karlsruhe.de">Simon Holzmayer</a>
 */
@Dependent
@Log
public class KundeService implements Serializable {
	private static final long serialVersionUID = -5520738420154763865L;
	
	public enum FetchType {
		NUR_KUNDE,
		MIT_BESTELLUNGEN,
		MIT_WARTUNGSVERTRAEGEN
	}
	
	public enum OrderType {
		KEINE,
		ID
	}
	
	@Inject
	private transient EntityManager em;

	/**
	 * Suche einen Kunden zu gegebener ID.
	 * @param id Die gegebene ID.
	 * @param fetch Angabe, welche Objekte aus der DB mitgeladen werden sollen, z.B. Bestellungen.
	 * @return Der gefundene Kunde.
	 * @exception ConstraintViolationException zu @NotNull, falls kein Kunde gefunden wurde
	 */
	@NotNull(message = "{kunde.notFound.id}")
	public Kunde findKundeById(Long id, FetchType fetch) {
		if (id == null) {
			return null;
		}
		
		Kunde kunde;
		EntityGraph<?> entityGraph;
		Map<String, Object> props;
		switch (fetch) {
			case NUR_KUNDE:
				kunde = em.find(Kunde.class, id);
				break;
			
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(Kunde.GRAPH_BESTELLUNGEN);
				props = ImmutableMap.of(LOADGRAPH, (Object) entityGraph);
				kunde = em.find(Kunde.class, id, props);
				break;
				
			case MIT_WARTUNGSVERTRAEGEN:
				entityGraph = em.getEntityGraph(Kunde.GRAPH_WARTUNGSVERTRAEGE);
				props = ImmutableMap.of(LOADGRAPH, (Object) entityGraph);
				kunde = em.find(Kunde.class, id, props);
				break;

			default:
				kunde = em.find(Kunde.class, id);
				break;
		}
		
		return kunde;
	}

	/**
	 * Suche nach IDs mit gleichem Praefix.
	 * @param idPrefix Der gemeinsame Praefix.
	 * @return Liste der passenden Praefixe.
	 */
	public List<Long> findIdsByPrefix(String idPrefix) {
		return em.createNamedQuery(Kunde.FIND_IDS_BY_PREFIX, Long.class)
				 .setParameter(Kunde.PARAM_KUNDE_ID_PREFIX, idPrefix + '%')
				 .getResultList();
	}
	
	/**
	 * Suche einen Kunden zu gegebener Email-Adresse.
	 * @param email Die gegebene Email-Adresse.
	 * @return Der gefundene Kunde.
	 * @exception ConstraintViolationException zu @NotNull, falls kein Kunde gefunden wurde
	 */
	@NotNull(message = "{kunde.notFound.email}")
	public Kunde findKundeByEmail(String email) {
		try {
			return em.createNamedQuery(Kunde.FIND_KUNDE_BY_EMAIL, Kunde.class)
					 .setParameter(Kunde.PARAM_KUNDE_EMAIL, email)
					 .getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Suche alle Kunden.
	 * @param fetch Angabe, welche Objekte aus der DB mitgeladen werden sollen, z.B. Bestellungen.
	 * @param order Sortierreihenfolge, z.B. noch ID
	 * @return Liste der Kunden
	 */
	public List<Kunde> findAllKunden(FetchType fetch, OrderType order) {
		final TypedQuery<Kunde> query = OrderType.ID.equals(order)
		                                        ? em.createNamedQuery(Kunde.FIND_KUNDEN_ORDER_BY_ID,
		                                        		              Kunde.class)
		                                        : em.createNamedQuery(Kunde.FIND_KUNDEN, Kunde.class);
		
		EntityGraph<?> entityGraph;
		switch (fetch) {
			case NUR_KUNDE:
				break;
			
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(Kunde.GRAPH_BESTELLUNGEN);
				query.setHint(LOADGRAPH, entityGraph);
				break;
				
			case MIT_WARTUNGSVERTRAEGEN:
				entityGraph = em.getEntityGraph(Kunde.GRAPH_WARTUNGSVERTRAEGE);
				query.setHint(LOADGRAPH, entityGraph);
				break;

			default:
				break;
		}

		return query.getResultList();
	}
	
	/**
	 * Suche alle Kunden mit gleichem Nachnamen
	 * @param nachname Der gemeinsame Nachname
	 * @param fetch Angabe, welche Objekte aus der DB mitgeladen werden sollen, z.B. Bestellungen.
	 * @return Liste der gefundenen Kunden
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{kunde.notFound.nachname}")
	public List<Kunde> findKundenByNachname(String nachname, FetchType fetch) {
		final TypedQuery<Kunde> query = em.createNamedQuery(Kunde.FIND_KUNDEN_BY_NACHNAME,
                                                                    Kunde.class)
                                                  .setParameter(Kunde.PARAM_KUNDE_NACHNAME, nachname);
		
		EntityGraph<?> entityGraph;
		switch (fetch) {
			case NUR_KUNDE:
				break;
				
			case MIT_BESTELLUNGEN:
				entityGraph = em.getEntityGraph(Kunde.GRAPH_BESTELLUNGEN);
				query.setHint(LOADGRAPH, entityGraph);
				break;
				
			case MIT_WARTUNGSVERTRAEGEN:
				entityGraph = em.getEntityGraph(Kunde.GRAPH_WARTUNGSVERTRAEGE);
				query.setHint(LOADGRAPH, entityGraph);
				break;
				
			default:
				break;
		}
		
		return query.getResultList();
	}

	/**
	 * Suche alle Nachnamen mit gleichem Praefix
	 * @param nachnamePrefix Der gemeinsame Praefix
	 * @return Liste der passenden Nachnamen
	 */
	public List<String> findNachnamenByPrefix(String nachnamePrefix) {
		return em.createNamedQuery(Kunde.FIND_NACHNAMEN_BY_PREFIX, String.class)
				 .setParameter(Kunde.PARAM_KUNDE_NACHNAME_PREFIX, nachnamePrefix + '%')
				 .getResultList();
	}
	
	/**
	 * Die Kunden mit gleicher Postleitzahl suchen.
	 * @param plz Die Postleitzahl
	 * @return Liste der passenden Kunden.
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{kunde.notFound.plz}")
	public List<Kunde> findKundenByPLZ(String plz) {
		return em.createNamedQuery(Kunde.FIND_KUNDEN_BY_PLZ, Kunde.class)
                 .setParameter(Kunde.PARAM_KUNDE_ADRESSE_PLZ, plz)
                 .getResultList();
	}

	/**
	 * Diejenigen Kunden suchen, die seit einem bestimmten Datum registriert sind. 
	 * @param seit Das zu vergleichende Datum
	 * @return Die Liste der passenden Kunden
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{kunde.notFound.seit}")
	public List<Kunde> findKundenBySeit(Date seit) {
		return em.createNamedQuery(Kunde.FIND_KUNDEN_BY_DATE, Kunde.class)
                 .setParameter(Kunde.PARAM_KUNDE_SEIT, seit)
                 .getResultList();
	}
	
	/**
	 * Alle Privat- und Firmenkunden suchen.
	 * @return Liste der Privat- und Firmenkunden.
	 */
	public List<Kunde> findPrivatkundenFirmenkunden() {
		return em.createNamedQuery(Kunde.FIND_PRIVATKUNDEN_FIRMENKUNDEN, Kunde.class)
                 .getResultList();
	}
	
	/**
	 * Kunden mit gleichem Nachnamen durch eine Criteria-Query suchen.
	 * @param nachname Der gemeinsame Nachname.
	 * @return Liste der passenden Kunden
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{kunde.notFound.nachname}")
	public List<Kunde> findKundenByNachnameCriteria(String nachname) {
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<Kunde> criteriaQuery = builder.createQuery(Kunde.class);
		final Root<Kunde> k = criteriaQuery.from(Kunde.class);

		final Path<String> nachnamePath = k.get(Kunde_.nachname);
		//final Path<String> nachnamePath = k.get("nachname");
		
		final Predicate pred = builder.equal(nachnamePath, nachname);
		criteriaQuery.where(pred);
		
		// Ausgabe des komponierten Query-Strings. Voraussetzung: das Modul "org.hibernate" ist aktiviert
		//LOGGER.tracef("", em.createQuery(criteriaQuery).unwrap(org.hibernate.Query.class).getQueryString());
		return em.createQuery(criteriaQuery).getResultList();
	}
	
	/**
	 * Die Kunden mit einer bestimmten Mindestbestellmenge suchen.
	 * @param minMenge Die Mindestbestellmenge
	 * @return Liste der passenden Kunden
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{kunde.notFound.minBestMenge}")
	public List<Kunde> findKundenMitMinBestMenge(short minMenge) {
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<Kunde> criteriaQuery  = builder.createQuery(Kunde.class);
		final Root<Kunde> k = criteriaQuery.from(Kunde.class);

		final Join<Kunde, Bestellung> b = k.join(Kunde_.bestellungen);
		final Join<Bestellung, Bestellposition> bp = b.join(Bestellung_.bestellpositionen);
		criteriaQuery.where(builder.gt(bp.<Short>get(Bestellposition_.anzahl), minMenge))
		             .distinct(true);
		
		return em.createQuery(criteriaQuery)
		         .getResultList();
	}

	/**
	 * Kunden zu den Suchkriterien suchen
	 * @param email Email-Adresse
	 * @param nachname Nachname
	 * @param plz Postleitzahl
	 * @üaram seit Datum seit
	 * @param minBestMenge Mindestbestellmenge
	 * @return Die gefundenen Kunden
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@NotNull(message = "{kunde.notFound.criteria}")
	public List<Kunde> findKundenByCriteria(String email, String nachname, String plz, Date seit,
			                                        Short minBestMenge) {
		// SELECT DISTINCT k
		// FROM   Kunde k
		// WHERE  email = ? AND nachname = ? AND k.adresse.plz = ? and seit = ?
		
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<Kunde> criteriaQuery  = builder.createQuery(Kunde.class);
		final Root<? extends Kunde> k = criteriaQuery.from(Kunde.class);
		
		Predicate pred = null;
		if (email != null) {
			final Path<String> emailPath = k.get(Kunde_.email);
			pred = builder.equal(emailPath, email);
		}
		if (nachname != null) {
			final Path<String> nachnamePath = k.get(Kunde_.nachname);
			final Predicate tmpPred = builder.equal(nachnamePath, nachname);
			pred = pred == null ? tmpPred : builder.and(pred, tmpPred);
		}
		if (plz != null) {
			final Path<String> plzPath = k.get(Kunde_.adresse)
                                          .get(Adresse_.plz);
			final Predicate tmpPred = builder.equal(plzPath, plz);
			pred = pred == null ? tmpPred : builder.and(pred, tmpPred);
		}
		if (seit != null) {
			final Path<Date> seitPath = k.get(Kunde_.seit);
			final Predicate tmpPred = builder.equal(seitPath, seit);
			pred = pred == null ? tmpPred : builder.and(pred, tmpPred);
		}
		if (minBestMenge != null) {
			final Path<Short> anzahlPath = k.join(Kunde_.bestellungen)
                                            .join(Bestellung_.bestellpositionen)
                                            .get(Bestellposition_.anzahl);
			final Predicate tmpPred = builder.gt(anzahlPath, minBestMenge);
			pred = pred == null ? tmpPred : builder.and(pred, tmpPred);
		}
		
		criteriaQuery.where(pred)
		             .distinct(true);
		return em.createQuery(criteriaQuery).getResultList();
	}
	
	/**
	 * Einen neuen Kunden in der DB anlegen.
	 * @param kunde Der anzulegende Kunde.
	 * @return Der neue Kunde einschliesslich generierter ID.
	 */
	public <T extends Kunde> T createKunde(T kunde) {
		if (kunde == null) {
			return kunde;
		}

		// Pruefung, ob die Email-Adresse schon existiert
		final Kunde tmp = findKundeByEmail(kunde.getEmail());   // Kein Aufruf als Business-Methode
		if (tmp != null) {
			throw new EmailExistsException(kunde.getEmail());
		}
		
		em.persist(kunde);
		return kunde;		
	}
	
	/**
	 * Einen vorhandenen Kunden aktualisieren
	 * @param kunde Der Kunde mit aktualisierten Attributwerten
	 * @return Der aktualisierte Kunde
	 */
	public <T extends Kunde> T updateKunde(T kunde) {
		if (kunde == null) {
			return null;
		}
		
		// kunde vom EntityManager trennen, weil anschliessend z.B. nach Id und Email gesucht wird
		em.detach(kunde);
		
		// Gibt es ein anderes Objekt mit gleicher Email-Adresse?
		final Kunde tmp = findKundeByEmail(kunde.getEmail());  // Kein Aufruf als Business-Methode
		if (tmp != null) {
			em.detach(tmp);
			if (tmp.getId().longValue() != kunde.getId().longValue()) {
				// anderes Objekt mit gleichem Attributwert fuer email
				throw new EmailExistsException(kunde.getEmail());
			}
		}

		em.merge(kunde);
		return kunde;
	}

	/**
	 * Einen Kunden aus der DB loeschen, falls er existiert.
	 * @param kunde Der zu loeschende Kunde.
	 */
	public void deleteKunde(Kunde kunde) {
		if (kunde == null) {
			return;
		}
		
		// Bestellungen laden, damit sie anschl. ueberprueft werden koennen
		kunde = findKundeById(kunde.getId(), FetchType.MIT_BESTELLUNGEN);  // Kein Aufruf als Business-Methode
		if (kunde == null) {
			return;
		}
		
		// Gibt es Bestellungen?
		if (!kunde.getBestellungen().isEmpty()) {
			throw new KundeDeleteBestellungException(kunde);
		}

		em.remove(kunde);
	}

	/**
	 * Wartungsvertrage zu einem Kunden suchen
	 * @param kundeId ID des Kunden
	 * @return Liste der Wartungsvertraege des Kunden
	 * @exception ConstraintViolationException zu @Size, falls die Liste leer ist
	 */
	@Size(min = 1, message = "{wartungsvertrag.notFound.kundeId}")
	public List<Wartungsvertrag> findWartungsvertraege(Long kundeId) {
		return em.createNamedQuery(Wartungsvertrag.FIND_WARTUNGSVERTRAEGE_BY_KUNDE_ID, Wartungsvertrag.class)
                 .setParameter(Wartungsvertrag.PARAM_KUNDE_ID, kundeId)
                 .getResultList();
	}
	
	/**
	 * Einen neuen Wartungsvertrag in der DB anlegen.
	 * @param wartungsvertrag Der neu anzulegende Wartungsvertrag
	 * @param kunde Der zugehoerige Kunde
	 * @return Der neu angelegte Wartungsvertrag
	 */
	public Wartungsvertrag createWartungsvertrag(Wartungsvertrag wartungsvertrag, Kunde kunde) {
		if (wartungsvertrag == null || kunde == null) {
			return null;
		}
		
		kunde = findKundeById(kunde.getId(), FetchType.NUR_KUNDE);
		wartungsvertrag.setKunde(kunde);
		kunde.addWartungsvertrag(wartungsvertrag);
		
		em.persist(wartungsvertrag);
		return wartungsvertrag;
	}
}
