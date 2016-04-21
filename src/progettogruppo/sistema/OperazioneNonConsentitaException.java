package progettogruppo.sistema;

/**
 * eccezione sollevata quando si cerca di fare una qualsiasi operazione non
 * valida nel sistema (ad esempio un acquisto senza avere punti o senza essersi
 * registrati)
 */
@SuppressWarnings("serial")
public class OperazioneNonConsentitaException extends Exception {

	/**
	 * costruttore che riceve il messaggio d'errore
	 * 
	 * @param message
	 *            messaggio
	 */
	public OperazioneNonConsentitaException(String message) {
		super(message);
	}

}
