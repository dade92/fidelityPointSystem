package progettogruppo.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import progettogruppo.premieofferte.OffertaSpeciale;

/**
 * classe che si occupa della gestione del file in cui sono memorizzate le
 * offerte speciali fornite dalle societą partner.invocabile da remoto
 * 
 * @see OffertaSpeciale
 */
public class GestoreFileOfferteSpeciali extends GestoreFile<OffertaSpeciale>
		implements RemoteGestoreFileOfferte {

	/**
	 * costruisce un nuovo GestoreFileOfferteSpeciali dato il nome
	 * 
	 * @param fileName
	 *            nome del file da aprire
	 * @throws FileNotFoundException
	 *             se non trova il file
	 * @throws IOException
	 *             se non trova il file
	 * @throws ClassNotFoundException
	 *             se non riesce a deserializzare
	 */
	public GestoreFileOfferteSpeciali(String fileName)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		super(fileName);
	}

	/**
	 * ritorna la List di offerte speciali
	 * 
	 * @param idSocietą
	 *            id della societą fornitrice
	 * @return la lista di offerte
	 * @throws RicercaFallitaException
	 *             se non ci sono offerte di quella societą
	 * @see OffertaSpeciale SocietąPartnerOfferte
	 */
	public ArrayList<OffertaSpeciale> recupera(int idSocietą)
			throws RicercaFallitaException {
		OffertaSpeciale temp;
		ArrayList<OffertaSpeciale> retrievedBuffer = new ArrayList<OffertaSpeciale>(); // dichiara
																						// un
																						// buffer
																						// temporaneo
		// crea l'iterator per il buffer
		Iterator<OffertaSpeciale> iterator = buffer.iterator();
		// scorre il buffer:se trova l'id cercato in un oggetto,aggiunge
		// l'oggetto al retrievedBuffer
		while (iterator.hasNext()) {
			temp = iterator.next();
			if (temp.getIdSocietą() == idSocietą)
				retrievedBuffer.add(temp);
		}
		// se non ha trovato nessuna OffertaSpeciale di quella societą
		if (retrievedBuffer.isEmpty())
			throw new RicercaFallitaException("Non ci sono offerte");
		return retrievedBuffer;
	}

}