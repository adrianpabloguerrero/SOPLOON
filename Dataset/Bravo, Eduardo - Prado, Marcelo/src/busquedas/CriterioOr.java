package busquedas;

import base.Pista;

public class CriterioOr implements Criterio{
	Criterio criterio1;
	Criterio criterio2;
	
	public CriterioOr(Criterio c1, Criterio c2) {
		criterio1 = c1;
		criterio2 = c2;
	}
	
	public boolean cumple(Pista p) {
		return (criterio1.cumple(p) || criterio2.cumple(p));
	}
	
}
