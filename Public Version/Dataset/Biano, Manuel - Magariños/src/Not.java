

public class Not implements Condicion{
	Condicion uno;
	
	public Not(Condicion c1){
		this.uno = c1;
	}

	public boolean cumple(ElementoMusical e1) {
		return !(uno.cumple(e1));
	}
	
}
