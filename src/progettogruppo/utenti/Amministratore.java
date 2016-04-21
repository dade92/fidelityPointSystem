package progettogruppo.utenti;

/**
 * classe che rappresenta gli amministratori di sistema
 */

public class Amministratore extends Utente {

	private static final long serialVersionUID = 6L;

	/**
	 * costruisce un Amministratore con id e password
	 * 
	 * @param id
	 *            id
	 * @param psw
	 *            password
	 */
	public Amministratore(int id, String psw) {
		super(id, psw);
	}

	/**
	 * ritorna la rappresentazione in formato stringa dell'oggetto
	 * 
	 * @Overrides toString nella classe Utente
	 * @return la String dell'oggetto
	 */
	public String toString() {
		return String.format("Amministratore:\n%s", super.toString());
	}

}