package progettogruppo.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import progettogruppo.premieofferte.Premio;

/**
 * classe che si occupa della gestione del file in cui sono memorizzati i premi
 * inseriti dalle societ� partner.invocabile da remoto
 * 
 * @see Premio
 */
public class GestoreFilePremi extends GestoreFile<Premio> implements
		RemoteGestoreFilePremi {

	/**
	 * classe che gestisce il file di premi
	 * 
	 * @param fileName
	 *            nome del file
	 * @throws FileNotFoundException
	 *             se non trova il file
	 * @throws IOException
	 *             se non trova il file
	 * @throws ClassNotFoundException
	 *             se non riesce nella deserializzazione
	 */
	public GestoreFilePremi(String fileName) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		super(fileName);
	}

	/**
	 * recupera la List di premi dato l'id della societ� che richiede
	 * 
	 * @param idSociet�
	 *            id societ� partner fornitrice
	 * @return Lista dei premi
	 * @throws RicercaFallitaException
	 *             se non ha trovato nessun Premio di quella societ�
	 * @see SocietaPartnerPremi Premio
	 */
	public ArrayList<Premio> recupera(int idSociet�)
			throws RicercaFallitaException {

		Premio temp;
		ArrayList<Premio> retrievedBuffer = new ArrayList<Premio>();
		// crea l'iterator
		Iterator<Premio> iterator = buffer.iterator();
		// se trova id uguale,aggiunge l'oggetto al retrievedBuffer
		while (iterator.hasNext()) {
			temp = iterator.next();
			if (temp.getIdSociet�() == idSociet�)
				retrievedBuffer.add(temp);
		}
		// se non ha trovato nessun Premio di quella societ�
		if (retrievedBuffer.isEmpty())
			throw new RicercaFallitaException("Non ci sono premi");
		return retrievedBuffer;
	}

}