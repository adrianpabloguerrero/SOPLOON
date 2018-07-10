package Filtros;
public class FiltroAND extends FiltroCompuesto {

	public FiltroAND(Filtro f1, Filtro f2) {
		super(f1,f2);
	}
	
	public boolean operar(boolean f1, boolean f2) {
		return f1 && f2;
	}

}
