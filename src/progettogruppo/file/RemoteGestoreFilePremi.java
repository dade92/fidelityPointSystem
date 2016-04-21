package progettogruppo.file;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import progettogruppo.premieofferte.Premio;

/**
 * interfaccia lato server per le società
 * partner premi
 * 
 */
public interface RemoteGestoreFilePremi extends Remote {

	/**
	 * aggiunge un premio
	 * 
	 * @param object
	 *            premio da aggiungere
	 * @throws RemoteException
	 *             se ci sono problemi di connessione
	 * @throws IOException
	 *             se non riesce a aggiornare il file
	 */
	void aggiungi(Premio object) throws RemoteException, IOException;

	/**
	 * elimina un premio
	 * 
	 * @param index
	 *            indice del premio
	 * @throws RicercaFallitaException
	 *             se l'indice è sbagliato
	 * @throws RemoteException
	 *             se ci sono problemi di connessione
	 * @throws IOException
	 *             se non riesce a aggiornare il file
	 */
	void elimina(int index) throws RicercaFallitaException, RemoteException,
			IOException;

	/**
	 * recupera i premi
	 * 
	 * @param id
	 *            id della società
	 * @return una List di premi
	 * @throws RicercaFallitaException
	 *             se non ha premi
	 * @throws RemoteException
	 *             se ci sono problemi di connessione
	 */
	ArrayList<Premio> recupera(int id) throws RicercaFallitaException,
			RemoteException;
	
	/**
	 * chiude la connessione con il file dei premi
	 * @throws RemoteException se ha problemi di connessione remota
	 * @throws IOException se non riesce a chiudere gli stream
	 */
	void chiudiConnessione() throws RemoteException,IOException;

}
