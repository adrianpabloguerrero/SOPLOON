public class CriterioGenero extends Criterio {
	private String genero;

	public CriterioGenero(String genero){
		this.genero = genero.toLowerCase();
	}	
	public boolean cumple(Cancion c) {

		if(c.getGenero().toLowerCase().contains(genero)){
			return true;	
		}
		else return false;
	}
}