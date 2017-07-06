package classi;

import java.io.Serializable;
import java.util.Calendar;
/**
 * Classe che rappresenta tutte le informazioni relative ad una sala e al
 * corrispondente spettacolo in essa proiettato in un certo tempo
 * @author domian94
 *
 */
public class Sala implements Comparable<Sala>, Serializable {
	
	private Posto[] array;
	private String titolo;
	private Calendar inizioFilm;
	private int nSala;
	private int postiDisp;
	
	/**
	 * Costruisce un oggetto sala
	 * @param s numero della sala
	 * @param iF data e ora di inizio del film proiettato nella sala
	 * @param p array di posti prsenti in sala
	 * @param titolo titolo del film proiettato in sala
	 */
	public Sala (int s,Calendar iF, Posto[] p,String titolo){
		nSala=s;
		inizioFilm=iF;
		array=p;
		postiDisp=25;
		this.titolo=titolo;
	}
	
	/**
	 * Restituisce un oggetto Posto dato il suo numero
	 * @param i numero di posto
	 * @return oggetto Posto di numero i
	 */
	public Posto getPosto(int i) {
		return array[i];
	}
	
	/**
	 * Restituisce la data e l'ora di inizio del film nella sala
	 * @return data e ora di inizio del film nella sala
	 */
	public Calendar getInizioFilm() {
		return inizioFilm;
	}
	
	/**
	 * Restituisce il numero della sala
	 * @return numero della sala
	 */
	public int getNSala() {
		return nSala;
	}
	
	/**
	 * Restituisce il numero di posti disponibili per lo spettacolo
	 * @return numero di posti disponibili
	 */
	public int getPostiDisp() {
		return postiDisp;
	}
	
	/**
	 * Modifica il posto di numero i
	 * @param p nuovo posto da inserire al posto di un altro
	 * @param i numero del posto da modificare
	 */
	public void setPosto(Posto p,int i) {
		array[i]=p;
	}
	
	/**
	 * Modifica il numero di posti disponibili
	 * @param x posti da aggiungere o sottrarre ai posti disponibili per lo spettacolo
	 */
	public void setPostiDisp(int x){
		postiDisp=postiDisp+x;
	}
	
	/**
	 * Confronta due spettacoli per posti disponibili
	 * @param arg0 oggetto Sala da confrontare con questo
	 * @return =0 se il numero di posti disponibili è lo stesso, <0 se il numero
	 * 		   di posti disponibili in questa sala è minore di quello della sala arg0,
	 * 		   >0 se il numero di posti disponibili in questa sala è maggiore 
	 * 		   di quello della sala arg0
	 */
	public int compareTo(Sala arg0) {
		int result;
		result = postiDisp - arg0.getPostiDisp();
		return result;
	}
	
	/**
	 * Restituisce le informazioni relative ad uno spettacolo sottoforma di stringa
	 * @return stringa con le informazioni di uno spettacolo
	 */
	public String toString(){
		int x=getInizioFilm().get(Calendar.MINUTE);
		return "Posti disponibili: "+postiDisp+"\n"+"Numero sala: "+nSala+"\n"+"Inizio film: "+
				getInizioFilm().get(Calendar.DATE)+"/"+(getInizioFilm().get(Calendar.MONTH)+1)+"/"+getInizioFilm().get(Calendar.YEAR)+" "+
				getInizioFilm().get(Calendar.HOUR_OF_DAY)+":"+((x<=9)?("0"+x):(x))+"\n"
				+"Titolo: "+titolo+"\n\n";
	}
}
