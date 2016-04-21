package progettogruppo.utenti;

/**
 * classe che rappresenta gli addetti al supporto
 */
public class AddettoAlSupporto extends Utente {

	private static final long serialVersionUID = 6L;

	/**
	 * costruisce un AddettoalSupporto con id e password
	 * 
	 * @param id
	 *            id
	 * @param psw
	 *            password
	 */
	public AddettoAlSupporto(int id, String psw) {
		super(id, psw);
	}

	/**
	 * ritorna la rappresentazione in formato stringa dell'oggetto
	 * 
	 * @Overrides toString nella classe Utente
	 * @return la String dell'oggetto
	 */
	public String toString() {
		return String.format("Addetto al supporto:\n%s", super.toString());
	}

}