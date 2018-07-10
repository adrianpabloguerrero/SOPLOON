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
public class CriterioArtista extends Criterio {
   private String art;

	/**
	 * Class constructor.
	 * 
     * @param aut
	 */
	public CriterioArtista(String aut) {
		this.art = aut;
	}

	/**
	 * Returns true if the track's artist is the expected.
     * @param e
     * @return 
	 */
	public boolean cumple(Pista e) {
		this.art = this.art.toLowerCase();
		String t = e.getArtista().toLowerCase();
		return t.indexOf(art) != -1;
	}
    
}
