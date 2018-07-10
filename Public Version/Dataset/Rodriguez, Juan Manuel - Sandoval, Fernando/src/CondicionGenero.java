import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CondicionGenero extends Condicion {
	String genero;
	   public CondicionGenero (String genero){
		   this.genero = genero;
		   }
	
	
	@Override
	public boolean cumple(ElementoSistema es) {
		if(!((PistaDeAudio)es).getGenero().equalsIgnoreCase(genero)){
			Pattern pat= Pattern.compile("(?i).*"+genero+"*.");
			Matcher mat = pat.matcher(((PistaDeAudio)es).getGenero());
			if(mat.find()){
				return true;
			}
			else{return false;}
		}
		return true;
	}
}