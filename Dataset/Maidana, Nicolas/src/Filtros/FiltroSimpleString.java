package Filtros;
import Musica.PistaDeAudio;

public abstract class FiltroSimpleString extends FiltroSimple{
	
	public FiltroSimpleString(String atributo){
		super(atributo);
	}
	
	public boolean cumple(PistaDeAudio pista){
		if (getAtributoPista(pista).toUpperCase().contains(((String) getAtributo()).toUpperCase()))
			return true;
		return false;
	}
	
	public abstract String getAtributoPista(PistaDeAudio pista);

}
