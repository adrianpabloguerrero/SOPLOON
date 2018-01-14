
public class Reproductor {

	public static void main (String [ ] args) {

	    Pista p1 = new Pista(1, "El Tiempo No Para", 311, "Bersuit Vergarabat", "De la cabeza", 2002, "Rock nacional","");
	    Pista p2 = new Pista(2, "Mi Caramelo", 290, "Bersuit Vergarabat", "De la cabeza", 2002, "Rock nacional","");
	    Pista p3 = new Pista(3, "Party Rock Anthem", 408, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop","");
	    Pista p4 = new Pista(4, "Sorry for Party Rocking", 421, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop","");
	    Pista p5 = new Pista(5, "Fix you", 255, "Coldplay", "X&Y", 2005, "Rock alternativo","");
	    Pista p6 = new Pista(6, "Speed of Sound", 455, "Coldplay", "X&Y", 2005, "Rock alternativo","");
	    Pista p7 = new Pista(7, "Viva la vida", 320, "Coldplay", "Viva la vida", 2008, "Rock alternativo","");
	    Pista p8 = new Pista(8, "With or without you", 360, "U2", "The Joshua Tree", 1987, "Rock","");
	    Pista p9 = new Pista(9, "Vertigo", 355, "U2", "How to Dismantle an Atomic Bomb", 2004, "rock","");
	    Pista p10 = new Pista(10, "City of Blinding Lights", 284, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock","");
	    Pista p11 = new Pista(11, "A la luz de la luna", 438, "El Indio Solari", "Pajaritos, bravos muchachitos", 2013, "rock nacional","");
	    Pista p12 = new Pista(12, "Yo canibal", 258, "Patricio rey y sus redonditos de ricota", "Lobo Suelto, Cordero atado", 1993, "Rock Nacional","");
	    Pista p13 = new Pista(13, "Paradise", 365, "Coldplay", "Mylo Xyloto", 2011, "Rock alternativo","");

	    ListaReproduccion l1 = new ListaReproduccion("Clasicos del Rock");
	    ListaReproduccion l2 = new ListaReproduccion("Lo mejor");
	    ListaReproduccion l3 = new ListaReproduccion("Coldplay");
	    ListaReproduccion l4 = new ListaReproduccion("EL Indio");
	    
	    ListaReproduccion l5 = new ListaReproduccion("Todas");
	    
	    l1.agregarElemento(p1);	    l1.agregarElemento(p2);
	    l1.agregarElemento(p8);	    l1.agregarElemento(p9);
	    l1.agregarElemento(p10);	l1.agregarElemento(p12);
	    
	    l2.agregarElemento(p3);	    l2.agregarElemento(p4);
	    l2.agregarElemento(p7);	    l2.agregarElemento(p12);

	    l3.agregarElemento(p5);
	    l3.agregarElemento(p6);
	    l3.agregarElemento(p7);
	    
	    l4.agregarElemento(p11);
	    l4.agregarElemento(p12);
	    
	    l5.agregarElemento(p1);	    l5.agregarElemento(p2);	    l5.agregarElemento(p3);
	    l5.agregarElemento(p4);	    l5.agregarElemento(p5);	    l5.agregarElemento(p6);
	    l5.agregarElemento(p7);	    l5.agregarElemento(p8);	    l5.agregarElemento(p9);
	    l5.agregarElemento(p10);	l5.agregarElemento(p11);	l5.agregarElemento(p12);
	    l5.agregarElemento(p13);

	    
	    l1.imprimirColeccion();
	    l2.imprimirColeccion();
	    l3.imprimirColeccion();
	    
	    System.out.println(l1.duracionTotal());
	    System.out.println(l2.duracionTotal());
	    System.out.println(l3.duracionTotal());
	    System.out.println(l4.duracionTotal());
	    
	    
	    Filtro f1 = new Filtro();

	    ListaReproduccion duracionMasDe400 = f1.listaFiltrada(Parametro.DURACION_MAYOR_A, 400, l5);
	    duracionMasDe400.imprimirColeccion();
	    
	    ListaReproduccion generoRock = f1.listaFiltrada(Parametro.GENERO_CONTIENE, "Rock", l5);
	    generoRock.imprimirColeccion();
	    
	    ListaReproduccion tituloRock = f1.listaFiltrada(Parametro.TITULO_CONTIENE, "Rock", l5);
	    ListaReproduccion interpreteLmfao = f1.listaFiltrada(Parametro.INTERPRETE_CONTIENE, "LMFAO", l5);
	    f1.concatenarFiltro(Operacion.NOT, tituloRock, interpreteLmfao).imprimirColeccion();
	    
	    ListaReproduccion anioPosterior2006 = f1.listaFiltrada(Parametro.AÑO_MAYOR_A, 2006, l5);	    
	    ListaReproduccion interpreteColdplay = f1.listaFiltrada(Parametro.INTERPRETE_CONTIENE, "Coldplay", l5);

	    ListaReproduccion generoRock_anioPosterior2006 = f1.concatenarFiltro(Operacion.AND, generoRock, anioPosterior2006);
	    ListaReproduccion generoRock_interpreteColdplay = f1.concatenarFiltro(Operacion.AND, generoRock, interpreteColdplay);

	    f1.concatenarFiltro(Operacion.OR, generoRock_anioPosterior2006, generoRock_interpreteColdplay).imprimirColeccion();
	}
}
