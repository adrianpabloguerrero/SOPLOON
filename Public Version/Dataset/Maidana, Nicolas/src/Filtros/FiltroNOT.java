package Filtros;
import Musica.PistaDeAudio;

public class FiltroNOT implements Filtro{
	
	private Filtro f;
	
	public FiltroNOT(Filtro f) {
		this.f = f;
	}
	
	public boolean cumple(PistaDeAudio pista){
		return ! f.cumple(pista);
	}

}
