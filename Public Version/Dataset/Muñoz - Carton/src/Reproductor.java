package practicoEspecial;

public class Reproductor {

	public static void main(String[] args) {
		ElementoMusica rock = new ListaReproduccion("Clasicos del Rock");
		ElementoMusica loMejor = new ListaReproduccion("Lo mejor");
		ElementoMusica coldp = new ListaReproduccion("Coldplay");
		ElementoMusica indio = new ListaReproduccion("El Indio");

		ElementoMusica p1 = new Pista(1, "El Tiempo No Para", 311, "Bersuit Vergabarat", "De la cabeza", 2002,
				"Rock Nacional");
		ElementoMusica p2 = new Pista(2, "Mi Caramelo", 290, "Bersuit Vergabarat", "De la cabeza", 2002,
				"Rock Nacional");
		ElementoMusica p3 = new Pista(3, "Party Rock Anthem", 408, "LMFAO", "Sorry for Party Rocking", 2011,
				"Electro pop");
		ElementoMusica p4 = new Pista(4, "Sorry for Party Rocking", 421, "LMFAO", "Sorry for Party Rocking", 2011,
				"Electro pop");
		ElementoMusica p5 = new Pista(5, "Fix you", 255, "Coldplay", "Y&Y", 2005, "Rock alternativo");
		ElementoMusica p6 = new Pista(6, "Speed of sound", 455, "Coldplay", "Y&Y", 2005, "Rock alternativo");
		ElementoMusica p7 = new Pista(7, "Viva la vida", 320, "Coldplay", "Viva la vida", 2008, "Rock alternativo");
		ElementoMusica p8 = new Pista(8, "Whit or whitout you", 360, "U2", "The Joshua Tree", 1987, "Rock");
		ElementoMusica p9 = new Pista(9, "Vertigo", 355, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock");
		ElementoMusica p10 = new Pista(10, "City of Blinding Lights", 284, "U2", "How to Dismantle an Atomic Bomb",
				2004, "Rock");
		ElementoMusica p11 = new Pista(11, "A la luz de la luna", 438, "El Indio Solari",
				"Pajaritos, bravos muchachitos", 2013, "Rock Nacional");
		ElementoMusica p12 = new Pista(12, "Yo canibal", 258, "Patricio rey y sus redonditos de ricota",
				"Lobo suelto, Cordero atado", 1993, "Rock Nacional");

		rock.addElement(p1);
		rock.addElement(p2);
		rock.addElement(p8);
		rock.addElement(p9);
		rock.addElement(p10);
		rock.addElement(p12);

		loMejor.addElement(p3);
		loMejor.addElement(p4);
		loMejor.addElement(p7);
		loMejor.addElement(p12);

		coldp.addElement(p5);
		coldp.addElement(p6);
		coldp.addElement(p7);

		indio.addElement(p12);
		indio.addElement(p11);

		System.out.println(rock.toString());
		System.out.println("La duracion de la pista " + rock.getNombre() + " es " + rock.getDuracion() + ".");
		System.out.println(loMejor.toString());
		System.out.println("La duracion de la pista " + loMejor.getNombre() + " es " + loMejor.getDuracion() + ".");
		System.out.println(coldp.toString());
		System.out.println("La duracion de la pista " + coldp.getNombre() + " es " + coldp.getDuracion() + ".");

		// System.out.println(rock.toString());

		// Ejercicio 1) a)
		Condicion a = new CondicionDuracionMayor(400);
		System.out.println("Pistas cuya duracion sea superior a 400 segundos: ");
		System.out.println("Pista " + rock.getNombre() + " " + rock.buscar(a));
		System.out.println("Pista " + loMejor.getNombre() + " " + loMejor.buscar(a));
		System.out.println("Pista " + coldp.getNombre() + " " + coldp.buscar(a));

		// Ejercicio 1) b)
		Condicion b = new CondicionGenero("rock");
		System.out.println("Pistas cuyo genero contenga la palabra 'rock':");
		System.out.println("Pista " + rock.getNombre() + " " + rock.buscar(a));
		System.out.println("Pista " + loMejor.getNombre() + " " + loMejor.buscar(a));
		System.out.println("Pista " + coldp.getNombre() + " " + coldp.buscar(a));

		// Ejercicio 1) c)
		Condicion nombreRock = new CondicionNombre("rock");
		Condicion artistaLMFAO = new CondicionArtista("LMFAO");
		Condicion notArtista = new CondicionNot(artistaLMFAO);
		Condicion c = new CondicionAnd(nombreRock, notArtista);
		System.out.println("Pistas cuyo nombre contenga la palabra 'rock' pero cuyo interprete no sea 'LMFAO:");
		System.out.println("Pista " + rock.getNombre() + " " + rock.buscar(a));
		System.out.println("Pista " + loMejor.getNombre() + " " + loMejor.buscar(a));
		System.out.println("Pista " + coldp.getNombre() + " " + coldp.buscar(a));

		// Ejercicio 1) d)

		Condicion generoRock = new CondicionGenero("rock");
		Condicion fecha = new CondicionAnioMayor(2006);
		Condicion artista = new CondicionArtista("coldplay");
		Condicion primera = new CondicionAnd(generoRock, fecha);
		Condicion segunda = new CondicionAnd(generoRock, artista);

		Condicion d = new CondicionOr(primera, segunda);

		System.out.println(
				"Pistas cuyo genero contenga la palabra 'rock' y cuya fecha sea mayor a 2006, o cuyo genero contenga 'rock' y cuyo interprete sea 'Coldplay':");
		System.out.println("Pista " + rock.getNombre() + " " + rock.buscar(a));
		System.out.println("Pista " + loMejor.getNombre() + " " + loMejor.buscar(a));
		System.out.println("Pista " + coldp.getNombre() + " " + coldp.buscar(a));

		System.out.println("Insertamos la nueva pista e imprimimos todo de nuevo");
		ElementoMusica p13 = new Pista(1, "Paradise", 365, "Coldplay", "Mylo Xyloto ", 2011, "Rock Alternativo");
		coldp.addElement(p13);
		System.out.println("Pistas cuya duracion sea superior a 400 segundos: ");
		System.out.println(rock.buscar(a));
		System.out.println(loMejor.buscar(a));
		System.out.println(coldp.buscar(a));

		System.out.println("Pistas cuyo genero contenga la palabra 'rock':");
		System.out.println(rock.buscar(b));
		System.out.println(loMejor.buscar(b));
		System.out.println(coldp.buscar(b));

		System.out.println("Pistas cuyo nombre contenga la palabra 'rock' pero cuyo interprete no se 'LMFAO:");
		System.out.println(rock.buscar(c));
		System.out.println(loMejor.buscar(c));
		System.out.println(coldp.buscar(c));

		System.out.println(
				"Pistas cuyo genero contenga la palabra 'rock' y cuya fecha sea mayor a 2006, o cuyo genero contenga 'rock' y cuyo interprete sea 'Coldplay':");
		System.out.println(rock.buscar(d));
		System.out.println(loMejor.buscar(d));
		System.out.println(coldp.buscar(d));

	}

}
