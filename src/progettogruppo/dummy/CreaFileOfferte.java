package progettogruppo.dummy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import progettogruppo.premieofferte.OffertaSpeciale;

public class CreaFileOfferte {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("offerte.dat"));
			ArrayList<OffertaSpeciale> offerte = new ArrayList<OffertaSpeciale>();
			out.writeObject(offerte);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
