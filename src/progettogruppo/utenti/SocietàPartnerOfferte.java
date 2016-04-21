package progettogruppo.utenti;

/**
 * classe che rappresenta le societ‡ partner offerte
 */

public class Societ‡PartnerOfferte extends Utente {

	private static final long serialVersionUID = 6L;

	/**
	 * costruisce una Societ‡PartnerOfferte con id e password
	 * 
	 * @param id
	 *            id
	 * @param psw
	 *            password
	 */
	public Societ‡PartnerOfferte(int id, String psw) {
		super(id, psw);
	}

	/**
	 * ritorna la rappresentazione in formato stringa dell'oggetto
	 * 
	 * @Overrides toString nella classe Utente
	 * @return la String dell'oggetto
	 */
	public String toString() {
		return String.format("Societ‡ partner offerte:\n%s", super.toString());
	}

}