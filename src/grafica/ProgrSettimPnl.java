package grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import classi.FileFilm;
import classi.FileSala;
import classi.Film;
import classi.Posto;
import classi.Sala;
import classi.StatoPosto;
import classi.TipoSettimana;
import eccezioni.CampiVuotiException;
import eccezioni.CoppiaDateErrateException;
import eccezioni.FilmNonInseribileException;
/**
 * Crea un pannello in cui saranno contenuti i vari campi per inserire un film nella programmazione settimanale
 * @author domian94
 *
 */
public class ProgrSettimPnl extends JPanel {
		
		private JButton conf;	
		private TipoSettimana mod;
		private JTextField txtTitolo;
		private JTextArea txtDescrizione;
		private JTextField txtPrezzoI;
		private DataPnl pnlDataIn;
		private DataPnl pnlDataFine;
		private OraPnl oraInizio;
		private OraPnl oraFine;
		private JComboBox sala; 
		/**
		 * Costruisce un pannello ProgSettimPnl 
		 * @param m settimana della programmazione (Corrente o prossima)
		 */
		public ProgrSettimPnl(TipoSettimana m){//mettere tipo settimana
			mod=m;
			sala=new JComboBox();
			for (int i=1;i<4;i++)sala.addItem(i);
			pnlDataIn= new DataPnl(m);
			pnlDataFine= new DataPnl(m);
			/*
			mod=m;
			if (mod==1){
				pnlDataIn= new DataPnl(TipoSettimana.CORRENTE);
				pnlDataFine= new DataPnl(TipoSettimana.CORRENTE);
			}
			else {
				pnlDataIn= new DataPnl(TipoSettimana.PROSSIMA);
				pnlDataFine= new DataPnl(TipoSettimana.PROSSIMA);
			}
			*/
			oraInizio=new OraPnl();
			oraFine=new OraPnl();
			conf=btnConferma();
			setLayout(new GridLayout(19,2,1,3));
			JLabel lblTitolo=new JLabel ("Titolo:");
			JLabel lblDescrizione=new JLabel ("Descrizione:");
			JLabel lblPrezzoI=new JLabel ("Prezzo:");
			JLabel lblSala=new JLabel ("Sala:");
			JLabel lblDataInizio=new JLabel ("Data inizio proiezione (gg/mm/aaaa):");
			JLabel lblDataFine=new JLabel ("Data fine proiezione (gg/mm/aaaa):");
			JLabel lblOreInizio=new JLabel ("Ora inizio proiezione (hh:mm):");
			JLabel lblOreFine=new JLabel ("Ora fine proiezione (hh:mm):");
						
			txtTitolo=new JTextField();
			txtDescrizione=new JTextArea (5,25);
			txtPrezzoI=new JTextField();
			JScrollPane sp = new JScrollPane(txtDescrizione);
			
			add(lblTitolo);
			add(txtTitolo);
			add(new JLabel(""));
			add(new JLabel(""));
			add(lblDescrizione);
			add(sp);
			add(new JLabel(""));
			add(new JLabel(""));
			add(lblPrezzoI);
			add(txtPrezzoI);
			add(new JLabel(""));
			add(new JLabel(""));
			add(lblSala);
			add(sala);
			add(new JLabel(""));
			add(new JLabel(""));
			add(lblDataInizio);
			add(pnlDataIn);
			add(new JLabel(""));
			add(new JLabel(""));			
			add(lblDataFine);
			add(pnlDataFine);
			add(new JLabel(""));
			add(new JLabel(""));
			add(lblOreInizio);
			add(oraInizio);
			add(new JLabel(""));
			add(new JLabel(""));
			add(lblOreFine);
			add(oraFine);
			add(new JLabel(""));
			add(new JLabel(""));
			add(conf);
			
			
		}
		/**
		 * Restituisce il titolo del film inserito nell'apposito campo
		 * @return stringa contenente il titolo del film inserito
		 */
		public String getTitolo() {
			return txtTitolo.getText();
		}
		
		/**
		 * Restituisce la descrizione del film inserito nell'apposito campo
		 * @return stringa contenente la descrizione del film inserito
		 */
		public String getDescrizione() {
			return txtDescrizione.getText();
		}
		
