
public class CondicionNOT extends Condicion{
	Condicion c;
	public CondicionNOT(Condicion c){
		this.c=c;
	}
	@Override
	public boolean cumple(ElementoSistema es) {
		return !(c.cumple(es));
	}
}
