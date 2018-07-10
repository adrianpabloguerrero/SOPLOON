package PracticoEspecial;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Pista p1 = new Pista("El Tiempo No Para ",311, "Bersuit Vergarabat", "De la Cabeza", 2002, "Rock Nacional", "Sin Comentarios");
		Pista p2 = new Pista("Mi caramelo",290, "Bersuit Vergarabat", "De la Cabeza", 2002, "Rock Nacional", "Sin Comentarios");
		Pista p3 = new Pista("Party Rock Anthem ",408,"LMFAO", "Sorry For Party Rocking", 2011, "Electro pop", "Sin Comentarios");
		Pista p4 = new Pista("Sorry For Party Rocking ",421,"LMFAO", "Sorry For Party Rocking", 2011, "Electro pop", "Sin Comentarios");
		Pista p5 = new Pista("Mi Fix you ",255,"Coldplay", "X&Y", 2005, "Rock Alternativo", "Sin Comentarios");
		Pista p6 = new Pista("Speed of Sound ",455,"Coldplay", "X&Y", 2005, "Rock Alternativo", "Sin Comentarios");
		Pista p7 = new Pista("Viva la Vida ",320,"Coldplay", "Viva la Vida", 2008, "Rock Alternativo", "Sin Comentarios");
		Pista p8 = new Pista("With or whitout you ",360,"U2", "The Joshua Tree", 1987, "Rock", "Sin Comentarios");
		Pista p9 = new Pista("Vertigo ",355,"U2", "How to Dismantle an Atomic Bomb", 2004, "rock", "Sin Comentarios");
		Pista p10 = new Pista("City of Blinding Lights ",384,"U2", "How to Dismantle an Atomic Bomb", 2004, "Rock", "Sin Comentarios");
		Pista p11 = new Pista("A la luz de la luna ",438,"El Indio Solari", "Pajaritos, bravos muchachitos", 2013, "rock nacional", "Sin Comentarios");
		Pista p12 = new Pista("Yo Canibal ",258,"Patricio rey y sus redonditos de ricota", "Lobo Suelto, Cordero atado", 1993, "Rock Nacional", "Sin Comentarios");
		
		Playlist clasicos = new Playlist("Clasicos del Rock");
		Playlist coldplay = new Playlist("Coldplay");
		Playlist mejor = new Playlist("Lo mejor");
		Playlist indio = new Playlist("EL indio");
		
		clasicos.setPista(p1);
		clasicos.setPista(p2);
		clasicos.setPista(p8);
		clasicos.setPista(p9);
		clasicos.setPista(p10);
		clasicos.setPista(p12);
		
		mejor.setPista(p3);
		mejor.setPista(p4);
		mejor.setPista(p7);
		mejor.setPista(p12);
		
		coldplay.setPista(p5);
		coldplay.setPista(p6);
		coldplay.setPista(p7);
		
		indio.setPista(p12);
		indio.setPista(p11);
		
		System.out.println(clasicos.imprimir());
		System.out.println(mejor.imprimir());
		System.out.println(coldplay.imprimir());
		
		System.out.println("Duracion total Clasicos del Rock: " + clasicos.getDuracion());
		System.out.println("Duracion total Lo mejor: " + mejor.getDuracion());
		System.out.println("Duracion total Colplay: " + coldplay.getDuracion());
		System.out.println("Duracion total El indio: " + indio.getDuracion());
		
		Playlist sistema = new Playlist("Sistema Reproductor");
		
		sistema.setPista(clasicos);
		sistema.setPista(mejor);
		sistema.setPista(coldplay);
		sistema.setPista(indio);
		
		Condicion_duracion c1 = new Condicion_duracion(400);
		Condicion_genero  c2 = new Condicion_genero("rock");
		Condicion_nombre  c3 = new Condicion_nombre("rock");
		Condicion_artista c4 = new Condicion_artista("LMFAO");
		Condicion_negacion c5 = new Condicion_negacion(c4);
		Condicion_and c6 = new Condicion_and(c3, c5);
		Condicion_anio c7 = new Condicion_anio(2006);
		Condicion_and c8 = new Condicion_and(c2,c7);
		Condicion_artista c9 = new Condicion_artista("coldplay");
		Condicion_and c10 = new Condicion_and(c2,c9);
		Condicion_or c11 = new Condicion_or(c8,c10);
		
		System.out.println(sistema.imprimir(c1));
		System.out.println(sistema.imprimir(c2));
		System.out.println(sistema.imprimir(c6));
		System.out.println(sistema.imprimir(c11));
		
		Pista p13 = new Pista("Paradise ",365,"Coldplay", "Mylo Xyloto", 2011, "Rock Alternativo", "Sin Comentarios");
		
		sistema.setPista(p13);
		
		System.out.println(sistema.imprimir(c1));
		System.out.println(sistema.imprimir(c2));
		System.out.println(sistema.imprimir(c6));
		System.out.println(sistema.imprimir(c11));
		
		
	}

}
