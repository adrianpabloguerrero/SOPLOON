public class CriterioNOT extends Criterio {
	
	private Criterio c;
	
	 public CriterioNOT(Criterio c) {
		this.c=c;
	}
	@Override
	public boolean cumple(Cancion c1) {
		return (!c.cumple(c1));
	}
}