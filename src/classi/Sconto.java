package classi;
/**
 * Interfaccia di politica di sconto
 * @author domian94
 *
 */
public interface Sconto {
	/**
	 * Restituisce il valore dello sconto
	 * @param costoIntero costo di tutti i biglietti snza sconto
	 * @return importo dello sconto
	 */
	public double getImportoSconto(double costoIntero);
}
