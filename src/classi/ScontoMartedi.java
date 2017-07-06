package classi;

import java.util.Calendar;
/**
 * Classe che implementa lo sconto del 50% sul costo intero se è martedì
 * @author domian94
 *
 */
public class ScontoMartedi implements Sconto {
	
	private Calendar cal;
	private boolean stato;
	
	/**
	 * Costruisce un oggetto ScontoMartedi
	 * @param s booleana che indica se lo sconto è attivo o no
	 */
	public ScontoMartedi(boolean s){
		cal=cal.getInstance();
		stato=s;
	}
	
	/**
	 * Restituisce l'importo dello sconto da effettuare
	 * @param costoIntero è il costo dei biglietti senza sconto
	 * @return sconto da effettuare
	 */
	public double getImportoSconto(double costoIntero) {
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		if ((cal.get(Calendar.DAY_OF_WEEK)==3)&&(stato)) return ((costoIntero/100)*50);
		return 0;
	}

}
