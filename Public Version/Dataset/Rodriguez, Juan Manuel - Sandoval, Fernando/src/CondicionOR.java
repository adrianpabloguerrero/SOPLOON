
public class CondicionOR extends Condicion{
	Condicion c1;
	Condicion c2;
	public CondicionOR(Condicion c1, Condicion c2){
		this.c1=c1;
		this.c2=c2;
	}
	@Override
	public boolean cumple(ElementoSistema es) {
		return c1.cumple(es)||c2.cumple(es);
	}
}
