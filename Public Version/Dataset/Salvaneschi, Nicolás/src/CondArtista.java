
public class CondArtista extends Condicion {
	private String artista;
	
	public CondArtista (String a){
		artista=a;
	}
	
	public String getArtista(){
		return artista;
	}
	
	public boolean cumple(Elemento e ){
	 return  ((Cancion)e).getArtista().contains(artista);
	 }
}
