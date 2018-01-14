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
public class Pista extends Elemento {
    private String titulo;
    private int duracion;
    private String artista;
    private String tituloAlbum;
    private int año;
    private String genero;
    private List<String> comentarios = new ArrayList();
    
    public void setTitulo(String titu){
    titulo = titu;
    }
    
    public String getTitulo(){
    return titulo;
    }
    
    public void setDuracion(int dur){
    duracion = dur;
    }   
    
    @Override
    public int getDuracion() {
        return duracion;
    }
    
    public void setArtista(String art){
    artista = art;
    }
    
    public String getArtista(){
    return artista;   
    }
    
    public void setTitAlbum(String alb){
    tituloAlbum = alb;
    }
    
    public String getTitAlbum(){
    return tituloAlbum;
    }
    
    public void setAño(int an){
    año = an;
    }
    
    public int getAño(){
    return año;
    }
    
    public void setGenero(String gen){
    genero = gen;
    }
    
    public String getGenero(){
    return genero;
    }
    
    public void setComentario(String comen){
    comentarios.add(comen);
    }
    
    public String getComentarios(){
    String res = " ♫ ♪ " + getTitulo() + " ♫ ♪ ";    
        if (!comentarios.isEmpty()){
            for (String c:comentarios){
            res = res + "\n" + c + " ";
            }        
        }    
    return res;
    }
    
    /**
     *
     * @param c
     * @return
     */
    public ArrayList<Pista> busqueda (Criterio c){
        ArrayList resultado = new ArrayList<>();
             if (c.cumple(this))
                 resultado.add(this);
        return resultado;
    }

    @Override
    public int cantElementos() {
        return 1;
    }
    
    
    @Override
    public String toString(){
        return getId() + " " + titulo + " " + duracion + " " + artista + " " + tituloAlbum + " " + año + " " + genero;
    }
    
}
