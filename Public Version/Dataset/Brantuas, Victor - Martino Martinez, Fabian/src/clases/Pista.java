package clases;

import CriteriosBusqueda.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

public class Pista extends PistaAbs{
    
    private Map<String, Object> descripcion;
    
    public Pista(){
        super();
        descripcion = new HashMap<>();
    }
    
    @Override
    public void setAtributo(String nombre, Object valor){
        descripcion.put(nombre, valor);
    }

    @Override
    public Object getValorAtributo(String nombre){
        return descripcion.get(nombre);
    }
    
    @Override
    public Integer CantElementos(){
        return 1;
    }
    
    @Override
    public Vector<PistaAbs> getElementos(){
        Vector<PistaAbs> Resultado = new Vector<PistaAbs>();
        Resultado.add(this);
        return Resultado;
    }

}
