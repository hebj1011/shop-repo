package shop.Kundenverwaltung.rest;

import static shop.util.Constants.ADD_LINK;
import static shop.util.Constants.FIRST_LINK;
import static shop.util.Constants.KEINE_ID;
import static shop.util.Constants.LAST_LINK;
import static shop.util.Constants.LIST_LINK;
import static shop.util.Constants.REMOVE_LINK;
import static shop.util.Constants.SELF_LINK;
import static shop.util.Constants.UPDATE_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.validator.constraints.Email;
import org.jboss.logging.Logger;

import com.google.common.base.Strings;

import shop.Bestellverwaltung.domain.Bestellung;
import shop.Bestellverwaltung.rest.BestellungResource;
import shop.Bestellverwaltung.service.BestellungService;
import shop.Kundenverwaltung.domain.Kunde;
import shop.Kundenverwaltung.domain.Adresse;
import shop.Kundenverwaltung.service.KundeService;
import shop.Kundenverwaltung.service.KundeService.FetchType;
import shop.Kundenverwaltung.service.KundeService.OrderType;
import shop.util.interceptor.Log;
import shop.util.rest.UriHelper;


/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@Path("/kunden")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
@RequestScoped
@Transactional
@Log
public class KundeResource {
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	private static final String VERSION = "1.0";
	
	public static final String KUNDEN_ID_PATH_PARAM = "kundeId";
	public static final String KUNDEN_NACHNAME_QUERY_PARAM = "nachname";
	public static final String KUNDEN_VORNAME_QUERY_PARAM = "vorname";
	public static final String KUNDEN_PLZ_QUERY_PARAM = "plz";
	public static final String KUNDEN_EMAIL_QUERY_PARAM = "email";
	
    @Context
    private UriInfo uriInfo;
    
	@Inject
	private KundeService ks;
	
	@Inject
	private BestellungService bs;
	
	@Inject
	private BestellungResource bestellungResource;
	
	@Inject
	private UriHelper uriHelper;
	
	@GET
	@Produces({ TEXT_PLAIN, APPLICATION_JSON + ";qs=0.75" })
	@Path("version")
	public String getVersion() {
		return VERSION;
	}
	
	/**
	 * Mit der URL /kunden/{id} einen Kunden ermitteln
	 * @param id ID des Kunden
	 * @return Objekt mit Kundendaten, falls die ID vorhanden ist
	 */
	@GET
	@Path("{" + KUNDEN_ID_PATH_PARAM + ":[1-9][0-9]*}")
	public Response findKundeById(@PathParam(KUNDEN_ID_PATH_PARAM) Long id) {
		final Kunde kunde = ks.findKundeById(id, FetchType.NUR_KUNDE);
		setStructuralLinks(kunde, uriInfo);
	
		final Response response = Response.ok(kunde)
                                          .links(getTransitionalLinks(kunde, uriInfo))
                                          .build();

		return response;
	}
	
	public void setStructuralLinks(Kunde kunde, UriInfo uriInfo) {
		kunde.setBestellungenUri(getUriBestellungen(kunde, uriInfo));
	}
	
	private URI getUriBestellungen(Kunde kunde, UriInfo uriInfo) {
		return uriHelper.getUri(KundeResource.class, "findBestellungenByKundeId", kunde.getId(), uriInfo);
	}
	
	public Link[] getTransitionalLinks(Kunde kunde, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriKunde(kunde, uriInfo))
	                          .rel(SELF_LINK)
	                          .build();

		final Link list = Link.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
		                      .rel(LIST_LINK)
		                      .build();

		final Link add = Link.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
                             .rel(ADD_LINK)
                             .build();
		
		final Link update = Link.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
			                    .rel(UPDATE_LINK)
			                    .build();
		
		final Link remove = Link.fromUri(uriHelper.getUri(KundeResource.class, "deleteKunde", kunde.getId(), uriInfo))
		                        .rel(REMOVE_LINK)
		                        .build();

