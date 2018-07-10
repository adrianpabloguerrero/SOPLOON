import java.util.*;
public class PlayList extends ElementoSistema{
	private Vector<ElementoSistema> lista;
	
	public PlayList(String titulo){
		super(titulo);
		lista=new Vector<ElementoSistema>();
	}
	public void setTitulo(String titulo){
		this.titulo=titulo;
	}
	public void add(ElementoSistema es){
		lista.add(es);
	}
	@Override
	public int cantidadElementos() {
		int ce=0;
		for(int i=0;i<this.lista.size();i++){
			ce+=lista.elementAt(i).cantidadElementos();
		}
		return ce;
	}
	@Override
	public int duracionTotal() {
		int duracion=0;
		for(int i=0;i<this.lista.size();i++){
			duracion+=lista.elementAt(i).duracionTotal();
		}
		return duracion;
	}
	@Override
	public String imprimir() {
		String impr=new String();
		for(int i=0;i<lista.size();i++){
			impr+=lista.elementAt(i).imprimir();
		}
		return impr;
	}
	@Override
	public Vector<ElementoSistema> buscar(Condicion c) {
		Vector<ElementoSistema> resultado=new Vector<ElementoSistema>();
		for(int i=0;i<lista.size();i++){
			ElementoSistema es1=lista.elementAt(i);
			Vector resultadoAuxiliar= es1.buscar(c);
			resultado.addAll(resultadoAuxiliar);
		}
		return resultado;
	}
	
	public Vector<ElementoSistema> intercambiarTema(int origen, int destino) {
		if ((origen<lista.size()) && (origen>=0) && (destino<lista.size())&&(destino>=0) && (origen!=destino)) { 
		        Collections.swap(lista, origen, destino);
		}
		return lista;
	}
	
	public void eliminar(String titulo){
		for(int i=0; i<lista.size() ;i++){
			ElementoSistema es=lista.elementAt(i);
			if(es.getTitulo().contains(titulo)){
				es.eliminar(titulo);
				lista.remove(i);	
			}
		}
	}	
}
