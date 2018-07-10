import java.util.Set;


public class MusicSystem {

	public static void main(String[] args) {
		
		Playlist collection = new Playlist("collection");
		
		//1era parte
		//1)
		Song s1 = new Song(1, "El Tiempo No Para", 311, "Bersuit Vergarabat", "De la cabeza", 2002, "Rock nacional");
		Song s2 = new Song(2, "Mi caramelo", 290, "Bersuit Vergarabat", "De la cabeza", 2002, "Rock nacional");
		Song s3 = new Song(3, "Party Rock Anthem", 408, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop");
		Song s4 = new Song(4, "Sorry for Party Rocking", 421, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop");
		Song s5 = new Song(5, "Fix you", 255, "Coldplay", "X&Y", 2005, "Rock alternativo");
		Song s6 = new Song(6, "Speed of Sound", 455, "Coldplay", "X&Y", 2005, "Rock alternativo");
		Song s7 = new Song(7, "Viva la vida", 320, "Coldplay", "Viva la vida", 2008, "Rock alternativo");
		Song s8 = new Song(8, "Whit or whitout you", 360, "U2", "The Joshua Tree", 1987, "Rock");
		Song s9 = new Song(9, "Vertigo", 355, "U2", "How to Dismantle an Atomic Bomb", 2004, "rock");
		Song s10 = new Song(10, "City of Blinding Lights", 284, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock");
		Song s11 = new Song(11, "A la luz de la luna", 438, "El Indio Solari", "Pajaritos, bravos muchachitos", 2013, "rock nacional");
		Song s12 = new Song(12, "Yo Canibal", 258, "Patricio rey y sus redonditos de ricota", "Lobo Suelto, Cordero atado", 1993, "Rock Nacional");
		
		collection.add(s1);
		collection.add(s2);
		collection.add(s3);
		collection.add(s4);
		collection.add(s5);
		collection.add(s6);
		collection.add(s7);
		collection.add(s8);
		collection.add(s9);
		collection.add(s10);
		collection.add(s11);
		collection.add(s12);
		
		
		//2)
		Playlist p1 = new Playlist("Clasicos del Rock");
		Playlist p2 = new Playlist("Lo mejor");
		Playlist p3 = new Playlist("Coldplay");
		Playlist p4 = new Playlist("EL Indio");
		
		p1.add(s1);
		p1.add(s2);
		p1.add(s8);
		p1.add(s9);
		p1.add(s10);
		p1.add(s12);
		
		p2.add(s3);
		p2.add(s4);
		p2.add(s7);
		p2.add(s12);
		
		p3.add(s5);
		p3.add(s6);
		p3.add(s7);
		
		p4.add(s12);
		p4.add(s11);
		
		collection.add(p1);
		collection.add(p2);
		collection.add(p3);
		collection.add(p4);
		
		//3)
		p1.print();
		p2.print();
		p3.print();
		
		//4)
		System.out.println("Playlist Duration " + p1.getName() + ": " + p1.getDuration());
		System.out.println("Playlist Duration " + p2.getName() + ": " + p2.getDuration());
		System.out.println("Playlist Duration " + p3.getName() + ": " + p3.getDuration());
		System.out.println("Playlist Duration " + p4.getName() + ": " + p4.getDuration());
		
		
		//2da parte
		//1)
		//a)	
		Filter f1 = new FilterDurationGreaterThan(400);
		//b)
		Filter f2 = new FilterGender("rock");
		//c
		Filter f3 = new FilterTitle("rock");
		Filter f4 = new FilterArtist("LMFAO");
		Filter f5 = new FilterNot(f4);
		Filter f6 = new FilterAnd(f3, f5);
		//d
		Filter f7 = new FilterYearAfter(2006);
		Filter f8 = new FilterArtist("coldplay");
		Filter f9 = new FilterAnd(f2, f7);
		Filter f10 = new FilterAnd(f2, f8);	
		Filter f11 = new FilterOr(f9, f10);
		
		printSongs("Duración mayor a 400 segundos", collection.search(f1));
		printSongs("Género rock", collection.search(f2));
		printSongs("Nombre rock pero no del artista LMFAO", collection.search(f6));
		printSongs("Genero rock después del 2006 o género rock de coldplay", collection.search(f11));
		
		//2)
		Song s13 = new Song(13, "Paradise", 365, "Coldplay", "Mylo Xyloto", 2011, "Rock alternativo");
		collection.add(s13);
		
		printSongs("Duración mayor a 400 segundos", collection.search(f1));
		printSongs("Género rock", collection.search(f2));
		printSongs("Nombre rock pero no del artista LMFAO", collection.search(f6));
		printSongs("Genero rock después del 2006 o género rock de coldplay", collection.search(f11));
	}
	
	private static void printSongs(String name, Set<Song> songs) {
		System.out.println("-----------------------------");
		System.out.println(name);
		for(Song s : songs) {
			s.print();
		}
		System.out.println("-----------------------------");
	}
}
