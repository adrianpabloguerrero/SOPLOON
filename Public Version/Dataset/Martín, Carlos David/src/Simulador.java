import java.util.*;

public class Simulador {
	public static void main(String[] args) {

		ElementoAudio elTiempoNoPara = new PistaAudio(1,"El Tiempo no Para",311,"Bersuit Vergarabat","De la cabeza",2002,"Rock Nacional");
		ElementoAudio miCaramelo = new PistaAudio(2,"Mi caramelo",290,"Bersuit Vegarabat","De la cabeza",2002,"Rock Nacional");
		ElementoAudio partyRockAnthem = new PistaAudio(3,"Party Rock Anthem",408,"LMFO","Sorry for Party Rocking",2011,"Electro Pop");
		ElementoAudio sorryForPartyRocking = new PistaAudio(4,"Sorry for Party Rocking",421,"LMFO","Sorry for Party Rocking",2011,"Electro Pop");
		ElementoAudio fixYou = new PistaAudio(5,"Fix You",255,"Coldplay","X&Y",2005,"Rock alternativo");
		ElementoAudio speedOfSound = new PistaAudio(6,"Speed of Sound",455,"Coldplay","X&Y",2005,"Rock alternativo");
		ElementoAudio vivaLaVida = new PistaAudio(7,"Viva la Vida",320,"Coldplay","Viva la vida",2008,"Rock alternativo");
		ElementoAudio withofWhitoutYou = new PistaAudio(8,"With of whitout you",360,"U2","The Joshua Tree",1987,"Rock");
		ElementoAudio vertigo = new PistaAudio(9,"Vertigo",355,"U2","How to Dismantle an Atomic Bomb",2004,"Rock");
		ElementoAudio cityofBlindingLights = new PistaAudio(10,"City of Blinding Lights",284,"U2","How to Dismantle an Atomic Bomb",2004,"Rock");
		ElementoAudio alaLuzdelaLuna = new PistaAudio(11,"A la luz de la luna",438,"El Indio Solari","Pajaritos, bravos muchachitos",2013,"Rock Nacional");
		ElementoAudio yoCanibal = new PistaAudio(12,"Yo canibal",258,"Patricio Rey y sus redonditos de ricota","Lobo suelto, cordero atado",1993,"Rock Nacional");

		ElementoAudio coleccion = new Playlist("Coleccion");

		((Playlist) coleccion).agregarAPlaylist(elTiempoNoPara);
		((Playlist) coleccion).agregarAPlaylist(miCaramelo);
		((Playlist) coleccion).agregarAPlaylist(partyRockAnthem);
		((Playlist) coleccion).agregarAPlaylist(sorryForPartyRocking);
		((Playlist) coleccion).agregarAPlaylist(fixYou);
		((Playlist) coleccion).agregarAPlaylist(speedOfSound);
		((Playlist) coleccion).agregarAPlaylist(vivaLaVida);
		((Playlist) coleccion).agregarAPlaylist(withofWhitoutYou);
		((Playlist) coleccion).agregarAPlaylist(vertigo);
		((Playlist) coleccion).agregarAPlaylist(cityofBlindingLights);
		((Playlist) coleccion).agregarAPlaylist(alaLuzdelaLuna);
		((Playlist) coleccion).agregarAPlaylist(yoCanibal);

		ElementoAudio lista1 = new Playlist("Clasicos de rock");
		ElementoAudio lista2 = new Playlist("Lo mejor");
		ElementoAudio lista3 = new Playlist("Coldplay");
		ElementoAudio lista4 = new Playlist("El Indio");


		((Playlist) coleccion).agregarAPlaylist(lista1);
		((Playlist) coleccion).agregarAPlaylist(lista2);
		((Playlist) coleccion).agregarAPlaylist(lista3);
		((Playlist) coleccion).agregarAPlaylist(lista4);


		((Playlist) lista1).agregarAPlaylist(elTiempoNoPara);
		((Playlist) lista1).agregarAPlaylist(miCaramelo);
		((Playlist) lista1).agregarAPlaylist(withofWhitoutYou);
		((Playlist) lista1).agregarAPlaylist(vertigo);
		((Playlist) lista1).agregarAPlaylist(cityofBlindingLights);
		((Playlist) lista1).agregarAPlaylist(yoCanibal);

		((Playlist) lista2).agregarAPlaylist(partyRockAnthem);
		((Playlist) lista2).agregarAPlaylist(sorryForPartyRocking);
		((Playlist) lista2).agregarAPlaylist(vivaLaVida);
		((Playlist) lista2).agregarAPlaylist(yoCanibal);

		((Playlist) lista3).agregarAPlaylist(fixYou);
		((Playlist) lista3).agregarAPlaylist(speedOfSound);
		((Playlist) lista3).agregarAPlaylist(vivaLaVida);

		((Playlist) lista4).agregarAPlaylist(yoCanibal);
		((Playlist) lista4).agregarAPlaylist(alaLuzdelaLuna);

		//***********************************Guia 1**********************************************
		System.out.println(lista1.toString());
		System.out.println("********************************************************************");
		System.out.println(lista2.toString());
		System.out.println("********************************************************************");
		System.out.println(lista3.toString());
		System.out.println("********************************************************************");

		System.out.println(lista1.getDuracion());
		System.out.println(lista2.getDuracion());
		System.out.println(lista3.getDuracion());
		System.out.println(lista4.getDuracion());
		System.out.println("********************************************************************");

		//***********************************Guia 2*********************************************
		Condicion c1 = new CondicionMayorDuracion(400);

		Condicion c2 = new CondicionGenero("rock");

		Condicion c3 = new CondicionArtista("lmfo");
		Condicion c4 = new CondicionNot(c3);
		Condicion c5 = new CondicionAnd(c2,c4); //Contega la palabra "rock" Y cuyo interprete no sea "LMFO"

		Condicion c6 = new CondicionMayorYear(2006);
		Condicion c7 = new CondicionArtista("coldplay");
		Condicion c8 = new CondicionAnd(c2,c6); 
		Condicion c9 = new CondicionAnd(c2,c7); 
		Condicion c10 = new CondicionOr(c8,c9); //Contega la palabra "rock" Y sea mayor al año 2006 O Contega la palabra "rock" Y Y cuyo interprete sea "Colplay"

		Vector<ElementoAudio> v1 = coleccion.buscar(c1); //c1,c2,c5 y c10 son las condiciones a probar por la guía

		for (int i = 0; i < v1.size(); i++) {
			System.out.print(v1.elementAt(i).toString());
		}
		
		System.out.println("********************************************************************");
		
		ElementoAudio paradise = new PistaAudio(13,"Paradise",365,"Coldplay","X&Y",2011,"Rock alternativo");
		((Playlist) coleccion).agregarAPlaylist(paradise);
		//Agregamos paradise y probamos la busqueda nuevamente
		v1 = coleccion.buscar(c1); 

		for (int i = 0; i < v1.size(); i++) {
			System.out.print(v1.elementAt(i).toString());
		}
	}	
}
