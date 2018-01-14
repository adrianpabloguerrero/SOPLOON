import java.util.Vector;

public class Pista extends Elemento {
	int id;
	int duracion;
	String interprete;
	String tituloAlbum;
	int anio;
	String genero;
	String comentarios;
	
	public Pista(int i, String nomb, int durac, String interp, String album, int an, String gen, String comment) {
		super(nomb);
		id = i;
		duracion = durac;
		interprete = interp;
		tituloAlbum = album;
		anio = an;
		genero = gen;
		comentarios = comment;
	}
	
	public void add(Elemento e) {
		
	}
	
	public int getCantPista() {
		return 1;
	} 
	
	public String toString() {
		String datos = " -ID: " + this.id + " -TÍTULO: " + this.nombre + " -INTERPRETE: " + this.interprete + " -GÉNERO: " + this.genero + " -AÑO: " + this.anio + " -DURACIÓN: " + this.duracion + "\n";
		return datos;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public String getInterprete() {
		return interprete;
	}
	
	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}
	
	public String getTituloAlbum() {
		return tituloAlbum;
	}
	
	public void setTituloAlbum(String tituloAlbum) {
		this.tituloAlbum = tituloAlbum;
	}
	
	public int getAnio() {
		return anio;
	}
	
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getComentarios() {
		return comentarios;
	}
	
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	public Vector<Pista> buscarCondicion(Condicion c1) {
		Vector<Pista> resultado = new Vector<Pista>();
		
		if(c1.cumple(this)){
			resultado.add(this);
		}
		
		return resultado;
	}

	public void delete(Pista p) {
		
	}
	
	
}
