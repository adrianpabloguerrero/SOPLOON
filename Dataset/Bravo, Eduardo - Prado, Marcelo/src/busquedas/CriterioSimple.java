package busquedas;

import extraccion.Extractor;
import base.Pista;

public abstract class CriterioSimple implements Criterio {
	
	Extractor extr;
	Object valor;
	
	public CriterioSimple(Extractor ex, Object val) {
		extr = ex;
		valor = val;
	}
	
	public abstract boolean cumple(Pista p);
	
}
