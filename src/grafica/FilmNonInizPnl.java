package grafica;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import classi.FileFilm;
import classi.Film;
import classi.Modalita;
import classi.StatoPosto;
import classi.TipoSettimana;
/**
 * Pannello che contiene la lista degli spettacoli non iniziati e i pulsanti
 * per gestire i posti a sedere
 * @author domian94
 *
 */
public class FilmNonInizPnl extends JPanel {
	
	private JPanel pnlButtons;
	private ArrayList<Film> arr;
	private JPanel pnlFilms;
	private JFrame fr;
	private Modalita mod;
	/**
	 * Costruisce il pannello che contiene la lista degli spettacoli non ancora iniziati 
	 * e i pulsanti per gestire i posti
	 * @param f frame in cui deve essere messo il pannello
	 * @param m soggetto che utilizza il programma (Cliente o Gestore)
	 */
	public FilmNonInizPnl(JFrame f,Modalita m){
		mod=m;
		fr=f;
		setLayout(new BorderLayout());
		pnlButtons=new JPanel();
		pnlFilms=new JPanel();
		pnlButtons.setLayout(new GridLayout(1,3));
		FileFilm ff=new FileFilm("corrente.txt", TipoSettimana.CORRENTE);
		try {
			JTextArea txt;
			arr=ff.filmNonIniziati();
			int l=arr.size();
			pnlFilms.setLayout(new GridLayout(l,3,10,10));
			pnlButtons.add(btnOrdCron());//BOTTONE ORDINE CRONOLOGICO
			pnlButtons.add(btnOrdSala());//BOTTONE ORDINE PER SALA
			pnlButtons.add(btnOrdTitolo());//BOTTONE ORDINE ALFABETICO
			JScrollPane scr=new JScrollPane(pnlFilms);
			if (mod==Modalita.CLIENTE)
				for(int i=0;i<l;i++){
					txt=new JTextArea(arr.get(i).toString());
					txt.setEditable(false);
					pnlFilms.add(new JScrollPane(txt));
					pnlFilms.add(pnlAcquisti(i));
					pnlFilms.add(new JLabel(""));//PULSANTE PRENOTA E ACQUISTA
				}
			else
				for(int i=0;i<l;i++){
					txt=new JTextArea(arr.get(i).toString());
					txt.setEditable(false);
					pnlFilms.add(new JScrollPane(txt));
					pnlFilms.add(pnlGestore(i));
					pnlFilms.add(new JLabel(""));//PULSANTE PRENOTA E ACQUISTA
				}
			add(pnlButtons,BorderLayout.NORTH);
			add(scr,BorderLayout.CENTER);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * Crea il pulsante alla cui pressione gli spettacoli vengono ordinati in ordine 
	 * cronologico
	 * @return pulsante "Ordine cronologico"
	 */
	private JButton btnOrdCron(){
		JButton btn=new JButton("Ordine cronologico");
		/**
		 * Si occupa di ordinare cronologicamente gli spettacoli e di ridisegnare
		 * il pannello con essi ordinati nel modo giusto.
		 */
		class OrdCronListener implements ActionListener {

			public void actionPerformed(ActionEvent arg0)  {
				JTextArea txt;
				arr.sort(new Film.OraComparator());
				removeAll();
				pnlFilms.removeAll();
				pnlButtons.removeAll();
				int l=arr.size();
				setLayout(new BorderLayout());
				pnlFilms.setLayout(new GridLayout(l,3,10,10));
				pnlButtons.add(btnOrdCron());//BOTTONE ORDINE CRONOLOGICO
				pnlButtons.add(btnOrdSala());//BOTTONE ORDINE PER SALA
				pnlButtons.add(btnOrdTitolo());//BOTTONE ORDINE ALFABETICO
				JScrollPane scr=new JScrollPane(pnlFilms);
				if (mod==Modalita.CLIENTE)
					for(int i=0;i<l;i++){
						txt=new JTextArea(arr.get(i).toString());
						txt.setEditable(false);
						pnlFilms.add(new JScrollPane(txt));
						pnlFilms.add(pnlAcquisti(i));
						pnlFilms.add(new JLabel(""));//PULSANTE PRENOTA E ACQUISTA
					}
				else
					for(int i=0;i<l;i++){
						txt=new JTextArea(arr.get(i).toString());
						txt.setEditable(false);
						pnlFilms.add(new JScrollPane(txt));
						pnlFilms.add(pnlGestore(i));
						pnlFilms.add(new JLabel(""));//PULSANTE PRENOTA E ACQUISTA
					}
				pnlFilms.revalidate();
				pnlButtons.revalidate();
				add(pnlButtons,BorderLayout.NORTH);
				add(scr,BorderLayout.CENTER);
				revalidate();
			}
		}
		
		ActionListener listener = new OrdCronListener();
		btn.addActionListener(listener);
		return btn;
	}
	/**
	 * Crea il pulsante alla cui pressione gli spettacoli vengono ordinati per sala
	 * @return pulsante "Ordine per sala"
	 */
	private JButton btnOrdSala(){
		JButton btn=new JButton("Ordine per sala");
		/**
		 * Si occupa di ordinare per sala gli spettacoli e di ridisegnare
		 * il pannello con essi ordinati nel modo giusto.
		 */
		class OrdCronListener implements ActionListener {

			public void actionPerformed(ActionEvent arg0)  {
				JTextArea txt;
				arr.sort(new Film.SalaComparator());
				removeAll();
				pnlFilms.removeAll();
				pnlButtons.removeAll();
				int l=arr.size();
				setLayout(new BorderLayout());
				pnlFilms.setLayout(new GridLayout(l,3,10,10));
				pnlButtons.add(btnOrdCron());//BOTTONE ORDINE CRONOLOGICO
				pnlButtons.add(btnOrdSala());//BOTTONE ORDINE PER SALA
				pnlButtons.add(btnOrdTitolo());//BOTTONE ORDINE ALFABETICO
				JScrollPane scr=new JScrollPane(pnlFilms);
				if (mod==Modalita.CLIENTE)
					for(int i=0;i<l;i++){
						txt=new JTextArea(arr.get(i).toString());
						txt.setEditable(false);
						pnlFilms.add(new JScrollPane(txt));
						pnlFilms.add(pnlAcquisti(i));
						pnlFilms.add(new JLabel(""));//PULSANTE PRENOTA E ACQUISTA
					}
				else
					for(int i=0;i<l;i++){
						txt=new JTextArea(arr.get(i).toString());
						txt.setEditable(false);
						pnlFilms.add(new JScrollPane(txt));
						pnlFilms.add(pnlGestore(i));
						pnlFilms.add(new JLabel(""));//PULSANTE PRENOTA E ACQUISTA
					}
				pnlFilms.revalidate();
				pnlButtons.revalidate();
				add(pnlButtons,BorderLayout.NORTH);
				add(scr,BorderLayout.CENTER);
				revalidate();
			}
		}
		
		ActionListener listener = new OrdCronListener();
		btn.addActionListener(listener);
		return btn;
	}
	/**
	 * Crea il pulsante alla cui pressione gli spettacoli vengono ordinati per titolo
	 * @return pulsante "Ordine per titolo"
	 */
	private JButton btnOrdTitolo(){
		JButton btn=new JButton("Ordine per titolo");
		/**
		 * Si occupa di ordinare per titolo gli spettacoli e di ridisegnare
		 * il pannello con essi ordinati nel modo giusto.
		 */
		class OrdCronListener implements ActionListener {

			public void actionPerformed(ActionEvent arg0)  {
				JTextArea txt;
				Collections.sort(arr);
				removeAll();
				pnlFilms.removeAll();
				pnlButtons.removeAll();
				int l=arr.size();
				setLayout(new BorderLayout());
				pnlFilms.setLayout(new GridLayout(l,3,10,10));
				pnlButtons.add(btnOrdCron());//BOTTONE ORDINE CRONOLOGICO
				pnlButtons.add(btnOrdSala());//BOTTONE ORDINE PER SALA
				pnlButtons.add(btnOrdTitolo());//BOTTONE ORDINE ALFABETICO
				JScrollPane scr=new JScrollPane(pnlFilms);
				if (mod==Modalita.CLIENTE)
					for(int i=0;i<l;i++){
						txt=new JTextArea(arr.get(i).toString());
						txt.setEditable(false);
						pnlFilms.add(new JScrollPane(txt));
						pnlFilms.add(pnlAcquisti(i));
						pnlFilms.add(new JLabel(""));//PULSANTE PRENOTA E ACQUISTA
					}
				else
					for(int i=0;i<l;i++){
						txt=new JTextArea(arr.get(i).toString());
						txt.setEditable(false);
						pnlFilms.add(new JScrollPane(txt));
						pnlFilms.add(pnlGestore(i));
						pnlFilms.add(new JLabel(""));//PULSANTE PRENOTA E ACQUISTA
					}
				pnlFilms.revalidate();
				pnlButtons.revalidate();
				add(pnlButtons,BorderLayout.NORTH);
				add(scr,BorderLayout.CENTER);
				revalidate();
			}
		}
		
		ActionListener listener = new OrdCronListener();
		btn.addActionListener(listener);
		return btn;
	}
	/**
	 * Crea un pannello contenente i pulsanti per la gestione dei posti a 
	 * sedere di uno spettacolo da parte di un cliente
	 * @param i spettacolo all'i-esimo posto nella collezione di spettacoli memorizzata
	 * 			nel file
	 * @return pannello coi pulsanti di acquisto e prenotazione posti
	 */
	private JPanel pnlAcquisti(int i){
		JPanel pnl=new JPanel();
		pnl.add(btnAcquista(i));
		pnl.add(btnPrenota(i));
		return pnl;
	}
	/**
	 * Crea un pulsante per l'acquisto di posti a sedere
	 * @param i spettacolo all'i-esimo posto nella collezione di spettacoli memorizzata
	 * 			nel file
	 * @return pulsante "Acquista direttamente"
	 */
	private JButton btnAcquista(int i){
		JButton btn=new JButton("Acquista direttamente");
		int indice=i;
		/**
		 * Apre il frame per l'acquisto di posti a sedere
		 */
		class AcquistaListener implements ActionListener {

			public void actionPerformed(ActionEvent arg0)  {
				Film f=arr.get(indice);
				JFrame frame= new ScegliSpettacoloFrame(f.getGInizio(), f.getGFine(), f.getHInizio(), f.getSala(), Modalita.CLIENTE, f.getTitolo(),StatoPosto.DISPONIBILE);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				fr.dispose();	
			}
		}
		
		ActionListener listener = new AcquistaListener();
		btn.addActionListener(listener);
		return btn;
	}
	/**
	 * Crea un pulsante per la prenotazione di posti a sedere
	 * @param i spettacolo all'i-esimo posto nella collezione di spettacoli memorizzata
	 * 			nel file
	 * @return pulsante "Acquista tramite prenotazione"
	 */
	private JButton btnPrenota(int i){
		JButton btn=new JButton("Acquista tramite prenotazione");
		int indice=i;
		/**
		 * Apre il frame per la prenotazione o l'acquisto di una prenotazione 
		 * di posti a sedere
		 */
		class AcquistaListener implements ActionListener {

			public void actionPerformed(ActionEvent arg0)  {
				Film f=arr.get(indice);
				JFrame frame= new ScegliSpettacoloFrame(f.getGInizio(), f.getGFine(), f.getHInizio(), f.getSala(), Modalita.CLIENTE, f.getTitolo(),StatoPosto.PRENOTATO);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				fr.dispose();	
			}
		}
		
		ActionListener listener = new AcquistaListener();
		btn.addActionListener(listener);
		return btn;
	}
	/**
	 * Crea un pannello contenente i pulsanti per la gestione dei posti a 
	 * sedere di uno spettacolo da parte di un gestore
	 * @param i spettacolo all'i-esimo posto nella collezione di spettacoli memorizzata
	 * 			nel file
	 * @return pannello coi pulsanti per cambiare prezzo ai biglietti o per rendere 
	 * 			dei posti non disponibili
	 */
	private JPanel pnlGestore(int i){
		JPanel pnl=new JPanel();
		pnl.add(btnCambiaPrezzo(i));
		pnl.add(btnRendiDisp(i));
		return pnl;
	}
	/**
	 * Crea un pulsante per cambiare il prezzo di un biglietto di un film
	 * @param i spettacolo all'i-esimo posto nella collezione di spettacoli memorizzata
	 * 			nel file
	 * @return pulsante "Modifica prezzo"
	 */
	private JButton btnCambiaPrezzo(int i){
		JButton btn=new JButton("Modifica prezzo");
		int indice=i;
		class CambiaPrezzoListener implements ActionListener {
			/**
			 * Apre il frame per modificare il prezzo dei biglietti per il film selezionato
			 */
			public void actionPerformed(ActionEvent arg0)  {
				Film f=arr.get(indice);
				double newPrezzo;
				try{
					newPrezzo = Double.parseDouble(JOptionPane.showInputDialog ("Inserisci nuovo prezzo:", f.getPrezzo()));
					f.setPrezzo(newPrezzo);
					arr.set(indice,f);
					ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("corrente.txt"));
					oos.writeObject(arr);
					oos.close();
				}
				catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Inserire un numero!", "Errore", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		ActionListener listener = new CambiaPrezzoListener();
		btn.addActionListener(listener);
		return btn;
	}
	/**
	 * Crea un pulsante per rendere disponibili o indisponibili i posti di uno spettacolo
	 * @param i spettacolo all'i-esimo posto nella collezione di spettacoli memorizzata
	 * 			nel file
	 * @return pulsante "Rendi disponibili/indisponibili"
	 */
	private JButton btnRendiDisp(int i){
		JButton btn=new JButton("Rendi disponibili/indisponibili");
		int indice=i;
		class RendiDispListener implements ActionListener {

			public void actionPerformed(ActionEvent arg0)  {
				Film f=arr.get(indice);
				JFrame frame= new ScegliSpettacoloFrame(f.getGInizio(), f.getGFine(), f.getHInizio(), f.getSala(), Modalita.GESTORE, f.getTitolo(),StatoPosto.INDISPONIBILE);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				fr.dispose();	
			}
		}
		
		ActionListener listener = new RendiDispListener();
		btn.addActionListener(listener);
		return btn;
	}
}
