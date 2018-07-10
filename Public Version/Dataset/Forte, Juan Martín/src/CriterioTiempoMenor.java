public class CriterioTiempoMenor extends Criterio {

	private double tiempo;
	public CriterioTiempoMenor(double tiempo){
		this.tiempo = tiempo;
	}
	public boolean cumple(Cancion c1) {
		return (c1.getDuracion()<=tiempo);
	}
}