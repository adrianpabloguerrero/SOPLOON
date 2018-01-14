public class CriterioTiempoMayor extends Criterio {

	private double tiempo;
	
	public CriterioTiempoMayor(double tiempo){
		this.tiempo = tiempo;
	}
	public boolean cumple(Cancion c1) {
		return (c1.getDuracion()>=tiempo);		
	}
}