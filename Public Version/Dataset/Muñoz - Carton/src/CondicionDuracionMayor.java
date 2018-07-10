package practicoEspecial;

public class CondicionDuracionMayor extends Condicion{
	private int duracion;
	public CondicionDuracionMayor(int d){
		duracion=d;
	}
	public boolean cumple(Pista p) {
		return (p.getDuracion()>duracion);
	}


}
