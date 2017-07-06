package grafica;

import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import classi.TipoSettimana;
/**
 * Pannello in cui ci sono le combobox per selezionare una data
 * @author domian94
 *
 */
public class DataPnl extends JPanel {
	
	private JComboBox anno;
	private JComboBox mese;
	private JComboBox giorno;
	/**
	 * Costruisce un pannello DataPnl
	 * @param mod tipo della settimana in cui deve ricadere la data (corrente o prossima)
	 */
	public DataPnl(TipoSettimana mod){
		Calendar cal = Calendar.getInstance();
		int a;
		int m;
		int g;
		int dow;
		anno=new JComboBox();
		mese=new JComboBox();
		giorno=new JComboBox();
		dow = cal.get(Calendar.DAY_OF_WEEK);
		if (mod==TipoSettimana.CORRENTE){
			a=cal.get(Calendar.YEAR);
			m=cal.get(Calendar.MONTH)+1;
			g=cal.get(Calendar.DATE);
			anno.addItem(a);
			mese.addItem(m);		
			giorno.addItem(g);
			for (int i=dow;i<7;i++){
				cal.add(Calendar.DAY_OF_WEEK, 1);
				giorno.addItem(cal.get(Calendar.DATE));
			}
		}
		else{
			cal.add(Calendar.DAY_OF_WEEK, 8-dow);
			a=cal.get(Calendar.YEAR);
			m=cal.get(Calendar.MONTH)+1;
			g=cal.get(Calendar.DATE);
			anno.addItem(a);
			mese.addItem(m);	
			giorno.addItem(cal.get(Calendar.DATE));			
			for (int i=1;i<7;i++){
				cal.add(Calendar.DAY_OF_WEEK, 1);
				giorno.addItem(cal.get(Calendar.DATE));				
			}
		}
		if (a<cal.get(Calendar.YEAR)){
			anno.addItem(cal.get(Calendar.YEAR));
			mese.addItem((cal.get(Calendar.MONTH)+1));
		}
		if (m<(cal.get(Calendar.MONTH)+1))mese.addItem((cal.get(Calendar.MONTH)+1));
		add(anno);
		add(mese);
		add(giorno);
	}
	
	/**
	 * Restituisce il valore dell'anno selezionato nella combobox
	 * @return valore dell'anno selezionato
	 */
	public int getAnno(){
		return (int)anno.getSelectedItem();
	}

	/**
	 * Restituisce il valore del mese selezionato nella combobox
	 * @return valore del mese selezionato
	 */
	public int getMese(){
		return (int)mese.getSelectedItem()-1;
	}

	/**
	 * Restituisce il valore del giorno selezionato nella combobox
	 * @return valore del giorno selezionato
	 */
	public int getGiorno(){
		return (int)giorno.getSelectedItem();
	}

}
