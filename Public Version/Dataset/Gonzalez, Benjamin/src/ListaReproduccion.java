package Lista_de_Musica;
import java.util.Vector;	

public class ListaReproduccion extends Elemento{
	private Vector<Elemento> elementos;
	
	public ListaReproduccion(String nombre){
			super(nombre);
			elementos = new Vector<Elemento>();
	}
	public void agregarComentario(String s){
		super.agregarComentario(s);
	}
	public void setComentario(String s, int i){
		super.setComentarios(i,s);
	}
	public void agregar(Elemento e){
		elementos.add(e);
	}
	public void eliminar(Elemento e){
		elementos.remove(e);
	}
	public int getTamaño(){
		int total = 0;
		for(int i=0; i<elementos.size();i++){
			total+= elementos.elementAt(i).getTamaño();
		}
		return total+1;
	}
	public int getDuracion(){
		int total = 0;
		for(int i=0; i<elementos.size();i++){
			total+= elementos.elementAt(i).getDuracion();
		}
		return total;
	}
	
	public String getNombre(){
		return super.getNombre();
	}
	public void setNombre(String nombre){
		super.setNombre(nombre);
	}
	public String getElemento(int i){
		
		return elementos.elementAt(i).getElemento(i); 
		
	}
	public Vector<Elemento> getElementos(){
		return elementos;
	}
	
	public void borrar(Elemento e){
		if(elementos.contains(e))
			elementos.remove(e);
	}
	
	public Vector<Elemento> buscar(Condicion c){
		Vector<Elemento> salida = new Vector<Elemento>();
		for(int i=0; i<elementos.size(); i++){
			Elemento temporal = elementos.elementAt(i);
			Vector<Elemento> e1 = temporal.buscar(c);
			salida.addAll(e1);
		}
		return salida;
	}
	@Override
	public String getArtista() {
		for(int i=0;i<elementos.size();i++){
			return elementos.elementAt(i).getArtista();
		}
		return null;
	}
	@Override
	public String getTituloAlbum() {
		for(int i=0;i<elementos.size();i++){
			return elementos.elementAt(i).getTituloAlbum();
		}
		return null;
	}
	@Override
	public String getGenero(){
		for(int i=0; i<elementos.size();i++){
			return elementos.elementAt(i).getGenero();
		}
		return null;
	}
	

}
