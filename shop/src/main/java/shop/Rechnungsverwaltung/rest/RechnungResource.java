package shop.Rechnungsverwaltung.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import shop.Rechnungsverwaltung.service.Mock;
import shop.Bestellverwaltung.rest.BestellungResource;
import shop.Rechnungsverwaltung.domain.Rechnung;
import shop.util.rest.NotFoundException;
import shop.util.rest.UriHelper;

/**
 * @author <a href="mailto:hosi1015@HS-Karlsruhe.de">Simon Holzmayer</a>
 */
@Path("/rechnungen")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
public class RechnungResource {
	private static final String SELF_LINK = null;

	@Context
	private UriInfo uriInfo;
	
	@Inject
	private UriHelper uriHelper;
	
//	@Inject
//	private RechnungResource rechnungResource;
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findRechnungByID(@PathParam("id") Long id) {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		final Rechnung rechnung = Mock.findRechnungByID(id);
		if (rechnung == null) {
			throw new NotFoundException("Keine Rechnung mit der ID " + id + " gefunden.");
		}
		
//		setStructuralLinks(rechnung, uriInfo);
		
		// Link-Header setzen
		final Response response = Response.ok(rechnung)
                                          .links(getTransitionalLinks(rechnung, uriInfo))
                                          .build();
		
		return response;
	}
	

	
//	public void setStructuralLinks(Rechnung rechng, UriInfo uriInfo) {
//		// URI fuer Kunde setzen
//		final Rechnung rechnung = rechng.getRechnung();
//		if (rechnung != null) {
//			final URI rechnungUri = rechnungResource.getUriRechnung(rechng.getRechnung(), uriInfo);
//			rechng.setRechnungUri(rechnungUri);
//		}
//	}
	
	private Link[] getTransitionalLinks(Rechnung rechnung, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriRechnung(rechnung, uriInfo))
                              .rel(SELF_LINK)
                              .build();
		return new Link[] { self };
	}
	
	public URI getUriRechnung(Rechnung rechnung, UriInfo uriInfo) {
		return uriHelper.getUri(BestellungResource.class, "findBestellungById", rechnung.getId(), uriInfo);
	}
	

	
}
