
public class CondicionAnioMenorA extends Condicion{
	int anio;
	public CondicionAnioMenorA(int anio){
		this.anio=anio;
	}
	@Override
	public boolean cumple(ElementoSistema es) {
		return ((PistaDeAudio)es).getAnio()<anio;
	}
}
