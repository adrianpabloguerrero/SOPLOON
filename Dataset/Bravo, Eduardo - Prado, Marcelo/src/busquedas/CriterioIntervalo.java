package busquedas;

import extraccion.Extractor;
import base.Pista;

public class CriterioIntervalo extends CriterioSimple{
	int forma;
	public static final int MAYOR = 1;
	public static final int IGUAL = 0;
	public static final int MENOR = -1;
	
	
	public CriterioIntervalo(Extractor ex, Object val, int f) {
		super(ex, val);
		forma = f;
	}

	public boolean cumple(Pista p) {
		return(((Comparable)extr.get(p)).compareTo((Comparable)valor) == forma);
	}


}
