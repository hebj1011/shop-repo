package shop.Artikelverwaltung.service;

import javax.enterprise.context.Dependent;

import shop.Artikelverwaltung.domain.AbstractArtikel;
import shop.Artikelverwaltung.domain.Ersatzteil;
import shop.Artikelverwaltung.domain.Fahrrad;
import shop.Artikelverwaltung.domain.Sicherheitszubehoer;
import shop.util.cdi.MockService;
import shop.util.interceptor.Log;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@MockService
@Dependent
@Log
public class ArtikelServiceMock extends ArtikelService {
	private static final long serialVersionUID = -2919310633845009282L;
	private final int fahval = 200;
	private final int ersval = 300;
	private final int sichval = 400;

	/**
	 * {inheritDoc}
	 */
	@Override
	public AbstractArtikel findArtikelById(Long id) {
		if (id == null) {
			return null;
		}
		
		final AbstractArtikel artikel;
		if (fahval < id && id < ersval)
			artikel = new Fahrrad();
		else if (ersval < id && id < sichval)
			artikel = new Ersatzteil();
		else
			artikel = new Sicherheitszubehoer();
		
		artikel.setId(id);
		artikel.setName("Name: " + id);
		
		return artikel;	
	}
}