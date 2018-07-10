
public class DuracionMayor extends Condicion{
	
	int dato;
	
	public DuracionMayor(int dato) {
		this.dato = dato;
	}

	public boolean cumple(Pista p) {
		return p.getDuracion() > dato;
	}

}
