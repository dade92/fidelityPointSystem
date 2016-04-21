package progettogruppo.file;

/**
 * lanciata per indicare che l'Utente cercato non è presente nel file
 * 
 */
@SuppressWarnings("serial")
public class UtenteInesistenteException extends RicercaFallitaException {

	/**
	 * costruisce l'oggetto con il messaggio d'errore
	 * 
	 * @param message
	 *            messaggio d'errore
	 */
	public UtenteInesistenteException(String message) {
		super(message);
	}

}
