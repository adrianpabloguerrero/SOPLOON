/**
 * Es la clase que contiene los métodos utilizados para devolver una Lista de reproducción filtrada
 * por un único criterio, o bien, concatenar multiples parámetros.
 * 
 *  
 * @author Bernardo Corengia
 */	
public class Filtro {
		
	public ListaReproduccion listaFiltrada(Parametro p, Object v, ListaReproduccion l) {
		ListaReproduccion aux = new ListaReproduccion(p.toString()+": "+ v);
		for (ElementoReproduccion pista : l.elementos) {
			if (pista.cantidadElementos() == 1) {
				if (cumpleParametro(p, v, (Pista) pista)) {
					aux.agregarElemento(pista);
				}
			}
		}
		return aux;
	}

	public boolean cumpleParametro(Parametro p, Object v, Pista q) {

		switch (p) {
		case ID_IGUAL_A:
			return (q.getId() == (int) v);
		case AÑO_MENOR_A:
			return (q.getAnio() < (int) v);
		case AÑO_IGUAL_A:
			return (q.getAnio() == (int) v);
		case AÑO_MAYOR_A:
			return (q.getAnio() > (int) v);
		case DURACION_MENOR_A:
			return (q.getDuracion() < (int) v);
		case DURACION_IGUAL_A:
			return (q.getDuracion() == (int) v);
		case DURACION_MAYOR_A:
			return (q.getDuracion() > (int) v);
		case TITULO_CONTIENE:
			String t = (String)v;
			return (q.getNombre().toLowerCase().contains(t.toLowerCase()));
		case ALBUM_CONTIENE:
			String a = (String)v;
			return (q.getAlbum().toLowerCase().contains(a.toLowerCase()));
		case GENERO_CONTIENE:
			String g = (String)v;
			return (q.getGenero().toLowerCase().contains(g.toLowerCase()));
		case INTERPRETE_CONTIENE:
			String i = (String)v;
			return (q.getInterprete().toLowerCase().contains(i.toLowerCase()));
		default:
			return true;
		}
	}
	
	public ListaReproduccion concatenarFiltro (Operacion o, ListaReproduccion l1, ListaReproduccion l2) {
		ListaReproduccion aux = new ListaReproduccion("");
		switch (o) {
		case AND:
			aux.setNombre("("+l1.getNombre()+" -AND- "+l2.getNombre()+")");
			for (ElementoReproduccion pista : l2.elementos) {
				if (l1.elementos.contains(pista)) {
					aux.agregarElemento(pista);
				}
			}
			break;
		case OR:
			aux = l1;
			aux.setNombre("("+l1.getNombre()+" -OR- "+l2.getNombre()+")");
			for (ElementoReproduccion pista : l2.elementos) {
				if (!l1.elementos.contains(pista)) {
					aux.agregarElemento(pista);
				}
			}
			break;
		case NOT:
			aux = l1;
			aux.setNombre("("+l1.getNombre()+" -NOT- "+l2.getNombre()+")");
			for (ElementoReproduccion pista : l2.elementos) {
				if(l1.elementos.contains(pista)) {
					aux.quitarElemento(pista);
				}
			}
			break;
		}
		return aux;
	}
}