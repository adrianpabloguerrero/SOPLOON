package practicoEspecial;

public class CondicionAnioMayor extends Condicion{
	private int anio;
	
	public CondicionAnioMayor(int a){
		anio=a;
	}
	public boolean cumple(Pista p) {
		return (p.getAnio()>anio);
	}
	

}
