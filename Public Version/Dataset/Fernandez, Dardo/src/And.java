public class And extends Condicion {

	private Condicion condicion1;
	private Condicion condicion2;

	public And (Condicion condicion1, Condicion condicion2) {
		this.condicion1 = condicion1;
		this.condicion2 = condicion2;
	}

	public boolean satisfaceCondicion (Pista pista) {
		boolean resultado = (condicion1.satisfaceCondicion(pista) && condicion2.satisfaceCondicion(pista));
		return resultado;
	}

}
