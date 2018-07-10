package Filtros;
import Musica.PistaDeAudio;

public class FiltroInterprete extends FiltroSimpleString{
	
	
	public FiltroInterprete(String interprete){
		super(interprete);
	}
	
	public String getAtributoPista(PistaDeAudio pista){
		return pista.getInterprete();
	}

}
