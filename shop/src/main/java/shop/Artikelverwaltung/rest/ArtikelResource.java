package shop.Artikelverwaltung.rest;

import static shop.util.Constants.ADD_LINK;
import static shop.util.Constants.FIRST_LINK;
import static shop.util.Constants.LAST_LINK;
import static shop.util.Constants.REMOVE_LINK;
import static shop.util.Constants.SELF_LINK;
import static shop.util.Constants.UPDATE_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.List;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
//import javax.ws.rs.DefaultValue;
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









import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Artikelverwaltung.service.ArtikelService;
import shop.Artikelverwaltung.service.Mock;
import shop.util.interceptor.Log;
import shop.util.rest.UriHelper;
import shop.util.rest.NotFoundException;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">Bjoern Hetzel</a>
 */


@Path("/artikel")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.75" })
@Consumes
@Transactional
@Log
public class ArtikelResource {
	
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	private static final String NOT_FOUND_ARTIKELNUMMER = "artikel.notFound.artikelnummer";
	
	public static final String ARTIKEL_ID_PATH_PARAM = "artikelId";
	public static final String ARTIKEL_NAME_QUERY_PARAM = "name";
	
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private ArtikelService as;
	
	@Inject
	private UriHelper uriHelper;
	
	@GET
	@Produces({ TEXT_PLAIN, APPLICATION_JSON })
	@Path("version")
	public String getVersion() {
		return "1.0";
	}
	
	
	@GET
	@Path("{" + ARTIKEL_ID_PATH_PARAM + ":[1-9][0-9]*}")
	public Response findArtikelByID(@PathParam(ARTIKEL_ID_PATH_PARAM) Long artikelnummer) {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
				final AbstractArtikel artikel = Mock.findArtikelByID(artikelnummer);
				if (artikel == null) {
					throw new NotFoundException("Kein Artikel mit der ID " + artikelnummer + " gefunden.");
				}
				
				
				return Response.ok(artikel)
		                       .links(getTransitionalLinks(artikel, uriInfo))
		                       .build();
	}
	
	

	
	
	public Link[] getTransitionalLinks(AbstractArtikel artikel, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriArtikel(artikel, uriInfo))
	                          .rel(SELF_LINK)
	                          .build();
		
		final Link add = Link.fromUri(uriHelper.getUri(ArtikelResource.class, uriInfo))
                             .rel(ADD_LINK)
                             .build();

		final Link update = Link.fromUri(uriHelper.getUri(ArtikelResource.class, uriInfo))
                                .rel(UPDATE_LINK)
                                .build();

		final Link remove = Link.fromUri(uriHelper.getUri
						(ArtikelResource.class, "deleteArtikel", artikel.getArtikelnummer(), uriInfo))
                                .rel(REMOVE_LINK)
                                .build();
		
		return new Link[] {self, add, update, remove };
	}

	
	public URI getUriArtikel(AbstractArtikel artikel, UriInfo uriInfo) {
		return uriHelper.getUri(ArtikelResource.class, "findArtikelByID", artikel.getArtikelnummer(), uriInfo);
	}
	
	
	@GET
	public Response findArtikelByName(@QueryParam(ARTIKEL_NAME_QUERY_PARAM) String name) {
		List<? extends AbstractArtikel> artikel = null;
		if (name != null) {
			artikel = Mock.findArtikelByName(name);
			if (artikel.isEmpty()) {
				throw new NotFoundException("Kein Artikel mit Name " + name + " gefunden.");
			}
		}
		else {
			artikel = Mock.findAllArtikel();
			if (artikel.isEmpty()) {
				throw new NotFoundException("Keine Artikel vorhanden.");
			}
		}
		
		
		return Response.ok(new GenericEntity<List<? extends AbstractArtikel>>(artikel) { })
                       .links(getTransitionalLinksArtikel(artikel, uriInfo))
                       .build();
	}
	
	private Link[] getTransitionalLinksArtikel(List<? extends AbstractArtikel> artikel, UriInfo uriInfo) {
		if (artikel == null || artikel.isEmpty()) {
			return null;
		}
		
		final Link first = Link.fromUri(getUriArtikel(artikel.get(0), uriInfo))
	                           .rel(FIRST_LINK)
	                           .build();
		final int lastPos = artikel.size() - 1;
		final Link last = Link.fromUri(getUriArtikel(artikel.get(lastPos), uriInfo))
                              .rel(LAST_LINK)
                              .build();
		
		return new Link[] {first, last };
	}

	@POST
	@Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createArtikel(AbstractArtikel artikel) {
		artikel = Mock.createArtikel(artikel);
		return Response.created(getUriArtikel(artikel, uriInfo))
			           .build();
	}
	
	
	@PUT
	@Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public void updateArtikel(AbstractArtikel artikel) {
		Mock.updateArtikel(artikel);
	}
	
	@DELETE
	@Path("{id:[1-9][0-9]*}")
	@Produces
	public void deleteArtikel(@PathParam(ARTIKEL_ID_PATH_PARAM) Long artikelnummer) {
		Mock.deleteArtikel(artikelnummer);
	}
	
}

