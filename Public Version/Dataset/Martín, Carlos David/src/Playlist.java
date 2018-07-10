import java.util.*;

public class Playlist extends ElementoAudio {
	private Vector<ElementoAudio> pistas;

	public Playlist (String _nombre) {
		titulo = _nombre;
		pistas = new Vector<ElementoAudio>();
	}


	public void agregarAPlaylist(ElementoAudio p1) {
		pistas.add(p1);
	}

	public void removePista(ElementoAudio track) {
		pistas.remove(track);
	}

	public void moverPista (ElementoAudio p1, int pos) {
		int posp1 = pistas.indexOf(p1);
		
		if (posp1!= -1) {
		pistas.remove(posp1);
		pistas.add(pos, p1);
		}
	}

		
	@Override
	public int getDuracion() {
		int segundos=0;

		for(int i=0;i<pistas.size();i++) {
			segundos += pistas.elementAt(i).getDuracion();
		}
		return segundos;
	}
	
	@Override
	public int cantPistas () {
		int cant=0;
		for(int i=0;i<pistas.size();i++) {
			cant+= pistas.elementAt(i).cantPistas();		
		}
		return cant;
	}
	
	@Override
	public Vector<ElementoAudio> buscar (Condicion cond) {
		Vector<ElementoAudio> resultado = new Vector<ElementoAudio>();
		
		for (int i = 0; i < pistas.size(); i++) {
			ElementoAudio elm = pistas.elementAt(i);
			Vector<ElementoAudio> elmAux = elm.buscar(cond);
			resultado.addAll(elmAux);
		}
		return resultado;
	}
	
	@Override
	public String toString () {
		String info="";
		for(int i=0;i<pistas.size();i++) {
			info += pistas.elementAt(i).toString();
		}
		return info;
	}
	
	@Override
	public boolean equals(Object p1) {
		if(this==p1)
			return true;
		if (p1 instanceof Playlist) {
			Playlist p = (Playlist)p1;
			return titulo.equals(p.getTitulo());
		}
		return false;
	}
}
