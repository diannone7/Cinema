package eccezioni;
/**
 * Eccezione che viene lanciata nel caso in cui un film non pu� essere inserito
 * @author domian94
 *
 */
public class FilmNonInseribileException extends Exception {
	public FilmNonInseribileException (String s){
		super (s);
	}
}
