package classi;
/**
 * Classe che implementa lo sconto del 20% sul costo intero se sono stati prenotati 10 o 
 * più biglietti
 * @author domian94
 *
 */
public class ScontoPerDieci implements Sconto {
	
	private int nPers;
	private boolean stato;
	
	/**
	 * Costruisce un oggetto ScontoPerDieci
	 * @param n numero di biglietti acquistati
	 * @param s booleana che indica se lo sconto è attivo o no
	 */
	public ScontoPerDieci(int n,boolean s){
		nPers=n;
		stato=s;
	}
	
	/**
	 * Restituisce l'importo dello sconto da effettuare
	 * @param costoIntero è il costo dei biglietti senza sconto
	 * @return sconto da effettuare
	 */
	public double getImportoSconto(double costoIntero) {
		if ((nPers>=10)&&(stato)) return ((costoIntero/100)*20);
		return 0;
	}
}
