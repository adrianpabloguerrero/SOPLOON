public class Not extends Condicion{
	Condicion cond1;
	
	public Not(Condicion cond1){
		this.cond1 = cond1;
	}

	public boolean cumple(ElementoMusical elemento) {
		return !(cond1.cumple(elemento));
	}
	
}
