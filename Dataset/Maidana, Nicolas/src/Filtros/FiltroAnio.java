package Filtros;
import Criterio.Criterio;
import Musica.PistaDeAudio;

public class FiltroAnio extends FiltroSimpleEntero {

	public FiltroAnio(int atributo, Criterio c) {
		super(atributo, c);
	}

	public int getAtributoPista(PistaDeAudio pista) {
		return pista.getAnio();
	}

}
