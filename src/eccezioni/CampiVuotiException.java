package eccezioni;
/**
 * Eccezione che viene lanciata nel caso in cui non sono stati compilati
 * alcuni campi obbligatori
 * @author domian94
 *
 */
public class CampiVuotiException extends Exception {
	public CampiVuotiException(String s){
		super(s);
	}
}
