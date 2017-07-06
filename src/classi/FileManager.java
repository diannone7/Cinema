package classi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 * Classe contenente i metodi più generali per la gestione dei dati su file
 * @author domian94
 *
 * @param <T> tipo di oggetti contenuti nel file che si andrà a gestire
 */
public class FileManager<T> {
	
	private ObjectInputStream ois;
	private String s;
	private ArrayList<T> flm;
	private ObjectOutputStream oos;
	private FileInputStream is;
	protected File f;
	/**
	 * Costruisce l'oggetto FileManager
	 * @param s stringa che contiene il nome del file
	 */
	public FileManager (String s){
		flm=new ArrayList<T>();
		this.s=s;
		ois=null;
		oos=null;
		f=new File(s);
	}
	
	/**
	 * Apre il file s in lettura
	 */
	private void openForRead() throws IOException {
		if (ois!=null) ois.close();
		is=new FileInputStream(s);		
		ois=new ObjectInputStream(is);
	}
	
	/**
	 * Apre il file s in lettura
	 */
	private void openForWrite() throws IOException {
		if (ois!=null) ois.close();
		oos=new ObjectOutputStream(new FileOutputStream(s));
	}
	
	/**
	 * Ritorna la taglia della collezione di oggetti T presenti nel file
	 * @return numero di oggetti T presenti nella collezione nel file s
	 */
	public int size() throws IOException, ClassNotFoundException {
		return getList().size();
	}
	
	/**
	 * Chiude il file aperto in lettura
	 */
	private void closeForRead() throws IOException {
		if (ois!=null) ois.close();
		ois=null;
	}
	
	/**
	 * Chiude il file aperto in scrittura
	 */
	public void closeForWrite() throws IOException {
		if (oos!=null) oos.close();
		oos=null;
	}
	
	/**
	 * Ritorna la collezione di oggetti T presenti nel file s
	 * @return flm è la collezione di oggetti T presente nel file s
	 */
	public ArrayList<T> getList() throws IOException, ClassNotFoundException {
		if (f.exists()){
		openForRead();
		flm=(ArrayList<T>)ois.readObject();
		closeForRead();
		}
		return flm;
	}
	
	/**
	 * Scrive un record nel file s
	 * @param record oggetto T da scrivere nel file
	 */
	public void writeRecord(T record) throws Exception {
		ArrayList<T>app=new ArrayList<T>();
		app=getList();					
		app.add(record);
		openForWrite();
		oos.writeObject(app);
		closeForWrite();
	}
	
	/**
	 * Modifica un record
	 * @param i indice del record da modificare
	 * @param record nuovo oggetto da inserire al posto del vecchio
	 */
	public void modificaRecord (int i,T record) throws ClassNotFoundException, IOException{
		ArrayList<T>app=new ArrayList<T>();
		app=getList();					
		app.set(i,record);
		openForWrite();
		oos.writeObject(app);
		closeForWrite();
	}
	
	/**
	 * Scrive una collezione di oggetti T nel file s
	 * @param aL collezione di oggetti T da scrivere in s
	 */
	public void writeList(ArrayList<T> aL) throws IOException{
		openForWrite();
		oos.writeObject(aL);
		closeForWrite();
	}

}
