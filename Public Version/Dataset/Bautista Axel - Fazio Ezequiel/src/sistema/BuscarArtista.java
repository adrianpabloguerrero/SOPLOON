package sistema;

public class BuscarArtista extends Busqueda {

    String nombre;

    public BuscarArtista(String _nombre) {
        nombre = _nombre;
    }

    public boolean busqueda(Pista p) {
        return coincide(p.getArtista(), nombre);
    }

}
