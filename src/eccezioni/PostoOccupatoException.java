package eccezioni;
/**
 * Eccezione che viene lanciata nel caso in cui viene selezionato un posto occupato
 * @author domian94
 *
 */
public class PostoOccupatoException extends Exception {
	public PostoOccupatoException (String s){
		super(s);
	}
}
