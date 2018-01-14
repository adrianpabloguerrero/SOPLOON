/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecamusical;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yerman
 */
public class PlayList extends Elemento {
    private List<Elemento> elementos;
    
    public PlayList(){
    elementos = new ArrayList<>();
    }
    
    public void setElemento (Elemento agreg){
    elementos.add(agreg);
    }

    
    
    @Override
    public int getDuracion() {
    int res = 0;    
        if (!elementos.isEmpty()){
            for (Elemento p:elementos){
            res += p.getDuracion();
            }
        }
    return res;
    }
    
    public ArrayList<Pista> busqueda (Criterio c){
        ArrayList<Pista> resultado = new ArrayList<>();
            for(Elemento e : elementos){
                if(!resultado.containsAll(e.busqueda(c)))
                    resultado.addAll(e.busqueda(c));
            }
        return resultado;
    }

    @Override
    public int cantElementos() {
    int res = 0;
        if (!elementos.isEmpty()){
            for (Elemento p:elementos){
            res += p.cantElementos();
            }
        }
    return res;
    }
    
    public String toString(){
    String res = "";
        if (!elementos.isEmpty()){
            for (Elemento p:elementos){
            res += p.toString() + "\n" ;
            }
        }
    return res;
    }
    
}
