package practicoEspecial;

public class CondicionAlbum extends Condicion {
	
	private String album;
	public CondicionAlbum(String a){
		album=a.toLowerCase();
	}
	public boolean cumple(Pista p) {
		return (p.getAlbum().toLowerCase().contains(album));
	}

}
