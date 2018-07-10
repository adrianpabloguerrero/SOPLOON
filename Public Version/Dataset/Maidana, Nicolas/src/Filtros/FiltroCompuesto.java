package Filtros;
import Musica.PistaDeAudio;

public abstract class FiltroCompuesto implements Filtro {

	protected Filtro f1;
	protected Filtro f2;

	public FiltroCompuesto(Filtro f1, Filtro f2) {
		this.f1 = f1;
		this.f2 = f2;
	}
	
	public boolean cumple (PistaDeAudio pista){
		return operar(f1.cumple(pista),f2.cumple(pista)); 
	}

	public abstract boolean operar(boolean f1, boolean f2);
	
}
