package progettogruppo.utenti;

/**
 * classe che rappresenta le societ‡ partner premi
 */

public class Societ‡PartnerPremi extends Utente {

	private static final long serialVersionUID = 6L;

	/**
	 * costruisce una Societ‡PartnerPremi con id e password
	 * 
	 * @param id
	 *            id dell'Utente
	 * @param psw
	 *            password dell'Utente
	 */
	public Societ‡PartnerPremi(int id, String psw) {
		super(id, psw);
	}

	/**
	 * ritorna la rappresentazione in formato stringa dell'oggetto
	 * 
	 * @Overrides toString nella classe Utente
	 * @return la String dell'oggetto
	 */
	public String toString() {
		return String.format("Societ‡ partner premi:\n%s", super.toString());
	}

}