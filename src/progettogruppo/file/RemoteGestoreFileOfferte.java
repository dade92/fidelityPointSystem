package progettogruppo.file;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import progettogruppo.premieofferte.OffertaSpeciale;

/**
 * interfaccia remota utilizzata dalle società partner offerte che si connettono
 * da remoto
 * 
 */
public interface RemoteGestoreFileOfferte extends Remote {

	/**
	 * aggiunge una offerta speciale
	 * 
	 * @param object
	 *            l'OffertaSpeciale da aggiungere
	 * @throws RemoteException
	 *             se ci sono problemi di connessione
	 * @see OffertaSpeciale
	 */
	void aggiungi(OffertaSpeciale object) throws RemoteException;

	/**
	 * elimina l'offerta i-esima
	 * 
	 * @param index
	 *            indice della offerta da eliminare
	 * @throws RicercaFallitaException
	 *             se l'indice è sbagliato
	 * @throws RemoteException
	 *             se ci sono problemi di connessione
	 */

	void elimina(int index) throws RicercaFallitaException, RemoteException;

	/**
	 * recupera la lista di offerte inserite dalla società
	 * 
	 * @param id
	 *            id della società partner fornitrice
	 * @return List di offerte
	 * @throws RicercaFallitaException
	 *             se non trova nessuna offerta della società
	 * @throws RemoteException
	 *             se ci sono problemi di connessione
	 * @see OffertaSpeciale
	 */
	ArrayList<OffertaSpeciale> recupera(int id) throws RicercaFallitaException,
			RemoteException;
	
	/**
	 * chiude la connessione con il file delle offerte
	 * @throws RemoteException se ha problemi di connessione remota
	 * @throws IOException se non riesce a chiudere gli stream
	 */
	void chiudiConnessione() throws RemoteException,IOException;

}
