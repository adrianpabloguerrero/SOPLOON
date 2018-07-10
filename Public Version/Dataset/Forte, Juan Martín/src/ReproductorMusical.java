import java.util.Vector;

public class ReproductorMusical {

	public static Vector<Cancion> filtrar(Criterio c, Vector<Cancion> canciones){

		Vector<Cancion> aDevolver = new Vector<>();

		for (Cancion cancion : canciones) {
			if (c.cumple(cancion)){
				aDevolver.add(cancion);
			}
		}return aDevolver;
	}

	public static void imprimir(Vector<Cancion> filtradas){

		for(Cancion temas : filtradas){
			System.out.println("Cancion: "+temas.getNombre());

		}if(filtradas.isEmpty()){
			System.out.println("*No se encontro ninguna cancion.*");
		}
	}

	public static void main(String[] args) {

		Cancion c1 = new Cancion(1, "El Tiempo No Para", 311, "Bersuit Vergarabat", "De la cabeza", 2002, "Rock Nacional", "*comentario*");
		Cancion c2 = new Cancion(2, "Mi caramelo", 290, "Bersuit Vergarabat", "De la cabeza", 2002, "Rock Nacional", "*comentario*");
		Cancion c3 = new Cancion(3, "Party Rock Anthem", 408, "LMFAO", "Sorry for Party Rocking", 2011, "Electro Pop", "*comentario*");
		Cancion c4 = new Cancion(4, "Sorry for Party Rocking", 421, "LMFAO", "Sorry for Party Rocking", 2011, "Electro Pop", "*comentario*");
		Cancion c5 = new Cancion(5, "Fix you", 255, "Coldplay", "X&Y", 2005, "Rock alternativo", "*comentario*");
		Cancion c6 = new Cancion(6, "Speed of Sound", 455, "Coldplay", "X&Y", 2005, "Rock alternativo", "*comentario*");
		Cancion c7 = new Cancion(7, "Viva la vida", 320, "Coldplay", "Viva la vida", 2008, "Rock alternativo", "*comentario*");
		Cancion c8 = new Cancion(8, "Whit or whitout you", 360, "U2", "The Joshua Tree", 1987, "Rock", "*comentario*");
		Cancion c9 = new Cancion(9, "Vertigo", 355, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock", "*comentario*");
		Cancion c10 = new Cancion(10, "City of Blinding Lights", 284, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock", "*comentario*");
		Cancion c11 = new Cancion(11, "A la luz de la luna", 438, "El Indio Solari", "Pajaritos, bravos muchachitos", 2013, "Rock Nacional", "*comentario*");
		Cancion c12 = new Cancion(12, "Yo Canibal", 258, "Patricio rey y sus redonditos de ricota", "Lobo Suelto, Cordero atado", 1993, "Rock Nacional", "*comentario*");

		Playlist p1 = new Playlist("Clasicos del Rock");
		Playlist p2 = new Playlist("Lo mejor");
		Playlist p3 = new Playlist("Coldplay");
		Playlist p4 = new Playlist("El Indio");

		p1.addElemento(c1);
		p1.addElemento(c2);
		p1.addElemento(c8);
		p1.addElemento(c9);
		p1.addElemento(c10);
		p1.addElemento(c12);

		p2.addElemento(c3);
		p2.addElemento(c4);
		p2.addElemento(c7);
		p2.addElemento(c12);

		p3.addElemento(c5);
		p3.addElemento(c6);
		p3.addElemento(c7);

		p4.addElemento(c12);
		p4.addElemento(c11);

		System.out.println("Parte 1 Trabajo Especial:");
		System.out.println();

		System.out.println(p1.getNombre() +":");
		p1.imprimir();
		System.out.println();
		System.out.println(p2.getNombre() +":");
		p2.imprimir();
		System.out.println();
		System.out.println(p3.getNombre() +":");
		p3.imprimir();
		System.out.println();

		System.out.println("La duracion de '" +p1.getNombre() +"' es: " +p1.getDuracion() +" segundos.");
		System.out.println();
		System.out.println("La duracion de '" +p2.getNombre() +"' es: " +p2.getDuracion() +" segundos.");
		System.out.println();
		System.out.println("La duracion de '" +p3.getNombre() +"' es: " +p3.getDuracion() +" segundos.");
		System.out.println();
		System.out.println("La duracion de '" +p4.getNombre() +"' es: " +p4.getDuracion() +" segundos.");
		System.out.println();

		//Parte 2.

		System.out.println();
		System.out.println("Parte 2 Trabajo Especial:");
		System.out.println();

		Vector<Cancion> canciones = new Vector<Cancion>();
		canciones.addElement(c1);
		canciones.addElement(c2);
		canciones.addElement(c3);
		canciones.addElement(c4);
		canciones.addElement(c5);
		canciones.addElement(c6);
		canciones.addElement(c7);
		canciones.addElement(c8);
		canciones.addElement(c9);
		canciones.addElement(c10);
		canciones.addElement(c11);
		canciones.addElement(c12);

		System.out.println("Las pistas cuya duración sea superior a 400 segundos: ");

		CriterioTiempoMayor criterio1 = new CriterioTiempoMayor(400);
		Vector<Cancion> filtradas = filtrar(criterio1,canciones);
		imprimir(filtradas);

		System.out.println();
		System.out.println("Las pistas cuyo género contenga la palabra “rock”: ");

		CriterioGenero criterio2 = new CriterioGenero("rock");
		Vector<Cancion> filtradas2 = filtrar(criterio2,canciones);
		imprimir(filtradas2);

		System.out.println();
		System.out.println("Las pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”: ");

		CriterioTitulo criterio3 = new CriterioTitulo("rock");
		Vector<Cancion> filtradas3 = filtrar(criterio3,canciones);

		CriterioArtista criterio4 = new CriterioArtista("LMFAO");
		Vector<Cancion> filtradas4 = filtrar(criterio4,canciones);

		CriterioNOT criterio5 = new CriterioNOT(criterio4);
		Vector<Cancion> filtradas5 = filtrar(criterio5,canciones);

		CriterioAND criterio6 = new CriterioAND(criterio3, criterio5);
		Vector<Cancion> filtradas6 = filtrar(criterio6,canciones);
		imprimir(filtradas6);

		System.out.println();
		System.out.println("Las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”: ");

		CriterioFecha criterio7 = new CriterioFecha(2006);
		Vector<Cancion> filtradas7 = filtrar(criterio7,canciones); //Mayor a 20006

		CriterioAND criterio8 = new CriterioAND(criterio2, criterio7);
		Vector<Cancion> filtradas8 = filtrar(criterio8,canciones); //Hace el and, genero rock y mayor a 2006.

		CriterioArtista criterio9 = new CriterioArtista("Coldplay");
		Vector<Cancion> filtradas9 = filtrar(criterio9,canciones);

		CriterioAND criterio10 = new CriterioAND(criterio2, criterio9);
		Vector<Cancion> filtradas10 = filtrar(criterio10,canciones);

		CriterioOR criterio11 = new CriterioOR(criterio8, criterio10);
		Vector<Cancion> filtradas11 = filtrar(criterio11,canciones);
		imprimir(filtradas11);

		Cancion c13 = new Cancion(13, "Paradise", 365, "Coldplay", "Mylo Xyloto", 2011, "Rock alternativo", "*comentario*");
		canciones.addElement(c13);

		//---------------------------------------------------------------------------------------------------------------------------------------------//
		
		System.out.println();
		System.out.println("#Nueva busqueda#");
		System.out.println();

		System.out.println("Las pistas cuya duración sea superior a 400 segundos: ");

		CriterioTiempoMayor criterio12 = new CriterioTiempoMayor(400);
		Vector<Cancion> filtradas12 = filtrar(criterio12,canciones);
		imprimir(filtradas12);

		System.out.println();
		System.out.println("Las pistas cuyo género contenga la palabra “rock”: ");

		CriterioGenero criterio13 = new CriterioGenero("rock");
		Vector<Cancion> filtradas13 = filtrar(criterio13,canciones);
		imprimir(filtradas13);

		System.out.println();
		System.out.println("Las pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”: ");

		CriterioTitulo criterio14 = new CriterioTitulo("rock");
		Vector<Cancion> filtradas14 = filtrar(criterio14,canciones);

		CriterioArtista criterio15 = new CriterioArtista("LMFAO");
		Vector<Cancion> filtradas15 = filtrar(criterio15,canciones);

		CriterioNOT criterio16 = new CriterioNOT(criterio15);
		Vector<Cancion> filtradas16 = filtrar(criterio16,canciones);

		CriterioAND criterio17 = new CriterioAND(criterio14, criterio16);
		Vector<Cancion> filtradas17 = filtrar(criterio17,canciones);
		imprimir(filtradas17);

		System.out.println();
		System.out.println("Las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”: ");

		CriterioFecha criterio18 = new CriterioFecha(2006);
		Vector<Cancion> filtradas18 = filtrar(criterio18,canciones); //Mayor a 20006

		CriterioAND criterio19 = new CriterioAND(criterio2, criterio7);
		Vector<Cancion> filtradas19 = filtrar(criterio19,canciones); //Hace el and, genero rock y mayor a 2006.

		CriterioArtista criterio20 = new CriterioArtista("Coldplay");
		Vector<Cancion> filtradas20 = filtrar(criterio20,canciones);

		CriterioAND criterio21 = new CriterioAND(criterio13, criterio20);
		Vector<Cancion> filtradas21 = filtrar(criterio21,canciones);

		CriterioOR criterio22 = new CriterioOR(criterio19, criterio21);
		Vector<Cancion> filtradas22 = filtrar(criterio22,canciones);
		imprimir(filtradas22);

		System.out.println();
		System.out.println("FINALIZA EL PROGRAMA.");
		
	}
}