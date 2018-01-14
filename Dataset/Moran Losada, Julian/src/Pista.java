package PracticoEspecial;

import java.util.Vector;

public class Pista extends Elemento {
		protected int id;
		protected static int contador = 0;
		
		protected int duracion;
		protected String artista;
		protected String album;
		protected int anio;
		protected String comentario;
		private String genero;
		
		public Pista(){
			contador++;
			id = contador;
			titulo = "Sin definir";
			duracion = 001;
			artista = "Sin Definir";
			album = "Sin Definir";
			anio = 2016;
			genero = "Sin definir";
			comentario = "Sin comentarios";
		}
		public Pista(String _titulo, int _duracion, String _artista
				, String _album, int _anio, String _genero, String _comentario){
			contador++;
			id = contador;
			titulo = _titulo;
			duracion = _duracion;
			artista = _artista;
			album = _album;
			anio = _anio;
			genero = _genero;
			comentario = _comentario;
		}
		
		
		public void setDuracion(int d){
			duracion = d;
		}
		public void setArtista(String a){
			artista = a;
		}
		public void setAlbum(String al){
			album = al;
		}
		public void setAnio(int an){
			anio = an;
		}
		public void setGenero(String g){
			genero = g;
		}
		public void setComentario(String c){
			comentario = c;
		}
		
		
		public int getDuracion(){
			return duracion;
		}
		public String getArtista(){
			return artista;
		}
		public String getAlbum(){
			return album;
		}
		public int getAnio(){
			return anio;
		}
		public String getGenero(){
			return genero;
		}
		public String getComentario(){
			return comentario;
		}
		public int getID(){
			return id;
		}
		
		
		
		public Vector<Pista> buscar(Condicion c){
			Vector<Pista> resultado = new Vector<Pista>();
			if(c.cumple(this)){
				resultado.add(this);
			}
			return resultado;
		}
		
		public String imprimir(){
			return getID() + " " + getTitulo() + " " + getDuracion() + " " + 
					getArtista() + " " + getAlbum() + " " + getAnio()  + " " +
					getGenero() + " " + getComentario();
		}
		
		public String imprimir(Condicion c){
			if(c.cumple(this)){
				return imprimir();
			}
			return "";
		}
		
		public int getCantidadElementos(){
			return 1;
		}
}
