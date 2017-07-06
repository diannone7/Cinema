package grafica;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import classi.Modalita;
import classi.Posto;
import classi.StatoPosto;
import eccezioni.PostoNonDisponibileException;
import eccezioni.PostoOccupatoException;
import eccezioni.PostoPrenotatoException;
/**
 * Pannello che raffigura un posto nella sala.
 * @author domian94
 *
 */
public class PostoPnl extends JPanel implements MouseListener {
	
	private boolean inserisci;
	private Modalita mod;
	private Posto pos;
	private Color original;
	private StatoPosto stato;
	/**
	 * Crea un pannello PostoPnl
	 * @param p Oggetto Posto che è memorizzato nel file di posti e che sarà raffigurato dal pannello
	 * @param m Modalità di accesso al posto (Cliente o Gestore)
	 * @param sp Stato 
	 */
	public PostoPnl(Posto p, Modalita m,StatoPosto sp){
		JLabel lbl=new JLabel("Posto n°"+p.getNumero());
		lbl.setForeground(Color.WHITE);
		stato=sp;
		mod=m;
		inserisci=false;
		Border whiteBorder = BorderFactory.createLineBorder(Color.WHITE);
		setBorder(whiteBorder);
		pos=p;
		StatoPosto x=p.getStato();
		switch(x){
		case INDISPONIBILE:
			original=Color.BLACK;
			setBackground(original);
			break;
		case DISPONIBILE:
			original=Color.GREEN;
			setBackground(original);
			break;
		case OCCUPATO:
			original=Color.RED;
			setBackground(original);
			break;
		case PRENOTATO:
			original=Color.ORANGE;
			setBackground(original);
			break;
		}
		add(lbl);
		super.addMouseListener(this);
	}
	/**
	 * Al click del mouse, in base alla funzione che deve essere svolta, il pannello cambia colore. 
	 * Se il posto deve essere reso indisponibile, il pannello si colora di rosso, se disponibile si colora di verde,
	 *  se prenotato si colora di arancio e viene opportunamente modificato il relativo oggetto Posto.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Calendar cal;
		switch (mod){
		case CLIENTE:
			if (stato==StatoPosto.DISPONIBILE){
				try{
					controlloPostoCliente();		
					setBackground(Color.RED);
					pos.setStato(StatoPosto.OCCUPATO);
					inserisci=true;
				    repaint();
				}
				catch(PostoNonDisponibileException ex){
					JOptionPane.showMessageDialog(null, "Posto indisponibile!", "Errore", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
				catch(PostoOccupatoException ex){
					JOptionPane.showMessageDialog(null, "Posto occupato!", "Errore", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
				catch(PostoPrenotatoException ex){
					JOptionPane.showMessageDialog(null, "Posto già prenotato!", "Errore", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}	
			}
			else if (stato==StatoPosto.PRENOTATO){
				try{
					controlloPostoCliente();		
					setBackground(Color.ORANGE);
					pos.setStato(StatoPosto.PRENOTATO);
					inserisci=true;
				    repaint();
				   }
				catch(PostoNonDisponibileException ex){
					JOptionPane.showMessageDialog(null, "Posto indisponibile!", "Errore", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
				catch(PostoOccupatoException ex){
					JOptionPane.showMessageDialog(null, "Posto occupato!", "Errore", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
				catch(PostoPrenotatoException ex){
					JOptionPane.showMessageDialog(null, "Posto già prenotato!", "Errore", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
			break;
		case GESTORE://RENDERE I POSTI INDISPONIBILI/DISPONIBILI
				try{
					controlloPostoGestore();
					if (pos.getStato()==StatoPosto.DISPONIBILE){								
						setBackground(Color.BLACK);
						pos.setStato(StatoPosto.INDISPONIBILE);
						inserisci=true;
					    repaint();
				    }
					else{
						setBackground(Color.GREEN);
						pos.setStato(StatoPosto.DISPONIBILE);
						inserisci=true;
					    repaint();
					}
					if (getBackground().equals(original))inserisci=false;
				   }
				catch(PostoOccupatoException ex){
					JOptionPane.showMessageDialog(null, "Posto occupato!", "Errore", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
				catch(PostoPrenotatoException ex){
					JOptionPane.showMessageDialog(null, "Posto già prenotato!", "Errore", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}	
			break;
		}		
	}
	/**
	 * Non fa nulla
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	/**
	 * Non fa nulla
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Non fa nulla
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Non fa nulla
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Controlla un posto selezionato da un cliente
	 * @throws PostoNonDisponibileException se il posto è non disponibile, ma viene cliccato
	 * @throws PostoOccupatoException se il posto è occupato, ma viene cliccato
	 * @throws PostoPrenotatoException se il posto è prenotato, ma viene cliccato
	 */
	private void controlloPostoCliente() throws PostoNonDisponibileException,PostoOccupatoException,PostoPrenotatoException{
		if (pos.getStato()==StatoPosto.INDISPONIBILE)throw new PostoNonDisponibileException("Posto non disponibile");
		if (pos.getStato()==StatoPosto.OCCUPATO)throw new PostoOccupatoException("Posto occupato");
		if (pos.getStato()==StatoPosto.PRENOTATO)throw new PostoPrenotatoException("Posto prenotato");
	}
	
	/**
	 * Controlla un posto selezionato da un cliente
	 * @throws PostoOccupatoException se il posto è occupato, ma viene cliccato
	 * @throws PostoPrenotatoException se il posto è prenotato, ma viene cliccato
	 */
	private void controlloPostoGestore() throws PostoOccupatoException,PostoPrenotatoException{
		if (pos.getStato()==StatoPosto.OCCUPATO)throw new PostoOccupatoException("Posto occupato");
		if (pos.getStato()==StatoPosto.PRENOTATO)throw new PostoPrenotatoException("Posto prenotato");
	}
	
	/**
	 * Restituisce la variabile booleana inserisci che indica se lo stato del posto è stato modificato
	 * @return variabile inserisci
	 */
	public boolean getInserisci(){
		return inserisci;
	}
	
	/**
	 * Restituisce l'oggetto Posto al quale si riferisce il pannello
	 * @return oggetto Posto
	 */
	public Posto getPosto(){
		return pos;
	}
}
