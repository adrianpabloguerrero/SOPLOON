package sistema;

public class BuscarGenero extends Busqueda {

    String nombre;

    public BuscarGenero(String _nombre) {
        nombre = _nombre;
    }

    public boolean busqueda(Pista p) {
        return coincide(p.getGenero(), nombre);
    }


}
