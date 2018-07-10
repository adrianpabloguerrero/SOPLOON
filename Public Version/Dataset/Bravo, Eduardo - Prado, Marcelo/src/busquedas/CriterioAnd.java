package busquedas;

import base.Pista;

public class CriterioAnd implements Criterio {
	public Criterio criterio1;
	public Criterio criterio2;
	
	public CriterioAnd(Criterio c1, Criterio c2) {
		criterio1 = c1;
		criterio2 = c2;
	}
	
	public boolean cumple(Pista p) {
		return (criterio1.cumple(p) && criterio2.cumple(p));
	}

	
}
