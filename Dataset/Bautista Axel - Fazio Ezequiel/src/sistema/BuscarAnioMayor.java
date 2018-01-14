package sistema;

public class BuscarAnioMayor extends Busqueda{

    int anio;
    public BuscarAnioMayor(int _anio){
        anio = _anio;
    }

    public boolean busqueda(Pista p) {
        return p.getAnio() >= anio;
    }
}
