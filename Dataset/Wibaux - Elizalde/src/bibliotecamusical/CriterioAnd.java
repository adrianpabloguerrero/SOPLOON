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
public class CriterioAnd extends Criterio {
        private Criterio c1;
	private Criterio c2;
	
	/**
	 * Class constructor.
	 * @param c1:Criterio
	 * @param c2:Criterio
	 */
	public CriterioAnd(Criterio c1, Criterio c2) {
		this.c1 = c1;
		this.c2 = c2;
	}    
	
	/**
	 * Returns true if a 'Pista' is fit to both criteria.
     * @param e
     * @return 
	 */
	public boolean cumple(Pista e) {
		return this.c1.cumple(e) && this.c2.cumple(e);
	}
}
