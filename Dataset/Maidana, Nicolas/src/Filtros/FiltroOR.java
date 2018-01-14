package Filtros;
public class FiltroOR extends FiltroCompuesto {

	public FiltroOR(Filtro f1, Filtro f2) {
		super(f1,f2);
	}
	
	public boolean operar(boolean f1, boolean f2) {
		return f1 || f2;
	}

}
