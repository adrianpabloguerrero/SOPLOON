import java.util.*;

public class Libreria {

	public static void main(String[] args) {

		/* CANCIONES */
		Pista p1 = new Pista (1, "Cant Stop", 269, "Red Hot Chili Peppers", "By the way", 2000, "Rock");
		Pista p2 = new Pista (2, "Color Esperanza", 260, "Diego Torres", "Mis Favoritas", 2005, "Rock nacional");
		Pista p3 = new Pista (3, "Cake by the ocean", 210, "DNCE", "SWAAY", 2016, "Electro pop");
		Pista p4 = new Pista (4, "Enter Sadman", 320, "Metallica", "Black Album", 2003, "Metal");
		Pista p5 = new Pista (5, "A fuego lento", 210, "Rosana", "Lunas Rotas", 2010, "Balada");
		Pista p6 = new Pista (6, "Dont speak", 420, "No Doubt", "Tragic Kingdom", 2004, "Rock alternativo");
		Pista p7 = new Pista (7,"Uprising", 360, "Muse", "The Resistance", 2007, "Rock alternativo");
		Pista p8 = new Pista (8, "Wake me up when september ends", 370, "Green Day", "American Idiot", 2006, "Rock");
		Pista p9 = new Pista (9,"Todos A Los Botes", 258, "Patricio rey y los fundaentalistas del aire acondicionado", "El perfume de la tempestad", 2012, "Rock nacional");
		Pista p10 = new Pista (10, "We are the champions", 284, "Queen", "Rhapsodia Bohemia", 1986, "Rock");
		Pista p11 = new Pista (11, "Dos dias en la vida", 379, "Fito Paez", "El amor despues del amor", 2002, "Rock nacional");
		Pista p12 = new Pista (12, "JIJIJI", 500, "El Indio Solari", "Oktubre", 2008, "Rock Nacional");
		Pista p13 = new Pista (13,"Viva la vida", 320, "Coldplay", "Viva la vida", 2008, "Rock alternativo");
		/* COLECCION a la que le agrego las CANCIONES*/
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

		/* Playlist para canciones */
		Playlist pl1 = new Playlist("Clasicos del Rock");
		pl1.addPista(p1);
		pl1.addPista(p2);
		pl1.addPista(p8);
		pl1.addPista(p9);
		pl1.addPista(p10);
		pl1.addPista(p12);

		Playlist pl2 = new Playlist("Lo mejor");
		pl2.addPista(p3);
		pl2.addPista(p4);
		pl2.addPista(p7);
		pl2.addPista(p12);

		Playlist pl3 = new Playlist("Coldplay");
		pl3.addPista(p5);
		pl3.addPista(p6);
		pl3.addPista(p7);

		Playlist pl4 = new Playlist("El indio");
		pl4.addPista(p12);
		pl4.addPista(p11);

		/* Prints*/
		pl1.printElemento();
		System.out.println("");
		pl2.printElemento();
		System.out.println("");
		pl3.printElemento();

		/* Print Duraciones */
		System.out.println("- - -");
		System.out.println("La duracion de  " + pl1.getNombre() + "es: " + pl1.getDuracion());
		System.out.println("La duracion de  " + pl2.getNombre() + "es: " + pl2.getDuracion());
		System.out.println("La duracion de  " + pl3.getNombre() + "es: " + pl3.getDuracion());
		System.out.println("La duracion de  " + pl4.getNombre() + "es: " + pl4.getDuracion());

		/* Busqueda */

		DuracionMayor condicionFinalA = new DuracionMayor(400);
		ArrayList<Pista> busquedaA = miMusica.resultadoBusqueda(condicionFinalA);
		System.out.println("- - -");
		System.out.println("Las pistas con duracion mayor a 400 segundos son:");

		if (busquedaA.size() == 0 ){
			System.out.println("No se encontro ninguna pista");
		}else
		{
			for (int i = 0; i < busquedaA.size(); i++) {
				busquedaA.get(i).printElemento();
			}
		}
		Genero condicionFinalB = new Genero("Rock");
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

		Nombre condicionC1 = new Nombre("Rock");
		Artista condicionC2 = new Artista("Muse");
		Not condicionNOT = new Not(condicionC2);
		And condicionFinalC = new And(condicionC1, condicionNOT);

		ArrayList<Pista> busquedaC = miMusica.resultadoBusqueda(condicionFinalC);
		System.out.println("- - -");
		System.out.println("Las pistas que contienen la palabra 'rock' y no 'Muse' son : ");

		if (busquedaC.size() == 0 ){
			System.out.println("No se encontro ninguna pista");
		}else
		{
			for (int i = 0; i < busquedaC.size(); i++) {
				busquedaC.get(i).printElemento();
			}
		}


		Genero condicionG = new Genero("rock");
		AñoMayor condicionPorAñoMayor= new AñoMayor(2006);
		Artista condicionArt = new Artista("Coldplay");

		Or condicionOr = new Or(condicionPorAñoMayor, condicionArt);
		And condicionFinalD = new And(condicionG, condicionOr);				/* A & (B | C) */

		ArrayList<Pista> busquedaD = miMusica.resultadoBusqueda(condicionFinalD);

		System.out.println("- - -");
		System.out.println("Las pistas que contienen la palabra 'rock' y su fecha es mayor a '2006'");
		System.out.println("o cuyo genero contiene la palabra 'rock'y cuyo interprete sea 'coldplay':");


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
