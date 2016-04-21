package progettogruppo.dummy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import progettogruppo.premieofferte.Premio;

public class CreaFilePremi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("premi.dat"));
			ArrayList<Premio> premi = new ArrayList<Premio>();
			out.writeObject(premi);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
