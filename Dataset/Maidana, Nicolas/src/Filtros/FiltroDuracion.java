package Filtros;
import Criterio.Criterio;
import Musica.PistaDeAudio;

public class FiltroDuracion extends FiltroSimpleEntero {

	public FiltroDuracion(int atributo, Criterio c) {
		super(atributo, c);
	}

	public int getAtributoPista(PistaDeAudio pista) {
		return pista.getDuracion();
	}

}
