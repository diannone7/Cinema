package grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Frame in cui verranno attivate/disattivate politiche di sconto
 * @author domian94
 *
 */
public class ScontiFrame extends JFrame {
	//vedere quali sconti sono già attivi
	private JCheckBox sc10B;
	private JCheckBox scMartedi;
	private JCheckBox scPom;
	private JLabel l;
	private JFrame f;
	private boolean so;
	private boolean sd;
	private boolean sm;
	private ObjectOutputStream oos;
	
	/**
	 * Costruisce un frame ScontiFrame
	 * @param fr frame da cui viene aperto ScontiFrame e a cui dovrà essere di nuovo passato il controllo
	 */
	public ScontiFrame(JFrame fr){
		boolean []arr=new boolean[3];
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("fileSconti.txt"));
			arr=(boolean[])ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
		so=false;
		sd=false;
		sm=false;
		f=fr;
		setUndecorated(true);
		setSize(250, 200);
		setResizable(false);
		l=new JLabel("Selezionare politiche di sconto:");
		sc10B=new JCheckBox("Sconto 10+ biglietti");
		scMartedi= new JCheckBox("Sconto martedì");
		scPom=new JCheckBox("Sconto fascia 12:00-15:00");
		if (arr[0])sc10B.setSelected(true);
		if (arr[1])scMartedi.setSelected(true);
		if (arr[2])scPom.setSelected(true);
		setLayout(new GridLayout(5,1));
		add(l);
		add(sc10B);
		add(scMartedi);
		add(scPom);
		add(btnOk());
		}
	
	/**
	 * Pulsante di conferma di attivazione/disattivazione politiche di sconto
	 * @return pulsante "Ok"
	 */
	private JButton btnOk(){
		JButton b=new JButton ("Ok");
		class OkListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				if (sc10B.isSelected()) sd=true;
				else sd=false;
				if (scMartedi.isSelected()) sm=true;
				else sm=false;
				if (scPom.isSelected()) so=true;
				else so=false;
				boolean [] a=new boolean[3];
				a[0]=sd;
				a[1]=sm;
				a[2]=so;
				try {
					oos=new ObjectOutputStream(new FileOutputStream("fileSconti.txt"));
					oos.writeObject(a);
					oos.close();
				} 
				catch (FileNotFoundException e) {
					e.printStackTrace();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("premuto ok");
				f.enable(true);
				dispose();
			}
		}
		ActionListener listener = new OkListener();
		b.addActionListener(listener);
		return b;
	}
}
