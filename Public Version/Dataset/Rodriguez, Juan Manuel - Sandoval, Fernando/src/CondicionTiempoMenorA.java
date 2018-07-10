public class CondicionTiempoMenorA extends Condicion{
	int tiempo;
	public CondicionTiempoMenorA(int tiempo){
		this.tiempo=tiempo;
	}
	@Override
	public boolean cumple(ElementoSistema es) {
		return ((PistaDeAudio)es).duracionTotal()<tiempo;
	}

}
