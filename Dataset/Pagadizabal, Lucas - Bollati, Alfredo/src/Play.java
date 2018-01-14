public class Play {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Guia 1
		
		//item a
		PLFE c1 = new Pista(1,"El Tiempo No Para", 311 , "Bersuit Vergabarat", "De la cabeza", 2002, "Rock Nacional", "Comentarios");
		PLFE c2 = new Pista(2,"Mi caramelo", 290 , "Bersuit Vergabarat", "De la cabeza", 2002, "Rock Nacional", "Comentarios");
		PLFE c3 = new Pista(3,"Party Rock Anthem", 408 , "LMFAO", "Sorry for Party Rocking", 2011, "Electro Pop", "Comentarios");
		PLFE c4 = new Pista(4,"Sorry for Party Rocking", 421 , "LMFAO", "Sorry for Party Rocking", 2011, "Electro Pop", "Comentarios");
		PLFE c5 = new Pista(5,"Fix You", 255 , "Coldplay", "Sorry for Party Rocking", 2011, "Rock Alternativo", "Comentarios");
		PLFE c6 = new Pista(6,"Speed of Sound", 421 , "Coldplay", "Sorry for Party Rocking", 2011, "Rock Alternativo", "Comentarios");
		PLFE c7 = new Pista(7,"Viva la Vida", 421 , "Coldplay", "Sorry for Party Rocking", 2011, "Rock", "Comentarios");
		PLFE c8 = new Pista(8,"Whit or whitout you", 421 , "U2", "Sorry for Party Rocking", 2011, "Rock", "Comentarios");
		PLFE c9 = new Pista(9,"Vertigo", 421 , "U2", "Sorry for Party Rocking", 2011, "Electro Pop", "Comentarios");
		PLFE c10 = new Pista(10,"City of Blinding Lights", 421 , "U2", "Sorry for Party Rocking", 2011, "Rock", "Comentarios");
		PLFE c11 = new Pista(11,"A la luz de la luna", 421 , "El Indio Solari", "Sorry for Party Rocking", 2011, "Rock Nacional", "Comentarios");
		PLFE c12 = new Pista(12,"Yo Canibal", 421 , "Patricio Rey y sus redonditos de ricota", "Lobo Suelto, Cordero atado", 1993, "Rock Nacional", "Comentarios");
		PLFE c13 = new Pista(13,"Paradise" , 365,"Coldplay","Mylo Xyloto",2011,"RockAlternativo","Comentarios");
		
		//item b
		Playlist p1 = new Playlist("Clasicos del Rock");
		Playlist p2 = new Playlist("Lo Mejor");
		Playlist p3 = new Playlist("Coldplay");
		Playlist p4 = new Playlist("El indio");
		Playlist playlistGeneral = new Playlist("DJ");
		
		p1.addElem(c1);
		p1.addElem(c2);
		p1.addElem(c8);
		p1.addElem(c9);
		p1.addElem(c10);
		p1.addElem(c12);

		p2.addElem(c3);
		p2.addElem(c4);
		p2.addElem(c7);

		p3.addElem(c5);
		p3.addElem(c6);

		p4.addElem(c12);
		p4.addElem(c11);
		
		playlistGeneral.addElem(p1);
		playlistGeneral.addElem(p2);
		playlistGeneral.addElem(p3);
		playlistGeneral.addElem(p4);
		
//		//item c
//		System.out.println(p1.toString());
//		System.out.println(p2.toString());
//		System.out.println(p3.toString());
//
//		//item d
//		System.out.println(p1.getDuracion());
//		System.out.println(p2.getDuracion());
//		System.out.println(p3.getDuracion());
//		System.out.println(p4.getDuracion());
//		
//		//Guia 2
//		
//		//item 1
//		//a
//		System.out.println("**************************************************");
//		Condicion mayorA = new CondicionDuracionMayorA(400);
//		
//		System.out.println(playlistGeneral.buscar(mayorA));
//		System.out.println("**************************************************");
//		//b
//		Condicion filtroGenero = new CondicionPorGenero("rock");
//		
//		System.out.println(playlistGeneral.buscar(filtroGenero));
//		System.out.println("**************************************************");
//		//c
//		Condicion filtroTitulo = new CondicionPorTitulo("rock");
//		Condicion artistaLMFAO= new CondicionPorArtista("LMFAO");
//		Condicion Not = new CondicionNot(artistaLMFAO);
//		Condicion And = new CondicionAnd(filtroTitulo,Not);
//		
//		System.out.println(playlistGeneral.buscar(And));
//		System.out.println("**************************************************");
//		//d
//		Condicion filtroAnio= new CondicionAnioMayorA(2006);
//		Condicion artistaColdplay = new CondicionPorArtista("coldplay");
//		Condicion And2 = new CondicionAnd(filtroGenero,filtroAnio);
//		Condicion And3 = new CondicionAnd(filtroGenero,artistaColdplay);
//		Condicion Or = new CondicionOr(And2,And3);
//		
//		//item 2
//		//playlistGeneral.addElem(c13); pista nueva
//		System.out.println(playlistGeneral.buscar(Or));
//

	}

}
