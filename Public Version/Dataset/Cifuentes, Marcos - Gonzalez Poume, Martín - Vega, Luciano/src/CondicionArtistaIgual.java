package trabajoEspecialProg2;

public class CondicionArtistaIgual extends Condicion {

	String s;
	
	public CondicionArtistaIgual(String s) {
		this.s = s.toLowerCase();
	}
	
	public boolean cumple(Pista p) {
		return p.getArtista().toLowerCase().contains(this.s);
	}
}
