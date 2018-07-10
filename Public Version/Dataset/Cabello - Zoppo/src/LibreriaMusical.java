import java.util.*;

public class LibreriaMusical {

	public static void main(String[] args) {
		
		/* 1- Creo las canciones */
		Pista p1 = new Pista (1, "El tiempo no para", 311, "Bersuit Vergarabat", "De la cabeza", 2002, "Rock nacional");
		Pista p2 = new Pista (2, "Mi caramelo", 290, "Bersuit Vergarabat", "De la cabeza", 2002, "Rock nacional");
		Pista p3 = new Pista (3, "Party rock anthem", 408, "LMFAO", "Sorry for party rocking", 2011, "Electro pop");
		Pista p4 = new Pista (4, "Sorry for party rocking", 421, "LMFAO", "Sorry for party pocking", 2011, "Electro pop");
		Pista p5 = new Pista (5, "Fix you", 255, "Coldplay", "X&Y", 2005, "Rock alternativo");
		Pista p6 = new Pista (6, "Speed of sound", 455, "Coldplay", "X&Y", 2005, "Rock alternativo");
		Pista p7 = new Pista (7, "Viva la vida", 320, "Coldplay", "Viva la vida", 2008, "Rock alternativo");
		Pista p8 = new Pista (8, "Whit or whitout you", 360, "U2", "The Joshua tree", 1987, "Rock");
		Pista p9 = new Pista (9, "Vertigo", 355, "U2", "How to dismantle an atomic bomb", 2004, "Rock");
		Pista p10 = new Pista (10, "City of blinding lights", 284, "U2", "How to dismantle an atomic bomb", 2004, "Rock");
		Pista p11 = new Pista (11, "A la luz de la luna", 438, "Indio Solari", "Pajaritos, bravos muchachitos", 2013, "Rock nacional");
		Pista p12 = new Pista (12, "Yo canibal", 258, "Patricio rey y sus redonditos de ricota", "Lobo suelto, cordero atado", 1993, "Rock nacional");
		Pista p13 = new Pista (13, "Paradise", 365, "Coldplay","Mylo Xyloto", 2011,"Rock alternativo");
		
		/* 2- Creo la coleccion total y le agrego las 12 canciones */
		Coleccion miMusica = new Coleccion();
		miMusica.addPista(p1);
		miMusica.addPista(p2);
		miMusica.addPista(p3);
		miMusica.addPista(p4);
		miMusica.addPista(p5);
		miMusica.addPista(p6);
		miMusica.addPista(p7);
		miMusica.addPista(p8);
		miMusica.addPista(p9);
		miMusica.addPista(p10);
		miMusica.addPista(p11);
		miMusica.addPista(p12);
		miMusica.addPista(p13);
		
		/* 2- Creo las PlayList, les agrego las canciones */
		PlayList pl1 = new PlayList("Clasicos del Rock");
		pl1.addPista(p1);
		pl1.addPista(p2);
		pl1.addPista(p8);
		pl1.addPista(p9);
		pl1.addPista(p10);
		pl1.addPista(p12);
		
		PlayList pl2 = new PlayList("Lo mejor");
		pl2.addPista(p3);
		pl2.addPista(p4);
		pl2.addPista(p7);
		pl2.addPista(p12);
		
		PlayList pl3 = new PlayList("Coldplay");
		pl3.addPista(p5);
		pl3.addPista(p6);
		pl3.addPista(p7);
		
		PlayList pl4 = new PlayList("El indio");
		pl4.addPista(p12);
		pl4.addPista(p11);
		
		/* 3- Imprimo por pantalla las PlayList */
		pl1.printElemento();
		System.out.println("");
		pl2.printElemento();
		System.out.println("");
		pl3.printElemento();
		
		/* 4- Imprimo por pantalla las duraciones */
		System.out.println("- - -");
		System.out.println("La duracion de la pista " + pl1.getNombre() + "es: " + pl1.getDuracion());
		System.out.println("La duracion de la pista " + pl2.getNombre() + "es: " + pl2.getDuracion());
		System.out.println("La duracion de la pista " + pl3.getNombre() + "es: " + pl3.getDuracion());
		System.out.println("La duracion de la pista " + pl4.getNombre() + "es: " + pl4.getDuracion());

		/* 5- Parte 2 - Busquedas */

		/* a) */
		PorDuracionMayor condicionFinalA = new PorDuracionMayor(400);
		ArrayList<Pista> busquedaA = miMusica.resultadoBusqueda(condicionFinalA);
		System.out.println("- - -");
		System.out.println("Las pistas con duracion mayor a 400sec son:");
		
		if (busquedaA.size() == 0 ){
			System.out.println("No se encontro ninguna pista");
		}else
		{
			for (int i = 0; i < busquedaA.size(); i++) {
				busquedaA.get(i).printElemento();
			}
		}
		/* b) */ 
		PorGenero condicionFinalB = new PorGenero("Rock");
		ArrayList<Pista> busquedaB = miMusica.resultadoBusqueda(condicionFinalB);
		System.out.println("- - -");
		System.out.println("Las pistas del genero Rock:");
		
		if (busquedaB.size() == 0 ){
			System.out.println("No se encontro ninguna pista");
		}else
		{
			for (int i = 0; i < busquedaB.size(); i++) {
				busquedaB.get(i).printElemento();
			}
		}
		
		/* c) */
		
		PorNombre condicionC1 = new PorNombre("Rock");
		PorArtista condicionC2 = new PorArtista("LMFAO");
		Not condicionNOT = new Not(condicionC2);
		And condicionFinalC = new And(condicionC1, condicionNOT);

		ArrayList<Pista> busquedaC = miMusica.resultadoBusqueda(condicionFinalC);
		System.out.println("- - -");
		System.out.println("Las pistas que contienen la palabra 'rock' y no 'LMFAO' son :");
		
		if (busquedaC.size() == 0 ){
			System.out.println("No se encontro ninguna pista");
		}else
		{
			for (int i = 0; i < busquedaC.size(); i++) {
				busquedaC.get(i).printElemento();
			}
		}

		/* d) */
		
		PorGenero condicionG = new PorGenero("rock");
		PorAñoMayor condicionPorAñoMayor= new PorAñoMayor(2006);
		PorArtista condicionArt = new PorArtista("Coldplay");

		Or condicionOr = new Or(condicionPorAñoMayor, condicionArt);              
		And condicionFinalD = new And(condicionG, condicionOr);				/* A & (B | C) */
		
		ArrayList<Pista> busquedaD = miMusica.resultadoBusqueda(condicionFinalD);
		
		System.out.println("- - -");
		System.out.println("Las pistas que contienen la palabra 'rock' y su fecha es mayor a '2006'");
		System.out.println("o cuyo genero contiene la palabra 'rock' cuyo interprete sea 'coldplay':");
		
		
		if (busquedaD.size() == 0 ){
			System.out.println("No se encontro ninguna pista");
		}else
		{
			for (int i = 0; i < busquedaD.size(); i++) {
				busquedaD.get(i).printElemento();
			}
		}
		
	}

}

