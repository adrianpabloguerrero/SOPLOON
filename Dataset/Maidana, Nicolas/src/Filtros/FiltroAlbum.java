package Filtros;
import Musica.PistaDeAudio;

public class FiltroAlbum extends FiltroSimpleString{
	
	public FiltroAlbum(String album){
		super(album);
	}
	
	public String getAtributoPista(PistaDeAudio pista){
		return pista.getAlbum();
	}

}
