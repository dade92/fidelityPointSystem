package progettogruppo.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import progettogruppo.utenti.Cliente;
import progettogruppo.utenti.Utente;

/**
 * classe che gestisce il file degli utenti si occupa di recuperare la List
 * degli utenti registrati nel sistema
 * 
 * @see Utente
 */
public class GestoreFileUtenti extends GestoreFile<Utente> {

	/**
	 * costruisce un nuovoGestoreFileUtenti
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
	public GestoreFileUtenti(String fileName) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		super(fileName);
	}

	/**
	 * ritorna l'Utente registrato
	 * 
	 * @param id
	 *            id dell'Utente
	 * @param password
	 *            password dell'Utente
	 * @return l'Utente loggato
	 * @throws UtenteInesistenteException
	 *             se l'Utente non è presente nel file
	 * @see Utente
	 */
	public Utente checkEsistenzaUtente(int id, String password)
			throws UtenteInesistenteException {

		Iterator<Utente> iterator = buffer.iterator();
		Utente temp;

		while (iterator.hasNext()) { // scorre l'ArrayList
			temp = iterator.next(); // recupera l'Utente
			// se l'id e la password sono uguali
			if (temp.getId() == id && temp.getPassword().equals(password))
				return temp; // ritorna l'Utente
		}
		// se non lo trova solleva un'eccezione
		throw new UtenteInesistenteException("Credenziali scorrette");
	}

	/**
	 * cerca il Cliente con l'id specificato
	 * 
	 * @param id
	 *            id del Cliente da cercare
	 * @return il Cliente
	 * @throws UtenteInesistenteException
	 *             se non trova il Cliente
	 * @see Utente
	 */
	public Cliente retrieveCliente(int id) throws UtenteInesistenteException {
		Iterator<Utente> iterator = buffer.iterator();
		Utente temp;

		while (iterator.hasNext()) {
			temp = iterator.next(); // recupera l'Utente
			if (temp instanceof Cliente && temp.getId() == id) // se l'Utente è
																// un Cliente
																// con quell'id
				return (Cliente) temp; // ritorna il Cliente
		}
		// altrimenti lancia un'eccezione perchè non ha trovato il Cliente
		throw new UtenteInesistenteException("Utente non trovato");

	}

	/**
	 * elimina l'Utente specificato
	 * 
	 * @param id
	 *            id dell'Utente che si vuole eliminare
	 * @throws UtenteInesistenteException
	 *             se non trova l'Utente
	 */
	public void elimina(int idUtente) throws UtenteInesistenteException {
		Iterator<Utente> iterator = (Iterator<Utente>) buffer.iterator();
		// scorre il buffer
		while (iterator.hasNext()) {
			if (iterator.next().getId() == idUtente) { // se trova l'id
				iterator.remove(); // rimuove l'elemento corrente
				return;
			}
		}
		throw new UtenteInesistenteException("Utente non trovato");
	}

}