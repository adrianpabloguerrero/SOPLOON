/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecamusical;

import java.util.ArrayList;

/**
 *
 * @author Yerman
 */
public class BibliotecaMusical {

    /**
     * @param args 
     */
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("************************* Primera Parte *************************************" + "\n");
        
        
        
        //**************Punto 1*****************//
        System.out.println("////////////////////////// Punto 1 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        //tema1
        Pista tema1 = new Pista();
        tema1.setId(1);
        tema1.setTitulo("El Tiempo No Para");
        tema1.setDuracion(311);
        tema1.setArtista("Bersuit Vergarabat");
        tema1.setTitAlbum("De la cabeza");
        tema1.setAño(2002);
        tema1.setGenero("Rock Nacional");
        
        //tema2
        Pista tema2 = new Pista();
        tema2.setId(2);
        tema2.setTitulo("Mi caramelo");
        tema2.setDuracion(290);
        tema2.setArtista("Bersuit Vergarabat");
        tema2.setTitAlbum("De la cabeza");
        tema2.setAño(2002);
        tema2.setGenero("Rock Nacional");
        
        //tema3
        Pista tema3 = new Pista();
        tema3.setId(3);
        tema3.setTitulo("Party Rock Anthem");
        tema3.setDuracion(408);
        tema3.setArtista("LMFAO");
        tema3.setTitAlbum("Sorry for Parting Rocking");
        tema3.setAño(2011);
        tema3.setGenero("Electro pop");

        //tema4
        Pista tema4 = new Pista();
        tema4.setId(4);
        tema4.setTitulo("Sorry for Party Rocking");
        tema4.setDuracion(421);
        tema4.setArtista("LMFAO");
        tema4.setTitAlbum("Sorry for Parting Rocking");
        tema4.setAño(2011);
        tema4.setGenero("Electro pop");      
        
        
        //tema5
        Pista tema5 = new Pista();
        tema5.setId(5);
        tema5.setTitulo("Fix you");
        tema5.setDuracion(255);
        tema5.setArtista("Coldplay");
        tema5.setTitAlbum("X&Y");
        tema5.setAño(2005);
        tema5.setGenero("Rock alternativo");
        
        //tema6
        Pista tema6 = new Pista();
        tema6.setId(6);
        tema6.setTitulo("Speed of Sound");
        tema6.setDuracion(455);
        tema6.setArtista("Coldplay");
        tema6.setTitAlbum("X&Y");
        tema6.setAño(2005);
        tema6.setGenero("Rock alternativo");
        
        //tema7
        Pista tema7 = new Pista();
        tema7.setId(7);
        tema7.setTitulo("Viva la vida");
        tema7.setDuracion(320);
        tema7.setArtista("Coldplay");
        tema7.setTitAlbum("Viva la vida");
        tema7.setAño(2008);
        tema7.setGenero("Rock alternativo");
        
        //tema8
        Pista tema8 = new Pista();
        tema8.setId(8);
        tema8.setTitulo("Whit or whitout you");
        tema8.setDuracion(360);
        tema8.setArtista("U2");
        tema8.setTitAlbum("The Joshua Tree");
        tema8.setAño(1987);
        tema8.setGenero("Rock");
        
        //tema9
        Pista tema9 = new Pista();
        tema9.setId(9);
        tema9.setTitulo("Vertigo");
        tema9.setDuracion(355);
        tema9.setArtista("U2");
        tema9.setTitAlbum("How to Dismantel an Atomic Bomb");
        tema9.setAño(2004);
        tema9.setGenero("rock");
        
        //tema10
        Pista tema10 = new Pista();
        tema10.setId(10);
        tema10.setTitulo("City of Blinding Lights");
        tema10.setDuracion(284);
        tema10.setArtista("U2");
        tema10.setTitAlbum("How to Dismantel an Atomic Bomb");
        tema10.setAño(2004);
        tema10.setGenero("rock");
        
        //tema11
        Pista tema11 = new Pista();
        tema11.setId(11);
        tema11.setTitulo("A la Luz de la Luna");
        tema11.setDuracion(438);
        tema11.setArtista("El Indio Solari");
        tema11.setTitAlbum("Pajaritos, bravos muchachitos");
        tema11.setAño(2013);
        tema11.setGenero("rock nacional");
        
        //tema12
        Pista tema12 = new Pista();
        tema12.setId(12);
        tema12.setTitulo("Yo Canibal");
        tema12.setDuracion(258);
        tema12.setArtista("Patricio rey y sus rendonditos de ricota");
        tema12.setTitAlbum("Lobo Suelto, Cordero atado");
        tema12.setAño(1993);
        tema12.setGenero("Rock Nacional");
        
        //Cree una PlayList con todas las canciones (se llama todas)
        PlayList todas = new PlayList();
        todas.setElemento(tema1);
        todas.setElemento(tema2);
        todas.setElemento(tema3);
        todas.setElemento(tema4);
        todas.setElemento(tema5);
        todas.setElemento(tema6);
        todas.setElemento(tema7);
        todas.setElemento(tema8);
        todas.setElemento(tema9);
        todas.setElemento(tema10);
        todas.setElemento(tema11);
        todas.setElemento(tema12);
        
        System.out.println(todas.toString());
        System.out.println("////////////////////////// Punto 1  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
        
        //**************Punto 1*****************//
        
        //**************Punto 2*****************//
        
        
        //Clasicos del Rock playlist 1,2,8,9,10,12
        PlayList clasRock = new PlayList();
        clasRock.setNombre("Claseicos del Rock");
        clasRock.setId(1);
        clasRock.setElemento(tema1);
        clasRock.setElemento(tema2);
        clasRock.setElemento(tema8);
        clasRock.setElemento(tema9);
        clasRock.setElemento(tema10);
        clasRock.setElemento(tema12);
        
        //Lo mejor playlist 3,4,7,12
        PlayList best = new PlayList();
        best.setNombre("Lo mejor");
        best.setId(2);
        best.setElemento(tema3);
        best.setElemento(tema4);
        best.setElemento(tema7);
        best.setElemento(tema12);
        
        //Coldplay del Rock playlist 5,6,7
        PlayList cold = new PlayList();
        cold.setNombre("Coldplay");
        cold.setId(3);
        cold.setElemento(tema5);
        cold.setElemento(tema6);
        cold.setElemento(tema7);
        
        //El Indio playlist 11,12
        PlayList indi = new PlayList();
        indi.setNombre("El Indio");
        indi.setId(4);
        indi.setElemento(tema12);
        indi.setElemento(tema11);
                
        //**************Punto 2*****************//
        
        //**************Punto 3*****************//
        
        System.out.println("////////////////////////// Punto 3 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        System.out.println("Clasicos del Rock ♫♫ ->"+ "\n" + clasRock.toString());
        System.out.println("Lo mejor ♪♪ ->" + "\n" + best.toString());
        System.out.println("Coldplay ♪♫ ->" + "\n" + cold.toString());
        System.out.println("////////////////////////// Punto 3 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
        
        //**************Punto 3*****************//
        
        //**************Punto 4*****************//
        
        System.out.println("////////////////////////// Punto 4 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        System.out.println("Duracion PlayLIst Clasicos del Rock ♫♫ -> "+ clasRock.getDuracion() + "seg");
        System.out.println("Duracion PlayLIst Lo mejor ♫♫ -> "+ best.getDuracion() + "seg");
        System.out.println("Duracion PlayLIst Coldplay ♫♫ -> "+ cold.getDuracion() + "seg");
        System.out.println("Duracion PlayLIst El Indio ♫♫ -> "+ indi.getDuracion() + "seg");
        System.out.println("////////////////////////// Punto 4 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
        
        System.out.println("************************* Final ** Primera Parte *************************************" + "\n");
        //**************Punto 4*****************//
        
        //**************2DA PARTE*****************//
        
        System.out.println("************************* Segunda Parte *************************************" + "\n");
        
        //**************  punto 1-a  *****************//
        CriterioDuracion sup = new CriterioDuracion(400);
        ArrayList<Pista> res;
        res = todas.busqueda(sup);            
            System.out.println("////////////////////////// Punto 1 a \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        for (Pista p:res){           
            System.out.println("Pistas de duracion mayor a 400 seg ♫♫ -> "+ p.toString() + "seg");            
        }
            System.out.println("////////////////////////// Punto 1 a \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
        //**************  punto 1-a  *****************//
        
        
        //**************  punto 1-b  *****************//
        System.out.println("////////////////////////// Punto 1 b \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        String gen = "Rock";
        CriterioGenero cont;
        cont = new CriterioGenero(gen);
        ArrayList<Pista> result;
        result = todas.busqueda(cont);
        
        for (Pista p:result){            
            System.out.println("Pistas que contienen rock en el genero ♫♫ -> "+ p.toString());        
        }
        System.out.println("////////////////////////// Punto 1 b \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
        //**************  punto 1-b  *****************//
        
        //**************  punto 1-c  *****************//
        System.out.println("////////////////////////// Punto 1 c \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        String inter = "LMFAO";
        CriterioArtista art;
        art = new CriterioArtista(inter);
        CriterioNot n = new CriterioNot(art);
        CriterioAnd a = new CriterioAnd(n,cont);
        ArrayList<Pista> resultado;
        resultado = todas.busqueda(a);
        
        for (Pista p:resultado){            
            System.out.println("Pistas que contienen rock en el genero y cuyo interprete no es LMFAO ♫♫ -> "+ p.toString());        
        }
        System.out.println("////////////////////////// Punto 1 c \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
        //**************  punto 1-c  *****************//
        
        //**************  punto 1-d  *****************//
        System.out.println("////////////////////////// Punto 1 d \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        String arti = "Coldplay";
        CriterioArtista artist;
        artist = new CriterioArtista(arti);        
        CriterioAnd comp = new CriterioAnd(art,cont);
        
        int ref = 2006;
        CriterioAñoMayor m;
        m = new CriterioAñoMayor(ref);
        CriterioAnd c;
        c = new CriterioAnd(m,cont);
        
        
        ArrayList<Pista> r;
        r = todas.busqueda(c);
        
        for (Pista l:r){            
            System.out.println("Punto d or ♫♫ -> "+ l.toString());        
        }
        System.out.println("////////////////////////// Punto 1 d \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
        //**************  punto 1-d  *****************//
        
        //**************  punto 2  *****************//
        
        //tema13
        Pista tema13 = new Pista();
        tema13.setId(13);
        tema13.setTitulo("Paradise");
        tema13.setDuracion(365);
        tema13.setArtista("Coldplay");
        tema13.setTitAlbum("Mylo Xyloto");
        tema13.setAño(2011);
        tema13.setGenero("Rock Alternativo");
        todas.setElemento(tema13);
        
            System.out.println("////////////////////////// Punto 2 a \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        res = todas.busqueda(sup);            
            
        for (Pista p:res){           
            System.out.println("Pistas de duracion mayor a 400 seg ♫♫ -> "+ p.toString() + "seg");            
        }
            System.out.println("////////////////////////// Punto 2 a \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
            
        result = todas.busqueda(cont);
            System.out.println("////////////////////////// Punto 2 b \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
        for (Pista p:result){            
            System.out.println("Pistas que contienen rock en el genero ♫♫ -> "+ p.toString());        
        }
            System.out.println("////////////////////////// Punto 2 b \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
            
        System.out.println("////////////////////////// Punto 2 c \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
            resultado = todas.busqueda(a);
        
        for (Pista p:resultado){            
            System.out.println("Pistas que contienen rock en el genero y cuyo interprete no es LMFAO ♫♫ -> "+ p.toString());        
        }
        System.out.println("////////////////////////// Punto 2 c \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
        
        System.out.println("////////////////////////// Punto 2 d \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
        r = todas.busqueda(c);
        
        for (Pista l:r){            
            System.out.println("Punto d or ♫♫ -> "+ l.toString());        
        }
        System.out.println("////////////////////////// Punto 2 d \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\" + "\n");
        
        
        
        //**************  punto 2  *****************//
        
        System.out.println("************************* Segunda Parte ** Final *************************************" + "\n");
        
        
        
    }
    
  
    
    
    
}
