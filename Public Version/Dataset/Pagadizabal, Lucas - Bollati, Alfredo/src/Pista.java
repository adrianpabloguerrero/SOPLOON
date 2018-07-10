import java.util.Vector;

public class Pista extends PLFE{
	
	private int id;
	private int duracion;
	private int anio;
	private String titulo;
	private String artista;
	private String tituloAlbum;
	private String genero;
	private String comentarios;
	
	public Pista(int _id,String t,int d, String a, String tA, int _anio, String g, String c){
		id = _id;
		titulo = t;
		duracion = d;
		artista = a;
		tituloAlbum = tA;
		anio = _anio;
		genero = g;
		comentarios = c;
	}
	
	public int cantPistas() {
		return 1;
	}
	
	public String toString(){
		String mensaje = "Id: "+id+", Titulo: "+titulo+", Duracion: "+duracion+", Interprete: "+artista+
				", Titulo Album: "+tituloAlbum+", Año: "+anio+", Genero: "+genero+"\n";
		return mensaje;
	}
	
	
	public Vector<PLFE> buscar(Condicion c){
		Vector<PLFE> aux = new Vector<PLFE>();		
		if (c.esIgual(this)){
			aux.add(this);
		}
		return aux;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj instanceof Pista){
			Pista objCast = ((Pista)obj);
			return ((id == objCast.getId())&&(titulo.equals(objCast.getTitulo()))&&(duracion == objCast.getDuracion())&&(artista .equals(objCast.getArtista()))&&(tituloAlbum.equals(objCast.getTituloAlbum()))&&(anio == objCast.getAnio())&&(genero.equals(objCast.getGenero())));
		}
		return false;
	}
	
	// Get y Set de cada atributo
	public void setId(int _id) {
		id = _id;
	}
	public void setTitulo(String _titulo) {
		titulo = _titulo;
	}
	public void setDuracion(int _duracion) {
		duracion = _duracion;
	}
	public void setArtista(String _artista) {
		artista = _artista;
	}
	public void setTituloAlbum(String _tituloAlbum) {
		tituloAlbum = _tituloAlbum;
	}
	public void setAnio(int _anio) {
		anio = _anio;
	}
	public void setGenero(String _genero) {
		genero = _genero;
	}
	public void setComentarios(String _comentarios) {
		comentarios = _comentarios;
	}
	public int getId(){
		return id;
	}
	public String getTitulo(){
		return titulo;
	}
	public String getTituloAlbum(){
		return tituloAlbum;
	}
	public int getDuracion(){
		return duracion;
	}
	public int getAnio(){
		return anio;
	}
	public String getGenero(){
		return genero;
	}
	public String getArtista(){
		return artista;
	}
	public String getComentarios(){
		return comentarios;
	}	
}
