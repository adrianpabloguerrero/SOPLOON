package CriteriosBusqueda;

import java.util.Vector;
import clases.*;

public class CriterioOR extends CriterioCompuestoAbs{
    
    
    public CriterioOR(CriterioAbs uno, CriterioAbs dos){
        super(uno, dos);
    }
    
    @Override
    public Vector<Pista> GetResultado(Vector<PistaAbs> LibreriaCompleta){
        Vector<Pista> ResultadoAux1 = cr1.GetResultado(LibreriaCompleta);
        Vector<Pista> ResultadoAux2 = cr2.GetResultado(LibreriaCompleta);
        Vector<Pista> Resultado = ResultadoAux1;
        for (Pista p: ResultadoAux2){
            if (!(Resultado.contains(p)))
                Resultado.add(p);
        }
        return Resultado;
    }

}
