package grafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import classi.FileFilm;
import classi.Film;
import classi.Modalita;
import classi.TipoSettimana;
/**
 * Frame che rappresenta il pannello di controllo delle funzionalità che può sfruttare 
 * un gestore
 * @author domian94
 *
 */
public class HomeGestoreFrame extends JFrame {
	
	private JPanel pnl;
	private JButton btn;
	private JPanel pnl2;
	/**
	 * Costruisce un frame HomeGestoreFrame
	 */
	public HomeGestoreFrame(){
		pnl=new JPanel();
		pnl.setLayout(new BorderLayout());
		pnl2=new JPanel();
		pnl.add(pnl2,BorderLayout.CENTER);
		JMenuBar menuGestore= new JMenuBar();
		menuGestore.add(visualizzaMenu());
		menuGestore.add(gestioneMenu());
		menuGestore.add(esciItem());
		setTitle ("Home gestore");
		setSize(800,1000);
		setResizable(false);
		add(menuGestore,BorderLayout.NORTH);
		add(pnl,BorderLayout.CENTER);
		add(btnChiudi(),BorderLayout.SOUTH);
	}
	
	
	/**
	 * Crea il pulsante "Spettacoli" 
	 * @return pulsante "Spettacoli"
	 */
	public JMenuItem spettacoliItem (){
		JMenuItem sp = new JMenuItem("Spettacoli");
		JFrame questo=this;
		/**
		 *Apre il pannello contenente la lista degli spettacoli non iniziati
		 */
		class SpettListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				pnl.remove(pnl2);
				pnl2=new FilmNonInizPnl(questo,Modalita.GESTORE);
				pnl.add(pnl2,BorderLayout.CENTER);
				validate();
			}
		}
		
		ActionListener listener = new SpettListener();
		sp.addActionListener(listener);
		return sp;
	}
	
	/**
	 * Crea il pulsante che porta alla visualizzazione dei dettagli di ogni spettacolo
	 * @return pulsante "Dettagli spettacoli"
	 */
	public JMenuItem saleItem (){
		JMenuItem sp = new JMenuItem("Dettagli spettacoli");
		JFrame questo=this;
		/**
		 * Apre il pannello contenente la lista di tutti gli spettacoli
		 */
		class SpettListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				pnl.remove(pnl2);
				pnl2=new SalePnl();
				pnl.add(pnl2,BorderLayout.CENTER);
				validate();
			}
		}
		
		ActionListener listener = new SpettListener();
		sp.addActionListener(listener);
		return sp;
	}
	
	/**
	 * Crea il pulsante che abilita la visualizzazione degli incassi per film
	 * @return pulsante "Per film"
	 */
	public JMenuItem incFilmItem (){
		JMenuItem incFilm = new JMenuItem("Per film");
		/**
		 * Apre il pannello con tutti i film in programmazione e relativo incasso
		 */
		class IncFilmListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				pnl.remove(pnl2);
				pnl2=new ProgrammazionePnl(Modalita.GESTORE);
				pnl.add(pnl2,BorderLayout.CENTER);
				validate();
			}
		}
		
		ActionListener listener = new IncFilmListener();
		incFilm.addActionListener(listener);
		return incFilm;
	}
	
	/**
	 * Crea il pulsante che porta alla funzionalità di attivazione/disattivazione sconti
	 * @return pulsante "Sconti"
	 */
	public JMenuItem scontiItem (){
		JMenuItem sc = new JMenuItem("Sconti");
		JFrame f= this;
		/**
		 * Mostra il frame della gestione delle politiche di sconto al click del mouse
		 */
		class ScontiListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				JFrame frame= new ScontiFrame(f);
				frame.setVisible(true);
				enable(false);
			}
		}
		
		ActionListener listener = new ScontiListener();
		sc.addActionListener(listener);
		return sc;
	}
	
	/**
	 * Crea il pulsante per la visualizzazione dell'incasso totale
	 * @return pusante "Totale"
	 */
	public JMenuItem incTotItem (){
		JMenuItem incTot = new JMenuItem("Totale");
		/**
		 * Mostra l'incasso totale settimanale
		 */
		class IncTotListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				FileFilm ff=new FileFilm("corrente.txt",TipoSettimana.CORRENTE);
				try {
					ArrayList<Film>arr=ff.getList();
					int l=arr.size();
					double inc=0;
					for (int i=0;i<l;i++)inc+=arr.get(i).getIncasso();
					JOptionPane.showMessageDialog(null,"Incasso totale: "+inc, "",JOptionPane.INFORMATION_MESSAGE);
				} 
				catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		ActionListener listener = new IncTotListener();
		incTot.addActionListener(listener);
		return incTot;
	}
	/**
	 * Crea il pulsante per la gestione della programmazione settimanale
	 * @return pulsante "Programma settimanale"
	 */
	public JMenuItem progSettItem (){
		JMenuItem progSett = new JMenuItem("Programma settimanale");
		/**
		 * Crea il frame per la scelta tra inserimento di programma settimana 
		 * corrente o prossima
		 */
		class ProgSettListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				JFrame frame= new SceltaProgrammaFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				dispose();
			}
		}
		
		ActionListener listener = new ProgSettListener();
		progSett.addActionListener(listener);
		return progSett;
	}
	/**
	 * Crea il sottomenu "Incasso settimanale"
	 * @return sottomenu "Incasso settimanale"
	 */
	public JMenu incSettMenu (){
		JMenu incSett = new JMenu("Incasso settimanale");
		incSett.add(incFilmItem());
		incSett.add(incTotItem());
		return incSett;
	}
	
	/**
	 * Crea il sottomenu per le visualizzazioni
	 * @return sottomenu "Visualizza"
	 */
	public JMenu visualizzaMenu (){
		JMenu visualizza = new JMenu("Visualizza");
		visualizza.add(incSettMenu());
		visualizza.add(saleItem());
		return visualizza;
	}
	/**
	 * Crea la voce del menu per le funzionalità di gestione
	 * @return voce "Gestione" del menu 
	 */
	public JMenu gestioneMenu (){
		JMenu gestione = new JMenu("Gestione");
		gestione.add(progSettItem());
		gestione.add(spettacoliItem());
		gestione.add(scontiItem());
		return gestione;
	}
	/**
	 * Crea la voce del menu per chiudere il frame
	 * @return voce "Esci" del menu
	 */
	public JMenuItem esciItem (){
		JMenuItem esci = new JMenuItem("Esci");
		/**
		 * Chiude il frame HomeGestoreFrame e apre il framen MainFrame
		 */
		class EsciListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				JFrame frame= new MainFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				dispose();
			}
		}
		
		ActionListener listener = new EsciListener();
		esci.addActionListener(listener);
		return esci;
	}
	/*
	public JButton chiudiBtn (){
		JButton chiudi = new JButton("Chiudi");
		
		class ChiudiListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				remove(pnl);
				remove(btn);
				repaint();
			}
		}
		
		ActionListener listener = new ChiudiListener();
		chiudi.addActionListener(listener);
		return chiudi;
	}*/
	/**
	 * Inserisce il pannello relativo all'inserimento della programmazione settimanale 
	 * corrente o prossima
	 * @param m tipo di settimana (corrente o prossima)
	 */
	public void addInsPnl(TipoSettimana m){
		pnl.remove(pnl2);
		pnl2=new ProgrSettimPnl(m);
		pnl.add(pnl2,BorderLayout.CENTER);
		validate();
		/*
		switch (m){
		case CORRENTE:
			pnl.remove(pnl2);
			pnl2=new ProgrSettimPnl(m);
			pnl.add(pnl2,BorderLayout.CENTER);
			validate();
			break;
		case PROSSIMA:
			pnl.remove(pnl2);
			pnl2=new ProgrSettimPnl(2);
			pnl.add(pnl2,BorderLayout.CENTER);
			validate();
			break;
		}*/
	}
	/**
	 * Crea un pulsante per la pulizia della schermata centrale del frame
	 * @return pulsante "Chiudi schermata"
	 */
	private JButton btnChiudi(){
		JButton btn=new JButton("Chiudi schermata");
		/**
		 * Rimuove il pannello al centro del frame
		 */
		class ChiudiListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				pnl.remove(pnl2);
				pnl.repaint();
			}
		}
		ActionListener listener = new ChiudiListener();
		btn.addActionListener(listener);
		return btn;
	}
}
