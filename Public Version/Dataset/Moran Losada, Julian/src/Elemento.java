package PracticoEspecial;

import java.util.Vector;

abstract public class Elemento {
	
		protected String titulo;
		
		abstract public String imprimir();
		abstract public String imprimir(Condicion c);
		abstract public int getDuracion();
		abstract public int getCantidadElementos();
		abstract public Vector<Pista> buscar(Condicion c);
		public void setTitulo(String t){
			titulo = t;
		}
		public String getTitulo(){
			return titulo;
		}

}
