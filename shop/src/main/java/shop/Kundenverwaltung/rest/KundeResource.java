package shop.Kundenverwaltung.rest;

import static shop.util.Constants.ADD_LINK;
import static shop.util.Constants.FIRST_LINK;
import static shop.util.Constants.LAST_LINK;
import static shop.util.Constants.LIST_LINK;
import static shop.util.Constants.REMOVE_LINK;
import static shop.util.Constants.SELF_LINK;
import static shop.util.Constants.UPDATE_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
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

import shop.Bestellverwaltung.domain.Bestellung;
import shop.Bestellverwaltung.rest.BestellungResource;
import shop.Bestellverwaltung.service.BestellungService;
import shop.Kundenverwaltung.domain.Kunde;
import shop.Kundenverwaltung.service.KundeService;
import shop.util.interceptor.Log;
import shop.util.rest.NotFoundException;
import shop.util.rest.UriHelper;

@Path("/kunden")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
@Log
public class KundeResource {
	public static final String KUNDEN_ID_PATH_PARAM = "kundeId";
	public static final String KUNDEN_NACHNAME_QUERY_PARAM = "nachname";
	public static final String KUNDEN_PLZ_QUERY_PARAM = "plz";
	
	private static final String NOT_FOUND_ID = "kunde.notFound.id";
	private static final String NOT_FOUND_NACHNAME = "kunde.notFound.nachname";
	private static final String NOT_FOUND_ALL = "kunde.notFound.all";

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
	@Produces({ TEXT_PLAIN, APPLICATION_JSON })
	@Path("version")
	public String getVersion() {
		return "1.0";
	}
	//TODO NotFoundException Fehler beheben
	@GET
	@Path("{" + KUNDEN_ID_PATH_PARAM + ":[1-9][0-9]*}")
	public Response findKundeById(@PathParam(KUNDEN_ID_PATH_PARAM) Long id) {
		final Kunde kunde = ks.findKundeById(id);
		if (kunde == null) {
			throw new NotFoundException(NOT_FOUND_ID, id);
		}
		
		setStructuralLinks(kunde, uriInfo);

		return Response.ok(kunde)
			           .links(getTransitionalLinks(kunde, uriInfo))
			           .build();
	}
	
	public void setStructuralLinks(Kunde kunde, UriInfo uriInfo) {
		// URI fuer Bestellungen setzen
		final URI uri = getUriBestellungen(kunde, uriInfo);
		kunde.setBestellungenUri(uri);
	}
	
	private URI getUriBestellungen(Kunde kunde, UriInfo uriInfo) {
		return uriHelper.getUri(KundeResource.class, "findBestellungenByKundeId", kunde.getId(), uriInfo);
	}
	// TODO Fehler beheben
//	private Link[] getTransitionalLinks(Kunde kunde, UriInfo uriInfo) {
//		final Link self = Link.fromUri(getUriKunde(kunde, uriInfo))
//	                          .rel(SELF_LINK)
//	                          .build();
//
//		final Link list = Link.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
//		                      .rel(LIST_LINK)
//		                      .build();
//
//		final Link add = Link.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
//		                     .rel(ADD_LINK)
//		                     .build();
//		
//		final Link update = Link.fromUri(uriHelper.getUri(KundeResource.class, uriInfo))
//			                    .rel(UPDATE_LINK)
//			                    .build();
//		
//		final Link remove = Link.fromUri(uriHelper.getUri(KundeResource.class, "deleteKunde", kunde.getId(), uriInfo))
//		                        .rel(REMOVE_LINK)
//		                        .build();
//
//		return new Link[] { self, list, add, update, remove };
//	}

	
	public URI getUriKunde(Kunde kunde, UriInfo uriInfo) {
		return uriHelper.getUri(KundeResource.class, "findKundeById", kunde.getId(), uriInfo);
	}
	
	@GET
	public Response findKunden(@QueryParam(KUNDEN_NACHNAME_QUERY_PARAM)
   	                           @Pattern(regexp = Kunde.NACHNAME_PATTERN, message = "{kunde.nachname.pattern}")
							   String nachname,
							   @QueryParam(KUNDEN_PLZ_QUERY_PARAM)
   	                           @Pattern(regexp = "\\d{5}", message = "{adresse.plz}")
							   String plz) {
		List<? extends Kunde> kunden = null;
		if (nachname != null) {
			kunden = ks.findKundenByNachname(nachname);
			//TODO Fehler beheben
//			if (kunden.isEmpty()) {
//				throw new NotFoundException(NOT_FOUND_NACHNAME, nachname);
//			}
		}
		else if (plz != null) {
			// TODO Beispiel fuer ein TODO bei fehlender Implementierung
			throw new RuntimeException("Suche nach PLZ noch nicht implementiert");
		}
		else {
			kunden = ks.findAllKunden();
			if (kunden.isEmpty()) {
				throw new NotFoundException(NOT_FOUND_ALL);
			}
		}
		
		for (Kunde k : kunden) {
			setStructuralLinks(k, uriInfo);
		}
		
		return Response.ok(new GenericEntity<List<? extends Kunde>>(kunden){})
                       .links(getTransitionalLinksKunden(kunden, uriInfo))
                       .build();
	}
	
	private Link[] getTransitionalLinksKunden(List<? extends Kunde> kunden, UriInfo uriInfo) {
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
		
		return new Link[] { first, last };
	}
	
	@GET
	@Path("{id:[1-9][0-9]*}/bestellungen")
	public Response findBestellungenByKundeId(@PathParam("id") Long kundeId) {
		final Kunde kunde = ks.findKundeById(kundeId);
		//TODO Fehler beheben
//		if (kunde == null) {
//			throw new NotFoundException(NOT_FOUND_ID, kundeId);
//		}
		
		final List<Bestellung> bestellungen = bs.findBestellungenByKunde(kunde);
		// URIs innerhalb der gefundenen Bestellungen anpassen
		if (bestellungen != null) {
			for (Bestellung bestellung : bestellungen) {
				bestellungResource.setStructuralLinks(bestellung, uriInfo);
			}
		}
		
		return Response.ok(new GenericEntity<List<Bestellung>>(bestellungen){})
                       .links(getTransitionalLinksBestellungen(bestellungen, kunde, uriInfo))
                       .build();
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
		
		return new Link[] { self, first, last };
	}
	
	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createKunde(@Valid Kunde kunde) {
		// Rueckwaertsverweis von Adresse zu Kunde setzen
		kunde.getAdresse().setKunde(kunde);
		
		kunde = ks.createKunde(kunde);
		
		return Response.created(getUriKunde(kunde, uriInfo))
			           .build();
	}
	// TODO Fehler beheben
//	@PUT
//	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
//	@Produces
//	public void updateKunde(@Valid Kunde kunde) {
//		ks.updateKunde(kunde);
//	}
	
	@DELETE
	@Path("{id:[1-9][0-9]*}")
	@Produces
	public void deleteKunde(@PathParam("id") Long kundeId) {
		ks.deleteKunde(kundeId);
	}
}
