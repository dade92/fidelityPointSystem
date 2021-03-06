package progettogruppo.utenti;

/**
 * classe che rappresenta le societą partner offerte
 */

public class SocietąPartnerOfferte extends Utente {

	private static final long serialVersionUID = 6L;

	/**
	 * costruisce una SocietąPartnerOfferte con id e password
	 * 
	 * @param id
	 *            id
	 * @param psw
	 *            password
	 */
	public SocietąPartnerOfferte(int id, String psw) {
		super(id, psw);
	}

	/**
	 * ritorna la rappresentazione in formato stringa dell'oggetto
	 * 
	 * @Overrides toString nella classe Utente
	 * @return la String dell'oggetto
	 */
	public String toString() {
		return String.format("Societą partner offerte:\n%s", super.toString());
	}

}