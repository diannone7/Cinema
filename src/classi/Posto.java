package classi;

import java.io.Serializable;
/**
 * Class che rappresenta le informazioni relative ad un posto
 * @author domian94
 *
 */
public class Posto implements Serializable{
	
	private int numero;
	private StatoPosto stato;
	
	/**
	 * Restituisce lo stato del posto
	 * @return stato del posto
	 */
	public StatoPosto getStato() {
		return stato;
	}
	
	/**
	 * Modifica lo stato del posto
	 * @param stato nuovo stato del posto
	 */
	public void setStato(StatoPosto stato) {
		this.stato = stato;
	}
	/**
	 * Costruisce un oggetto Posto
	 * @param numero
	 * @param stato
	 */
	public Posto(int numero, StatoPosto stato) {
		this.numero = numero;
		this.stato = stato;
	}

	/**
	 * Restituisce il numero del posto
	 * @return numero del posto
	 */	
	public int getNumero() {
		return numero;
	}
	
	/**
	* Modifica il numero del posto
	 * @param numero nuovo numero del posto
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

}
