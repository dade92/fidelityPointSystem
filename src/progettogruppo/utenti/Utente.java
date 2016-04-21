package progettogruppo.utenti;

import java.io.Serializable;

/**
 * classe astratta che rappresenta tutti i tipi di utente del sistema Utente e
 * ogni sottoclasse definisce un long int serialVersion per assicurare che la
 * deserializzazione avvenga con successo
 */

public abstract class Utente implements Serializable {

	private static final long serialVersionUID = 5L;

	private int id;

	private String password;

	/**
	 * costruisce un Utente con id e password
	 * 
	 * @param id
	 *            id dell'Utente
	 * @param password
	 *            password dell'Utente
	 */
	public Utente(int id, String password) {
		setId(id);
		setPassword(password);
	}

	/**
	 * ritorna l'id
	 * 
	 * @return id id
	 */
	public int getId() {
		return id;
	}

	/**
	 * setta l'id
	 * 
	 * @param id
	 *            id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * ritorna la password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * setta la password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ritorna la rappresentazione in formato stringa dell'oggetto
	 * 
	 * @Overrides toString di Object
	 * @return la String dell'oggetto
	 */
	public String toString() {
		return String.format("id:%d\npassword:%s\n", this.id, this.password);
	}

	/**
	 * esegue il confronto tra due oggetti Utente e dice se sono uguali
	 * 
	 * @return true se sono uguali,false altrimenti
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Utente))
			return false;

		// fa casting dell'oggetto
		Utente u = (Utente) o;
		// equals controlla l'id e la password dell'Utente.
		if (id == u.getId() && password == u.getPassword())
			return true;
		return false;
	}

}