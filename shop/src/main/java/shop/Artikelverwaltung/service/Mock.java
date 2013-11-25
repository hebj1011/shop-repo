//package shop.Artikelverwaltung.service;
//
//import static javax.interceptor.Interceptor.Priority.APPLICATION;
//
//import javax.annotation.Priority;
//
//import shop.Artikelverwaltung.domain.Artikel;
//import shop.util.cdi.MockService;
//import shop.util.interceptor.Log;
//
///**
// * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
// */
//@MockService
//@Priority(APPLICATION + 100)
//@Log
//public class ArtikelServiceMock extends ArtikelService {
//	private static final long serialVersionUID = -2919310633845009282L;
//
//	/**
//	 * {inheritDoc}
//	 */
//	@Override
//	public Artikel findArtikelById(Long id) {
//		final Artikel artikel = new Artikel();
//		artikel.setId(id);
//		artikel.setBezeichnung("Bezeichnung" + id);
//		return artikel;
//	}
//}
