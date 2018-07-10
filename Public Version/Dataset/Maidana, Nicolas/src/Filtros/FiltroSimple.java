package Filtros;

public abstract class FiltroSimple implements Filtro {
	
	private Object atributo;
	
	public FiltroSimple(Object atributo) {
		this.atributo = atributo;
	}
	
	public Object getAtributo() {
		return atributo;
	}

}
