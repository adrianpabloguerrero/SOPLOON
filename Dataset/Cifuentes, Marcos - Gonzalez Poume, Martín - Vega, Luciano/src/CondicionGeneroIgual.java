package trabajoEspecialProg2;

public class CondicionGeneroIgual extends Condicion {

	String s;

	public CondicionGeneroIgual(String s) {
		this.s = s.toLowerCase();
	}

	public boolean cumple(Pista p) {
		return p.getGenero().toLowerCase().contains(this.s);
	}
}
