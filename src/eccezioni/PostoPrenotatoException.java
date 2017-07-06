package eccezioni;
/**
 * Eccezione che viene lanciata nel caso in cui viene selezionato un posto prenotato
 * @author domian94
 *
 */
public class PostoPrenotatoException extends Exception {
	public PostoPrenotatoException(String msg){
		super(msg);
	}
}
