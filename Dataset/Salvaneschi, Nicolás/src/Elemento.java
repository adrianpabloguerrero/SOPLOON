import java.util.*;
 
 
public abstract class Elemento {


public abstract int getCantidad();



public abstract int getDuracion();


public abstract boolean Existe(Elemento x);

public abstract String getNombre();

public abstract void imprimir();

public abstract Vector<Elemento> buscarCondicion(Condicion x);

}