package clases;

import java.util.Vector;

public abstract class PistaAbs {
	
	public PistaAbs(){
		}

	public abstract Object getValorAtributo(String nombre);
	public abstract Integer CantElementos();
	public abstract void setAtributo(String nombre, Object valor);
	public abstract Vector<PistaAbs> getElementos();
	
}
