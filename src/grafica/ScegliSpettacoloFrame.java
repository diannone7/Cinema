package grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import classi.Modalita;
import classi.StatoPosto;
/**
 * Frame in cui verrà scelta la data dello spettacolo che interessa
 * @author domian94
 *
 */
public class ScegliSpettacoloFrame extends JFrame {
	private JComboBox giorno;
	private JComboBox<Integer> mese;
	private JComboBox<Integer> anno;
	private String titolo;
	private int sala;
	private Modalita mod;
	private Calendar c;
	private StatoPosto stato;
	private Calendar dataIn;
	private Calendar dataFi;
	private long numGiorni;
	/**
	 * Costruisce un frame ScegliSpettacoloFrame
	 * @param dataIn data di inizio delle proiezioni di un film
	 * @param dataFi data di fine delle proiezioni di un film
	 * @param hInizio ora di inizio delle proiezioni di un film
	 * @param sala sala di proiezione del film
	 * @param m modalità di accesso al frame (gestore o cliente)
	 * @param tit titolo del film
	 * @param sp segnala che il frame servirà a scegliere uno spettacolo per settare alcuni suoi posti allo stato sp
	 */
	public ScegliSpettacoloFrame(Calendar dataIn,Calendar dataFi,Calendar hInizio,int sala, Modalita m,String tit,StatoPosto sp){
		this.dataIn=(Calendar) dataIn.clone();
		this.dataIn.set(Calendar.HOUR_OF_DAY, hInizio.get(Calendar.HOUR_OF_DAY));
		this.dataIn.set(Calendar.MINUTE, hInizio.get(Calendar.MINUTE));
		this.dataFi=(Calendar) dataFi.clone();this.dataFi.set(Calendar.HOUR_OF_DAY, hInizio.get(Calendar.HOUR_OF_DAY));
		this.dataFi.set(Calendar.MINUTE, hInizio.get(Calendar.MINUTE)); 
		setSize(400,100);
		setTitle ("Scegli");
		setResizable(false);
		stato=sp;
		giorno=comboGiorno();
		mese=comboMese();
		anno=comboAnno();
		c=hInizio;
		titolo=tit;
		this.sala=sala;
		mod=m;
		setLayout(new GridLayout(3,3));
		JLabel lbl=new JLabel("Scegli data spettacolo");
		add(new JLabel(""));
		add(lbl);
		add(new JLabel(""));
		add(giorno);
		add(mese);
		add(anno);
		add(new JLabel(""));
		add(btnOk());
		add(new JLabel(""));
	}
	
	/**
	 * Crea un pulsante per la conferma della scelta e la successiva apertura 
	 * del frame PostiFrame 
	 * @return pulsante "Ok"
	 */
	private JButton btnOk(){
		JButton btn = new JButton("Ok");
		class OkListener implements ActionListener{
			
			public void actionPerformed(ActionEvent arg0) {
				Calendar cal=Calendar.getInstance();
				cal.set(Calendar.DATE, (int)giorno.getSelectedItem());
				cal.set(Calendar.MONTH, (int)mese.getSelectedItem()-1);
				cal.set(Calendar.YEAR, (int)anno.getSelectedItem());
				cal.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
				cal.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				try{
					JFrame frame= new PostiFrame(sala,mod,titolo,cal,stato);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
					dispose();
				}
				catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "La ricerca effettuata non dà risultati!", "Errore", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
		ActionListener listener = new OkListener();
		btn.addActionListener(listener);
		return btn;
	}
	
	/**
	 * Crea la combobox in cui sono contenuti i giorni di proiezione di uno spettacolo
	 * @return combobox con la lista dei giorni di proiezione 
	 */
	private JComboBox comboGiorno(){
		JComboBox e = new JComboBox();
		Calendar c=(Calendar) dataIn.clone();
		int giornoFine=dataFi.get(Calendar.DAY_OF_MONTH);
		numGiorni=(dataFi.getTime().getTime()-dataIn.getTime().getTime())/(24 * 3600 * 1000);
		System.out.println(Calendar.getInstance().getTime());
		System.out.println(c.getTime());
		for(int i=0;i<=numGiorni;i++){
			if (c.compareTo(Calendar.getInstance())>0) e.addItem(c.get(Calendar.DAY_OF_MONTH));
			c.add(Calendar.DATE,1);
		}
		return e;
	}
	
	/**
	 * Crea la combobox in cui sono contenuti i mesi di proiezione di uno spettacolo
	 * @return combobox con la lista dei mesi di proiezione 
	 */
	private JComboBox comboMese(){
		JComboBox<Integer> e = new JComboBox<Integer>();
		int meseCombo=dataIn.get(Calendar.MONTH)+1;
		Calendar cal=(Calendar) dataIn.clone();
		int i=1;
		if (cal.compareTo(Calendar.getInstance())>0) e.addItem(meseCombo);
		cal.add(Calendar.DATE,1);
		while (i<numGiorni){
			meseCombo=cal.get(Calendar.MONTH)+1;
			if (e.getItemCount()==0){
				if ((cal.compareTo(Calendar.getInstance())>0))e.addItem(meseCombo);
			}
			else if (e.getItemAt(0)!=meseCombo){
				e.addItem(meseCombo);
				return e;
			}
				i++;
				cal.add(Calendar.DATE,1);		
		}
		return e;
	}
	
	/**
	 * Crea la combobox in cui sono contenuti gli anni di proiezione di uno spettacolo
	 * @return combobox con la lista degli anni di proiezione 
	 */
	private JComboBox comboAnno(){
		JComboBox<Integer> e = new JComboBox<Integer>();
		int annoCombo=dataIn.get(Calendar.YEAR);
		Calendar cal=(Calendar) dataIn.clone();
		int i=1;
		if (cal.compareTo(Calendar.getInstance())>0) e.addItem(annoCombo);
		cal.add(Calendar.DATE,1);
		while (i<numGiorni){
			annoCombo=cal.get(Calendar.YEAR);
			if (e.getItemCount()==0){
				if ((cal.compareTo(Calendar.getInstance())>0))e.addItem(annoCombo);
			}
			else if (e.getItemAt(0)!=annoCombo){
				e.addItem(annoCombo);
				return e;
			}
				i++;
				cal.add(Calendar.DATE,1);		
		}
		return e;
	}
}
