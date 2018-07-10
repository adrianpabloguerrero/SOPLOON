public class CriterioFecha extends Criterio {

	private int anio;
	
	public CriterioFecha(int anio){
		this.anio = anio;
	}
	public boolean cumple(Cancion c1) {
		return (c1.getAnio()>=anio);		
	}
}