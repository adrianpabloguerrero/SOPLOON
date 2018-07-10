
public class CondGeneroConPalabra {
	private String palabra;
	
	public CondGeneroConPalabra(String val){
		palabra=val;
	}
	
	public String getPalabra(){
		return palabra;
	}
	
	public boolean cumple(Elemento e){
		return ((Cancion)e).getGenero().contains(palabra);
	}
}
