package CriteriosBusqueda;

import java.util.Vector;
import clases.*;

public abstract class CriterioCompuestoAbs extends CriterioAbs{
    
    protected CriterioAbs cr1;
    protected CriterioAbs cr2;
    
    public CriterioCompuestoAbs(CriterioAbs uno, CriterioAbs dos){
        super();
        cr1 = uno;
        cr2 = dos;
    }

    public abstract Vector<Pista> GetResultado(Vector<PistaAbs> LibreriaCompleta);
    
    @Override
    public String getAtributo(){
        return null;
    }
    
    @Override
    public Object getValor(){
        return null;
    }

}
