package progettogruppo.premieofferte;

/**
 * classe che rappresenta i premi forniti dalle societ‡ partner
 * 
 * @see progettogruppo.utenti.Societ‡PartnerPremi
 */
public class Premio extends AbstractPremio {

	private static final long serialVersionUID = 3L;

	private String nome;

	private String descrizione;

	private int quantit‡;

	private int idSociet‡;

	/**
	 * costruisce un Premio
	 * 
	 * @param costo
	 *            costo
	 * @param nome
	 *            nome
	 * @param descrizione
	 *            descrizione
	 * @param quantit‡
	 *            numero di premi di questo tipo disponibile
	 * @param id
	 *            id societ‡ partner fornitrice
	 */
	public Premio(int costo, String nome, String descrizione, int quantit‡,
			int id) {
		super(costo);
		setNome(nome);
		setDescrizione(descrizione);
		setQuantit‡(quantit‡);
		setIdSociet‡(id);
	}

	/**
	 * setta l'id della societ‡ fornitrice
	 * 
	 * @param id
	 *            id societ‡
	 */
	public void setIdSociet‡(int id) {
		this.idSociet‡ = id;
	}

	/**
	 * ritorna l'id della societ‡
	 * 
	 * @return id
	 */
	public int getIdSociet‡() {
		return idSociet‡;
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
	 * ritorna la descrizione
	 * 
	 * @return descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * setta il nome
	 * 
	 * @param n
	 *            nome
	 */
	public void setNome(String n) {
		this.nome = n;
	}

	/**
	 * ritorna il nome
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * setta la quantit‡ disponibile
	 * 
	 * @param q
	 *            quantit‡
	 */
	public void setQuantit‡(int q) {
		this.quantit‡ = q;
	}

	/**
	 * ritorna la quantit‡
	 * 
	 * @return quantit‡
	 */
	public int getQuantit‡() {
		return this.quantit‡;
	}

	/**
	 * decrementa di uno la quantit‡ del Premio
	 */
	public void decrementaQuantit‡() {
		quantit‡--;
	}

	/**
	 * confronta due oggetti di tipo Premio e dice se sono uguali
	 * 
	 * @return true se sono uguali false altrimenti
	 */
	public boolean equals(Object p) {
		if (!(p instanceof Premio))
			return false;
		Premio temp = (Premio) p;

		// due oggetti Premio sono uguali se hanno stesso nome,costo,quantit‡,e
		// id societ‡ fornitrice
		if (getCosto() == temp.getCosto() && this.nome.equals(temp.getNome())
				&& this.idSociet‡ == temp.getIdSociet‡())
			return true;
		return false;
	}

	/**
	 * ritorna la rappresentazione in formato stringa dell'oggetto
	 * 
	 * @Overrides toString nella classe AbstractPremio
	 * @return la String dell'oggetto
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format(" nome:%s;\n descrizione:%s;\n "
				+ " quantit‡ disponibile:%d;\n"
				+ " id societ‡ partner fornitrice:%d\n %s", this.nome,
				this.descrizione, this.quantit‡, this.idSociet‡,
				super.toString());
	}

}