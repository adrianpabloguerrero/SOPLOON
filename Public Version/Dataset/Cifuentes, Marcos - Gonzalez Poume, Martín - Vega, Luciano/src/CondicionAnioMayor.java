package trabajoEspecialProg2;

public class CondicionAnioMayor extends Condicion {

	int s;

	public CondicionAnioMayor(int s) {
		this.s = s;
	}

	public boolean cumple(Pista p) {
		return p.getAnio() > s;
	}
}
