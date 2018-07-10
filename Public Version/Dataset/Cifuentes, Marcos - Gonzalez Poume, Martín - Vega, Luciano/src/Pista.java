package trabajoEspecialProg2;

import java.util.Vector;

public class Pista extends ElementoDeMusica {

	private int ID;
	private String Titulo;
	private int Duracion;
	private String Artista;
	private String Album;
	private int Anio;
	private String Genero;
	private String Comentarios;
	private final static int cantPista=1;

	public Pista (int _id, String _titulo, int _duracion, String _artista, String _Album, int _anio, String _genero, String _comentarios){
		
		 ID=_id;
		 Titulo=_titulo;
		 Duracion=_duracion;
		 Artista=_artista;
		 Album=_Album;
		 Anio=_anio;
		 Genero=_genero;
		 Comentarios=_comentarios;

	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public int getDuracion() {
		return Duracion;
	}

	public void setDuracion(int duracion) {
		Duracion = duracion;
	}

	public String getArtista() {
		return Artista;
	}

	public void setArtista(String artista) {
		Artista = artista;
	}

	public String getAlbum() {
		return Album;
	}

	public void setAlbum(String _Album) {
		Album = _Album;
	}

	public int getAnio() {
		return Anio;
	}

	public void setAnio(int anio) {
		Anio = anio;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}

	public String getComentarios() {
		return Comentarios;
	}

	public void setComentarios(String comentarios) {
		Comentarios = comentarios;
	}
	
	public int getDuracionTotal(){
		
		return getDuracion();
	}
	
	public int getCantidadPistas(){
		
		return cantPista;
	}
	
	public String toString () {
		
		return " | ID: "+ getID()+" | Titulo: "+getTitulo()+" | Artista: "+getArtista()+" | Album(Genero,Anio): "+getAlbum()+" ("+getGenero() + "," +getAnio()+ ")" +" | Duracion: "+getDuracion()+" segundos | " +"\n";
	}
	
	public Vector<Pista> buscarCondicion(Condicion c1) {
		Vector<Pista> resultado = new Vector<Pista>();

		if(c1.cumple(this)){
			resultado.add(this);
		}

		return resultado;
	}

	public void eliminarPista(ElementoDeMusica e){

	}
	
}