package eccezioni;
/**
 * Eccezione che viene lanciata nel caso in cui viene selezionato un posto non disponibile
 * @author domian94
 *
 */
public class PostoNonDisponibileException extends Exception {
	public PostoNonDisponibileException (String s) {
		super (s);
	}
}
