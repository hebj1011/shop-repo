package shop.Rechnungsverwaltung.domain;

import java.util.List;


public class Rechnung {
	private List<String> artikel;
	/*TODO
		artikel muss Datentyp artikel annehmen, 
		allerdings muss dieser noch von Teammitglied erstellt werden.
	*/
	public Rechnung(List<String> artikel) {
		super();
		this.artikel = artikel;
	}
	
}
