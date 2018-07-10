public class Simulador {
	public static void main(String[] args) {
		Pista pista1  = new Pista("el tiempo no para", 311, "Bersuit Vergabarat", "De la cabeza", 2002, "Rock Nacional","");
		Pista pista2  = new Pista("Mi caramelo", 290, "Bersuit Vergarabat", "De la cabeza", 2002, "Rock nacional","");
		Pista pista3  = new Pista("Party Rock Anthem", 408, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop","");
		Pista pista4  = new Pista("Sorry for Party Rocking", 421, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop","");
		Pista pista5  = new Pista("Fix you", 255, "Coldplay", "X&Y", 2005, "Rock alternativo","");
		Pista pista6  = new Pista("Speed of Sound", 455, "Coldplay", "X&Y", 2005, "Rock alternativo","");
		Pista pista7  = new Pista("Viva la vida", 320, "Coldplay", "Viva la vida", 2008, "Rock alternativo","");
		Pista pista8  = new Pista("Whit or whitout you", 360, "U2", "The Joshua Tree", 1987, "Rock","");
		Pista pista9  = new Pista("Vertigo", 355, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock","");
		Pista pista10 = new Pista("City of Blinding Lights", 284, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock","");
		Pista pista11 = new Pista("A la luz de la luna", 438, "El Indio Solari", "How to Dismantle an Atomic Bomb", 2013, "Rock nacional","");
		Pista pista12 = new Pista("Yo Canibal", 258, "Patricio rey y sus redonditos de ricota", "Lobo Suelto, Cordero atado", 1993, "Rock nacional","");
		
		Playlist sistemaPrincipal = new Playlist("Sistema principal");
		
		Playlist clasicosDelRock = new Playlist("Clasicos del Rock");
		Playlist loMejor = new Playlist("Lo mejor");
		Playlist coldplay = new Playlist("Lo mejor");
		Playlist elIndio = new Playlist("El Indio");				
		
		clasicosDelRock.addElemento(pista1);clasicosDelRock.addElemento(pista2);clasicosDelRock.addElemento(pista8);clasicosDelRock.addElemento(pista9);clasicosDelRock.addElemento(pista10);clasicosDelRock.addElemento(pista12);
		loMejor.addElemento(pista3);loMejor.addElemento(pista4);loMejor.addElemento(pista7);loMejor.addElemento(pista12);
		coldplay.addElemento(pista5);coldplay.addElemento(pista6);coldplay.addElemento(pista7);
		elIndio.addElemento(pista12);elIndio.addElemento(pista11);
		
		sistemaPrincipal.addElemento(pista1);sistemaPrincipal.addElemento(pista2);sistemaPrincipal.addElemento(pista3);sistemaPrincipal.addElemento(pista4);sistemaPrincipal.addElemento(pista5);sistemaPrincipal.addElemento(pista6);sistemaPrincipal.addElemento(pista7);sistemaPrincipal.addElemento(pista8);sistemaPrincipal.addElemento(pista9);sistemaPrincipal.addElemento(pista10);sistemaPrincipal.addElemento(pista11);
				
		System.out.println(sistemaPrincipal.toString());
		
		Condicion condicionArtistaLMFAO = new BuscarPorArtista("LmFaO");
		Condicion condicionArtistaColdplay = new BuscarPorArtista("cOlDpLaY");
		Condicion condicionTituloRock = new BuscarPorTitulo("RoCk");
		Condicion condicionTiempoMayor400  = new BuscarPorDuracionMayorA(400);
		Condicion condicionGeneroRock = new BuscarPorGenero("rOcK");
		Condicion artistaNoLMFAO = new Not(condicionArtistaLMFAO);
		Condicion condicionFechaMayor2006 = new BuscarPorFechaMayorA(2006);
		Condicion condicionNoLmfaoAndNombreRock = new And (condicionTituloRock, artistaNoLMFAO);
		Condicion condicionGeneroRockAndMayor2006 = new And (condicionGeneroRock, condicionFechaMayor2006);
		Condicion condicionGeneroRockAndArtistaColdplay = new And (condicionGeneroRock, condicionArtistaColdplay);
		Condicion condicionGeneroRockAndMayor2006OrGeneroRockAndArtistaColdplay = new Or (condicionGeneroRockAndMayor2006, condicionGeneroRockAndArtistaColdplay);

		//PARTE 1
		System.out.println("Pistas cuya duración sea superior a 400 segundos.");
		System.out.println(sistemaPrincipal.devolverElemFiltrados(condicionTiempoMayor400));
		
		System.out.println("Pistas cuyo género contenga la palabra “rock”.");
		System.out.println(sistemaPrincipal.devolverElemFiltrados(condicionGeneroRock));
		
		System.out.println("Las pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”.");
		System.out.println(sistemaPrincipal.devolverElemFiltrados(condicionNoLmfaoAndNombreRock));
		
		System.out.println("Las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”.");
		System.out.println(sistemaPrincipal.devolverElemFiltrados(condicionGeneroRockAndMayor2006OrGeneroRockAndArtistaColdplay));
		
		//PARTE 2
		System.out.println("Agregar una nueva pista al Sistema con la descripción dada a continuación e imprimir nuevamente las búsquedas del punto 1:");
		Pista pista13 = new Pista("Paradise", 365, "Coldplay", "Mylo Xyloto", 2011, "Rock alternativo","");
		sistemaPrincipal.addElemento(pista13);
		
		System.out.println("Pistas cuya duración sea superior a 400 segundos.");
		System.out.println(sistemaPrincipal.devolverElemFiltrados(condicionTiempoMayor400));
		
		System.out.println("Pistas cuyo género contenga la palabra “rock”.");
		System.out.println(sistemaPrincipal.devolverElemFiltrados(condicionGeneroRock));
		
		System.out.println("Las pistas cuyo nombre contenga “rock” pero cuyo interprete NO sea “LMFAO”.");
		System.out.println(sistemaPrincipal.devolverElemFiltrados(condicionNoLmfaoAndNombreRock));
		
		System.out.println("Las pistas cuyo género contenga “rock” y cuya fecha sea mayor a “2006”, o cuyo género contenga “rock” y cuyo intérprete sea “coldplay”.");
		System.out.println(sistemaPrincipal.devolverElemFiltrados(condicionGeneroRockAndMayor2006OrGeneroRockAndArtistaColdplay));
		
	}
}