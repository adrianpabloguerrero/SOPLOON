package practicoEspecial;


public class CondicionNot extends Condicion{

private Condicion c;
	
	public CondicionNot (Condicion cond){
		c=cond;
	}
	
	
	public boolean cumple(Pista p) {
		if(!c.cumple(p))
			return true;
		return false;
	}
}
