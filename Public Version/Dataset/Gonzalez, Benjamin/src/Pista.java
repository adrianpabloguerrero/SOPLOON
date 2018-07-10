package Lista_de_Musica;
import java.util.Vector;

public class Pista extends Elemento{
	
	private static int contador;
	private  int id;//preguntar si es unico o se pueden cambiar;	
	private int	 duracion;
	private String artista;
	private String tituloAlbum;
	private int a�o;
	private String genero;
	
	public Pista(String titulo, int duracion, String artista, int a�o, String genero, String tituloAlbum){
		super(titulo);
		this.duracion = duracion;
		this.artista = artista;
		this.a�o = a�o;
		this.genero = genero;
		this.id = contador;
		contador++;
		this.tituloAlbum = tituloAlbum;
	}
	public void agregarComentarios(String s){
		super.agregarComentario(s);
	}
	public Vector<String> getComentarios(){
		return super.getComentarios();	
	}
	public void setComentario(String s, int i){
		super.setComentarios(i,s);
	}
	@Override
	public int getTama�o(){
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getDuracion() {
		// TODO Auto-generated method stub
		return duracion;
	}
	

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public void setTituloAlbum(String tituloAlbum) {
		this.tituloAlbum = tituloAlbum;
	}

	public void setA�o(int a�o) {
		this.a�o = a�o;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getId() {
		return id;
	}

	public String getArtista() {
		return artista;
	}

	public String getTituloAlbum() {
		return tituloAlbum;
	}

	public int getA�o() {
		return a�o;
	}

	public String getGenero() {
		return genero;
	}

	 public String getNombre(){
		 return super.getNombre();
	 }
	 public void setNombre(String nombre){
		 super.setNombre(nombre);
	 }
	 public String getElemento(int i){ 
		 String etiqueta = "Id: "+getId()+", Titulo: "+getNombre()+", Duracion: "+getDuracion()+", Interprete: "+getArtista()+", Album: "+getTituloAlbum()+", A�o: "+getA�o()+", Genero: "+getGenero();
		 return etiqueta;
	 }
	@Override
	public Vector<Elemento> buscar(Condicion c) {
		Vector<Elemento> salida = new Vector<Elemento>();
		if(c.cumple(this))
			salida.add(this);
		return salida;
	}
	
	
}
