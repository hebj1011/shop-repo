package shop.Rechnungsverwaltung.domain;

import java.util.Date;

class Rechnung {
	private int _nummer;
	private Date _datum;
	private double _betrag;
		
	public Rechnung(int rNr, Date rDatum, double rBetrag) {
		this._nummer = rNr;
		this._datum = rDatum;
		this._betrag = rBetrag;
	}

	public double mwst() {
		double mwst = _betrag * 0.19;
		return mwst;
	}

}