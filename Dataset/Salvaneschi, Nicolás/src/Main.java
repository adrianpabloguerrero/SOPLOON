import java.util.*;
public class Main {
	
	public static void main(String[]args){
		int id1;
		int a;
		int b;
		String x;
		String y;
		boolean aux=false;
		
		Cancion cancion1=new Cancion(1,"El tiempo no para", 311, "Bersuit Vergarabat", "De la cabeza", 2002, "rock nacional", "temazo");
		Cancion cancion2=new Cancion(2,"Mi caramelo", 290, "Bersuit Vergarabat", "De la cabeza", 2002, "rock nacional", "Cordera canta muy bien");
		Cancion cancion3=new Cancion(3,"Party Rock anthem", 408, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop", "La Rompe");
		Cancion cancion4=new Cancion(4,"Sorry for party rocking", 421, "LMFAO", "Sorry for Party Rocking", 2011, "Electro pop", "Este me encanta");
		Cancion cancion5=new Cancion(5,"Fix you", 255, "Coldplay", "X&Y", 2005, "rock alternativo", "Rock Britanico espacial");
		Cancion cancion6=new Cancion(6,"Speed of sound", 455, "Coldplay", "X&Y", 2005, "rock alternativo", "Rock British");
		Cancion cancion7=new Cancion(7,"Viva la vida", 320, "Coldplay", "Viva la vida", 2008, "rock alternativo", "Mas experimental");
		Cancion cancion8=new Cancion(8,"Whit out whitout you", 360, "U2", "The Joshua Tree", 1987, "Rock", "Gran base de bajo");
		Cancion cancion9=new Cancion(9,"Vertigo", 355, "U2", "How to dismantle an atomic bomb", 2004, "Rock", "Aguantaaaaaa");
		Cancion cancion10=new Cancion(10,"City of Blinding Lights", 290, "U2", "How to dismantle an atomic bomb", 2004, "Rock", "Gran base de bajo");
		Cancion cancion11=new Cancion(11,"A la luz de la luna", 438, "El indio Solari","Pajaritos, bravos muchachitos",2013,"rock nacional", "A la luzzz!");
		Cancion cancion12=new Cancion(12,"Yo canibal", 258, "Patricio Rey y sus redonditos de Ricota","Lobo suelto, cordero atado",1993,"Rock nacional","dale bolaaa");
		
		
		ListaReproduccion L1=new ListaReproduccion("primera");
		
		ListaReproduccion L2=new ListaReproduccion("segunda");
		
		ListaReproduccion L3=new ListaReproduccion("tercera");
		
		//Lista 1
		
		L1.add(cancion4);
		L1.add(cancion5);
		L1.add(cancion6);
		L1.add(cancion10);
		L1.add(cancion7);
		L1.add(cancion11);
		
		//Lista 2
		L2.add(cancion1);
		L2.add(cancion2);
		L2.add(cancion3);		
		L2.add(L1);
		L2.add(cancion8);
		L2.add(cancion9);
		L2.add(cancion12);
		
		
		L3.add(cancion1);
		
		
		Condicion cd=new CondFechaMayor(2002);
		Condicion cd1=new CondDuracion(290);
		Condicion cd2=new CondArtista("Col");
		Condicion cd3=new CondArtista("Ber");
		Condicion cand=new And(cd1,cd3);		
		Condicion cor=new Or(cd2,cd3);
		Condicion not=new Not(cor);
		
		
		Vector<Elemento> v1=new Vector<Elemento>();
		
		
		v1=L2.buscarCondicion(not);
		
		for (int j = 0; j < v1.size(); j++) {
			System.out.println(v1.elementAt(j).getNombre());
		}
		
		
		
		
			
		
		
		
		
		
			
		
		
		
		
		
		
		
		
	}

}
