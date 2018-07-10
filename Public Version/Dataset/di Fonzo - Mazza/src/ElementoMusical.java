package trabajoPracticoEspecial;

import java.util.Vector;

public abstract class ElementoMusical {
	
	public abstract int getDuracion();
	public abstract int getCantPistas();
	public abstract String imprimirPistas();
	public abstract Vector <Pista> buscarPistas(Condicion c);
	public abstract void eliminarElemento(ElementoMusical e);
		
		
	
	
}
