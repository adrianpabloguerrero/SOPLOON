
public class Guia {

	public static void main(String[] args) {
		Pista pista1  = new Pista(1, "el tiempo no para", 311, "Bersuit Vergabarat", "De la cabeza", 2002, "Rock Nacional","descipcion");
		Pista pista2  = new Pista(2, "Mi caramelo", 290, "Bersuit Vergabarat", "De la cabeza", 2002, "Rock nacional","descipcion");
		Pista pista3  = new Pista(3, "Party Rock Anthem", 408, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop","descipcion");
		Pista pista4  = new Pista(4, "Sorry for Party Rocking", 421, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop","descipcion");
		Pista pista5  = new Pista(5, "Fix you", 255, "Coldplay", "X&Y", 2005, "Rock alternativo","descipcion");
		Pista pista6  = new Pista(6, "Speed of Sound", 455, "Coldplay", "X&Y", 2005, "Rock alternativo","descipcion");
		Pista pista7  = new Pista(7, "Viva la vida", 320, "Coldplay", "Viva la vida", 2008, "Rock alternativo","descipcion");
		Pista pista8  = new Pista(8, "Whit or whitout you", 360, "U2", "The Joshua Tree", 1987, "Rock","descipcion");
		Pista pista9  = new Pista(9, "Vertigo", 355, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock","descipcion");
		Pista pista10 = new Pista(10,"City of Blinding Lights", 284, "U2", "How to Dismantle an Atomic Bomb", 2004, "Rock","descipcion");
		Pista pista11 = new Pista(11,"A la luz de la luna", 438, "El Indio Solari", "How to Dismantle an Atomic Bomb", 2013, "Rock nacional","descipcion");
		Pista pista12 = new Pista(12,"Yo Canibal", 258, "Patricio rey y sus redonditos de ricota", "Lobo Suelto, Cordero atado", 1993, "Rock nacional","descipcion");
		
		PlayList general = new PlayList("General");
		
		general.agregarElemento(pista1);
		general.agregarElemento(pista2);
		general.agregarElemento(pista3);
		general.agregarElemento(pista4);
		general.agregarElemento(pista5);
		general.agregarElemento(pista6);
		general.agregarElemento(pista7);
		general.agregarElemento(pista8);
		general.agregarElemento(pista9);
		general.agregarElemento(pista10);
		general.agregarElemento(pista11);
		general.agregarElemento(pista12);
		
		//a)
		CondicionDuracionMayorA c1 = new CondicionDuracionMayorA(400);//esta
		//b)
		CondicionGenero c2 = new CondicionGenero("rock");//esta
		//c)
		CondicionNombre c3 = new CondicionNombre("rock");
		CondicionAutor c4 = new CondicionAutor("LMFAO");
		Not c5 = new Not(c4);
		And c6 = new And(c3,c5);//esta
		//d)
		CondicionFechaMayorA c7= new CondicionFechaMayorA(2006);
		CondicionAutor c8 = new CondicionAutor("coldplay");
		And c9 = new And(c2,c7);
		And c10 = new And(c2,c8);
		Or c11 = new Or(c9,c10); //esta
		
		
		System.out.println(general.coincide(c2));
	}
}
             