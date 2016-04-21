package progettogruppo.premieofferte;

import java.io.Serializable;

/**
 * classe che rappresenta le offerte delle societ‡ partner.definisce un long int
 * serialVersionUID per deserializzare in modo corretto
 * 
 * @see progettogruppo.utenti.Societ‡PartnerOfferte
 */
public class OffertaSpeciale implements Serializable {

	private static final long serialVersionUID = 2L;

	private String nome;

	private String descrizione;

	private int idSociet‡;

	/**
	 * costruisce una OffertaSpeciale
	 * 
	 * @param nome
	 *            nome
	 * @param desc
	 *            descrizione
	 * @param id
	 *            id societ‡ partner fornitrice
	 */
	public OffertaSpeciale(String nome, String desc, int id) {
		setNome(nome);
		setDescrizione(desc);
		setIdSociet‡(id);
	}

	/**
	 * ritorna il nome
	 * 
	 * @return nome dell'OffertaSpeciale
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * setta il nome
	 * 
	 * @param nome
	 *            nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * ritorna la descrizione
	 * 
	 * @return descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * setta la descrizione
	 * 
	 * @param descrizione
	 *            descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * ritorna l'id della societ‡
	 * 
	 * @return id societ‡
	 */
	public int getIdSociet‡() {
		return idSociet‡;
	}

	/**
	 * setta l'id della societ‡
	 * 
	 * @param idSociet‡
	 *            id
	 */
	public void setIdSociet‡(int idSociet‡) {
		this.idSociet‡ = idSociet‡;
	}

	/**
	 * confronta due oggetti OffertaSpeciale
	 * 
	 * @Overrides equals nella classe Object
	 * @return true se sono uguali,false altrimenti
	 */

	public boolean equals(Object ob) {
		if (!(ob instanceof OffertaSpeciale))
			return false;

		OffertaSpeciale temp = (OffertaSpeciale) ob;

		// se due offerte speciali hanno stesso nome e stesso id societ‡
		if (this.nome.equals(temp.getNome())
				&& this.idSociet‡ == temp.getIdSociet‡())
			return true; // allora ritorna vero
		return false; // altrimenti falso
	}

	/**
	 * ritorna la rappresentazione in formato stringa dell'oggetto
	 * 
	 * @Overrides toString nella classe Object
	 * @return la String dell'oggetto
	 */
	public String toString() {
		return String.format(
				" nome:%s;\n descrizione:%s;\n id societ‡ proprietaria:%s;\n",
				this.nome, this.descrizione, this.idSociet‡);
	}

}