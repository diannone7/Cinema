package grafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import classi.Modalita;
/**
 * Frame che rappresenta il pannello di controllo delle funzionalità che può sfruttare 
 * un cliente
 * @author domian94
 *
 */
public class HomeClienteFrame extends JFrame {
	
	private JPanel pnl;	
	private JPanel pnl2;
	/**
	 * Costruisce un frame HomeClienteFrame
	 */
	public HomeClienteFrame(){
		pnl=new JPanel();
		pnl.setLayout(new BorderLayout());
		pnl2=new JPanel();
		pnl.add(pnl2,BorderLayout.CENTER);
		JMenuBar menuCliente= new JMenuBar();
		menuCliente.add(visualizzaMenu());;
		menuCliente.add(esciItem());
		setTitle ("Home cliente");
		setSize(800,1000);
		setResizable(false);
		this.setLayout(new BorderLayout());
		add(menuCliente,BorderLayout.NORTH);
		add(pnl,BorderLayout.CENTER);
		add(btnChiudi(),BorderLayout.SOUTH);
	}
	/**
	 * Crea il pulsante che porta alla visualizzazione della lista degli spettacoli non
	 * ancora iniziati
	 * @return pulsante "Lista spettacoli non iniziati"
	 */
	public JMenuItem nonInizItem (){
		JMenuItem nI = new JMenuItem("Lista spettacoli non iniziati");
		JFrame questo=this;
		/**
		 * Apre il pannello contenente la lista di tutti gli spettacoli non iniziati
		 */
		class NonInizListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				pnl.remove(pnl2);
				pnl2=new FilmNonInizPnl(questo,Modalita.CLIENTE);
				pnl.add(pnl2,BorderLayout.CENTER);
				validate();
			}
		}
		
		ActionListener listener = new NonInizListener();
		nI.addActionListener(listener);
		return nI;
	}
	/**
	 * Crea il pulsante che porta alla visualizzazione della programmazione settimanale 
	 * complessiva
	 * @return pulsante "Complessivo"
	 */
	public JMenuItem progComplItem (){
		JMenuItem progCompl = new JMenuItem("Complessivo");
		JFrame fre=this;
		/**
		 * Apre il pannello contenente la programmazione settimanale
		 */
		class ProgComplListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				pnl.remove(pnl2);
				pnl2=new ProgrammazionePnl(Modalita.CLIENTE);
				pnl.add(pnl2,BorderLayout.CENTER);
				validate();
				}
		}
		
		ActionListener listener = new ProgComplListener();
		progCompl.addActionListener(listener);
		return progCompl;
	}
	/**
	 * Crea il pulsante che porta alla visualizzazione della programmazione settimanale 
	 * per sala
	 * @return pulsante "Per sala"
	 */
	public JMenuItem progSalaItem (){
		JMenuItem progSala = new JMenuItem("Per sala");
		
		class ProgSalaListener implements ActionListener{
			/**
			 * Apre il pannello contenente la programmazione settimanale per una certa sala 
			 * di cui si immette il numero
			 */
			public void actionPerformed(ActionEvent arg0) {
				String name = JOptionPane.showInputDialog("Inserisci sala:");
				System.out.println(name);
				if (name.equals(""));
				else{
				int x=0;
				try {
					x=Integer.parseInt(name);
				}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Valore scorretto!", "Errore", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
				pnl.remove(pnl2);
				pnl2=new ProgrammazionePnl(x);
				pnl.add(pnl2,BorderLayout.CENTER);
				validate();
				}
			}
		}
		
		
		ActionListener listener = new ProgSalaListener();
		progSala.addActionListener(listener);
		return progSala;
	}
	/**
	 * Crea il menu per le funzioni relative alla programmazione settimanale
	 * @return menu "Programma settimanale"
	 */
	public JMenu progSettMenu (){
		JMenu progSett = new JMenu("Programma settimanale");
		progSett.add(progComplItem());
		progSett.add(progSalaItem());
		return progSett;
	}
	/**
	 * Crea il menu per le funzioni relative alle visualizzazioni
	 * @return menu "Visualizza"
	 */
	public JMenu visualizzaMenu (){
		JMenu visualizza = new JMenu("Visualizza");
		visualizza.add(progSettMenu());
		visualizza.add(nonInizItem());
		return visualizza;
	}
	/**
	 * Crea la voce del menu per chiudere il frame
	 * @return voce "Esci" del menu
	 */
	public JMenuItem esciItem (){
		JMenuItem esci = new JMenuItem("Esci");
		/**
		 * Chiude il frame HomeClienteFrame e apre il frame MainFrame
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
