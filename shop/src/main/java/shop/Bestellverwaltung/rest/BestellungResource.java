package shop.Bestellverwaltung.rest;

import static shop.Bestellverwaltung.service.BestellungService.FetchType.NUR_BESTELLUNG;
import static shop.util.Constants.SELF_LINK;
import static shop.util.Constants.ADD_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;


//import java.lang.invoke.MethodHandles;
import java.net.URI;
//import java.util.Locale;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Artikelverwaltung.rest.ArtikelResource;
import shop.Artikelverwaltung.service.ArtikelService;
import shop.Bestellverwaltung.domain.Bestellposition;
import shop.Bestellverwaltung.domain.Bestellung;
import shop.Kundenverwaltung.domain.Kunde;
import shop.Kundenverwaltung.rest.KundeResource;
import shop.Bestellverwaltung.service.BestellungService;
//import shop.Bestellverwaltung.service.Mock;
import shop.util.interceptor.Log;
import shop.util.rest.UriHelper;


/**
 * @author <a href="mailto:koju1020@HS-Karlsruhe.de">Julian Kohlhaas</a>  
 */

@Path("/bestellungen")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
@RequestScoped
@Transactional
@Log
public class BestellungResource {
	

	@Context
	private UriInfo uriInfo;
	
	@Inject
	private UriHelper uriHelper;	
	
	@Inject
	private BestellungService bs;
	
	@Inject
	private ArtikelService as;
	
	@Inject
	private ArtikelResource artikelResource;
	
	@Inject
	private KundeResource kundeResource;
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findBestellungById(@PathParam("id") Long id) {
		final Bestellung bestellung = bs.findBestellungById(id, NUR_BESTELLUNG);

		setStructuralLinks(bestellung, uriInfo);
		
		// Link-Header setzen
		final Response response = Response.ok(bestellung)
                                          .links(getTransitionalLinks(bestellung, uriInfo))
                                          .build();
		
		return response;
	}
	
	@GET
	@Path("{id:[1-9][0-9]*}/kunde")
	public Response findKundeByBestellungId(@PathParam("id") Long id) {
		final Kunde kunde = bs.findKundeById(id);
		kundeResource.setStructuralLinks(kunde, uriInfo);
		
		// Link Header setzen
		final Response response = Response.ok(kunde)
                                          .links(kundeResource.getTransitionalLinks(kunde, uriInfo))
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
		
		final Collection<Bestellposition> bestellpositionen = bestellung.getBestellpositionen();
		if (bestellpositionen != null && !bestellpositionen.isEmpty()) {
			for (Bestellposition bp : bestellpositionen) {
				final URI artikelUri = artikelResource.getUriArtikel(bp.getArtikel(), uriInfo);
				bp.setArtikelUri(artikelUri);
			}
		}
	}
	
	public Link[] getTransitionalLinks(Bestellung bestellung, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriBestellung(bestellung, uriInfo))
                              .rel(SELF_LINK)
                              .build();
		final Link list = Link.fromUri(uriHelper.getUri(BestellungResource.class, uriInfo))
                .rel(ADD_LINK)
                .build();
		final Link add = Link
				.fromUri(uriHelper.getUri(BestellungResource.class, uriInfo))
				.rel(ADD_LINK).build();
		return new Link[] {
				self, list, add
		};
	}
	
	public URI getUriBestellung(Bestellung bestellung, UriInfo uriInfo) {
		return uriHelper.getUri(BestellungResource.class, "findBestellungById", bestellung.getId(), uriInfo);
	}
	
	
//	@POST
//	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
//	@Produces
//	public Response createBestellung(@Valid Bestellung bestellung) {
//		
//		bestellung = bs.createBestellung(bestellung);
//
//		final URI bestellungUri = getUriBestellung(bestellung, uriInfo);
//		return Response.created(bestellungUri).build();
//	}
	
	@POST
	@Consumes({ APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createBestellung(@Valid Bestellung bestellung) {
		// TODO eingeloggter Kunde wird durch die URI im Attribut "kundeUri" emuliert
		final String kundeUriStr = bestellung.getKundeUri().toString();
		int startPos = kundeUriStr.lastIndexOf('/') + 1;
		final String kundeIdStr = kundeUriStr.substring(startPos);
		Long kundeId = null;
		try {
			kundeId = Long.valueOf(kundeIdStr);
		}
		catch (NumberFormatException e) {
			kundeIdInvalid();
		}
		
		// IDs der (persistenten) Artikel ermitteln
		final Collection<Bestellposition> bestellpositionen = bestellung.getBestellpositionen();
		final List<Long> artikelIds = new ArrayList<>(bestellpositionen.size());
		for (Bestellposition bp : bestellpositionen) {
			final URI artikelUri = bp.getArtikelUri();
			if (artikelUri == null) {
				continue;
			}
			final String artikelUriStr = artikelUri.toString();
			startPos = artikelUriStr.lastIndexOf('/') + 1;
			final String artikelIdStr = artikelUriStr.substring(startPos);
			Long artikelId = null;
			try {
				artikelId = Long.valueOf(artikelIdStr);
			}
			catch (NumberFormatException e) {
				// Ungueltige Artikel-ID: wird nicht beruecksichtigt
				continue;
			}
			artikelIds.add(artikelId);
		}
		
		if (artikelIds.isEmpty()) {
			// keine einzige Artikel-ID als gueltige Zahl
			artikelIdsInvalid();
		}

		final Collection<AbstractArtikel> gefundeneArtikel = as.findArtikelByIds(artikelIds);
		
		// Bestellpositionen haben URLs fuer persistente Artikel.
		// Diese persistenten Artikel wurden in einem DB-Zugriff ermittelt (s.o.)
		// Fuer jede Bestellposition wird der Artikel passend zur Artikel-URL bzw. Artikel-ID gesetzt.
		// Bestellpositionen mit nicht-gefundene Artikel werden eliminiert.
		int i = 0;
		// Keine Hashtabelle bei Bestellpositionen !!
//		final Set<Bestellposition> neueBestellpositionen = new HashSet<>();
		final List<Bestellposition> neueBestellpositionen = new ArrayList<>();
		for (Bestellposition bp : bestellpositionen) {
			// Artikel-ID der aktuellen Bestellposition (s.o.):
			// artikelIds haben gleiche Reihenfolge wie bestellpositionen
			final long artikelId = artikelIds.get(i++);
			
			// Wurde der Artikel beim DB-Zugriff gefunden?
			for (AbstractArtikel artikel : gefundeneArtikel) {
				if (artikel.getId().longValue() == artikelId) {
					// Der Artikel wurde gefunden
					bp.setArtikel(artikel);
					neueBestellpositionen.add(bp);
					break;					
				}
			}
		}
		bestellung.setBestellpositionen(neueBestellpositionen);
		
		bestellung = bs.createBestellung(bestellung, kundeId);

		final URI bestellungUri = getUriBestellung(bestellung, uriInfo);
		return Response.created(bestellungUri).build();
	}
	
	/**
	 * @NotNull verletzen, um die entsprechende Meldung zu verursachen, weil keine einzige Artikel-ID
	 *          eine gueltige Zahl war.
	 * @return null
	 */
	@NotNull(message = "{bestellung.artikelIds.invalid}")
	public List<Long> artikelIdsInvalid() {
		return null;
	}
	
	/**
	 * @NotNull verletzen, um die entsprechende Meldung zu verursachen, weil die Kunde-Id ungueltig ist.
	 * @return null
	 */
	@NotNull(message = "{bestellung.kunde.id.invalid}")
	public Long kundeIdInvalid() {
		return null;
	}
}


