package bibliotecamusical;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yerman
 */
public abstract class Elemento {
    private String nombre;
    private int id;
    
    public void setNombre(String nomb){
    nombre = nomb;
    }
    
    public String getNombre(){
    return nombre;
    }
    
    public void setId(int ID){
    id = ID;
    }
    
    public int getId(){
    return id;
    }
    
    public abstract ArrayList<Pista> busqueda(Criterio c);
    
    public abstract int getDuracion();
    
    public abstract int cantElementos();
    
}
