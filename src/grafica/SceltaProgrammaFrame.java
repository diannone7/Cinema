package grafica;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classi.TipoSettimana;
/**
 * Frame per la scelta da parte del gestore di inserire la programmazione della settimana corrente o della prossima
 * @author domian94
 *
 */
public class SceltaProgrammaFrame extends JFrame {
		/**
		 * Costruisce un frame SceltaProgrammaFrame
		 */
		public SceltaProgrammaFrame(){
			setSize(250,100);
			setTitle ("Scelta");
			setResizable(false);
			JLabel label= new JLabel ("Inserisci programma settimana");
			JPanel pnl=new JPanel();
			pnl.setLayout(new GridLayout(1,2));
			pnl.add(btnCorrente());
			pnl.add(btnProssima());
			add(label,BorderLayout.NORTH);
			add(pnl);
		}
		
		/**
		 * Crea un pulsante che cliccato porterà all'inserimento della programmazione della
		 * settimana corrente
		 * @return pulsante "Corrente"
		 */
		public JButton btnCorrente (){
			JButton corrente = new JButton("Corrente");
		
			class CorrenteListener implements ActionListener{

				public void actionPerformed(ActionEvent arg0) {
					HomeGestoreFrame frame= new HomeGestoreFrame();
					frame.addInsPnl(TipoSettimana.CORRENTE);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
			}
			
			ActionListener listener = new CorrenteListener();
			corrente.addActionListener(listener);
			return corrente;
		}
		
		/**
		 * Crea un pulsante che cliccato porterà all'inserimento della programmazione della
		 * settimana prossima
		 * @return pulsante "Prossima"
		 */
		public JButton btnProssima (){
			JButton prossima = new JButton("Prossima");
		
			class ProssimaListener implements ActionListener{

				public void actionPerformed(ActionEvent arg0) {
					HomeGestoreFrame frame= new HomeGestoreFrame();
					frame.addInsPnl(TipoSettimana.PROSSIMA);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
					dispose();
				}
			}
			
			ActionListener listener = new ProssimaListener();
			prossima.addActionListener(listener);
			return prossima;
		}
		
}
