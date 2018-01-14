import java.util.*;
public class Cancion  extends Elemento {
	private int id;
	private String titulo;
	private int duracion;
	private String artista;
	private String album;
	private int año;
	private String genero;
	private String comentario;
	
	public Cancion(int i, String tit,int dur, String art, String al, int _año, String gen, String coment){
		
		id=i;
	titulo=tit;
	duracion=dur;
	artista=art;
	album=al;
	año=_año;
	genero=gen;
	comentario=coment;	
	}
	
	public int getId(){
		return id;		
	}	
	
	public String getNombre(){
		return titulo;		
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
	
	public int getAño(){
		return año;		
	}
	
	public String getGenero(){
		return genero;		
	}
	
	public String getComentario(){
		return comentario;		
	}
	
	public void setId(int i){
		id=i;
	}
	
	public void setTitulo(String tit){
		titulo=tit;
	}
	
	public void setDuracion(int i){
		duracion=i;
	}
	public void setArtista(String art){
		artista=art;
	}
	
	public void setAlbum(String alb){
		album=alb;
	}
	
	public void setAño(int a){
		año=a;
	}
	
	public boolean Existe(Elemento x){
		boolean aux=false;
		if (x.getNombre()==this.getNombre())
			aux=true;
		return aux;
	}
	
	
	public void setGenero(String gen){
		genero=gen;
	}
	
	public int getCantidad(){
		return 1;
	}
	
	public void  imprimir(){
		System.out.println("\n      ID:"+id  +"\n<Titulo> "+titulo+"\n<Artista> "+artista+"\n<Album> "+album+"\n<Genero> "+genero+"\nEditado "+año+"\n<Duración>"+duracion+" seg.");
	}
	
	public Vector<Elemento> buscarCondicion(Condicion c){
		Vector<Elemento> retorno=new Vector<Elemento>();
		if(c.cumple(this)){
			retorno.add(this);
		}
		return retorno;
	}
		
	
	
	
	
	
	
	

}
