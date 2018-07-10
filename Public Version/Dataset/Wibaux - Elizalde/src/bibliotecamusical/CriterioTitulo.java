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
public class CriterioTitulo extends Criterio {
    private String tit;

	/**
	 * Class constructor.
	 * 
     * @param t
	 */
	public CriterioTitulo(String t) {
		this.tit = t;
	}

	/**
	 * Returns true if the track's artist is the expected.
     * @param e
     * @return 
	 */
	public boolean cumple(Pista e) {
		this.tit = this.tit.toLowerCase();
		String t = e.getTitulo().toLowerCase();
		return t.indexOf(tit) != -1;
	}
}
