package sistema;

import java.util.Vector;

public class MainMusica {

	public static void main(String[] args) {
		
		Pista p1 = new Pista("El Tiempo No Para", 311, "Bersuit Vergabarat", "De la cabeza", 2002, "Rock nacional");
		Pista p2 = new Pista("Mi caramelo", 290, "Bersuit Vergabarat", "De la cabeza", 2002, "Rock nacional");
		Pista p3 = new Pista("Party Rock Anthem", 408, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop");
		Pista p4 = new Pista("Sorry for Party Rocking", 421, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop");
		Pista p5 = new Pista("Fix you", 255, "Coldplay", "X&Y", 2005, "Rock alternativo");
		Pista p6 = new Pista("Speed of Sound", 455, "Coldplay", "X&Y", 2005, "Rock alternativo");
		Pista p7 = new Pista("Viva la vida", 320, "Coldplay", "Viva la vida", 2008, "Rock alternativo");
		Pista p8 = new Pista("With or without you", 360, "U2", "The Joshua Tree", 1987, "Rock");
		Pista p9 = new Pista("Vertigo", 355, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock");
		Pista p10 = new Pista("City of Blinding Lights", 284, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock");
		Pista p11 = new Pista("A la luz de la luna", 438, "El Indio Solari", "Pajaritos, bravos muchachitos", 2013, "Rock nacional");
		Pista p12 = new Pista("Yo Canibal", 258, "Patricio rey y sus redonditos de ricota", "Lobo Suelto, Cordero atado", 1993, "Rock nacional");
		
		Lista plist1 = new Lista("Clasicos del rock");
		plist1.add(p1);
		plist1.add(p2);
		plist1.add(p8);
		plist1.add(p9);
		plist1.add(p10);
		plist1.add(p12);
		
		Lista plist2 = new Lista("Lo mejor");
		plist2.add(p3);
		plist2.add(p4);
		plist2.add(p7);
		plist2.add(p12);
		plist2.add(p1);

		Lista plist3 = new Lista("Coldplay");		
		plist3.add(p5);
		plist3.add(p6);
		plist3.add(p7);
		
		Lista plist4 = new Lista("El indio");		
		plist4.add(p12);
		plist4.add(p11);	

        // Creo una coleccion de pistas que contiene otras playlists
		Lista coleccion = new Lista("Coleccion");
		coleccion.add(plist1);
		coleccion.add(plist2);
		coleccion.add(plist3);
		coleccion.add(plist4);

        // Imprimo playlist "Clasicos del rock" y su duracion.
        System.out.println(coleccion.getResumen(plist1));
        System.out.println("DURACION: " + coleccion.getDuracion(plist1));

        // Imprimo playlist "Lo mejor" y su duracion.
		System.out.println(coleccion.getResumen(plist2));
        System.out.println("DURACION: " + coleccion.getDuracion(plist2));

        // Imprimo playlist "Coldplay" y su duracion.
		System.out.println(coleccion.getResumen(plist3));
        System.out.println("DURACION: " + coleccion.getDuracion(plist3));


        // Pistas cuya duración es superior a 400 segundos.
        Busqueda busqueda1 = new BuscarDuracionMayor(400);
        printVector(coleccion.buscador(busqueda1));

        // Pistas cuyo género contenga la palabra “rock”.
		Busqueda busqueda2 = new BuscarGenero("rock");
        printVector(coleccion.buscador(busqueda2));

        // Pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”.
        Busqueda busqueda3 = new BuscarNombre("rock");
        Busqueda busqueda4 = new BuscarArtista("lmfao");
        Busqueda busqueda5 = new BNot(busqueda4);
        Busqueda busqueda6 = new BAnd(busqueda3, busqueda5);
        printVector(coleccion.buscador(busqueda6));

        // Las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”,
        // o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”.
        Busqueda busqueda7 = new BuscarGenero("rock");
        Busqueda busqueda8 = new BuscarAnioMayor(2006);
        Busqueda busqueda9 = new BAnd(busqueda7, busqueda8);
        Busqueda busqueda10 = new BuscarGenero("rock");
        Busqueda busqueda11 = new BuscarArtista("coldplay");
        Busqueda busqueda12 = new BAnd(busqueda10, busqueda11);
        Busqueda busqueda13 = new BOr(busqueda9, busqueda12);
        printVector(coleccion.buscador(busqueda13));

        Pista p13 = new Pista("Paradise", 365, "Coldplay", "Mylo Xyloto", 2011, "Rock alternativo");
        coleccion.add(p13);

        // Vuelvo a imprimir las busquedas despues de agregar una nueva pista
        printVector(coleccion.buscador(busqueda1));
        printVector(coleccion.buscador(busqueda2));
        printVector(coleccion.buscador(busqueda6));
        printVector(coleccion.buscador(busqueda13));

    }

    // Metodo usado para imprimir el vector de pistas luego de la busqueda.
    private static void printVector(Vector<Pista> pistas){
        System.out.println(pistas.size() + " elementos encontrados:");
        for (Pista p : pistas) System.out.println("- " + p.getResumen());
        System.out.println();
    }
}
