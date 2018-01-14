package extraccion;

import base.Pista;

public class Nombre extends Extractor{

	public Object get(Pista p) {
		return p.getNombre();
	}

}
