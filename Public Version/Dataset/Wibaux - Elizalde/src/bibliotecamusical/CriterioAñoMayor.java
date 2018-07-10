/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecamusical;

/**
 *
 * @author Yerman
 */
public class CriterioAñoMayor extends Criterio {
    private int añoRef;

	/**
	 * Class constructor.
	 * 
	 * @param anio:int
	 */
	public CriterioAñoMayor(int anio) {
		this.añoRef = anio;
	}

	/**
	 * Returns true if the track's year is greater than the determined year.
     * @param e
     * @return 
	 */
	public boolean cumple(Pista e) {
		return e.getAño() > añoRef;
	}
}
