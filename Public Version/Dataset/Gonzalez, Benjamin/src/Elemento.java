package Lista_de_Musica;
import java.util.Vector;

public abstract class Elemento {
	private String nombre;
	private Vector<String> comentarios;
	
	public Elemento(String nombre){
		this.nombre = nombre;
		comentarios = new Vector<String>();
	}
	public abstract int getTamaño();
	public abstract int getDuracion();
	public abstract String getTituloAlbum();
	public abstract Vector<Elemento> buscar(Condicion c);
	public abstract String getArtista();
	public abstract String getGenero();
	
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String nombre){
		this.nombre =nombre;
	} 
	public void agregarComentario(String s){
		comentarios.add(s);
	}
	public Vector<String> getComentarios(){
		return comentarios;
	}
	public void setComentarios(int i, String s){
		comentarios.set(i, s);
	}
	public String getElemento(int i){
		return null;
	}
	
	
	
}
