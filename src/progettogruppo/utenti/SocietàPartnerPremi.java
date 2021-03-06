package progettogruppo.utenti;

/**
 * classe che rappresenta le societą partner premi
 */

public class SocietąPartnerPremi extends Utente {

	private static final long serialVersionUID = 6L;

	/**
	 * costruisce una SocietąPartnerPremi con id e password
	 * 
	 * @param id
	 *            id dell'Utente
	 * @param psw
	 *            password dell'Utente
	 */
	public SocietąPartnerPremi(int id, String psw) {
		super(id, psw);
	}

	/**
	 * ritorna la rappresentazione in formato stringa dell'oggetto
	 * 
	 * @Overrides toString nella classe Utente
	 * @return la String dell'oggetto
	 */
	public String toString() {
		return String.format("Societą partner premi:\n%s", super.toString());
	}

}