package progettogruppo.sistema;

/**
 * eccezione sollevata quando il Cliente tenta di acquistare qualcosa senza
 * avere abbastanza punti
 */
@SuppressWarnings("serial")
public class SaldoInsufficenteException extends
		OperazioneNonConsentitaException {

	/**
	 * costruttore che riceve il messaggio d'errore
	 * 
	 * @param message
	 *            messaggio
	 */
	public SaldoInsufficenteException(String message) {
		super(message);
	}

}
