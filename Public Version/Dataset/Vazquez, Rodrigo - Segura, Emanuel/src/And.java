
public class And extends Condicion{

	Condicion cond1;
	Condicion cond2;
	
	public And(Condicion cond1,Condicion cond2){
		this.cond1 = cond1;
		this.cond2 = cond2;
	}

	public boolean cumple(ElementoMusical elemento) {
		return (cond1.cumple(elemento))&&(cond2.cumple(elemento));
	}
	
}

