package CriteriosBusqueda;

import java.util.Vector;
import clases.*;

public class CriterioMayor extends CriterioAbs {
    
    private Object valor;
    private String atributo;

    public CriterioMayor(String atributo, Object value){
        super();
        this.valor = value;
        this.atributo = atributo;
    }
    
    @Override
    public String getAtributo(){
        return atributo;
    }
    
    @Override
    public Object getValor(){
        return valor;
    }
    
    @Override
    public Vector<Pista> GetResultado(Vector<PistaAbs> LibreriaCompleta){
        Vector<Pista> Resultado = new Vector<Pista>();
        for (PistaAbs p : LibreriaCompleta){
            if (p.CantElementos() == 1 && (p.getValorAtributo("id") != null)){
                if ((Float)p.getValorAtributo(atributo) > (Float)valor && (!Resultado.contains(p)))
                    Resultado.add((Pista)p);
            }
            else{
                Vector<Pista> ResultadoAux = new Vector<Pista>(); // elimina duplicados
                ResultadoAux.addAll(GetResultado(p.getElementos()));
                for (Pista p1 : ResultadoAux)
                    if (!Resultado.contains(p1))
                        Resultado.add(p1);
            }
        }
        return Resultado;
    }

}