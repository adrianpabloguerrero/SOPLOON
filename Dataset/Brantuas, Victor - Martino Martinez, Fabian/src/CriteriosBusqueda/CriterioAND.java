package CriteriosBusqueda;

import java.util.Vector;
import clases.*;

public class CriterioAND extends CriterioCompuestoAbs{
    
    
    public CriterioAND(CriterioAbs uno, CriterioAbs dos){
        super(uno, dos);
    }
    
    @Override
    public Vector<Pista> GetResultado(Vector<PistaAbs> LibreriaCompleta){
        Vector<Pista> ResultadoAux1 = cr1.GetResultado(LibreriaCompleta);
        Vector<Pista> ResultadoAux2 = cr2.GetResultado(LibreriaCompleta);
        Vector<Pista> Resultado = new Vector<Pista>();
        for (Pista p: ResultadoAux1){
            if (ResultadoAux2.contains(p))
                Resultado.add(p);
        }
        return Resultado;
    }

}
