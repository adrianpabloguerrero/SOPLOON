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
public class CriterioNot extends Criterio {
    private Criterio c1;

	/**
	 * Class constructor.
	 * 
	 * @param c1:Criterio
	 */
	public CriterioNot(Criterio c1) {
		this.c1 = c1;
	}

	/**
	 * Returns the result of apply the negation of a criteria to a track.
     * @param e
     * @return 
	 */
	public boolean cumple(Pista e) {
		return !this.c1.cumple(e);
	}
}
