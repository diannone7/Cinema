package grafica;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import javax.swing.JFrame;

import classi.FileFilm;
import classi.TipoSettimana;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Calendar oggi=Calendar.getInstance();
		Calendar cal=Calendar.getInstance();
		File c=new File ("corrente.txt");
		File p=new File ("prossima.txt");
		File s=new File ("fileSconti.txt");
		if (!s.exists()){
			boolean []arr=new boolean[3];
			arr[0]=false;
			arr[1]=false;
			arr[2]=false;
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(s));
			oos.writeObject(arr);
		}
		if (c.exists()){
			FileFilm f=new FileFilm("corrente.txt",TipoSettimana.CORRENTE);
			cal=f.getDataCanc();
			if (oggi.compareTo(cal)>0){
				c.delete();
				if (p.exists())p.renameTo(c);
				c=new File ("dataCorrente.txt");
				p=new File ("dataProssima.txt");
				if (c.exists()){
					c.delete();
					if (p.exists())p.renameTo(c);
				}
			}
		}
		
		JFrame frame= new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
