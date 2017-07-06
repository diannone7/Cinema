package grafica;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import classi.FileFilm;
import classi.Film;
import classi.Modalita;
import classi.TipoSettimana;

/**
 * Pannello in cui è visualizzata la programmazione settimanale del cinema
 * @author domian94
 *
 */
public class ProgrammazionePnl extends JPanel {
	
	private FileFilm ff;
	private JTextArea txtPr;
	private Modalita mod;
	/**
	 * Costruisce un pannello in cui è contenuta la programmazione settimanale complessiva
	 * @param m modalità di accesso al pannello (Cliente o Gestore)
	 */
	public ProgrammazionePnl(Modalita m){
		mod=m;
		ff=new FileFilm ("corrente.txt",TipoSettimana.CORRENTE);
		txtPr=new JTextArea();
		txtPr.setEditable(false);
		setLayout(new BorderLayout());
		txtPr.setText(leggi());
		JScrollPane scr=new JScrollPane(txtPr);
		add(scr,BorderLayout.CENTER);
	}
	
	/**
	 * Costruisce un pannello in cui è contenuta la programmazione settimanale per sala
	 * @param s numero della sala di cui si vuole visualizzare la programmazione
	 */
	public ProgrammazionePnl(int s){
		ff=new FileFilm ("corrente.txt",TipoSettimana.CORRENTE);
		txtPr=new JTextArea();
		txtPr.setEditable(false);
		setLayout(new BorderLayout());
		txtPr.setText(leggi(s));
		JScrollPane scr=new JScrollPane(txtPr);
		add(scr,BorderLayout.CENTER);
	}
	
	/**
	 * Restituisce una stringa contenente tutti gli spettacoli della programmazione settimanale
	 * @return stringa contenente tutti gli spettacoli della settimana
	 */
	private String leggi(){
		ArrayList<Film> arr=new ArrayList<Film>();
		try {
			arr=ff.getList();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		String s="";
		if (mod==Modalita.GESTORE) 
			for (int i=0;i<arr.size();i++) {
				Film f=arr.get(i);
				s+="Incasso= €"+f.getIncasso()+"\n"+f.toString();
			}
		else
			for (int i=0;i<arr.size();i++) {
				Film f=arr.get(i);
				s+=f.toString();
			}
		return s;
	}
	
	/**
	 * Restituisce una stringa contenente tutti gli spettacoli della programmazione settimanale di una certa sala 
	 * @param sa numero della sala di cui si vuole visualizzare la programmazione
	 * @return stringa contenente tutti gli spettacoli della settimana nella sala sa
	 */
	private String leggi(int sa){
		ArrayList<Film> arr=new ArrayList<Film>();
		try {
			arr=ff.cercaPerSala(sa);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		String s="";
		for (int i=0;i<arr.size();i++) s+=arr.get(i).toString();
		return s;
	}

}
