package trabajoEspecialProg2;

public class CondicionDuracionMayor extends Condicion {

	int s;
	
	public CondicionDuracionMayor(int s) {
		this.s = s;
	}

	public boolean cumple(Pista p) {
		return p.getDuracion() > s;
	}
}
