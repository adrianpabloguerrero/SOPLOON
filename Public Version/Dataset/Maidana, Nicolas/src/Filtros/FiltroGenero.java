package Filtros;
import Musica.PistaDeAudio;

public class FiltroGenero extends FiltroSimpleString{
	
	public FiltroGenero(String genero){
		super(genero);
	}
	
	public String getAtributoPista(PistaDeAudio pista){
		return pista.getGenero();
	}
	

}
