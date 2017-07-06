package classi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import eccezioni.CoppiaDateErrateException;
import eccezioni.FilmNonInseribileException;

/**
 * Classe che contiene i metodi per gestire un file di film
 * @author domian94
 *
 */
public class FileFilm extends FileManager<Film> {
	
	private Calendar dataCanc;
	private TipoSettimana mod;
	/**
	 * Costruisce un oggetto FileFilm
	 * @param s nome del file
	 * @param m specifica se il file è quello della programmazione dei film di settimana corrente o prossima
	 */
	public FileFilm(String s,TipoSettimana m) {
		super(s);
		dataCanc= Calendar.getInstance();
		mod=m; 
	}
	
	/**
	 * Ricerca di un film dato il titolo
	 * @param titolo titolo del film da cercare
	 * @return indice del film nella collezione di film presente nel file
	 */
	public int cercaTitolo (String titolo)throws IOException, ClassNotFoundException{
		ArrayList<Film>app=super.getList();
		for (int i=0;i<app.size();i++) if (app.get(i).getTitolo().equals(titolo)) return i;
		return -1;
	}
	
	/**
	 * Ricerca i film che si proittano in una certa sala
	 * @param sala numero della sala che si intende ricercare
	 * @return una collezione dei film in programma nella sala cercata
	 */
	public ArrayList<Film> cercaPerSala (int sala)throws IOException, ClassNotFoundException{
		ArrayList<Film>app=super.getList();
		ArrayList<Film>r=new ArrayList<Film>();
		Film flm;
		for (int i=0;i<app.size();i++){
			flm=app.get(i);
			if (flm.getSala()==sala) r.add(flm);
		}
		return r;
	}
	
	/**
	 * Cerca i film non ancora iniziati
	 * @return collezione di film non ancora iniziati
	 */
	public ArrayList<Film> filmNonIniziati() throws ClassNotFoundException, IOException{
		ArrayList<Film> f=new ArrayList<Film>();
		ArrayList<Film> app=super.getList();
		Calendar dataOggi=Calendar.getInstance();
		Calendar oraOggi=Calendar.getInstance();
		oraOggi.set(0,0,0,dataOggi.get(Calendar.HOUR_OF_DAY),dataOggi.get(Calendar.MINUTE),dataOggi.get(Calendar.SECOND));
		dataOggi.set(dataOggi.get(Calendar.YEAR),dataOggi.get(Calendar.MONTH),dataOggi.get(Calendar.DATE),0,0,0);
		dataOggi.set(Calendar.MILLISECOND, 0);
		Film flm;
		for (int i=0;i<app.size();i++){
			flm=app.get(i);
			if (flm.getGFine().compareTo(dataOggi)>0) f.add(flm);
			if ((flm.getGFine().equals(dataOggi))&&(flm.getHInizio().compareTo(oraOggi)>0)) f.add(flm);
		}
		return f;
	}
	
	/**
	 * Controllo per l'inserimento di un film
	 * @param record film nuovo che si vuole inserire per cui si controlla se l'inserimento è possibile
	 * @return vero se il film si può inserire, falso altrimenti
	 */
	private boolean controllo(Film record) throws ClassNotFoundException, IOException{
		ArrayList<Film> list=cercaPerSala(record.getSala());
		Film flm;
		boolean inserisci=true;
		int s=list.size();
		for (int i=0;i<s;i++){
			flm=list.get(i);
			if ((record.getGFine().compareTo(flm.getGInizio())<0)||(record.getGInizio().compareTo(flm.getGFine())>0)){
				inserisci=true;
			}
			else if (((record.getGInizio().compareTo(flm.getGInizio())>=0)&&(record.getGInizio().compareTo(flm.getGFine())<=0))||
					((record.getGInizio().compareTo(flm.getGInizio())<=0)&&(record.getGFine().compareTo(flm.getGInizio())>=0))){
						if ((record.getHFine().compareTo(flm.getHInizio())<0)||(record.getHInizio().compareTo(flm.getHFine())>0)){ 
							inserisci=true;
						}
						else {return false;}
			}
				else {
					return false;
				}
		}
		return inserisci;
	}
	
	/**
	 * Scrive un record nel file
	 * @param record è l'oggetto film che si vuole inserire
	 * @throws FilmNonInseribileException se il film record non può essere inserito
	 * @throws CoppiaDateErrateException se le date inserite sono sbagliate
	 */
	public void writeRecord(Film record) throws IOException, ClassNotFoundException,FilmNonInseribileException,CoppiaDateErrateException{
		if((record.getGInizio().compareTo(record.getGFine())>0)||(record.getHInizio().compareTo(record.getHFine())>0))
			throw new CoppiaDateErrateException("Date scritte male, riprovare.");
		boolean inserisci=true;
		if (f.exists()){
			inserisci=controllo(record);
		}
		else{
			ObjectOutputStream oos;
			dataCanc.set(Calendar.HOUR_OF_DAY, 23);
			dataCanc.set(Calendar.MINUTE, 59);
			dataCanc.set(Calendar.SECOND, 59);
			dataCanc.set(Calendar.MILLISECOND, 999);
			int c=dataCanc.get(Calendar.DAY_OF_WEEK);
			if (mod==TipoSettimana.CORRENTE){
				dataCanc.add(Calendar.DATE, 7-c);
				oos=new ObjectOutputStream(new FileOutputStream("dataCorrente.txt"));
				oos.writeObject(dataCanc);
				oos.close();
			}
			else {
				dataCanc.add(Calendar.DATE, 14-c);
				oos=new ObjectOutputStream(new FileOutputStream("dataProssima.txt"));
				oos.writeObject(dataCanc);
				oos.close();
			}
			
		}
		if(inserisci){
			try {
				super.writeRecord(record);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else throw new FilmNonInseribileException("Si accavallano film.");
	}
	
	/**
	 * Restituisce la data in cui deve essere cancellato il file 
	 * @return la data in cui il file del programma della settimana corrente deve essere cancellato
	 * @throws ClassNotFoundException
	 */
	public Calendar getDataCanc() throws IOException, ClassNotFoundException{
		Calendar c=Calendar.getInstance();
		FileInputStream is = null;
		ObjectInputStream ois;
		is=new FileInputStream("dataCorrente.txt");
		ois=new ObjectInputStream(is);
		c=(Calendar)ois.readObject();
		ois.close();
		return c;
	}

}

















