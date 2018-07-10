import java.util.Vector;

public class Reproductor {
	Vector<Elemento> djTudai;
	
	public Reproductor() {
		djTudai = new Vector<>();
	}
	
	public void add(Elemento e){
		djTudai.add(e);
	}
	
	public String toString(){
		String msj = "";
		for (int i = 0; i < djTudai.size(); i++) {
			Elemento e = (Elemento)djTudai.elementAt(i);
			msj = msj + e;
		}
		return msj;
	}
	
	public static void main(String[] args) {
		
		//Creacion de pistas
		Elemento pista1 = new Pista(1, "El Tiempo No Para", 311, "Bersuit Vergabarat", "De la cabeza", 2002, "Rock Nacional", "");
		Elemento pista2 = new Pista(2, "Mi caramelo", 290, "Bersuit Vergabarat", "De la cabeza", 2002, "Rock Nacional", "");
		Elemento pista3 = new Pista(3, "Party Rock Anthem", 408, "LMFAO", "Sorry for Party Rocking", 2011, "Electro Pop", "");
		Elemento pista4 = new Pista(4, "Sorry for Party Rocking", 421, "LMFAO", "Sorry for Party Rocking", 2011, "Electro Pop", "");
		Elemento pista5 = new Pista(5, "Fix You", 255, "Coldplay", "X&Y", 2005, "Rock Alternativo", "");
		Elemento pista6 = new Pista(6, "Speed of Sound", 455, "Coldplay", "X&Y", 2005, "Rock Alternativo", "");
		Elemento pista7 = new Pista(7, "Viva la Vida", 320, "Coldplay", "Viva la Vida", 2008, "Rock Alternativo", "");
		Elemento pista8 = new Pista(8, "Whit or Whitout You", 360, "U2", "The Joshua Tree", 1987, "Rock", "");
		Elemento pista9 = new Pista(9, "Vértigo", 355, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock", "");
		Elemento pista10 = new Pista(10, "City of Blinding Lights", 284, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock", "");
		Elemento pista11 = new Pista(11, "A la Luz de la Luna", 438, "El Indio Solari", "Pajaritos, Bravos Muchachitos", 2013, "Rock Nacional", "");
		Elemento pista12 = new Pista(12, "Yo Canibal", 258, "Patricio Rey y sus Redonditos de Ricota", "Lobo Suelto, Cordero Atado", 1993, "Rock Nacional", "");
		Elemento pista13 = new Pista(13, "Paradise", 365, "Coldplay", "Mylo Xyloto", 2011, "Rock alternativo", "");
		
		//Creacion de playlist y agrega las pistas correspondientes
		Elemento playlist1 = new Playlist("Clasicos del Rock");
		playlist1.add(pista1);
		playlist1.add(pista2);
		playlist1.add(pista8);
		playlist1.add(pista9);
		playlist1.add(pista10);
		playlist1.add(pista12);
		
		//Creacion de playlist y agrega las pistas correspondientes
		Elemento playlist2 = new Playlist("Lo Mejor");
		playlist2.add(pista3);
		playlist2.add(pista4);
		playlist2.add(pista7);
		playlist2.add(pista12);
		
		//Creacion de playlist y agrega las pistas correspondientes
		Elemento playlist3 = new Playlist("Coldplay");
		playlist3.add(pista5);
		playlist3.add(pista6);
		playlist3.add(pista7);
		
		//Creacion de playlist y agrega las pistas correspondientes
		Elemento playlist4 = new Playlist("El Indio");
		playlist4.add(pista12);
		playlist4.add(pista11);
		
		//Playlist que contiene todos los elementos creados
		Elemento coleccion = new Playlist("");
		coleccion.add(pista1);
		coleccion.add(pista2);
		coleccion.add(pista3);
		coleccion.add(pista4);
		coleccion.add(pista5);
		coleccion.add(pista6);
		coleccion.add(pista7);
		coleccion.add(pista8);
		coleccion.add(pista9);
		coleccion.add(pista10);
		coleccion.add(pista11);
		coleccion.add(pista12);
		coleccion.add(playlist1);
		coleccion.add(playlist2);
		coleccion.add(playlist3);
		coleccion.add(playlist4);
		coleccion.add(pista13);
		
		//imprime las playlist
		System.out.println(playlist1.toString());
		System.out.println(playlist2.toString());
		System.out.println(playlist3.toString());
		System.out.println(playlist4.toString());
		
		//imprime la duracion de las playlist
		System.out.println(playlist1.getNombre() +" \nDuración: "+ playlist1.getDuracion());
		System.out.println(playlist2.getNombre() +" \nDuración: "+ playlist2.getDuracion());
		System.out.println(playlist3.getNombre() +" \nDuración: "+ playlist3.getDuracion());
		System.out.println(playlist4.getNombre() +" \nDuración: "+ playlist4.getDuracion());
		((Playlist)playlist3).modificaOrden(1, "Viva la Vida");
		System.out.println(playlist3.toString());
		
		//Creacion de las condiciones para las busquedas
		Condicion c1 = new DuracionMayor(400);
		Condicion c2 = new GeneroIgual("rock");
		Condicion c3 = new NombreIgual("rock");
		Condicion c4 = new InterpreteIgual("LMFAO");
		Condicion c5 = new AnioMayor(2006);
		Condicion c6 = new InterpreteIgual("Coldplay");
		
		Condicion c7 = new Not(c4);
		Condicion c8 = new And(c3, c7);
		Condicion c9 = new And(c2, c5);
		Condicion c10 = new And(c2, c6);
		Condicion c11 = new Or(c9, c10);
		
		//Prueba de las busquedas
		Vector<Pista> mayor400 = new Vector<Pista>();
		
		mayor400 = coleccion.buscarCondicion(c1);
		System.out.println("Las pistas cuya duración sea superior a 400 segundos: \n");
		System.out.println(mayor400.toString());
		
		Vector<Pista> generoRock = new Vector<Pista>();
		
		generoRock = coleccion.buscarCondicion(c2);
		System.out.println("Las pistas cuyo género contenga la palabra “rock”: \n");
		System.out.println(generoRock.toString());
		
		
		Vector<Pista> nombreRockAndNotLMFAO = new Vector<Pista>();
		
		nombreRockAndNotLMFAO = coleccion.buscarCondicion(c8);
		System.out.println("Las pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”: \n");
		System.out.println(nombreRockAndNotLMFAO.toString());
		
		
		Vector<Pista> coldplayOrRock = new Vector<Pista>();
		
		
		coldplayOrRock = coleccion.buscarCondicion(c11);
		System.out.println("Las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”: \n");
		System.out.println(coldplayOrRock.toString());
		
		((Playlist)playlist1).delete((Pista) pista12);
		
		System.out.println(playlist1.toString());
		
	}

}
