package extraccion;

import base.Pista;

public class Album extends Extractor{

	public Object get(Pista p) {
		return p.getAlbum();
	}
}
