package grafica;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Pannello che contiene combobox per specificare un certo orario
 * @author domian94
 *
 */
public class OraPnl extends JPanel {
	
	private JLabel dp=new JLabel(":");
	private JComboBox h=new JComboBox();
	private JComboBox m=new JComboBox();
	/**
	 * Costruisce un pannello OraPnl
	 */
	public OraPnl(){		
		for (int i=0;i<10;i++) h.addItem("0"+i);
		for (int i=10;i<24;i++) h.addItem(""+i);
		for (int i=0;i<10;i++) m.addItem("0"+i);
		for (int i=10;i<60;i++) m.addItem(""+i);
		add(h);
		add(dp);
		add(m);
	}
	
	/**
	 * Restituisce l'ora selezionata nella combobox
	 * @return valore dell'ora selezionata
	 */
	public int getH(){
		return Integer.parseInt(((String)h.getSelectedItem()));
	}
	
	/**
	 * Restituisce i minuti selezionati nella combobox
	 * @return valore deli minuti selezionati
	 */
	public int getM(){
		return Integer.parseInt(((String)m.getSelectedItem()));
	}
}
