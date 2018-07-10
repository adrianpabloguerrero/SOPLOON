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
public class CriterioDuracion extends Criterio {
    private int dur;

	/**
	 * Class constructor.
	 * 
     * @param leng
	 */
	public CriterioDuracion(int leng) {
		this.dur = leng;
	}

	/**
	 * Returns true if the track's duration is greater than expected.
     * @param e
     * @return 
	 */
	public boolean cumple(Pista e) {
		return (e.getDuracion() > dur);			
	}
}
