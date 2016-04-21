package progettogruppo.dummy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import progettogruppo.utenti.*;

public class CreaFileUtenti {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("utenti.dat"));
			ArrayList<Utente> utenti = new ArrayList<Utente>();
			utenti.add(new Amministratore(123, "admin"));
			utenti.add(new AddettoAlSupporto(125, "helpdesk"));
			out.writeObject(utenti);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
