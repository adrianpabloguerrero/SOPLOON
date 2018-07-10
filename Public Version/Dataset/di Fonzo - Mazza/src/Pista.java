package trabajoPracticoEspecial;

import java.util.Vector;

public class Pista extends ElementoMusical{
	
	private int ID;
	private String titulo;
	private int duracion;
	private String interprete;
	private String album;  
	private int anio;
	private String genero;
	private String comentarios;
	
	public Pista(int ID,String titulo,int duracion, String interprete, String album, int anio, String genero){
		
		this.ID = ID;
		this.titulo = titulo;
		this.duracion = duracion;
		this.interprete = interprete;
		this.album = album;
		this.anio = anio;
		this.genero = genero;
		
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
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


	public String getAlbum() {
		return album;
	}


	public void setAlbum(String album) {
		this.album = album;
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

	public int getDuracion() {
		return duracion;
	}

	public int getCantPistas() {
		return 1;
	}

	public String imprimirPistas() {
		
		return "ID: " + getID() + " - Titulo: ''" + getTitulo() + "'' - Interprete: ''" + getInterprete() 
		 + "'' -  Album: ''" + getAlbum() + "'' - Genero: ''" + getGenero() + "'' - Año: " + getAnio() + 
		 " - Duracion: " + getDuracion() + " segundos";
		
	}


	public Vector<Pista> buscarPistas(Condicion c) {
		
		Vector <Pista> resultado = new Vector<Pista>();
		
		if(c.cumple(this)){
			
			resultado.add(this);
			
		}
		
		return resultado;
	}


	@Override
	public void eliminarElemento(ElementoMusical e) {
		// TODO Auto-generated method stub
		
	}


}
