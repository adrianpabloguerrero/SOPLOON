package clases;

import java.util.Comparator;
import clases.Pista;


public class Comparar implements Comparator{
    
    private Comparar sig;
    private String atributo;
    private String tipo;
    
    public Comparar(Comparar sig, String s, String modo){
        this.sig = sig;
        atributo = s;
        tipo = modo;
    }
    @Override
    public int compare(Object uno, Object dos){
        Pista p1 = (Pista) uno;
        Pista p2 = (Pista) dos;
        String a1 = p1.getValorAtributo(atributo).toString().toLowerCase();
        String a2 = p2.getValorAtributo(atributo).toString().toLowerCase();
        int res = 0;
        if (tipo.equals("ascendente"))
            res = a1.compareTo(a2);
        else
            res = a2.compareTo(a1);
        if (res == 0 && sig != null)
            return sig.compare(uno,dos);
        else
            return res;
    }

}
