package progettogruppo.premieofferte;

import progettogruppo.utenti.UserType;

/**
 * Factory per creare gli oggetti AbstractPremio riceve in input il tipo di
 * Utente che vuole ottenere l'AbstractPremio,e costruisce l'AbstractPremio
 * appropriato
 */
public class AbstractPremioFactory {

	/**
	 * crea un AbstractPremio sapendo quale tipo di Utente usa l'oggetto
	 * 
	 * @param type
	 *            tipo di Utente che usa l'oggetto
	 * @param costo
	 *            costo dell'AbstractPremio
	 * @return l'AbstractPremio creato
	 * @see AbstractPremio
	 */
	public AbstractPremio creaPremio(UserType type, int costo) {
		if (type == UserType.CLIENTE)
			return new BigliettoPremio(costo, "", "", "", "", "");
		else if (type == UserType.SOCIETA_PARTNER_PREMI)
			return new Premio(costo, "", "", 0, 0);
		return null;
	}

}