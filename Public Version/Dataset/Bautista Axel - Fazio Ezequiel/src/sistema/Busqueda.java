package sistema;

public abstract class Busqueda {

	public abstract boolean busqueda(Pista p);
	
	public boolean coincide(String s1, String s2){
		return s1.toLowerCase().indexOf(s2.toLowerCase()) >= 0;
	}
	
}