		/**
		 * Restituisce il prezzo intero di un biglietto per il film inserito nell'apposito campo
		 * @return numero contenente il prezzo intero di un biglietto per il film inserito
		 */
		public double getPrezzoI() {
			double pI = 0;
			try{
				pI=Double.parseDouble(txtPrezzoI.getText());
			}
			catch (NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "Valore scorretto nel campo prezzo intero, riprovare l'inserimento", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			return pI;
		}
		
		/**
		 * Restituisce il numero di sala inserito in cui verrà proiettato il film
		 * @return numero di sala per il film inserito
		 */
		public int getSala() {
			return (int)sala.getSelectedItem();
		}
		
		/**
		 * Restituisce l'ora di inizio inserita in cui verrà proiettato il film
		 * @return ora di inizio per il film inserito
		 */
		public Calendar getHInizio() {
			Calendar cal= Calendar.getInstance();
			cal.set(0, 0, 0,0, oraInizio.getM(),0);
			cal.set(Calendar.HOUR_OF_DAY,oraInizio.getH());
			cal.set(Calendar.SECOND,0);
			cal.set(Calendar.MILLISECOND,0);
			return cal;
		}
		
		/**
		 * Restituisce l'ora di fine inserita in cui verrà proiettato il film
		 * @return ora di fine per il film inserito
		 */
		public Calendar getHFine() {
			Calendar cal= Calendar.getInstance();
			cal.set(0, 0, 0, 0, oraFine.getM(),0);
			cal.set(Calendar.HOUR_OF_DAY,oraFine.getH());
			cal.set(Calendar.SECOND,0);
			cal.set(Calendar.MILLISECOND,0);
			return cal;
		}
		
		/**
		 * Restituisce il giorno di inizio delle proiezioni inserito in cui verrà proiettato il film
		 * @return giorno di inizio delle proiezioni per il film inserito
		 */
		public Calendar getGInizio() {
			Calendar cal= Calendar.getInstance();
			cal.set(pnlDataIn.getAnno(), pnlDataIn.getMese(), pnlDataIn.getGiorno(), 0, 0,0);
			cal.set(Calendar.MILLISECOND,0);
			return cal;
		}
		
		/**
		 * Restituisce il giorno di fine delle proiezioni inserito in cui verrà proiettato il film
		 * @return giorno di fine delle proiezioni per il film inserito
		 */
		public Calendar getGFine() {
			Calendar cal= Calendar.getInstance();
			cal.set(pnlDataFine.getAnno(), pnlDataFine.getMese(), pnlDataFine.getGiorno(), 0, 0,0);
			cal.set(Calendar.MILLISECOND,0);
			return cal;
		}
		
		/**
		 * Controlla che tutti i campi siano stati compilati
		 * @throws CampiVuotiException se alcuni campi sono vuoti
		 */
		private void controlloCampi () throws CampiVuotiException{
			if ((txtDescrizione.getText().equals(""))||(txtPrezzoI.getText().equals(""))||
				(txtTitolo.getText().equals(""))) throw new CampiVuotiException("Campi vuoti.") ;
		}
		
		/**
		 * Pulisce i campi
		 */
		private void pulisci(){
			txtDescrizione.setText("");
			txtPrezzoI.setText("");
			txtTitolo.setText("");
		}
		
		/**
		 * Pulsante alla cui pressione viene confermato l'inserimento del film
		 * @return pulsante "Conferma"
		 */
		private JButton btnConferma (){
			JButton conferma = new JButton("Conferma");
		
			class ConfermaListener implements ActionListener {

				public void actionPerformed(ActionEvent arg0)  {
					try {
						controlloCampi();
						Film f= new Film(getTitolo(), getDescrizione(), getPrezzoI(), getSala(), getGInizio(), getGFine(),getHInizio(),getHFine());
						Calendar calInizio=Calendar.getInstance();
						Calendar calFine=Calendar.getInstance();
						calInizio.set(Calendar.DATE,getGInizio().get(Calendar.DATE));
						calInizio.set(Calendar.MONTH,getGInizio().get(Calendar.MONTH));
						calInizio.set(Calendar.YEAR,getGInizio().get(Calendar.YEAR));
						calInizio.set(Calendar.HOUR_OF_DAY,getHInizio().get(Calendar.HOUR_OF_DAY));
						calInizio.set(Calendar.MINUTE,getHInizio().get(Calendar.MINUTE));
						calInizio.set(Calendar.SECOND,0);
						calInizio.set(Calendar.MILLISECOND,0);
						calFine.set(Calendar.DATE,getGFine().get(Calendar.DATE));
						calFine.set(Calendar.MONTH,getGFine().get(Calendar.MONTH));
						calFine.set(Calendar.YEAR,getGFine().get(Calendar.YEAR));
						calFine.set(Calendar.HOUR_OF_DAY,getHInizio().get(Calendar.HOUR_OF_DAY));
						calFine.set(Calendar.MINUTE,getHInizio().get(Calendar.MINUTE));
						calFine.set(Calendar.SECOND,0);
						calFine.set(Calendar.MILLISECOND,0);
						FileFilm c;
						FileSala fs;
						if (mod==TipoSettimana.CORRENTE) {
							c=new FileFilm ("corrente.txt",TipoSettimana.CORRENTE);
							fs=new FileSala("saleCorrenti.txt");
						}
						else {
							c=new FileFilm ("prossima.txt",TipoSettimana.PROSSIMA);
							fs=new FileSala("saleProssime.txt");
						}
						while (calInizio.compareTo(calFine)<=0){
							Sala s;
							Posto[]p=new Posto[25];
							for (int i=0;i<25;i++) p[i]=new Posto(i, StatoPosto.DISPONIBILE);
							s=new Sala(getSala(), calInizio, p,getTitolo());
							try {
								fs.writeRecord(s);
							} 
							catch (Exception e) {
								e.printStackTrace();
							}
							calInizio.add(Calendar.DATE, 1);
						}
						try {
							c.writeRecord(f);
						} 
						catch (ClassNotFoundException e) {
							e.printStackTrace();
						} 
						catch (IOException e) {
							e.printStackTrace();
						} 
						catch (CoppiaDateErrateException e) {
							JOptionPane.showMessageDialog(null, "Coppia date e/o orari inseriti in modo scorretto", "Errore", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
						catch (FilmNonInseribileException e) {
							JOptionPane.showMessageDialog(null, "Si accavallano film, cambiare sala o cambiare orario di programmazione", "Errore", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
					} 
					catch (CampiVuotiException e) {
						JOptionPane.showMessageDialog(null, "Non tutti i campi sono stati compilati, riprovare", "Errore", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
						pulisci();
					}
				}
			}
			ActionListener listener = new ConfermaListener();
			conferma.addActionListener(listener);
			return conferma;
		}
			
}














