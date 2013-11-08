package shop.Bestellverwaltung.rest;

import static shop.util.Constants.SELF_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

//import java.lang.invoke.MethodHandles;
import java.net.URI;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

//import shop.Artikelverwaltung.domain.AbstractArtikel;
//import shop.Artikelverwaltung.rest.ArtikelResource;
//import shop.Bestellverwaltung.domain.Bestellposition;
import shop.Bestellverwaltung.domain.Bestellung ;
import shop.Kundenverwaltung.domain.Kunde;
import shop.Kundenverwaltung.rest.KundeResource;
import shop.Bestellverwaltung.service.Mock;
import shop.util.rest.UriHelper;
import shop.util.rest.NotFoundException;

@Path("/bestellungen")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes

public class BestellungResource {
	
	//private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
//	private static final String NOT_FOUND_ID = "bestellung.notFound.id";
//	private static final String NOT_FOUND_KUNDE_ID = "kunde.notFound.id";
//	private static final String NOT_FOUND_ID_ARTIKEL = "artikel.notFound.id";
	
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private UriHelper uriHelper;
	

//	@Inject
//	private shop.Artikelverwaltung.service.Mock as;
	
//	@Inject
//	private ArtikelResource artikelResource;
	
	@Inject
	private KundeResource kundeResource;
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findBestellungById(@PathParam("id") Long id) {
		final Bestellung bestellung = Mock.findBestellungById(id);
		if (bestellung == null) {
			throw new NotFoundException("Keine Bestellung mit der ID " + id + " gefunden.");
		}
		
		setStructuralLinks(bestellung, uriInfo);
		
		// Link-Header setzen
		final Response response = Response.ok(bestellung)
                                          .links(getTransitionalLinks(bestellung, uriInfo))
                                          .build();
		
		return response;
	}
	
	public void setStructuralLinks(Bestellung bestellung, UriInfo uriInfo) {
		// URI fuer Kunde setzen
		final Kunde kunde = bestellung.getKunde();
		if (kunde != null) {
			final URI kundeUri = kundeResource.getUriKunde(bestellung.getKunde(), uriInfo);
			bestellung.setKundeUri(kundeUri);
			// ------ auskommentiert ----
		}
	}
	
	private Link[] getTransitionalLinks(Bestellung bestellung, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriBestellung(bestellung, uriInfo))
                              .rel(SELF_LINK)
                              .build();
		return new Link[] { self };
	}
	
	public URI getUriBestellung(Bestellung bestellung, UriInfo uriInfo) {
		return uriHelper.getUri(BestellungResource.class, "findBestellungById", bestellung.getId(), uriInfo);
	}
	
	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createBestellung(@Valid Bestellung bestellung) {
		
		bestellung = Mock.createBestellung(bestellung);

		final URI bestellungUri = getUriBestellung(bestellung, uriInfo);
		return Response.created(bestellungUri).build();
	}
}


