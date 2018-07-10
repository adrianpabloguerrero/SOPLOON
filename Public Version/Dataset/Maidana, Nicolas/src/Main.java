import Musica.*;
import Filtros.*;
import Criterio.*;

public class Main {

	public static void main(String[] args) {

		Coleccion coleccion = new Coleccion();
		
//		System.out.println("Definir la lista de música.");
		
		PistaDeAudio p1 = new PistaDeAudio(1,	"El tiempo No Para", 311, "Bersuit Vergarabat", "De la cabeza",	2002, "Rock nacional", "Es una masa");
		PistaDeAudio p2 = new PistaDeAudio(2,	"Mi caramelo", 290, "Bersuit Vergarabat",	"De la cabeza", 2002, "Rock nacional", "");
		PistaDeAudio p3 = new PistaDeAudio(3,	"Party Rock Anthem", 408, "LMFAO",	 "Sorry for Party Rocking", 2011, "Electro pop", "");
		PistaDeAudio p4 = new PistaDeAudio(4,	"Sorry for Party Rocking", 421, "LMFAO",	 "Sorry for Party Rocking", 2011, "Electro pop", "");
		PistaDeAudio p5 = new PistaDeAudio(5,	"Fix you",	 255, "Coldplay", "X&Y", 2005, "Rock alternativo",	"");
		PistaDeAudio p6 = new PistaDeAudio(6,	"Speed of Sound", 455, "Coldplay", "X&Y", 2005, "Rock alternativo",	"");
		PistaDeAudio p7 = new PistaDeAudio(7,	"Viva la vida", 320, "Coldplay", "Viva la vida", 2008, "Rock alternativo",	"");
		PistaDeAudio p8 = new PistaDeAudio(8,	"Whit or whitout you", 360, "U2", "The Joshua Tree",	 1987, "Rock", "");
		PistaDeAudio p9 = new PistaDeAudio(9,	"Vertigo",	 355, "U2", "How to Dismantle an Atomic Bomb",	2004, "rock", "");
		PistaDeAudio p10 = new PistaDeAudio(10,	"City of Blinding Lights",	284, "U2", "How to Dismantle an Atomic Bomb",	2004, "Rock", "");
		PistaDeAudio p11 = new PistaDeAudio(11,	"A la luz de la luna", 438, "El Indio Solari Pajaritos", "bravos muchachitos", 2013, "rock nacional",		"");
		PistaDeAudio p12 = new PistaDeAudio(12,	"Yo Canibal", 258, "Patricio rey y sus redonditos de ricota",	"Lobo Suelto, Cordero atado",	 1993, "Rock Nacional",		"");

		
		coleccion.agregarPista(p1);
		coleccion.agregarPista(p2);
		coleccion.agregarPista(p3);
		coleccion.agregarPista(p4);
		coleccion.agregarPista(p5);
		coleccion.agregarPista(p6);
		coleccion.agregarPista(p7);
		coleccion.agregarPista(p8);
		coleccion.agregarPista(p9);
		coleccion.agregarPista(p10);
		coleccion.agregarPista(p11);
		coleccion.agregarPista(p12);
	
//		Definir las siguientes listas de reproducción en el orden especificado: 
//		       Clásicos del Rock: Nº1, 2, 8, 9, 10 y 12.
//			   Lo mejor: Nº 3, 4, 7 y 12.
//			   El Indio: Nº 11 y 12.
		
		Playlist play1 = coleccion.nuevaPlaylist("Clásicos del rock");
		Playlist play2 = coleccion.nuevaPlaylist("Lo mejor");
		Playlist play3 = coleccion.nuevaPlaylist("Coldplay");
		Playlist play4 = coleccion.nuevaPlaylist("EL Indio");

		play1.agregarMusica(p1);
		play1.agregarMusica(p2);
		play1.agregarMusica(p8);
		play1.agregarMusica(p9);
		play1.agregarMusica(p10);
		play1.agregarMusica(p12);
		play2.agregarMusica(p3);
		play2.agregarMusica(p4);
		play2.agregarMusica(p7);
		play2.agregarMusica(p12);
		play3.agregarMusica(p5);
		play3.agregarMusica(p6);
		play3.agregarMusica(p7);
		play4.agregarMusica(p12);
		play4.agregarMusica(p11);

 //3) Imprimir por pantalla las listas “Clásicos del Rock”, “Lo mejor” y “Coldplay”.
		System.out.println("3) Imprimir por pantalla las listas “Clásicos del Rock”, “Lo mejor” y “Coldplay”.");
		System.out.println("Clásicos del rock: \n");
		System.out.println(play1);
		System.out.println("Lo mejor:  \n");
		System.out.println(play2);
		System.out.println("Coldplay:  \n");
		System.out.println(play3);
		System.out.println("--------------------------------------------------------------------------------------------------------");

// 4) Imprimir por pantalla la duración total de cada playlist definida.
		System.out.println("4) Imprimir por pantalla la duración total de cada playlist definida. \n");
		System.out.println("Clásicos del rock: " + play1.getDuracion() + "\n");
		System.out.println("Lo mejor:  " + play2.getDuracion() + "\n");
		System.out.println("Coldplay: " + play3.getDuracion() + "\n");
		System.out.println("El Indio: " + play4.getDuracion() + "\n");
		System.out.println("-------------------------------------------------------------------------------------------------------- \n");
		System.out.println("///////////////////////////////////////////////////////////////////////////////////////// \n");
		
//Parte 2: 1) Ejecutar las siguientes búsquedas e imprimir los resultados por pantalla.		
		//a) Las pistas cuya duración sea superior a 400 segundos.
        System.out.println("Parte 2, 1) Ejecutar las siguientes búsquedas e imprimir los resultados por pantalla: \n");
	    System.out.println("a) Las pistas cuya duración sea superior a 400 segundos. \n");
		Filtro filtro_duracion_mayor_400 = new FiltroDuracion(400, new Mayor());
		System.out.println(coleccion.buscar(filtro_duracion_mayor_400));
		System.out.println("--------------------------------------------------------------------------------------------------------");

	// b) Las pistas cuyo género contenga la palabra “rock”.
		System.out.println("b) Las pistas cuyo género contenga la palabra “rock”. \n");
		Filtro filtro_genero_rock = new FiltroGenero("rock");
		System.out.println(coleccion.buscar(filtro_genero_rock));
		System.out.println("--------------------------------------------------------------------------------------------------------");

	// c) Las pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”.
		System.out.println("c) Las pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”. \n");
		Filtro filtro_union1 = new FiltroAND(filtro_genero_rock, new FiltroNOT(new FiltroInterprete("LMFAO")));
		System.out.println(coleccion.buscar(filtro_union1));
		System.out.println("--------------------------------------------------------------------------------------------------------");

	// d) Las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”.
		System.out.println(" d) Las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”. \n");
		Filtro filtro_union2 = new FiltroAND(filtro_genero_rock, new FiltroAnio(2006, new Mayor()));
		Filtro filtro_union3 = new FiltroAND(filtro_genero_rock, new FiltroInterprete("Coldplay"));
		Filtro filtro_interseccion = new FiltroOR(filtro_union2, filtro_union3);
		System.out.println(coleccion.buscar(filtro_interseccion));
		System.out.println("--------------------------------------------------------------------------------------------------------");
	
// 2) Agregar una nueva pista al Sistema con la descripción dada e imprimir nuevamente las búsquedas del punto 1.
		System.out.println("2) Agregar una nueva pista al Sistema con la descripción dada e imprimir nuevamente las búsquedas del punto 1. \n");
	    coleccion.agregarPista(new PistaDeAudio(13, "Paradise", 365, "Coldplay", "Mylo Xyloto", 2011, "Rock Alternativo", ""));
	  //a) Las pistas cuya duración sea superior a 400 segundos.
	    System.out.println("a) Las pistas cuya duración sea superior a 400 segundos. \n");
		System.out.println(coleccion.buscar(filtro_duracion_mayor_400));
		System.out.println("--------------------------------------------------------------------------------------------------------");

	// b) Las pistas cuyo género contenga la palabra “rock”.
		System.out.println("b) Las pistas cuyo género contenga la palabra “rock”. \n");
		System.out.println(coleccion.buscar(filtro_genero_rock));
		System.out.println("--------------------------------------------------------------------------------------------------------");

	// c) Las pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”.
		System.out.println("c) Las pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”. \n");
		System.out.println(coleccion.buscar(filtro_union1));
		System.out.println("--------------------------------------------------------------------------------------------------------");

	// d) Las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”.
		System.out.println(" d) Las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”. \n");
		System.out.println(coleccion.buscar(filtro_interseccion));
		
	}
}
