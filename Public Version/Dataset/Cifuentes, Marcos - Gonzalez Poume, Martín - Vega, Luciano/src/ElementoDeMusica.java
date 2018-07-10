package trabajoEspecialProg2;

import java.util.Vector;

public abstract class ElementoDeMusica {

	public abstract int getDuracionTotal();
	
	public abstract int getCantidadPistas();
	
	public abstract String toString();
	
	public abstract Vector<Pista> buscarCondicion(Condicion c1);

	public abstract void eliminarPista(ElementoDeMusica e);
	
	
}
