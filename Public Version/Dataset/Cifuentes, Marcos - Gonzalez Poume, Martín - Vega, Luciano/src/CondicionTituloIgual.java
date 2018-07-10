package trabajoEspecialProg2;

public class CondicionTituloIgual extends Condicion {

	String s;

	public CondicionTituloIgual(String s) {
		this.s = s.toLowerCase();
	}

	public boolean cumple(Pista p){
		return p.getTitulo().toLowerCase().contains(s);
	}
}
