import java.util.Vector;

public abstract class PLFE { 

	public abstract int getDuracion();
										
	public abstract int cantPistas();
	
	public abstract Vector<PLFE> buscar(Condicion c);
		
}
