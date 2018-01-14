package sistema;

public class BuscarDuracionMayor extends Busqueda {
	
	int tiempo;
	
	public BuscarDuracionMayor(int t) {
		tiempo = t;
	}
		
	public boolean busqueda(Pista p) {
		return(p.getDuracion() >= tiempo);
	}
		
}
