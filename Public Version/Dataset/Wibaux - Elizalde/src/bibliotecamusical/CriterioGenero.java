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
public class CriterioGenero extends Criterio {
    private String gen;

	/**
	 * Class constructor.
	 * 
     * @param g
	 */
	public CriterioGenero(String g) {
		this.gen = g;
	}

	/**
	 * Returns true if the track's artist is the expected.
     * @param e
     * @return 
	 */
	public boolean cumple(Pista e) {
		this.gen = this.gen.toLowerCase();
		String t = e.getGenero().toLowerCase();
		return t.indexOf(gen) != -1;
	}
    
}
