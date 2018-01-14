
public class CondicionTiempoMayorA extends Condicion{
	int tiempo;
	public CondicionTiempoMayorA(int tiempo){
		this.tiempo=tiempo;
	}
	@Override
	public boolean cumple(ElementoSistema es) {
		return ((PistaDeAudio)es).duracionTotal()>=tiempo;
	}

}