		return new Link[] {self, list, add, update, remove };
	}
	
	public URI getUriKunde(Kunde kunde, UriInfo uriInfo) {
		return uriHelper.getUri(KundeResource.class, "findKundeById", kunde.getId(), uriInfo);
	}


	/**
	 * Mit der URL /kunden werden alle Kunden ermittelt oder
	 * mit kundenverwaltung/kunden?nachname=... diejenigen mit einem bestimmten Nachnamen.
	 * @param nachname Nachname der gesuchten Kunden
	 * @return Collection mit den gefundenen Kundendaten
	 */
	@GET
	public Response findKunden(@QueryParam(KUNDEN_NACHNAME_QUERY_PARAM)
                               @Pattern(regexp = Kunde.NACHNAME_PATTERN, message = "{kunde.nachname.pattern}")
	                           String nachname,
	                           @QueryParam(KUNDEN_VORNAME_QUERY_PARAM)
                               @Pattern(regexp = Kunde.VORNAME_PATTERN, message = "{kunde.vorname.pattern}")
	                           String vorname,
                               @QueryParam(KUNDEN_PLZ_QUERY_PARAM)
                               @Pattern(regexp = "\\d{5}", message = "{adresse.plz}")
                               String plz,
                               @QueryParam(KUNDEN_EMAIL_QUERY_PARAM)
                               @Email(message = "{kunde.email}")
                               String email) {
		List<Kunde> kunden = null;
		Kunde kunde = null;
		// TODO Mehrere Query-Parameter koennen angegeben sein
		if (!Strings.isNullOrEmpty(nachname)) {
			kunden = ks.findKundenByNachname(nachname, FetchType.NUR_KUNDE);
		}
		else if (!Strings.isNullOrEmpty(vorname)) {
			kunden = ks.findKundenByVorname(vorname, FetchType.NUR_KUNDE);
		}
		else if (!Strings.isNullOrEmpty(plz)) {
			kunden = ks.findKundenByPLZ(plz);
		}
		else if (!Strings.isNullOrEmpty(email)) {
			kunde = ks.findKundeByEmail(email);
		}
		else {
			kunden = ks.findAllKunden(FetchType.NUR_KUNDE, OrderType.ID);
		}
		
		Object entity = null;
		Link[] links = null;
		if (kunden != null) {
			for (Kunde k : kunden) {
				setStructuralLinks(k, uriInfo);
			}
			// FIXME JDK 8 hat Lambda-Ausdruecke
			//kunden.parallelStream()
			//      .forEach(k -> setStructuralLinks(k, uriInfo));
			entity = new GenericEntity<List<Kunde>>(kunden) { };
			links = getTransitionalLinksKunden(kunden, uriInfo);
		}
		else if (kunde != null) {
			entity = kunde;
			links = getTransitionalLinks(kunde, uriInfo);
		}
		
		return Response.ok(entity)
		               .links(links)
		               .build();
	}
	
	private Link[] getTransitionalLinksKunden(List<Kunde> kunden, UriInfo uriInfo) {
		if (kunden == null || kunden.isEmpty()) {
			return null;
		}
		
		final Link first = Link.fromUri(getUriKunde(kunden.get(0), uriInfo))
	                           .rel(FIRST_LINK)
	                           .build();
		final int lastPos = kunden.size() - 1;
		final Link last = Link.fromUri(getUriKunde(kunden.get(lastPos), uriInfo))
                              .rel(LAST_LINK)
                              .build();
		
		return new Link[] {first, last };
	}
	
	/**
	 * IDs mit gleichem Praefix suchen
	 * @param idPrefix Der gemeinsame Praefix
	 * @return Collection der IDs mit gleichem Praefix
	 */
	@GET
	@Path("/prefix/id/{id:[1-9][0-9]*}")
	public Collection<Long> findIdsByPrefix(@PathParam("id") String idPrefix) {
		final Collection<Long> ids = ks.findIdsByPrefix(idPrefix);
		return ids;
	}
	
	/**
	 * Nachnamen mit gleichem Praefix suchen
	 * @param nachnamePrefix Der gemeinsame Praefix
	 * @return Collection der Nachnamen mit gleichem Praefix
	 */
	@GET
	@Path("/prefix/nachname/{nachname}")
	@Produces({ APPLICATION_JSON, TEXT_PLAIN + ";qs=0.75" })
	public Collection<String> findNachnamenByPrefix(@PathParam("nachname") String nachnamePrefix) {
		final Collection<String> nachnamen = ks.findNachnamenByPrefix(nachnamePrefix);
		return nachnamen;
	}
	
	
	/**
	 * Vornamen mit gleichem Praefix suchen
	 * @param vornamePrefix Der gemeinsame Praefix
	 * @return Collection der Nachnamen mit gleichem Praefix
	 */
	@GET
	@Path("/prefix/vorname/{vorname}")
	@Produces({ APPLICATION_JSON, TEXT_PLAIN + ";qs=0.75" })
	public Collection<String> findVornamenByPrefix(@PathParam("vorname") String vornamePrefix) {
		final Collection<String> vornamen = ks.findVornamenByPrefix(vornamePrefix);
		return vornamen;
	}

	
	/**
	 * Mit der URL /kunden/{id}/bestellungen die Bestellungen zu eine Kunden ermitteln
	 * @param kundeId ID des Kunden
	 * @return Objekt mit Bestellungsdaten, falls die ID vorhanden ist
	 */
	@GET
	@Path("{id:[1-9][0-9]*}/bestellungen")
	public Response findBestellungenByKundeId(@PathParam("id") Long kundeId) {
		final Kunde kunde = ks.findKundeById(kundeId, FetchType.MIT_BESTELLUNGEN);
		
		final List<Bestellung> bestellungen = bs.findBestellungenByKunde(kunde);
		// URIs innerhalb der gefundenen Bestellungen anpassen
		if (bestellungen != null) {
			for (Bestellung bestellung : bestellungen) {
				bestellungResource.setStructuralLinks(bestellung, uriInfo);
			}
			// FIXME JDK 8 hat Lambda-Ausdruecke
			//bestellungen.parallelStream()
			//            .forEach(b -> bestellungResource.setStructuralLinks(b, uriInfo));
		}
		
		final Response response = Response.ok(new GenericEntity<List<Bestellung>>(bestellungen) { })
                                          .links(getTransitionalLinksBestellungen(bestellungen, kunde, uriInfo))
                                          .build();
		return response;
	}
	
	private Link[] getTransitionalLinksBestellungen(List<Bestellung> bestellungen, Kunde kunde,
			                                        UriInfo uriInfo) {
		if (bestellungen == null || bestellungen.isEmpty()) {
			return new Link[0];
		}
		
		final Link self = Link.fromUri(getUriBestellungen(kunde, uriInfo))
                              .rel(SELF_LINK)
                              .build();
		
		final Link first = Link.fromUri(bestellungResource.getUriBestellung(bestellungen.get(0), uriInfo))
	                           .rel(FIRST_LINK)
	                           .build();
		
		final int lastPos = bestellungen.size() - 1;
		final Link last = Link.fromUri(bestellungResource.getUriBestellung(bestellungen.get(lastPos), uriInfo))
                              .rel(LAST_LINK)
                              .build();
		
		return new Link[] {self, first, last };
	}
	

	/**
	 * Bestellung-IDs zu einem Kunden suchen
	 * @param kundeId ID des Kunden
	 * @return Liste der Bestellung-IDs
	 */
	@GET
	@Path("{id:[1-9][0-9]*}/bestellungenIds")
	@Produces({ APPLICATION_JSON, TEXT_PLAIN + ";qs=0.75", APPLICATION_XML + ";qs=0.5" })
	public Response findBestellungenIdsByKundeId(@PathParam("id") Long kundeId) {
		final Kunde kunde = ks.findKundeById(kundeId, FetchType.MIT_BESTELLUNGEN);

		final Collection<Bestellung> bestellungen = bs.findBestellungenByKunde(kunde);		
		final int anzahl = bestellungen.size();
		final Collection<Long> bestellungenIds = new ArrayList<>(anzahl);
		for (Bestellung bestellung : bestellungen) {
			bestellungenIds.add(bestellung.getId());
		}
		// FIXME JDK 8 hat Lambda-Ausdruecke
		//bestellungen.parallelStream()
		//            .map(Bestellung::getId)
		//            .forEach(id -> bestellungenIds.add(id));
		
		return Response.ok(new GenericEntity<Collection<Long>>(bestellungenIds) { })
				       .build();
	}
	
	/**
	 * Mit der URL /kunden/{id}/wartungsvertraege die Wartungsvertraege ermitteln
	 * zu einem bestimmten Kunden ermitteln
	 * @param id ID des Kunden
	 * @return Wartungsvertraege, falls die ID vorhanden ist
	 */
	@GET
	@Path("{id:[1-9][0-9]*}/wartungsvertraege")
	public Response findWartungsvertraegeByKundeId(@PathParam("id") Long id) {
		// Diese Methode ist bewusst NICHT implementiert, um zu zeigen,
		// wie man Methodensignaturen an der Schnittstelle fuer andere
		// Teammitglieder schon mal bereitstellt, indem einfach ein "Internal
		// Server Error (500)" produziert wird.
		// Die Kolleg/inn/en koennen nun weiterarbeiten, waehrend man selbst
		// gerade keine Zeit hat, weil andere Aufgaben Vorrang haben.
		
		// TODO findWartungsvertraegeByKundeId noch nicht implementiert
		return Response.status(INTERNAL_SERVER_ERROR)
				       .entity("findWartungsvertraegeByKundeId: NOT YET IMPLEMENTED")
				       .type(TEXT_PLAIN)
				       .build();
	}
	

	/**
	 * Mit der URL /kunden einen Privatkunden per POST anlegen.
	 * @param kunde neuer Kunde
	 * @return Response-Objekt mit URL des neuen Privatkunden
	 */
	@POST
	@Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createKunde(@Valid Kunde kunde) {
		kunde.setId(KEINE_ID);
		
		final Adresse adresse = kunde.getAdresse();
		if (adresse != null) {
			adresse.setKunde(kunde);
		}
		
		kunde = ks.createKunde(kunde);
		LOGGER.tracef("Kunde: %s", kunde);
		
		return Response.created(getUriKunde(kunde, uriInfo))
				       .build();
	}
	
	
	/**
	 * Mit der URL /kunden einen Kunden per PUT aktualisieren
	 * @param kunde zu aktualisierende Daten des Kunden
	 */
	@PUT
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public void updateKunde(@Valid Kunde kunde) {
		// Vorhandenen Kunden ermitteln
		final Kunde origKunde = ks.findKundeById(kunde.getId(), FetchType.NUR_KUNDE);
		LOGGER.tracef("Kunde vorher: %s", origKunde);
	
		// Daten des vorhandenen Kunden ueberschreiben
		origKunde.setValues(kunde);
		LOGGER.tracef("Kunde nachher: %s", origKunde);
		
		// Update durchfuehren
		ks.updateKunde(origKunde);
	}
	
	
	/**
	 * Mit der URL /kunden{id} einen Kunden per DELETE l&ouml;schen
	 * @param kundeId des zu l&ouml;schenden Kunden
	 */
	@Path("{id:[0-9]+}")
	@DELETE
	@Produces
	public void deleteKunde(@PathParam("id") Long kundeId) {
		final Kunde kunde = ks.findKundeById(kundeId, FetchType.NUR_KUNDE);
		ks.deleteKunde(kunde);
	}
}
