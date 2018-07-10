public class Cancion extends Contenedor {

	private int id;
	private String titulo;
	private double duracion;
	private String artista;
	private String album;
	private int anio;
	private String genero;
	private String comentario;

	//Constructor:

	public Cancion(int id, String titulo, double duracion, String artista, String album, int anio, String genero, String comentario) {

		super(titulo);
		this.id = id;
		this.titulo = titulo;
		this.duracion = duracion;
		this.artista = artista;
		this.album = album;
		this.anio = anio;
		this.genero = genero; 
		this.comentario = comentario;  
	}

	//Metodos:
	
	public int getId(){
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public double getDuracion() {
		return duracion;
	}
	public String getArtista() {
		return artista;
	}
	public String getAlbum() {
		return album;
	}
	public int getAnio() {
		return anio;
	}
	public String getGenero() {
		return genero;
	}
	public String getComentario() {
		return comentario;
	}

	public int getElementos() {
		return 1;
	}
	
	public void imprimir() {
		System.out.println("ID: " +id +". Titulo: "+titulo +". Artista/Interprete: "+artista +". Album: "+album +". Año: " +anio +". Duracion: " +duracion +" segundos.");
	}

	public void borrarElemento(Contenedor c) {
	}
}