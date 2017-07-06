package eccezioni;
/**
 * Eccezione che viene lanciata nel caso in cui è stata inserita in modo errato una 
 * coppia data inizio-data fine
 * @author domian94
 *
 */
public class CoppiaDateErrateException extends Exception {
	public CoppiaDateErrateException (String s) {
		super (s);
	}
}
