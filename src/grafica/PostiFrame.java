package grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import classi.FileFilm;
import classi.FileSala;
import classi.Film;
import classi.Modalita;
import classi.Posto;
import classi.Sala;
import classi.ScontoMartedi;
import classi.ScontoOrario;
import classi.ScontoPerDieci;
import classi.StatoPosto;
import classi.TipoSettimana;
/**
 * Frame in cui si gestiscono i posti di una sala in un certo spettacolo
 * @author domian94
 *
 */
public class PostiFrame extends JFrame {
	
	private Sala s;
	private String titolo;
	private Calendar inizio;
	private PostoPnl[]posto;
	private FileSala fs;
	private int indiceSala;
	private ArrayList<Sala> arraySale;
	/**
	 * Crea un frame PostiFrame
	 * @param sala sala di proiezione dello spettacolo
	 * @param m modalità con cui si accede alla funzionalità (cliente o gestore)
	 * @param tit titolo del film
	 * @param in data e ora di inizio spettacolo
	 * @param sp segnala che il frame servirà a scegliere uno spettacolo per settare alcuni suoi posti allo stato sp
	 */
	public PostiFrame(int sala, Modalita m,String tit,Calendar in,StatoPosto sp){
		setTitle ("Scegli posti");
		setResizable(false);
		fs=new FileSala("saleCorrenti.txt");
		titolo=tit;
		inizio=in;
		setSize(700, 700);
		posto=new PostoPnl[25];
		setLayout(new GridLayout(6,5));
		try {
			fs.setPrenotazioni();
			arraySale=fs.getList();
			indiceSala=fs.cercaSpettacolo(sala, inizio);
			s=arraySale.get(indiceSala);
			if (m==Modalita.CLIENTE){				
				for (int i=0;i<25;i++) {
					posto[i]=new PostoPnl(s.getPosto(i), Modalita.CLIENTE,sp);
					add(posto[i]);
				}
				if (sp==StatoPosto.DISPONIBILE){
					add(new JLabel(""));
					add(new JLabel(""));
					add(btnConfScelte());
					add(new JLabel(""));
					add(new JLabel(""));
				}
				else{
					add(btnConfScelte());
					add(new JLabel(""));
					add(btnAcquistaPren());//BOTTONE ACQUISTA PRENTAZIONI
					add(new JLabel(""));
					add(btnCancPren());//BOTTONE CANCELLA PRENOTAZIONI
				}
			}
			else{
				for (int i=0;i<25;i++) {
					posto[i]=new PostoPnl(s.getPosto(i), Modalita.GESTORE,sp);
					add(posto[i]);
				}
				add(new JLabel(""));
				add(new JLabel(""));
				add(btnRendiIndisp());//BOTTONE RENDI INDISPONIBILE
				add(new JLabel(""));
				add(new JLabel(""));
			} 	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
		
		
		
	}
	
	/**
	 * Crea un pusante di conferma delle scelte effettuate (prenotazioni o acquisti)
	 * @return pulsante "Conferma"
	 */
	private JButton btnConfScelte(){
		JButton confScelte = new JButton("Conferma");
	
		class ConfScelteListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				double incasso = 0;
				int acquistati=0;
				int postiScelti=0;
				ObjectInputStream ois;
				boolean [] statoSconti=new boolean [3];
				try {
					ois = new ObjectInputStream(new FileInputStream("fileSconti.txt"));
					statoSconti=(boolean[])ois.readObject();
					ois.close();
					Posto p;
					for (int i=0;i<24;i++){
						if (((posto[i].getPosto().getStato()==StatoPosto.INDISPONIBILE)||(posto[i].getPosto().getStato()==StatoPosto.OCCUPATO)
						||(posto[i].getPosto().getStato()==StatoPosto.PRENOTATO))&&(posto[i].getInserisci())) {
							postiScelti+=1;
							s.setPosto(posto[i].getPosto(), i);
							if(s.getPosto(i).getStato()==StatoPosto.OCCUPATO)acquistati+=1;
						}													
					}
					s.setPostiDisp(-postiScelti);
					arraySale.set(indiceSala, s);
					fs.writeList(arraySale);
					ScontoPerDieci sd=new ScontoPerDieci(acquistati, statoSconti[0]);//INSERIRE NUMERO BIGLIETTI
					ScontoMartedi sm= new ScontoMartedi(statoSconti[1]);
					ScontoOrario so=new ScontoOrario(statoSconti[2], inizio);
					FileFilm ff=new FileFilm("corrente.txt",TipoSettimana.CORRENTE );						
					int j=ff.cercaTitolo(titolo);
					ArrayList<Film> arr=ff.getList();
					Film f=arr.get(j);
					double costoSingolo=f.getPrezzo();;
					incasso=acquistati*costoSingolo-(sd.getImportoSconto(acquistati*costoSingolo)+so.getImportoSconto(acquistati*costoSingolo)+
							sm.getImportoSconto(acquistati*costoSingolo));
					arr.get(j).addIncasso(incasso);
					ff.writeList(arr);
					System.out.println(s.getPostiDisp());
					JOptionPane.showMessageDialog(null,"Prezzo speso: "+incasso, "",JOptionPane.INFORMATION_MESSAGE);
					dispose();
					MainFrame frameChiusura=new MainFrame();
					frameChiusura.setVisible(true);
					frameChiusura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} 
				catch (IOException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
		}
		ActionListener listener = new ConfScelteListener();
		confScelte.addActionListener(listener);
		return confScelte;
	}
	
	/**
	 * Crea il pulsante che consente l'acquisto delle prenotazioni
	 * @return Pulsante "Acquista prenotazioni"
	 */
	private JButton btnAcquistaPren(){
		JButton acquistaPren = new JButton("Acquista prenotazioni");
	
		class AcquistaPrenListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				double incasso = 0;
				int acquistati=0;
				int pD=0;
				ObjectInputStream ois;
				boolean [] statoSconti=new boolean [3];
				try {
					ois = new ObjectInputStream(new FileInputStream("fileSconti.txt"));
					statoSconti=(boolean[])ois.readObject();
					ois.close();
					Posto p;
					for (int i=0;i<24;i++){
						if (((posto[i].getPosto().getStato()==StatoPosto.PRENOTATO))) {
							acquistati+=1;
							posto[i].getPosto().setStato(StatoPosto.OCCUPATO);
							s.setPosto(posto[i].getPosto(), i);
							if(posto[i].getInserisci())pD+=1;
						}													
					}
					s.setPostiDisp(-pD);
					arraySale.set(indiceSala, s);
					fs.writeList(arraySale);
					ScontoPerDieci sd=new ScontoPerDieci(acquistati, statoSconti[0]);//INSERIRE NUMERO BIGLIETTI
					ScontoMartedi sm= new ScontoMartedi(statoSconti[1]);
					ScontoOrario so=new ScontoOrario(statoSconti[2], inizio);
					FileFilm ff=new FileFilm("corrente.txt",TipoSettimana.CORRENTE );						
					int j=ff.cercaTitolo(titolo);
					ArrayList<Film> arr=ff.getList();
					Film f=arr.get(j);
					double costoSingolo=f.getPrezzo();
					double a=sd.getImportoSconto(acquistati*costoSingolo);
					double b=so.getImportoSconto(acquistati*costoSingolo);
					double c=sm.getImportoSconto(acquistati*costoSingolo);
					System.out.println(a);
					System.out.println(b);
					System.out.println(c);
					incasso=acquistati*costoSingolo-(a+b+c);
					arr.get(j).addIncasso(incasso);
					ff.writeList(arr);
					JOptionPane.showMessageDialog(null,"Prezzo speso: "+incasso, "",JOptionPane.INFORMATION_MESSAGE);
					dispose();
					MainFrame frameChiusura=new MainFrame();
					frameChiusura.setVisible(true);
					frameChiusura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} 
				catch (IOException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}		
		ActionListener listener = new AcquistaPrenListener();
		acquistaPren.addActionListener(listener);
		return acquistaPren;
	}
	
	/**
	 * Crea il pulsante che consente di annullare le prenotazioni effettuate
	 * @return pulsante "Cancella prenotazioni"
	 */
	private JButton btnCancPren(){
		JButton cancPren = new JButton("Cancella prenotazioni");
	
		class CancPrenListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				double incasso = 0;
				int acquistati=0;
				int pD=0;
				ObjectInputStream ois;
				boolean [] statoSconti=new boolean [3];
				try {
					ois = new ObjectInputStream(new FileInputStream("fileSconti.txt"));
					statoSconti=(boolean[])ois.readObject();
					ois.close();
					Posto p;
					for (int i=0;i<24;i++){
						if (((posto[i].getPosto().getStato()==StatoPosto.PRENOTATO))) {
							pD+=1;
							posto[i].getPosto().setStato(StatoPosto.DISPONIBILE);
							s.setPosto(posto[i].getPosto(), i);
						}													
					}
					s.setPostiDisp(pD);
					arraySale.set(indiceSala, s);
					fs.writeList(arraySale);
				} 
				catch (IOException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				dispose();
				MainFrame frameChiusura=new MainFrame();
				frameChiusura.setVisible(true);
				frameChiusura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		}		
		ActionListener listener = new CancPrenListener();
		cancPren.addActionListener(listener);
		return cancPren;
	}
	
	/**
	 * Crea il pulsante che consente di rendere Indisponibili/Disponibili dei posti
	 * @return pulsante "Rendi indisponibile/disponibile"
	 */
	private JButton btnRendiIndisp(){
		JButton cancPren = new JButton("Rendi indisponibile/disponibile");
	
		class CancPrenListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				double incasso = 0;
				int acquistati=0;
				int pD=0;
				ObjectInputStream ois;
				boolean [] statoSconti=new boolean [3];
				try {
					Posto p;
					for (int i=0;i<24;i++){
						if (((posto[i].getPosto().getStato()==StatoPosto.INDISPONIBILE))) {
							posto[i].getPosto().setStato(StatoPosto.INDISPONIBILE);
							s.setPosto(posto[i].getPosto(), i);
							if (posto[i].getInserisci())pD-=1;							
						}
						else if (((posto[i].getPosto().getStato()==StatoPosto.DISPONIBILE))) {
							posto[i].getPosto().setStato(StatoPosto.DISPONIBILE);
							s.setPosto(posto[i].getPosto(), i);
							if (posto[i].getInserisci())pD+=1;							
						} 
					}
					s.setPostiDisp(pD);
					System.out.println(s.getPostiDisp());
					arraySale.set(indiceSala, s);
					fs.writeList(arraySale);
					dispose();
					MainFrame frameChiusura=new MainFrame();
					frameChiusura.setVisible(true);
					frameChiusura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} 
				catch (IOException e1) {
					e1.printStackTrace();
				}				
			}
		}		
		ActionListener listener = new CancPrenListener();
		cancPren.addActionListener(listener);
		return cancPren;
	}
}
