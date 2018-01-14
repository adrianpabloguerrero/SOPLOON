package practicoEspecial;

import java.util.Vector;

public class Pista extends ElementoMusica {
	private int id;
	private int duracion;
	private String artista;
	private String album;
	private int anio;
	private String genero;
	private String comentarios;

	public Pista(int _id, String _nombre, int _duracion, String _artista, String _album, int _anio, String _genero) {
		super(_nombre);
		id = _id;
		duracion = _duracion;
		artista = _artista;
		album = _album;
		anio = _anio;
		genero = _genero;

	}

	public int getId() {
		return id;
	}

	public void setId(int _id) {
		this.id = _id;
	}

	public String getTitulo() {
		return nombre;
	}

	public void setTitulo(String _nombre) {
		this.nombre = _nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int _duracion) {
		this.duracion = _duracion;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String _album) {
		this.album = _album;
	}

	public int getAnio() {
		return anio;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String _genero) {
		this.genero = _genero;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String _comentarios) {
		this.comentarios = _comentarios;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String toString() {
		String datos = "ID " + this.getId() + " - Titulo " + this.getTitulo() + " / Artista " + this.getArtista()
				+ " / Album " + this.getAlbum() + " / Genero " + this.getGenero() + " / Año " + this.getAnio()
				+ " / Duracion " + this.getDuracion() + ".";

		return datos;
	}

	public Vector<Pista> buscar(Condicion f) {
		Vector<Pista> resultado = new Vector<Pista>();
		if (f.cumple(this)) {
			resultado.add(this);
		}
		return resultado;
	}

	@Override
	public void eliminar(ElementoMusica e) {

	}

	@Override
	public void addElement(ElementoMusica e) {

	}

}
