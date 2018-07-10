package trabajoPracticoEspecial;

import java.util.*;

public class Play {
	
	
	public static void main(String[] args) {
		
		//1) Definir pistas:
		ElementoMusical p1 = new Pista(1, "El tiempo no para", 311, "Bersuit Vergarabat", "De la Cabeza", 2002, "Rock Nacional");
		ElementoMusical p2 = new Pista(2, "Mi caramelo", 290, "Bersuit Vergarabat", "De la Cabeza", 2002, "Rock Nacional");
		ElementoMusical p3 = new Pista(3, "Party Rock", 408, "LMFAO", "Sorry for Party Rocking",2011, "Electro pop");
		ElementoMusical p4 = new Pista(4, "Sorry for Party Rocking", 421, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop");
		ElementoMusical p5 = new Pista(5, "Fix You", 255,"Coldplay" ,"X&Y", 2005, "Rock alternativo");
		ElementoMusical p6 = new Pista(6, "Speed of Sound", 455, "Coldplay", "X&Y", 2005, "Rock alternativo");
		ElementoMusical p7 = new Pista(7, "Viva la vida", 320, "Coldplay", "Viva la vida", 2008, "Rock alternativo");
		ElementoMusical p8 = new Pista(8, "With or without you", 360, "U2", "The Joshua Tree", 1987, "Rock");
		ElementoMusical p9 = new Pista(9, "Vertigo", 355, "U2", "How to dismantle an Atomic Bomb", 2004, "Rock");
		ElementoMusical p10 = new Pista(10, "City of blinding lights", 284, "U2", "How to dismantle an Atomic Bomb", 2004, "Rock");
		ElementoMusical p11 = new Pista(11, "A la luz de la luna", 438, "Indio Solari", "Pajaritos, bravos muchachitos", 2013, "Rock nacional");
		ElementoMusical p12 = new Pista(12, "Yo canibal", 258, "Patricio Rey y sus redonditos de ricota", "Lobo suelto, cordero atado", 1993, "Rock Nacional");
		
		PlayList reproductor = new PlayList("Reproductor");
		
		reproductor.addElemento(p1);
		reproductor.addElemento(p2);
		reproductor.addElemento(p3);
		reproductor.addElemento(p4);
		reproductor.addElemento(p5);
		reproductor.addElemento(p6);
		reproductor.addElemento(p7);
		reproductor.addElemento(p8);
		reproductor.addElemento(p9);
		reproductor.addElemento(p10);
		reproductor.addElemento(p11);
		reproductor.addElemento(p12);
		 
		
		//2) Crear playLists:
		PlayList pl1= new PlayList("Clasicos del Rock");
		pl1.addElemento(p1);
		pl1.addElemento(p2);
		pl1.addElemento(p8);
		pl1.addElemento(p9);
		pl1.addElemento(p10);
		pl1.addElemento(p12);
		
		
		PlayList pl2 = new PlayList("Lo mejor");
		pl2.addElemento(p3);
		pl2.addElemento(p4);
		pl2.addElemento(p7);
		pl2.addElemento(p12);
		
		PlayList pl3 = new PlayList("Coldplay");
		pl3.addElemento(p5);
		pl3.addElemento(p6);
		pl3.addElemento(p7);
		
		PlayList pl4 = new PlayList("El Indio");
		pl4.addElemento(p11);
		pl4.addElemento(p12);
		
		//Se agregan las playlist al preproductor
		reproductor.addElemento(pl1);
		reproductor.addElemento(pl2);
		reproductor.addElemento(pl3);
		reproductor.addElemento(pl4);
	
		
		//Eliminar pistas de las playlist:
		/*pl1.eliminarTodo();
		pl2.eliminarElemento(p3);
		pl2.eliminarElemento(p4);
		pl2.eliminarElemento(p7);*/
		
	
		
		// 3) Imprimir las playlists
		System.out.println(pl1.imprimirPistas());
		System.out.println(pl2.imprimirPistas());
		System.out.println(pl3.imprimirPistas());
		
		//4) imprimir duracion
		System.out.println( "Duracion de " + pl1.getNombre() + " " + pl1.getDuracion() + " Segundos");
		System.out.println( "Duracion de " + pl2.getNombre() + " " + pl2.getDuracion() + " Segundos");
		System.out.println( "Duracion de " + pl3.getNombre() + " " + pl3.getDuracion() + " Segundos");
		System.out.println( "Duracion de " + pl4.getNombre() + " " + pl4.getDuracion() + " Segundos");
			
		// Segunda Parte
	
		//1)
		
		Condicion c1 = new CondicionDuracionMayorQue(400);
		Condicion c2 = new CondicionGenero("rock");
		Condicion c3 = new CondicionTitulo("rock");
		Condicion c4 = new CondicionInterprete("LMFAO");
		Condicion c5 = new CondicionNot(c4);
		Condicion c6 = new CondicionAnd(c5, c3);
		Condicion c7 = new CondicionAñoMayorQue(2006);
		Condicion c8 = new CondicionAnd(c2,c7);
		Condicion c9 = new CondicionInterprete("Coldplay");
		Condicion c10 = new CondicionAnd(c2, c9);
		Condicion c11 = new CondicionOr(c8,c10);
		
		System.out.println("a) Duracion mayor a 400 segundos.");
		
		Vector <Pista> busqueda = new Vector <Pista>(reproductor.buscarPistas(c1));		
		imprimirResultado(busqueda);
		
		System.out.println("b) Pistas cuyo genero contenga la palabra rock.");

		busqueda = reproductor.buscarPistas(c2);
		imprimirResultado(busqueda);
	
		System.out.println("c)Pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”.");

		busqueda = reproductor.buscarPistas(c6);
		imprimirResultado(busqueda);
		
		System.out.println("d)Pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”.");
		
		busqueda = reproductor.buscarPistas(c11);
		imprimirResultado(busqueda);
		
		//2) 

		Pista p13 = new Pista(13, "Paradise", 365, "Coldplay", "Mylo Xyloto", 2011, "Rock Alternativo");
		
		reproductor.addElemento(p13);
		
		
		System.out.println("2a) Duracion mayor a 400 segundos.");

		busqueda = reproductor.buscarPistas(c1);
		imprimirResultado(busqueda);
		
		System.out.println("2b) Pistas cuyo genero contenga la palabra rock.");

		busqueda = reproductor.buscarPistas(c2);
		imprimirResultado(busqueda);
	
		System.out.println("2c)Pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”.");

		busqueda = reproductor.buscarPistas(c6);
		imprimirResultado(busqueda);

		
		System.out.println("2d)Pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”.");
		
		busqueda = reproductor.buscarPistas(c11);
		imprimirResultado(busqueda);
		
	}

	private static void imprimirResultado(Vector<Pista> busqueda) {
		
		for (Pista pista : busqueda) {
			
			System.out.println(pista.imprimirPistas());
			
		}
		
		System.out.println("");
		
	}

}
