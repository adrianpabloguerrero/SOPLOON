import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CondicionNombre extends Condicion{
	String nombre;
	public CondicionNombre(String nombre){
		this.nombre=nombre;
	}
	@Override
	public boolean cumple(ElementoSistema es) {	
		if(!((PistaDeAudio)es).getTitulo().equalsIgnoreCase(nombre)){
			Pattern pat= Pattern.compile("(?i).*"+nombre+"*.");
			Matcher mat = pat.matcher(((PistaDeAudio)es).getTitulo());
			if(mat.find()){
				return true;
			}
			else{return false;}
		}
		return true;
	}

}
