package practicoEspecial;

import java.util.Vector;

public abstract class ElementoMusica {

	String nombre;

	public ElementoMusica(String _nombre) {

		nombre = _nombre;
	}

	public abstract void addElement(ElementoMusica e);

	public abstract void eliminar(ElementoMusica e);

	public abstract int getDuracion();

	public abstract Vector buscar(Condicion c);

	public String getNombre() {
		return nombre;
	}

}
