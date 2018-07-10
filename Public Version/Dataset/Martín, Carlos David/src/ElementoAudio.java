import java.util.Vector;

public abstract class ElementoAudio {
	String titulo;

	public void setTitulo (String _titulo) {
		titulo = _titulo;
	}

	public String getTitulo () {
		return titulo;
	}
	
	public abstract int cantPistas ();
	public abstract int getDuracion();
	public abstract Vector<ElementoAudio> buscar (Condicion cond);
}