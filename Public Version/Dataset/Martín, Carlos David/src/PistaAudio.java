import java.util.Vector;

public class PistaAudio extends ElementoAudio {
	private String artista , album, genero, comentario;
	private int ID,year,duracion; 

	public PistaAudio (int _id,String _titulo,int _duracion,String _artista, String _album, int _year, String _gen) {
		ID = _id;
		titulo = _titulo;
		duracion = _duracion;
		artista = _artista;
		album = _album;
		year = _year;
		genero = _gen;
		comentario = "";
	}
	//***************SET atributos**********************
	public void setID (int _id) {
		ID = _id;
	}			

	public void setDuracion (int _dur) {
		duracion = _dur;
	}

	public void setYear (int _year) {
		year = _year;
	}

	public void setArtista (String _artista) {
		artista = _artista;
	}

	public void setAlbum (String _album) {
		album = _album;
	}

	public void setGenero (String _genero) {
		genero = _genero;
	}

	public void setComentario (String _coment) {
		comentario = _coment;
	}
	//***************GET atributos**********************
	public int getID () {
		return ID;
	}

	public int getDuracion () {
		return duracion;
	}

	public int getYear () {
		return year;
	}

	public String getArtista() {
		return artista;
	}
	public String getAlbum() {
		return album;
	}
	public String getGenero() {
		return genero;
	}
	public String getComentario() {
		return comentario;
	}

	//*******************************************
	@Override
	public int cantPistas () {
		return 1;
	}
	@Override
	public Vector<ElementoAudio> buscar (Condicion cond) {
		Vector<ElementoAudio> resultado = new Vector<ElementoAudio>();

		if(cond.cumple(this)) 
			resultado.add(this);

		return resultado;
	}

	@Override
	public String toString () {
		return ("ID: "+ID+" Titulo: "+titulo+" Duración: "+duracion+"seg"+" Artista: "+artista+" Album: "+album+"("+genero+", "+year+")"+"\n");
	}
	
	@Override
	public boolean equals(Object p1) {
		if(this==p1)
			return true;
		if (p1 instanceof PistaAudio) { 
			PistaAudio p = (PistaAudio)p1;
			return ID==p.getID()&&year==p.getYear()&&duracion==p.getDuracion()&&artista.equals(p.getArtista())&&album.equals(p.getAlbum());
		}
		return false;
	}
}
