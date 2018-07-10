
public class CondicionAnioMayorA extends Condicion{
	int anio;
	public CondicionAnioMayorA(int anio){
		this.anio=anio;
	}
	@Override
	public boolean cumple(ElementoSistema es) {
		return ((PistaDeAudio)es).getAnio()>anio;
	}

}
