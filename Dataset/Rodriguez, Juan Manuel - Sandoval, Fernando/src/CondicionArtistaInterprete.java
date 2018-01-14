import java.util.regex.*;

public class CondicionArtistaInterprete extends Condicion{
		String artistaInterprete;
		   public CondicionArtistaInterprete (String artistaInterprete){
			   this.artistaInterprete = artistaInterprete;
			   }
		
		
		@Override
		public boolean cumple(ElementoSistema es) {
			if(!((PistaDeAudio)es).getArtistaInterprete().equalsIgnoreCase(artistaInterprete)){
				Pattern pat= Pattern.compile("(?i).*"+artistaInterprete+"*.");
				Matcher mat = pat.matcher(((PistaDeAudio)es).getArtistaInterprete());
				if(mat.find()){
					return true;
				}
				else{return false;}
			}
			return true;
		}
	}
