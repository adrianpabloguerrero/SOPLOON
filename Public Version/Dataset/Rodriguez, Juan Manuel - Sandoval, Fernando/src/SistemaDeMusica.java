import java.util.*;

public class SistemaDeMusica {
	private Vector<ElementoSistema> listasytemas;

	public SistemaDeMusica() {
		listasytemas = new Vector<ElementoSistema>();
	}

	public PlayList getPlayList(String titulo) {
		for (int i = 0; i < this.listasytemas.size(); i++) {
			ElementoSistema pl = this.listasytemas.elementAt(i);
			if (pl.getTitulo().equals(titulo)) {
				return (PlayList) pl;
			}
		}
		return null;
	}
	public void addPlayListOTema(ElementoSistema es){
		listasytemas.add(es);
	}

	public int duracionTotal(String nombrePlayList) {//duracion total en segs de pistas de audio de una playlist
		return this.getPlayList(nombrePlayList).duracionTotal();
	}

	public int duracionTotal() {//duracion total en segs de pistas de audio del sistema
		int dt = 0;
		for(int i=0;i<this.listasytemas.size();i++){
		dt+=listasytemas.elementAt(i).duracionTotal();
		}
		return dt;
	}

	public int cantidadElementos(String nombrePlayList) {//cantidad de pistas de audio de una playlist
		return this.getPlayList(nombrePlayList).cantidadElementos();
	}

	public int cantidadElementos() {//cantidad de pistas de audio del sistema

		int ce=0;
		for(int i=0;i<this.listasytemas.size();i++){
			ce+=this.listasytemas.elementAt(i).cantidadElementos();
		}
		return ce;
	}

	public Vector<ElementoSistema> intercambiarTemas(String nombrePlaylist,int origen,int destino){
		return this.getPlayList(nombrePlaylist).intercambiarTema(origen-1, destino-1);//le restamos 1 porque el vector empieza de la posicion 0...si el usuario quiere cambiar tema 1 con tema 2, e el vector estan en la posicion 0 y 1 respectivamente
	}
	
	public String imprimir(String nombrePlayList) {//imprimir pistas de audio de una playlist
		String aImprimir="Play List "+nombrePlayList+"\n";
		aImprimir+=this.getPlayList(nombrePlayList).imprimir();
		return aImprimir;
	}

	public String imprimir() { //imprimir pistas de audio del sistema
		String impr=new String();
		for(int j=0;j<this.listasytemas.size();j++){
			impr+=this.listasytemas.elementAt(j).imprimir();
		}
		return impr;
	}
	
	public String imprimirDuracionDePlaylist(String nombrePlaylist){
		int duracion=this.duracionTotal(nombrePlaylist);
		if(duracion>0){
			return ("La duracion total de la playlist "+nombrePlaylist+" es: "+duracion);
		}
		return ("La playlist esta vacia o no existe");
		
	}

	public String imprimirBusqueda(Vector<ElementoSistema> resultadoBusqueda){
		String impr=new String();
		for(int k=0;k<resultadoBusqueda.size();k++){
			impr+=((PistaDeAudio)(resultadoBusqueda.elementAt(k))).imprimir();
		}
		return impr;
	}
	
	public Vector<ElementoSistema> buscar(Condicion c){
		Vector<ElementoSistema> resultado= new Vector<ElementoSistema>();
		for(int j=0;j<this.listasytemas.size();j++){
			resultado.addAll(this.listasytemas.elementAt(j).buscar(c));
		}
		return resultado;
	}
	
	public void eliminar(String titulo){
		for(int i=0;i<listasytemas.size();i++){
			listasytemas.elementAt(i).eliminar(titulo);
			if(listasytemas.elementAt(i).getTitulo().equals(titulo)){
				listasytemas.remove(listasytemas.elementAt(i));
			}
		}
	}

