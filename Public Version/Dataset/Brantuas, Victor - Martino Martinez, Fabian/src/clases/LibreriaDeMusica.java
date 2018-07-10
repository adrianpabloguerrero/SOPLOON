package clases;

import CriteriosBusqueda.*;
import java.util.Vector;
import clases.*;

public class LibreriaDeMusica {

    public static void main(String[] args) throws CloneNotSupportedException {
    	//--------------------------------- PRIMERA PARTE  ---------------------------------
        System.out.println("PRIMERA PARTE");
        //--------------------------------- INCISO 1 ---------------------------------
        
        Pista p1 = new Pista();
        p1.setAtributo("id",1);
        p1.setAtributo("titulo","El Tiempo no Para");
        p1.setAtributo("duracion",311.0f);
        p1.setAtributo("interprete","Bersuit Vergarabat");
        p1.setAtributo("Titulo del Album","De la Cabeza");
        p1.setAtributo("anio",2002.0f);
        p1.setAtributo("genero","Rock Nacional");
        p1.setAtributo("comentario", "");
        
        Pista p2 = new Pista();
        p2.setAtributo("id",2);
        p2.setAtributo("titulo","mi caramelo");
        p2.setAtributo("duracion",290.0f);
        p2.setAtributo("interprete","Bersuit Vergarabat");
        p2.setAtributo("Titulo del Album","De la Cabeza");
        p2.setAtributo("anio",2002.0f);
        p2.setAtributo("genero","Rock Nacional");
        p2.setAtributo("comentario", "");

        
        Pista p3 = new Pista();
        p3.setAtributo("id",3);
        p3.setAtributo("titulo","Party Rock Anthem");
        p3.setAtributo("duracion",408.0f);
        p3.setAtributo("interprete","LMFAO");
        p3.setAtributo("Titulo del Album","Sorry for Party Rocking");
        p3.setAtributo("anio",2011.0f);
        p3.setAtributo("genero","Electro Pop");
        p3.setAtributo("comentario", "");

        
        Pista p4 = new Pista();
        p4.setAtributo("id",4);
        p4.setAtributo("titulo","Sorry for Party Rocking");
        p4.setAtributo("duracion",421.0f);
        p4.setAtributo("interprete","LMFAO");
        p4.setAtributo("Titulo del Album","Sorry for Party Rocking");
        p4.setAtributo("anio",2011.0f);
        p4.setAtributo("genero","Electro Pop");
        p4.setAtributo("comentario", "");

        
        Pista p5 = new Pista();
        p5.setAtributo("id",5);
        p5.setAtributo("titulo","Fix You");
        p5.setAtributo("duracion",255.0f);
        p5.setAtributo("interprete","Coldplay");
        p5.setAtributo("Titulo del Album","X&Y");
        p5.setAtributo("anio",2005.0f);
        p5.setAtributo("genero","Rock alternativo");
        p5.setAtributo("comentario", "");

        
        Pista p6 = new Pista();
        p6.setAtributo("id",6);
        p6.setAtributo("titulo","speed of sound");
        p6.setAtributo("duracion",455.0f);
        p6.setAtributo("interprete","Coldplay");
        p6.setAtributo("Titulo del Album","X&Y");
        p6.setAtributo("anio",2005.0f);
        p6.setAtributo("genero","Rock Alternativo");
        p6.setAtributo("comentario", "");

        
        Pista p7 = new Pista();
        p7.setAtributo("id",7);
        p7.setAtributo("titulo","Viva la Vida");
        p7.setAtributo("duracion",320.0f);
        p7.setAtributo("interprete","Coldplay");
        p7.setAtributo("Titulo del Album","Viva la Vida");
        p7.setAtributo("anio",2008.0f);
        p7.setAtributo("genero","Rock Alternativo");
        p7.setAtributo("comentario", "");

        
        Pista p8 = new Pista();
        p8.setAtributo("id",8);
        p8.setAtributo("titulo","With or Without you");
        p8.setAtributo("duracion",360.0f);
        p8.setAtributo("interprete","U2");
        p8.setAtributo("Titulo del Album","The Joshua Tree");
        p8.setAtributo("anio",1987.0f);
        p8.setAtributo("genero","Rock");
        p8.setAtributo("comentario", "");

        
        Pista p9 = new Pista();
        p9.setAtributo("id",9);
        p9.setAtributo("titulo","Vertigo");
        p9.setAtributo("duracion",355.0f);
        p9.setAtributo("interprete","U2");
        p9.setAtributo("Titulo del Album","How to Dismantle an Atomic Bomb");
        p9.setAtributo("anio",2004.0f);
        p9.setAtributo("genero","rock");
        p9.setAtributo("comentario", "");

        
        Pista p10 = new Pista();
        p10.setAtributo("id",10);
        p10.setAtributo("titulo","City of Blinding Lights");
        p10.setAtributo("duracion",284.0f);
        p10.setAtributo("interprete","U2");
        p10.setAtributo("Titulo del Album","How to Dismantle an Atomic Bomb");
        p10.setAtributo("anio",2004.0f);
        p10.setAtributo("genero","Rock");
        p10.setAtributo("comentario", "");

        
        Pista p11 = new Pista();
        p11.setAtributo("id",11);
        p11.setAtributo("titulo","A la luz de la luna");
        p11.setAtributo("duracion",438.0f);
        p11.setAtributo("interprete","El Indio Solari");
        p11.setAtributo("Titulo del Album","Pajaritos, bravos muchachitos");
        p11.setAtributo("anio",2013.0f);
        p11.setAtributo("genero","rock nacional");
        p11.setAtributo("comentario", "");

        
        Pista p12 = new Pista();
        p12.setAtributo("id",12);
        p12.setAtributo("titulo","Yo Canibal");
        p12.setAtributo("duracion",258.0f);
        p12.setAtributo("interprete","Patricio rey y sus redonditos de ricota");
        p12.setAtributo("Titulo del Album","Lobo Suelto, Cordero atado");
        p12.setAtributo("anio",1993.0f);
        p12.setAtributo("genero","Rock Nacional");
        p12.setAtributo("comentario", "");

        
        //--------------------------------- INCISO 2 ---------------------------------
        
        PlayList clrock = new PlayList();
        clrock.setAtributo("nombre","Clasicos del Rock");
        clrock.addElemento(p1);
        clrock.addElemento(p2);
        clrock.addElemento(p8);
        clrock.addElemento(p9);
        clrock.addElemento(p10);
        clrock.addElemento(p12);
        
        PlayList lomejor = new PlayList();
        lomejor.setAtributo("nombre","Lo Mejor");
        lomejor.addElemento(p3);
        lomejor.addElemento(p4);
        lomejor.addElemento(p7);
        lomejor.addElemento(p12);
        
        PlayList coldplay = new PlayList();
        coldplay.setAtributo("nombre","Coldplay");
        coldplay.addElemento(p5);
        coldplay.addElemento(p6);
        coldplay.addElemento(p7);
        
        PlayList indio = new PlayList();
        indio.setAtributo("nombre","EL Indio");
        indio.addElemento(p12);
        indio.addElemento(p11);
        
        //--------------------------------- INCISO 3 ---------------------------------
        
        System.out.println("INCISO 3");
        System.out.println("Playlist - Clasicos del Rock");
        clrock.ImprimirPlayList();
        System.out.println("Playlist - Lo mejor");
        lomejor.ImprimirPlayList();
        System.out.println("Playlist - Coldplay");
        coldplay.ImprimirPlayList();
        
        //--------------------------------- INCISO 4 ---------------------------------
        
        System.out.println("INCISO 4");
        System.out.println("Playlist: "+clrock.getValorAtributo("nombre")+" - duracion: "+clrock.getValorAtributo("duracion"));
        System.out.println("Playlist: "+lomejor.getValorAtributo("nombre")+" - duracion: "+lomejor.getValorAtributo("duracion"));
        System.out.println("Playlist: "+coldplay.getValorAtributo("nombre")+" - duracion: "+coldplay.getValorAtributo("duracion"));     
        
      //--------------------------------- SEGUNDA PARTE  ---------------------------------
        System.out.println();
        System.out.println();
        Vector<PistaAbs> LibreriaCompleta = new Vector<PistaAbs>();
        LibreriaCompleta.add(clrock);
        LibreriaCompleta.add(lomejor);
        LibreriaCompleta.add(coldplay);
        LibreriaCompleta.add(indio);
        LibreriaCompleta.add(p1);
        LibreriaCompleta.add(p2);
        LibreriaCompleta.add(p3);
        LibreriaCompleta.add(p4);
        LibreriaCompleta.add(p5);
        LibreriaCompleta.add(p6);
        LibreriaCompleta.add(p7);
        LibreriaCompleta.add(p8);
        LibreriaCompleta.add(p9);
        LibreriaCompleta.add(p10);
        LibreriaCompleta.add(p11);
        LibreriaCompleta.add(p12);
      //--------------------------------- INCISO 1.A  ---------------------------------

        System.out.println("INCISO 1");
        System.out.println("Busqueda 1 - Duracion mayor a 400s:");
        CriterioMayor buscarA = new CriterioMayor("duracion", 400.0f);
        Vector<Pista> ResultadoA = new Vector<>();
        ResultadoA = buscarA.GetResultado(LibreriaCompleta);
        if (ResultadoA.isEmpty())
            System.out.println("No se encontraron resultados que satisfagan la busqueda");
        else
            for (Pista p : ResultadoA){
                System.out.println(p.getValorAtributo("titulo"));
            }
        
      //--------------------------------- INCISO 1.B  ---------------------------------
        
        System.out.println();
        System.out.println("Busqueda 2 - Genero Rock:");
        CriterioContiene buscarB = new CriterioContiene("genero", "rock");
        Vector<Pista> ResultadoB = new Vector<>();
        ResultadoB = buscarB.GetResultado(LibreriaCompleta);
        if (ResultadoB.isEmpty())
            System.out.println("No se encontraron resultados que satisfagan la busqueda");
        else
            for (Pista p : ResultadoB){
                System.out.println(p.getValorAtributo("titulo"));
            }
        
      //--------------------------------- INCISO 1.C  ---------------------------------
        
        System.out.println();
        System.out.println("Busqueda 3 - Titulo rock y no LMFAO:");
        Vector<Pista> ResultadoC = new Vector<>();
        CriterioContiene Aux1 = new CriterioContiene("titulo", "rock");
        CriterioNOT Aux2 = new CriterioNOT("interprete", "LMFAO");
        CriterioAND buscarC = new CriterioAND(Aux1, Aux2);
        ResultadoC = buscarC.GetResultado(LibreriaCompleta);
        if (ResultadoC.isEmpty())
            System.out.println("No se encontraron resultados que satisfagan la busqueda");
        else
            for (Pista p : ResultadoC){
                System.out.println(p.getValorAtributo("titulo"));
            }
        
      //--------------------------------- INCISO 1.D  ---------------------------------
        
        System.out.println();
        System.out.println("Busqueda 4 - Genero contenga rock y Fecha mayor 2006 o Genero contegna rock y Interprete Coldplay:");
        Vector<Pista> ResultadoD = new Vector<>();
        CriterioContiene Aux3 = new CriterioContiene("genero", "rock");
        CriterioMayor Aux4 = new CriterioMayor("anio", 2006.0f);
        CriterioIgual Aux5 = new CriterioIgual("interprete", "Coldplay");
        CriterioAND Aux6 = new CriterioAND(Aux3, Aux4);
        CriterioAND Aux7 = new CriterioAND(Aux3, Aux5);
        CriterioOR buscarD = new CriterioOR(Aux6, Aux7);
        ResultadoD = buscarD.GetResultado(LibreriaCompleta);
        if (ResultadoD.isEmpty())
            System.out.println("No se encontraron resultados que satisfagan la busqueda");
        else
            for (Pista p : ResultadoD){
                System.out.println(p.getValorAtributo("titulo"));
            }
        System.out.println();

        //--------------------------------- INCISO 2 ---------------------------------
                              
        Pista p13 = new Pista();
        p13.setAtributo("id",13);
        p13.setAtributo("titulo","Paradise");
        p13.setAtributo("duracion",365.0f);
        p13.setAtributo("interprete","Coldplay");
        p13.setAtributo("Titulo del Album","Mylo Xyloto");
        p13.setAtributo("anio",2011.0f);
        p13.setAtributo("genero","Rock alternativo");
        p13.setAtributo("comentario", "");
        
        LibreriaCompleta.add(p13);

        //--------------------------------- INCISO 2.A  ---------------------------------

        System.out.println("INCISO 2");
        System.out.println("Busqueda 1 - Duracion mayor a 400s:");
        ResultadoA = buscarA.GetResultado(LibreriaCompleta);
        if (ResultadoA.isEmpty())
            System.out.println("No se encontraron resultados que satisfagan la busqueda");
        else
            for (Pista p : ResultadoA){
                System.out.println(p.getValorAtributo("titulo"));
            }
        
      //--------------------------------- INCISO 2.B  ---------------------------------
        
        System.out.println();
        System.out.println("Busqueda 2 - Genero Rock:");
        ResultadoB = buscarB.GetResultado(LibreriaCompleta);
        if (ResultadoB.isEmpty())
            System.out.println("No se encontraron resultados que satisfagan la busqueda");
        else
            for (Pista p : ResultadoB){
                System.out.println(p.getValorAtributo("titulo"));
            }
        
      //--------------------------------- INCISO 2.C  ---------------------------------
        
        System.out.println();
        System.out.println("Busqueda 3 - Titulo rock y no LMFAO:");
        ResultadoC = buscarC.GetResultado(LibreriaCompleta);
        if (ResultadoC.isEmpty())
            System.out.println("No se encontraron resultados que satisfagan la busqueda");
        else
            for (Pista p : ResultadoC){
                System.out.println(p.getValorAtributo("titulo"));
            }
        
      //--------------------------------- INCISO 2.D  ---------------------------------
        
        System.out.println();
        System.out.println("Busqueda 4 - Genero contenga rock y Fecha mayor 2006 o Genero contegna rock y Interprete Coldplay:");
        ResultadoD = buscarD.GetResultado(LibreriaCompleta);
        if (ResultadoD.isEmpty())
            System.out.println("No se encontraron resultados que satisfagan la busqueda");
        else
            for (Pista p : ResultadoD){
                System.out.println(p.getValorAtributo("titulo"));
            }
        System.out.println(); 
    }   
}
