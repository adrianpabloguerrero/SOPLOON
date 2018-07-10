package Filtros;
import Criterio.Criterio;
import Musica.PistaDeAudio;

public abstract class FiltroSimpleEntero extends FiltroSimple {

	private Criterio criterio;

	public FiltroSimpleEntero(int atributo, Criterio c) {
		super(atributo);
		this.criterio = c;
	}

	public boolean cumple(PistaDeAudio pista) {
		if (criterio.comparar(getAtributoPista(pista),(int) getAtributo()))
			return true;
		return false;

	}

	public abstract int getAtributoPista(PistaDeAudio pista);

}
