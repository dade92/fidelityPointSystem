package progettogruppo.file;

/**
 * lanciata per indicare che l'offerta non è stata trovata
 * 
 * @see OffertaSpeciale
 */
@SuppressWarnings("serial")
public class OffertaNonTrovataException extends RicercaFallitaException {

	/**
	 * costruisce l'eccezione con il messaggio d'errore
	 * 
	 * @param message
	 */
	public OffertaNonTrovataException(String message) {
		super(message);
	}

}
