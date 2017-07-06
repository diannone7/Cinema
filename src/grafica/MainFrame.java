package grafica;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Frame in cui si sceglie in quale modalit� eseguire il programma
 * @author domian94
 *
 */
public class MainFrame extends JFrame {
	/**
	 * Costruisce un frame MainFrame
	 */
	public MainFrame (){
		setBounds(300, 300, 300, 120);
		//setSize(250,100);
		setTitle ("Cinema");
		setResizable(false);
		JLabel label= new JLabel ("Scegliere modalit�:");
		JPanel pnl=new JPanel();
		pnl.setLayout(new GridLayout(1,2));
		pnl.add(btnCli());
		pnl.add(btnGest());
		add(label,BorderLayout.NORTH);
		add(pnl);
	}
	
	/**
	 * Crea il pulsante per la scelta della modalit� cliente
	 * @return pulsante "Cliente"
	 */
	public JButton btnCli (){
		JButton btnCl= new JButton ("Cliente");
		/**
		 * Apre il frame Home Cliente al click del pulsante
		 */
		class OpenCliListener implements ActionListener{
			
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame= new HomeClienteFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				dispose();
			}
		}
		
		ActionListener listener = new OpenCliListener();
		btnCl.addActionListener(listener);
		return btnCl;
	}
	
	/**
	 * Crea il pulsante per la scelta della modalit� gestore
	 * @return pulsante "Gestore"
	 */
	public JButton btnGest (){
		JButton btnGes= new JButton ("Gestore");
		/**
		 * Apre il frame Home Cliente al click del pulsante
		 */
		class OpenCliListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				JFrame frame= new HomeGestoreFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				dispose();
			}
		}
		
		ActionListener listener = new OpenCliListener();
		btnGes.addActionListener(listener);
		return btnGes;
	}
}
