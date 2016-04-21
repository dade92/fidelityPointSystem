package progettogruppo.dummy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class creaFileCosti {

	/*
	 * classe contenente un main che genera il file con i costi di default dei
	 * biglietti
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			int costi[][] = { { 300, 500, 700 }, { 400, 600, 800 },
					{ 500, 700, 900 }, { 600, 800, 1000 } };

			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("costiBiglietto.dat"));
			out.writeObject(costi);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
