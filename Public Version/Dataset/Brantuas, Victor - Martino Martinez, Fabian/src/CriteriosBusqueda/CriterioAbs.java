package CriteriosBusqueda;

import clases.*;
import CriteriosBusqueda.*;
import java.util.Vector;

public abstract class CriterioAbs {
    
    public CriterioAbs(){
    }
    
    public abstract String getAtributo();
    public abstract Object getValor();
    public abstract Vector<Pista> GetResultado(Vector<PistaAbs> LibreriaCompleta);
}
