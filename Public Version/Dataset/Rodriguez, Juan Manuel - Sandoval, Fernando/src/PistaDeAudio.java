import java.util.*;
public class PistaDeAudio extends ElementoSistema{
	private int id;
	private int duracionSegs;
	private String artistaInterprete;
	private String tituloAlbum;
	private int anio;
	private String genero;
	public PistaDeAudio(int id,String titulo,int duracion,String artista,String album,int anio,String genero){
		super(titulo);
		this.id=id;
		this.duracionSegs=duracion;
		this.artistaInterprete=artista;
		this.tituloAlbum=album;
		this.anio=anio;
		this.genero=genero;
	}
	public int getId(){
		return this.id;
	}
	public String getTitulo(){
		return this.titulo;
	}
	public int getDuracion(){
		return this.duracionSegs;	
	}
	public String getArtistaInterprete(){
		return this.artistaInterprete;
	}
	public String getTituloAlbum(){
		return this.tituloAlbum;
	}
	public int getAnio(){
		return this.anio;
	}
	public String getGenero(){
		return this.genero;
	}
	public void setTitulo(String titulo){
		this.titulo=titulo;
	}
	public void setDuracion(int duracion){
		this.duracionSegs=duracion;	
	}
	public void setArtistaInterprete(String artista){
		this.artistaInterprete=artista;
	}
	public void setTituloAlbum(String album){
		this.tituloAlbum=album;
	}
	public void setAnio(int anio){
		this.anio=anio;
	}
	public void setGenero(String genero){
		this.genero=genero;
	}
	@Override
	public int cantidadElementos() {
		return 1;
	}
	@Override
	public int duracionTotal() {
		return this.getDuracion();
	}
	@Override
	public String imprimir() {
		String aImprimir=new String();
		aImprimir="Id pista: "+this.getId()+"\n";
		aImprimir+="titulo: "+this.getTitulo()+"\n";
		aImprimir+="Artista/Interprete: "+this.getArtistaInterprete()+"\n";
		aImprimir+="Album: "+this.getTituloAlbum()+"("+this.getGenero()+","+this.getAnio()+") \n";
		aImprimir+="Duracion: "+this.getDuracion()+"\n";
		return aImprimir;
	}
	@Override
	public Vector buscar(Condicion c) {
		Vector<ElementoSistema> resultado=new Vector<ElementoSistema>();
		if(c.cumple(this)){
			resultado.add(this);
		}
		return resultado;
	}
	@Override
	public void eliminar(String titulo) {
		// No hago nada
		System.out.println("pista de audio: "+this.getTitulo()+" eliminada");
	}
}