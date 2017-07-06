package classi;

import java.util.Calendar;

/**
 * Classe che implementa lo sconto del 30% sul costo intero se lo spettacolo inizia tra le 12:00 e le 15:00
 * @author domian94
 *
 */
public class ScontoOrario implements Sconto {
	
	private Calendar hI;
	private Calendar max;
	private Calendar min;
	private boolean stato;
	/**
	 * Costruisce un oggetto ScontoOrario
	 * @param s booleana che indica se lo sconto è attivo o no
	 * @param c ora in cui si prenota lo spettacolo
	 */
	public ScontoOrario(boolean s, Calendar c){
		max=max.getInstance();
		min=min.getInstance();
		hI=c;
		max.set(Calendar.DATE,hI.get(Calendar.DATE));
		max.set(Calendar.MONTH,hI.get(Calendar.MONTH));
		max.set(Calendar.YEAR,hI.get(Calendar.YEAR));
		max.set(Calendar.HOUR_OF_DAY,15);
		max.set(Calendar.MINUTE,0);
		max.set(Calendar.MILLISECOND,0);
		min.set(Calendar.DATE,hI.get(Calendar.DATE));
		min.set(Calendar.MONTH,hI.get(Calendar.MONTH)-1);
		min.set(Calendar.YEAR,hI.get(Calendar.YEAR));
		min.set(Calendar.HOUR_OF_DAY,12);
		min.set(Calendar.MINUTE,0);
		min.set(Calendar.MILLISECOND,0);
		stato=s;
	}
	
	/**
	 * Restituisce l'importo dello sconto da effettuare
	 * @param costoIntero è il costo dei biglietti senza sconto
	 * @return sconto da effettuare 
	 */
	public double getImportoSconto(double costoIntero) {
		if (((hI.compareTo(max)<=0)&&(hI.compareTo(min)>=0))&&(stato))return ((costoIntero/100)*30);
		return 0;
	}

}
