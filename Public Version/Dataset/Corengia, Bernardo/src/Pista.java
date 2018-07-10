/**
 * Clase "Hoja" del composite, contiene los atributos de la pista (id - duracion - interprete - album  
 * anio, genero, comentarios) con los get y set, implementa los métodos abstractos de su clase padre
 * 
 * @author Bernardo Corengia
 */
public class Pista extends ElementoReproduccion {

	// ----------- Atributos -----------
	
	int id;
	int duracion;
	String interprete;
	String album;
	int anio;
	String genero;
	String comentarios;
	
	// ------------ Constructor -------------
	
	public Pista(int i, String n, int d, String in, String al, int an, String g, String c) {
		super(n);
		id = i;
		duracion = d;
		interprete = in;
		album = al;
		anio = an;
		genero= g;
		comentarios = c;
	}
	
	// --------------------------------- GET --------------------------------------------
	
	public int getId() {
		return id;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public int getAnio() {
		return anio;
	}
	
	public String getInterprete() {
		return interprete;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public String getComentarios() {
		return comentarios;
	}
	
	  // ----------------------------------- SET --------------------------------------
	
	public void setId(int i) {
		id = i;
	}
	
	public void setDuracion(int d) {
		duracion = d;
	}
	
	public void setAnio(int a) {
		anio = a;
	}
	
	public void setInterprete(String i) {
		interprete = i;
	}
	
	public void setAlbum(String a) {
		album = a;
	}
	
	public void setGenero(String g) {
		genero = g;
	}
	
	public void setComentarios(String c) {
		comentarios = c;
	}
	
	// ----------------------------- METODOS -------------------------------------

	
	public int duracionTotal() {
		return duracion;
	}
	
	public int cantidadElementos() {
		return 1;
	}
	
	public void imprimirColeccion() {
		System.out.println(getId()+" - "+getNombre()+" - "+getInterprete()+" - "
	+getAlbum()+" ("+getGenero()+", "+getAnio()+") - "+getDuracion());
	}
	
}
