package sistema;

public class BNot extends Busqueda {

    Busqueda b1;

    public BNot(Busqueda _b1){
        b1 = _b1;
    }


    public boolean busqueda(Pista p) {
        return !b1.busqueda(p);
    }
}