	public static void main(String[] args) {
		SistemaDeMusica sm = new SistemaDeMusica();
		PistaDeAudio p1 = new PistaDeAudio(1,"El Tiempo No Para",311,"Bersuit Vergarabat","De La Cabeza",2002,"Rock Nacional");
		PistaDeAudio p2 = new PistaDeAudio(2,"Mi Caramelo",290,"Bersuit Vergarabat","De La Cabeza",2002,"Rock Nacional");
		PistaDeAudio p3 = new PistaDeAudio(3,"Party Rock Anthem",408,"LMFAO","Sorry For Party Rocking",2011,"Electro Pop");
		PistaDeAudio p4 = new PistaDeAudio(4,"Sorry For Party Rocking",421,"LMFAO","Sorry For Party Rocking",2011,"Electro Pop");
		PistaDeAudio p5 = new PistaDeAudio(5,"Fix You",255,"Coldplay","X&Y",2005,"Rock Alternativo");
		PistaDeAudio p6 = new PistaDeAudio(6,"Speed Of Sound",455,"Coldplay","X&Y",2005,"Rock Alternativo");
		PistaDeAudio p7 = new PistaDeAudio(7,"Viva La Vida",320,"Coldplay","Viva La Vida",2008,"Rock Alternativo");
		PistaDeAudio p8 = new PistaDeAudio(8,"With Or Whitout You",360,"U2","The Joshua Tree",1987,"Rock");
		PistaDeAudio p9 = new PistaDeAudio(9,"Vertigo",355,"U2","How To Dismantle An Atomic Bomb",2004,"Rock");
		PistaDeAudio p10 = new PistaDeAudio(10,"City Of Blindings Lights",284,"U2","How To Dismantle An Atomic Bomb",2004,"Rock");
		PistaDeAudio p11 = new PistaDeAudio(11,"A La Luz De La Luna",438,"El Indio Solari","Pajaritos, Bravos Muchachitos",2013,"Rock Nacional");
		PistaDeAudio p12 = new PistaDeAudio(12,"Yo Canibal",258,"Patricio Rey Y Sus Redonditos De Ricota","Lobo Suelto, Cordero Atado",1993,"Rock Nacional");
		PistaDeAudio p13 = new PistaDeAudio(13,"Paradise",365,"Coldplay","Mylo Xyloto",2011,"Rock Alternativo");
		PlayList pl1=new PlayList("Clasicos Del Rock");
		PlayList pl2=new PlayList("Lo Mejor");
		PlayList pl3=new PlayList("Coldplay");
		PlayList pl4=new PlayList("El Indio");
		pl1.add(p1);
		pl1.add(p2);
		pl1.add(p8);
		pl1.add(p9);
		pl1.add(p10);
		pl1.add(p12);
		
		pl2.add(p3);
		pl2.add(p4);
		pl2.add(p7);
		pl2.add(p12);
		
		pl3.add(p5);
		pl3.add(p6);
		pl3.add(p7);
		
		pl4.add(p12);
		pl4.add(p11);
		//agrega las playlist y el tema 13 al sistema de musica
		sm.addPlayListOTema(pl1);
		sm.addPlayListOTema(pl2);
		sm.addPlayListOTema(pl3);
		sm.addPlayListOTema(pl4);
		sm.addPlayListOTema(p13);
		//muestra los elementos que contiene la PlayList="clasicos del rock"
		System.out.println(sm.imprimir("Clasicos Del Rock"));
		//muestra los elementos que contiene la PlayList="Lo Mejor"
		System.out.println(sm.imprimir("Lo Mejor"));
		//muestra los elementos que contiene la PlayList="Coldplay"
		System.out.println(sm.imprimir("Coldplay"));
		//duracion de playlists
		System.out.println(sm.imprimirDuracionDePlaylist(pl1.getTitulo()));
		System.out.println(sm.imprimirDuracionDePlaylist(pl2.getTitulo()));
		System.out.println(sm.imprimirDuracionDePlaylist(pl3.getTitulo()));
		System.out.println(sm.imprimirDuracionDePlaylist(pl4.getTitulo()));
		System.out.println("la duracion total del sistema es:"+sm.duracionTotal());
		
		// busquedas
	
		//System.out.println(sm.imprimirBusqueda(sm.buscar(c1)));
		//muestra las pistas cuya duración sea superior a 400 segundos
		CondicionTiempoMayorA c2 = new CondicionTiempoMayorA(400);
		System.out.println(sm.imprimirBusqueda(sm.buscar(c2)));
		
		//muestra las pistas cuyo género contenga la palabra “rock”
		CondicionGenero c4=new CondicionGenero("rock");
		System.out.println(sm.imprimirBusqueda(sm.buscar(c4)));
		
		//muestra las pistas cuyo nombre contenga “rock” Y cuyo interprete NO sea “LMFAO”
		CondicionNombre c5=new CondicionNombre("rock");
		CondicionArtistaInterprete c6=new CondicionArtistaInterprete("LMFAO");
		CondicionNOT c7=new CondicionNOT(c6);
		CondicionAND c8=new CondicionAND(c5,c7);
		System.out.println(sm.imprimirBusqueda(sm.buscar(c8)));
		
		//muestra las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y
		//cuyo intérprete sea “coldplay”
		CondicionAnioMayorA c9=new CondicionAnioMayorA(2006);
		CondicionArtistaInterprete c1=new CondicionArtistaInterprete("colDplay");
		CondicionAND c10=new CondicionAND(c4,c9);
		CondicionAND c11=new CondicionAND(c4,c1);
		CondicionOR c12=new CondicionOR(c10,c11);
		System.out.println(sm.imprimirBusqueda(sm.buscar(c12)));
		
		//imprimir todos los elementos, eliminar la playlist 1 y volver a imprimir para comprobar que se elimino
		//System.out.println(sm.imprimir());
		//System.out.println("------------------probando elminar--------------------------");
		//sm.eliminar(pl1.getTitulo());
		//System.out.println(sm.imprimir());
	}
}
