package busquedas;

import extraccion.Extractor;
import base.Pista;

public class CriterioParcial extends CriterioSimple{

	public CriterioParcial(Extractor ex, Object val) {
		super(ex, val);
	}

	public boolean cumple(Pista p) {
		return ((""+extr.get(p)).toLowerCase().contains((""+valor).toLowerCase()));
	}


}
