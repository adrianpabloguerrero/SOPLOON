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
public class CriterioTituloAlbum extends Criterio {
    private String alb;

	/**
	 * Class constructor.
	 * 
     * @param g
	 */
	public CriterioTituloAlbum(String a) {
		this.alb = a;
	}

	/**
	 * Returns true if the track's artist is the expected.
     * @param e
     * @return 
	 */
	public boolean cumple(Pista e) {
		this.alb = this.alb.toLowerCase();
		String t = e.getTitAlbum().toLowerCase();
		return t.indexOf(alb) != -1;
	}
}
