package progettogruppo.file;

/**
 * lanciata per indicare che il Premio non � stato trovato
 * 
 * @see Premio
 */
@SuppressWarnings("serial")
public class PremioNonTrovatoException extends RicercaFallitaException {

	public PremioNonTrovatoException(String message) {
		super(message);
	}

}
