package progettogruppo.premieofferte;

import java.io.Serializable;

/**
 * superclasse per gli oggetti Premio e BigliettoPremio dichiara l'attributo in
 * comune costoAbstractPremio e le sue sottoclassi definiscono un long int
 * serialVersionUIDper assicurare che la deserializzazione avvenga con successo
 */
public abstract class AbstractPremio implements Serializable {

	private static final long serialVersionUID = 1L;
	private int costo;

	/**
	 * costruisce un AbstractPremio con il costo in punti
	 * 
	 * @param costo
	 */
	public AbstractPremio(int costo) {
		setCosto(costo);
	}

	/**
	 * ritorna il costo dell'AbstractPremio
	 * 
	 * @return costo
	 */
	public int getCosto() {
		return costo;
	}

	/**
	 * setta il costo
	 * 
	 * @param costo
	 *            costo
	 */
	public void setCosto(int costo) {
		this.costo = costo;
	}

	/**
	 * ritorna la rappresentazione in formato stringa dell'oggetto
	 * 
	 * @Overrides toString nella classe Object
	 * @return la String dell'oggetto
	 */
	public String toString() {
		return String.format("prezzo:%d;\n", getCosto());
	}

}