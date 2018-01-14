package Filtros;
import Musica.PistaDeAudio;

public class FiltroTitulo extends FiltroSimpleString{
	
	
	public FiltroTitulo(String titulo){
		super(titulo);
	}
	
	public String getAtributoPista(PistaDeAudio pista){
		return pista.getTitulo();
	}

}
