package extraccion;

import base.Pista;

public class Genero extends Extractor{

	public Object get(Pista p) {
		return p.getGenero();
	}

}
