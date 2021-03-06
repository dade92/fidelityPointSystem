package progettogruppo.premieofferte;

import java.io.Serializable;

/**
 * classe che rappresenta le offerte delle societą partner.definisce un long int
 * serialVersionUID per deserializzare in modo corretto
 * 
 * @see progettogruppo.utenti.SocietąPartnerOfferte
 */
public class OffertaSpeciale implements Serializable {

	private static final long serialVersionUID = 2L;

	private String nome;

	private String descrizione;

	private int idSocietą;

	/**
	 * costruisce una OffertaSpeciale
	 * 
	 * @param nome
	 *            nome
	 * @param desc
	 *            descrizione
	 * @param id
	 *            id societą partner fornitrice
	 */
	public OffertaSpeciale(String nome, String desc, int id) {
		setNome(nome);
		setDescrizione(desc);
		setIdSocietą(id);
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
	 * ritorna l'id della societą
	 * 
	 * @return id societą
	 */
	public int getIdSocietą() {
		return idSocietą;
	}

	/**
	 * setta l'id della societą
	 * 
	 * @param idSocietą
	 *            id
	 */
	public void setIdSocietą(int idSocietą) {
		this.idSocietą = idSocietą;
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

		// se due offerte speciali hanno stesso nome e stesso id societą
		if (this.nome.equals(temp.getNome())
				&& this.idSocietą == temp.getIdSocietą())
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
				" nome:%s;\n descrizione:%s;\n id societą proprietaria:%s;\n",
				this.nome, this.descrizione, this.idSocietą);
	}

}