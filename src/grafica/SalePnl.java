package grafica;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import classi.FileSala;
import classi.Sala;
/**
 * Costruisce un pannello in cui verranno visualizzate le informazioni relative a tutti gli spettacoli
 * @author domian94
 *
 */
public class SalePnl extends JPanel {
	private FileSala fs;
	private JTextArea txtPr;
	/**
	 * Costruisce un pannello SalePnl
	 */
	public SalePnl(){
		fs=new FileSala ("saleCorrenti.txt");
		txtPr=new JTextArea();
		txtPr.setEditable(false);
		setLayout(new BorderLayout());
		txtPr.setText(leggi());
		JScrollPane scr=new JScrollPane(txtPr);
		add(scr,BorderLayout.CENTER);
	}
	
	/**
	 * Restituisce una stringa contenente tutti gli spettacoli della settimana in ordine crescente di posti diponibili
	 * @return stringa contenente le informazioni di ogni spettacolo
	 */
	private String leggi(){
		ArrayList<Sala> arr=new ArrayList<Sala>();
		try {
			arr=fs.getList();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		String s="";
		Collections.sort(arr);
		for (int i=0;i<arr.size();i++) {
			Sala f=arr.get(i);
			s+=f.toString();
		}
		return s;
	}
}
