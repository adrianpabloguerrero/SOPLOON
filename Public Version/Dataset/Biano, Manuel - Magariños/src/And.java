

public class And implements Condicion{

	Condicion uno;
	Condicion dos;
	
	public And(Condicion c1,Condicion c2){
		this.uno = c1;
		this.dos = c2;
	}

	public boolean cumple(ElementoMusical e1) {
		return (uno.cumple(e1))&&(dos.cumple(e1));
	}
	
}


