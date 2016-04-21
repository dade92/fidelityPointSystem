package progettogruppo.utenti;

/**
 * Factory per creare i diversi tipi di utenti. riceve in input un oggetto
 * UserType chespecifica il tipo di Utente da creare
 */

public class UtenteFactory {

	/**
	 * metodo che crea i diversi tipi di Utente
	 * 
	 * @param type
	 *            variabile che specifica il tipo di Utente da creare
	 * @param id
	 *            id dell'Utente
	 * @param password
	 *            password dell'Utente
	 * @see UserType
	 * @return Utente creato
	 */
	public Utente creaUtente(UserType type, int id, String password) {
		if (type == UserType.CLIENTE)
			return new Cliente(id, password, "", "", 0);
		else if (type == UserType.SOCIETA_PARTNER_PREMI)
			return new Societ‡PartnerPremi(id, password);
		else if (type == UserType.SOCIETA_PARTNER_OFFERTE)
			return new Societ‡PartnerOfferte(id, password);
		else if (type == UserType.ADDETTO_AL_SUPPORTO)
			return new AddettoAlSupporto(id, password);
		return new Amministratore(id, password);
	}

}