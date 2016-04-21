package progettogruppo.file;

/**
 * lanciata per indicare che una generica ricerca è fallita
 * 
 */
@SuppressWarnings("serial")
public class RicercaFallitaException extends Exception {

	/**
	 * costruisce l'oggetto con il messaggio d'errore
	 * 
	 * @param message
	 *            messaggio d'errore
	 */
	public RicercaFallitaException(String message) {
		super(message);
	}
}
