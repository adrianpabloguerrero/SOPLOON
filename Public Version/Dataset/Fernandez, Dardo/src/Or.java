public class Or extends Condicion {

	private Condicion condicion1;
	private Condicion condicion2;

	public Or (Condicion condicion1, Condicion condicion2) {
		this.condicion1 = condicion1;
		this.condicion2 = condicion2;
	}

	public boolean satisfaceCondicion (Pista pista) {
		return (condicion1.satisfaceCondicion(pista) || condicion2.satisfaceCondicion(pista));
	}

}
