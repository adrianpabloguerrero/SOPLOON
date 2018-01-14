package clases;

import CriteriosBusqueda.*;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;


public class PlayList extends PistaAbs{
    
    private Vector<PistaAbs> elementos;
    private Map<String, Object> descripcion;
    
    public PlayList(){
        super();
        elementos = new Vector<PistaAbs>();
        descripcion = new HashMap<>();
    }
    
    public void addElemento(PistaAbs nuevapista){
        elementos.add(nuevapista);
        if (descripcion.containsKey("duracion")){
            descripcion.replace("duracion",((Float)this.getValorAtributo("duracion")+(Float)nuevapista.getValorAtributo("duracion")));
        }
        else
            this.setAtributo("duracion",nuevapista.getValorAtributo("duracion"));
    }
    
    public void removeElemento(PistaAbs pista){
        if (elementos.contains(pista))
            elementos.remove(pista);
    }
    
    public void changeElementos(PistaAbs pista1, PistaAbs pista2){
        if (elementos.contains(pista1) && (elementos.contains(pista2))){
            Integer index1 = elementos.indexOf(pista1);
            Integer index2 = elementos.indexOf(pista2);
            if (index1 < index2){
                elementos.removeElementAt(index1);
                elementos.insertElementAt(pista2, index1);
                elementos.removeElementAt(index2);
                elementos.insertElementAt(pista1, index2);
            }
            else{
                elementos.removeElementAt(index2);
                elementos.insertElementAt(pista2, index2);
                elementos.removeElementAt(index1);
                elementos.insertElementAt(pista1, index1);
            }
        }     
    }
    
    @Override
    public void setAtributo(String nombre, Object valor){
        descripcion.put(nombre,valor);
    }
   
    @Override
    public Object getValorAtributo(String nombre){
        return descripcion.get(nombre);
    }
    
    @Override
    public Integer CantElementos(){
        Integer Acum = 0;
        for (PistaAbs p : elementos)
            Acum += p.CantElementos();
        return Acum;
    }
    
    @Override
    public Vector<PistaAbs> getElementos(){
        return elementos;
    } 
    
    public void OrdenarElementos(String atributo, String modo){
        Comparar c = new Comparar(null, atributo, modo);
        Collections.sort(elementos,c);
    }
    
    public void ImprimirPlayList(){
        for (PistaAbs p : elementos)
            System.out.println("Titulo: "+p.getValorAtributo("titulo")+
            " - Artista/Interprete: "+p.getValorAtributo("interprete")+ 
            " - Album: "+p.getValorAtributo("Titulo del Album")+
            "("+p.getValorAtributo("genero")+", "+Math.round((float) p.getValorAtributo("anio"))+")");
        System.out.println();
    }
    

}
