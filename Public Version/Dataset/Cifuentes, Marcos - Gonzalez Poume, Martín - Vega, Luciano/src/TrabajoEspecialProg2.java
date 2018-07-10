package trabajoEspecialProg2;

public class TrabajoEspecialProg2 {

	public static void main(String[] args) {
		
		Playlist coleccion = new Playlist("Coleccion");

		Pista p1 = new Pista (1,"El Tiempo No Para",311,"Bersuit Vergarabat","De la cabeza",2002,"Rock nacional","algun comentario");
		Pista p2 = new Pista (2,"Mi caramelo",290,"Bersuit Vergarabat","De la cabeza",2002,"Rock nacional","algun comentario");
		Pista p3 = new Pista (3,"Party Rock Anthem",408,"LMFAO","Sorry for Party Rocking",2011,"Electro pop","algun comentario");
		Pista p4 = new Pista (4,"Sorry for Party Rocking",421,"LMFAO","Sorry for Party Rocking",2011,"Electro pop","algun comentario");
		Pista p5 = new Pista (5,"Fix you",255,"Coldplay","X&Y",2005,"Rock alternativo","algun comentario");
		Pista p6 = new Pista (6,"Speed of Sound",455,"Coldplay","X&Y",2005,"Rock alternativo","algun comentario");
		Pista p7 = new Pista (7,"Viva la vida",320,"Coldplay","Viva la vida",2008,"Rock alternativo","algun comentario");
		Pista p8 = new Pista (8,"Whit or whitout you",360,"U2","The Joshua Tree",1987,"Rock","algun comentario");
		Pista p9 = new Pista (9,"Vertigo",355,"U2","How to Dismantle an Atomic Bomb",2004,"rock","algun comentario");
		Pista p10 = new Pista (10,"City of Blinding Lights",284,"U2","How to Dismantle an Atomic Bomb",2004,"Rock","algun comentario");
		Pista p11 = new Pista (11,"A la luz de la luna",438,"El Indio Solari","Pajaritos, bravos muchachos",2013,"rock nacional","algun comentario");
		Pista p12 = new Pista (12,"Yo Canibal",258,"Patricio rey y sus redonditos de ricota","Lobo suelto, Cordero atado",1993,"Rock nacional","algun comentario");


		Playlist pl1 = new Playlist ("Clasicos del Rock");
		Playlist pl2 = new Playlist ("Lo mejor");
		Playlist pl3 = new Playlist ("Coldplay");
		Playlist pl4 = new Playlist ("EL Indio");

		coleccion.addElemento(p1);
		coleccion.addElemento(p2);
		coleccion.addElemento(p3);
		coleccion.addElemento(p4);
		coleccion.addElemento(p5);
		coleccion.addElemento(p6);
		coleccion.addElemento(p7);
		coleccion.addElemento(p8);
		coleccion.addElemento(p9);
		coleccion.addElemento(p10);
		coleccion.addElemento(p11);
		coleccion.addElemento(p12);
		coleccion.addElemento(pl1);
		coleccion.addElemento(pl2);
		coleccion.addElemento(pl3);
		coleccion.addElemento(pl4);

		pl1.addElemento(p1);
		pl1.addElemento(p2);
		pl1.addElemento(p8);
		pl1.addElemento(p9);
		pl1.addElemento(p10);
		pl1.addElemento(p12);

		pl2.addElemento(p3);
		pl2.addElemento(p4);
		pl2.addElemento(p7);
		pl2.addElemento(p12);

		pl3.addElemento(p5);
		pl3.addElemento(p6);
		pl3.addElemento(p7);

		pl4.addElemento(p11);
		pl4.addElemento(p12);

		coleccion.addElemento(pl1);
		coleccion.addElemento(pl2);
		coleccion.addElemento(pl3);

		CondicionDuracionMayor c1=new CondicionDuracionMayor(400);
		CondicionGeneroIgual c2= new CondicionGeneroIgual("rock");
		CondicionTituloIgual c3=new CondicionTituloIgual("rock");
		CondicionArtistaIgual c4=new CondicionArtistaIgual("LMFAO");
		CondicionNot c5=new CondicionNot(c4);
		CondicionAnd c6=new CondicionAnd(c3,c5);
		CondicionAnioMayor c7=new CondicionAnioMayor(2006);
		CondicionArtistaIgual c8=new CondicionArtistaIgual("Coldplay");
		CondicionAnd c9=new CondicionAnd(c2,c7);
		CondicionAnd c10=new CondicionAnd(c2,c8);
		CondicionOr c11=new CondicionOr(c9,c10);

		System.out.println("Pistas de Clasicos del Rock ");
		System.out.println(pl1.toString() +" \n");
		System.out.println("Pistas de Lo mejor");
		System.out.println(pl2.toString() +" \n");
		System.out.println("Pistas de Coldplay");
		System.out.println(pl3.toString() +" \n");
		System.out.println("Duracion Total de Coleccion");
		System.out.println(coleccion.getDuracionTotal()+" segundos \n");
		System.out.println("Duracion Total de Lo mejor");
		System.out.println(pl1.getDuracionTotal()+" segundos \n");
		System.out.println("Duracion Total de Coldplay");
		System.out.println(pl2.getDuracionTotal()+" segundos \n");
		System.out.println("Duracion Total de Coldplay");
		System.out.println(pl3.getDuracionTotal()+" segundos \n");
		System.out.println("Duracion Total de EL Indio");
		System.out.println(pl4.getDuracionTotal()+" segundos \n");

		System.out.println("Primera Busqueda \n");

		System.out.println("Las pistas cuya duración sea superior a 400 segundos. \n");
		System.out.println(coleccion.buscarCondicion(c1));

		System.out.println("Las pistas cuyo género contenga la palabra “rock”. \n");
		System.out.println(coleccion.buscarCondicion(c2));

		System.out.println("Las pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”. \n");
		System.out.println(coleccion.buscarCondicion(c6));

		System.out.println("Las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”. \n");
		System.out.println(coleccion.buscarCondicion(c11));

		Pista p13 = new Pista (13,"Paradise",365,"Coldplay","Mylo Xyloto",2011,"Rock alternativo","algun comentario");
		coleccion.addElemento(p13);

		System.out.println("Segunda Busqueda \n");

		System.out.println("Las pistas cuya duración sea superior a 400 segundos. \n");
		System.out.println(coleccion.buscarCondicion(c1));

		System.out.println("Las pistas cuyo género contenga la palabra “rock”. \n");
		System.out.println(coleccion.buscarCondicion(c2));

		System.out.println("Las pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”. \n");
		System.out.println(coleccion.buscarCondicion(c6));

		System.out.println("Las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”. \n");
		System.out.println(coleccion.buscarCondicion(c11));
	}

}
