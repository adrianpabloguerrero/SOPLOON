package Lista_de_Musica;

public class AdministracionDeListaReproduccion {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Pista p1 = new Pista("El tiempo no para", 311, "Bersuit Vergarabat", 2002, "Rock Nacional","de la cabeza");
		Pista p2 = new Pista("Mi caramelo", 290, "Bersuit Vergarabat", 2002, "Rock Nacional","de la cabeza");
		Pista p3 = new Pista("Party Rock Athem", 408, "LMFAO", 2011, "Electro Pop", "Sorry for Party Rocking");
		Pista p4 = new Pista("Sorry for Party Rocking", 421, "LMFAO", 2011, "Electro Pop","Sorry for Party Rocking");
		Pista p5 = new Pista("Fix you", 255, "coldplay", 2005, "Rock Alternativo", "X&Y");
		Pista p6 = new Pista("Speed of Sound", 455, "coldplay", 2005, "Rock Alternativo", "X&Y");
		Pista p7 = new Pista("Viva la vida", 320, "coldplay", 2008, "Rock Alternativo", "Viva la vida");
		Pista p8 = new Pista("Whit or whitout you", 360, "U2", 1987, "Rock", "The Joshua Tree");
		Pista p9 = new Pista("Vertigo", 355, "U2", 2008, "Rock", "How to Dismantle an Atomic Bomb");
		Pista p10 = new Pista("City of Blinding Lights", 284, "U2", 2004, "Rock", "How to Dismantle an Atomic Bomb");
		Pista p11 = new Pista("A la luz de la luna", 438, "Indio Solari", 2013, "Rock Nacional", "Pajaritos, Bravo muchachos");
		Pista p12 = new Pista("Yo Canibal", 258, "Patricio Rey y sus Redonditos de Ricota", 1993, "Rock Nacioanl", "Lobo suelto, Cordero atado");
		ListaReproduccion l2 = new ListaReproduccion("Lo Mejor");

			l2.agregar(p3);
			l2.agregar(p4);
			l2.agregar(p7);
			l2.agregar(p12);
		
		ListaReproduccion l = new ListaReproduccion("Clasicos del rock");
		
			l.agregar(p1);
			l.agregar(p2);
			l.agregar(p8);
			l.agregar(p9);
			l.agregar(p10);
			l.agregar(p12);
			
		ListaReproduccion l3 = new ListaReproduccion("Coldplay");
			l3.agregar(p5);
			l3.agregar(p6);
			l3.agregar(p7);
			
		ListaReproduccion l4 = new ListaReproduccion("El Indio");	
			l4.agregar(p12);
			l4.agregar(p11);
			
			l2.agregar(l);
			
		  Administrador benja = new Administrador();
		  
		  benja.agregarMusica(l);
		  benja.agregarMusica(l2);
		  benja.agregarMusica(l3);
		  benja.agregarMusica(l4);
		  
		  
		  
		 System.out.println("Eliminar Elemento de la lista: "+l.getNombre());
		 System.out.println("antes: "+"tamaño = "+l.getTamaño());
		 benja.mostrarListado(l);
		 System.out.println("");
		 benja.eliminar(p1);
		 System.out.println("ahora: "+"tamaño = "+l.getTamaño());
		 System.out.println("");
		 benja.mostrarListado(l);
		 System.out.println("");
		 System.out.println(l.getTamaño());
		 
		  
		System.out.println("<------------------------------------ Playlist's -------------------------------------->");
		System.out.println("");
		
		System.out.println(l2.getNombre()+": ");
			benja.mostrarListado(l2);
			System.out.println("");
		
		System.out.println(l.getNombre()+": ");
			benja.mostrarListado(l);
			System.out.println("");
		System.out.println(l3.getNombre()+": ");
			benja.mostrarListado(l3);
			System.out.println("");	
		System.out.println(l4.getNombre()+": ");
			benja.mostrarListado(l4);
			System.out.println("");
		System.out.println("<------------------------------------ Duracion -------------------------------------->");
			
		System.out.println("duracion de: "+ l.getNombre());
			System.out.println(l.getDuracion());
			System.out.println("");
		
		System.out.println("duracion de: "+ l2.getNombre());
			System.out.println(l2.getDuracion());
			System.out.println("");
		
		System.out.println("duracion de: "+ l3.getNombre());
			System.out.println(l3.getDuracion());
			System.out.println("");
		
		System.out.println("duracion de: "+ l4.getNombre());
			System.out.println(l4.getDuracion());
		System.out.println("");
		
		
		Condicion c1 = new CondicionDuracionMayor(400);
		Condicion c2 = new CondicionGenero("rock");
		Condicion c3 = new CondicionArtista("LMFAO");
		Condicion c4 = new CondicionAñoMayor(2006);
		Condicion c5 = new CondicionArtista("coldplay");
		Condicion c6 = new CondicionNombre("rock");
		Condicion not = new CondicionNot(c3);
		Condicion and = new CondicionAnd(c6, not);
		Condicion and2 = new CondicionAnd(c2,c5);
		Condicion and3 = new CondicionAnd(c2,c4);
		Condicion or = new CondicionOr(and2, and3);
		
		
		System.out.println("<------------------------------------ Busquedas -------------------------------------->");
		System.out.println("");
		
		System.out.println("duracion mayor a 400 seg. de la lista: "+l2.getNombre());
		if(l2.buscar(c1).size() == 0)
			System.out.println(" y no hay resultados de la busqueda.");
		else
			for(int i=0; i<l2.buscar(c1).size();i++){
				System.out.println(l2.buscar(c1).elementAt(i).getNombre());
			}
		System.out.println("");
		
		System.out.println("pistas cuyo genero es 'rock' de la lista: "+l.getNombre());
		if(l4.buscar(c2).size() == 0)
			System.out.println(" y no hay resultados de la busqueda.");
		else 
			for(int i=0; i<l.buscar(c2).size();i++){
				System.out.println(l.buscar(c2).elementAt(i).getNombre());
			}		
		System.out.println("");
		
		System.out.println("pistas cuyas pisatas tengan nombre sea rock && cuyo interprete no sea 'LMFAO'), de la pistaa: "+l4.getNombre());
	
		if(l4.buscar(and).size() == 0)
			System.out.println(" no hay resultados de la busqueda.");
		else 
			for(int i=0; i<l4.buscar(and).size();i++){
				System.out.println(l4.buscar(and).elementAt(i).getNombre());
			}
		
		
		System.out.println("");
			
		System.out.println("pistas cuyo genero sea 'rock' $$ cuya fecha sea mayor a 2006, o cuyo genero contenga 'rock &&cuyo interprete sea 'coldplay', de la lisata: "+l3.getNombre());
		if(l4.buscar(or).size() == 0)
			System.out.println(" no hay resultados de la busqueda.");
		else 	
			for(int i=0; i<l3.buscar(or).size();i++){
					System.out.println(l3.buscar(or).elementAt(i).getNombre());
				}
		System.out.println("");
		
		/*
		 * esto no cuenta, estoy probando cosas
		benja.buscar(c1);
		benja.buscar(c2);
		benja.buscar(c3);
		benja.buscar(c4);
		benja.buscar(c5);
		benja.buscar(c6);
		benja.buscar(and);
		benja.buscar(and2);
		benja.buscar(and3);
		benja.buscar(not);
		*/
	}

}
