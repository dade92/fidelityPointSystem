package progettogruppo.premieofferte;

/**
 * classe che rappresenta i biglietti premio erogati dal sistema di ticketing
 * 
 * @see progettogruppo.sistematicketing.SistemaTicketingImpl
 */
public class BigliettoPremio extends AbstractPremio {

	private static final long serialVersionUID = 4L;

	private String partenza;

	private String arrivo;

	private String data;

	private String fasciaDistanza;

	private String classe;

	/**
	 * costruisce un BigliettoPremio
	 * 
	 * @param c
	 *            costo
	 * @param p
	 *            luogo di partenza
	 * @param a
	 *            luogo di arrivo
	 * @param d
	 *            data
	 * @param f
	 *            fascia di distanza
	 * @param cle
	 *            classe del Biglietto aereo
	 */
	public BigliettoPremio(int c, String p, String a, String d, String f,
			String cle) {
		super(c);
		setPartenza(p);
		setArrivo(a);
		setData(d);
		setFasciaDistanza(f);
		setClasse(cle);
	}

	/**
	 * ritorna il luogo di partenza
	 * 
	 * @return nome della partenza
	 */
	public String getPartenza() {
		return partenza;
	}

	/**
	 * setta il luogo di partenza
	 * 
	 * @param partenza
	 *            luogo di partenza
	 */
	public void setPartenza(String partenza) {
		this.partenza = partenza;
	}

	/**
	 * ritorna il luogo di arrivo
	 * 
	 * @return luogo di arrivo
	 */
	public String getArrivo() {
		return arrivo;
	}

	/**
	 * setta il luogo di arrivo
	 * 
	 * @param arrivo
	 *            luogo di arrivo
	 */
	public void setArrivo(String arrivo) {
		this.arrivo = arrivo;
	}

	/**
	 * ritorna la data
	 * 
	 * @return data
	 */
	public String getData() {
		return data;
	}

	/**
	 * setta la data
	 * 
	 * @param data
	 *            data
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * ritorna la fascia di distanza
	 * 
	 * @return fascia di distanza
	 */
	public String getFasciaDistanza() {
		return fasciaDistanza;
	}

	/**
	 * setta la fascia di distanza
	 * 
	 * @param fasciaDistanza
	 *            fascia di distanza
	 */
	public void setFasciaDistanza(String fasciaDistanza) {
		this.fasciaDistanza = fasciaDistanza;
	}

	/**
	 * ritorna la classe del biglietto aereo
	 * 
	 * @return classe
	 */
	public String getClasse() {
		return classe;
	}

	/**
	 * setta la classe del biglietto
	 * 
	 * @param classe
	 *            classe
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}

	/**
	 * ritorna la rappresentazione in formato stringa dell'oggetto
	 * 
	 * @Overrides toString nella classe AbstractPremio
	 * @return la String dell'oggetto
	 */
	@Override
	public String toString() {
		return String.format(
				"Biglietto aereo:\npartenza:%s\narrivo:%s\ndata:%s\n"
						+ "fascia di distanza:%s\nclasse:%s\n%s",
				this.partenza, this.arrivo, this.data, this.fasciaDistanza,
				this.classe, super.toString());
	}

}