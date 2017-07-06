package classi;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Classe rappresentante un film
 * @author domian94
 *
 */
public class Film implements Comparable <Film>,Serializable{
	
	private String titolo;
	private String descrizione;
	private double prezzo;
	private int sala;
	private Calendar gInizio;
	private Calendar gFine;
	private Calendar hInizio;
	private Calendar hFine;
	private double incasso;
	
	/**
	 * Restituisce il titolo del film
	 * @return titolo del film
	 */
	public String getTitolo() {
		return titolo;
	}
	
	/**
	 * Modifica il titolo di un film
	 * @param titolo nuovo titolo del film
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * Restituisce la descrizione del film
	 * @return descrizione del film
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Modifica la descrizione di un film
	 * @param descrizione nuova descrizione del film
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * Restituisce il prezzo di un biglietto del film
	 * @return prezzo del biglietto film
	 */
	public double getPrezzo() {
		return prezzo;
	}

	/**
	 * Modifica il prezzo di un biglietto del film
	 * @param prezzo nuovo prezzo del biglietto del film
	 */
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	/**
	 * Restituisce la sala del film
	 * @return sala del film
	 */	
	public int getSala() {
		return sala;
	}
	
	/**
	 * Modifica la sala di proiezione del film
	 * @param sala nuova sala di proiezione del film
	 */
	public void setSala(int sala) {
		this.sala = sala;
	}
	
	/**
	 * Costruisce un oggetto Film
	 * @param titolo titolo del film
	 * @param descrizione descrizione del film
	 * @param prezzo prezzo biglietto del film
	 * @param sala sala del film
	 * @param gInizio giorno di inizio delle proiezioni del film
	 * @param gFine giorno di fine delle proizioni del film
	 * @param hInizio ora di inizio di ogni proiezione del film
	 * @param hFine ora di fine di ogni proiezione del film
	 */
	public Film(String titolo, String descrizione, double prezzo,
		 int sala, Calendar gInizio,
			Calendar gFine, Calendar hInizio, Calendar hFine) {
		super();
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.sala = sala;
		this.gInizio = gInizio;
		this.gFine = gFine;
		this.hInizio = hInizio;
		this.hFine = hFine;
		this.incasso = 0;
	}

	/**
	 * Restituisce l'incasso del film
	 * @return incasso del film
	 */
	public double getIncasso() {
		return incasso;
	}
	
	/**
	 * Aggiunge un certo incasso a quello del film
	 * @param incasso incasso da aggiungere
	 */
	public void addIncasso(double incasso) {
		this.incasso += incasso;
	}

	/**
	 * Restituisce il giorno di inizio della proiezione del film
	 * @return giorno di inizio della proiezione del film
	 */
	public Calendar getGInizio() {
		return gInizio;
	}
	
	/**
	 * Modifica il giorno di inizio delle priezioni
	 * @param gInizio nuovo giorno di inizio delle proiezioni
	 */
	public void setGInizio(Calendar gInizio) {
		this.gInizio = gInizio;
	}

	/**
	 * Restituisce il giorno di fine delle proiezioni del film
	 * @return giorno di fine delle proiezioni del film
	 */
	public Calendar getGFine() {
		return gFine;
	}
	
	/**
	 * Modifica il giorno di fine delle priezioni
	 * @param gFine nuovo giorno di fine delle proiezioni
	 */
	public void setGFine(Calendar gFine) {
		this.gFine = gFine;
	}

	/**
	 * Restituisce l'ora in cui ogni giorno inizia il film
	 * @return ora in cui ogni giorno inizia il film
	 */
	public Calendar getHInizio() {
		return hInizio;
	}
	
	/**
	 * Modifica l'ora di inizio di ogni proiezione
	 * @param hInizio nuova ora di inizio di ogni proiezione
	 */
	public void setHInizio(Calendar hInizio) {
		this.hInizio = hInizio;
	}

	/**
	 * Restituisce l'ora in cui ogni giorno finisce il film
	 * @return ora in cui ogni giorno finisce il film
	 */
	public Calendar getHFine() {
		return hFine;
	}
	
	/**
	 * Modifica l'ora di fine di ogni proiezione
	 * @param hFine nuova ora di fine di ogni proiezione
	 */
	public void setHFine(Calendar hFine) {
		this.hFine = hFine;
	}
	
	/**
	 * Confronta due film per ordine alfabetico di titolo
	 * @param o film da confrontare con questo oggetto film
	 * @return 0 se uguali,<0 se il titolo di questo film è minore di quello del film o 
	 * 		   e >0 se il titolo di questo film è maggiore di quello del film o
	 */
	public int compareTo(Film o) {
		return titolo.compareTo(o.getTitolo());
	}
	
	/**
	 * Classe che definisce un metodo per confrontare due film per sala
	 * @author domian94
	 *
	 */
	public static class SalaComparator implements Comparator<Film> {
		/**
		 * Confronta due film per sala
		 * @param o1 primo film da confrontare per sala
		 * @param o2 secondo film da confrontare per sala
		 * @return differenza tra numeri di sala
		 */
        public int compare(Film o1, Film o2) {
            return o1.getSala() - o2.getSala();
        }
    }
	
	/**
	 * Classe che definisce un metodo per confrontare due film per data e ora d'inizio
	 * @author domian94
	 *
	 */
	public static class OraComparator implements Comparator<Film> {
		/**
		 * Confronta due film per sala
		 * @param o1 primo film da confrontare per data e ora d'inizio
		 * @param o2 secondo film da confrontare per data e ora d'inizio
		 * @return 0 se uguali,<0 se la data/ora del film o1 è minore di quella del film o2 
		 * 		   e >0 se la data/ora del film o1 è maggiore di quella del film o2 
		 */	
        public int compare(Film o1, Film o2) {
        	int a=o1.getGInizio().compareTo(o2.getGInizio());
        	if (a==0) a=o1.getHInizio().compareTo(o2.getHInizio());
            return a;
        }
	}
	/**
	 * Restituisce le informazioni di un film in formato stringa
	 * @return stringa contenente le informazioni dell'oggetto
	 */
	public String toString(){
		String s="\n";
		String minI;
		String minF;
		int hI=getHInizio().get(Calendar.MINUTE);
		int hF=getHFine().get(Calendar.MINUTE);
		if (hI<10) minI="0"+hI;
		else minI=""+hI;
		if (hF<10) minF="0"+hF;
		else minF=""+hF;
		return "Titolo: "+getTitolo()+s+"Descrizione: "+getDescrizione()+s+"Sala proiezione: "+getSala()+s+"Date spettacoli: "
				+getGInizio().get(Calendar.DATE)+"/"+(getGInizio().get(Calendar.MONTH)+1)+"/"+
				+getGInizio().get(Calendar.YEAR)+" - "+getGFine().get(Calendar.DATE)+"/"
				+(getGFine().get(Calendar.MONTH)+1)+"/"+getGFine().get(Calendar.YEAR)+s
				+"Orario Spettacoli: "+getHInizio().get(Calendar.HOUR_OF_DAY)+":"+minI
				+" - "+getHFine().get(Calendar.HOUR_OF_DAY)+":"+minF+s
				+"Costo biglietto: €"+getPrezzo()+s+s;
	}

}
