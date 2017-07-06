package classi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Classe che contiene i metodi per gestire un file di sale
 * @author domian94
 *
 */
public class FileSala extends FileManager<Sala> {
	
	/**
	 * Costruisce un oggetto FileSala
	 * @param s nome del file
	 */
	public FileSala(String s) {
		super(s);
	}
	
	/**
	 * Cerca tutte le occorrenze di oggetti sala nel file s
	 * @param ns numero della sala
	 * @return collezione di oggetti Sala presenti nel file s
	 */
	public ArrayList<Sala> cercaSala(int ns) throws ClassNotFoundException, IOException{
		ArrayList<Sala> a=super.getList();
		ArrayList<Sala> r=new ArrayList<Sala>();
		int l=a.size();
		Sala s;
		for (int i=0;i<l;i++){
			s=a.get(i);
			if (s.getNSala()==ns)r.add(s);
		}
		return r;
	}
	
	/**
	 * Cerca uno spettacolo in particolare
	 * @param ns numero della sala in cui si cerca lo spttacolo
	 * @param cal data e ora di inizio dello spettacolo  
	 * @return indice della collezione di sale a cui si riferisce lo spettacolo da cercare
	 */
	public int cercaSpettacolo(int ns,Calendar cal) throws ClassNotFoundException, IOException{
		ArrayList<Sala> a=super.getList();
		Calendar inizio=Calendar.getInstance();
		int l=a.size();
		Sala app;
		for (int i=0;i<l;i++){
			app=a.get(i);
			if ((app.getInizioFilm().equals(cal))&&(app.getNSala()==ns))return i;
		}
		return -1;
	}
	
	/**
	 * Rende liberi i posti prenotati e non acquistati dopo 12 ore
	 */
	public void setPrenotazioni() throws IOException, ClassNotFoundException{
		ArrayList<Sala> app=new ArrayList<Sala>();
		app=getList();
		int l=app.size();
		Sala s;
		Posto p;
		Calendar oraAttuale=Calendar.getInstance();
		Calendar oraScadenza;
		oraAttuale.add(Calendar.HOUR_OF_DAY, 12);
		for (int i=0;i<l;i++){
			s=app.get(i);
			oraScadenza=s.getInizioFilm();
			if ((oraAttuale.compareTo(oraScadenza)>=0)){
				for (int j=0;j<25;j++){
					p=s.getPosto(j);
					if(p.getStato()==StatoPosto.PRENOTATO){
						p.setStato(StatoPosto.DISPONIBILE);
						s.setPostiDisp(1);
						s.setPosto(p, j);
					}
				}
			}
			app.set(i, s);
		}
		writeList(app);
	}

}
