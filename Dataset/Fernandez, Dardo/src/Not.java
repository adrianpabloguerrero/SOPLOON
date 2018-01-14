public class Not extends Condicion {

	public Condicion condicion;

	public Not (Condicion condicion) {
		this.condicion = condicion;
	}

	public boolean satisfaceCondicion (Pista pista) {

		return !(condicion.satisfaceCondicion(pista));

	}

}
