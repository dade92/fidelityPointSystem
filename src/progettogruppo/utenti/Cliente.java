package progettogruppo.utenti;

import java.util.ArrayList;

import progettogruppo.premieofferte.OffertaSpeciale;

/**
 * classe che rappresenta un Cliente del sistema tiene traccia dei punti
 * acquisiti del nome e cognome,dei premi acquistati e l'ultima offerta
 * richiesta dal Cliente
 */
public class Cliente extends Utente {

	private static final long serialVersionUID = 6L;

	private int puntiTotali;

	private String nome;

	private String cognome;

	private ArrayList<String> premiAcquistati;

	private OffertaSpeciale offertaRichiesta;

	/**
	 * costruisce un Cliente con id password nome cognome e punti
	 * 
	 * @param id
	 *            id dell'Utente
	 * @param psw
	 *            password dell'Utente
	 * @param nome
	 *            nome del Cliente
	 * @param cognome
	 *            cognome del Cliente
	 * @param punti
	 *            punti totali acquisiti dal Cliente
	 */
	public Cliente(int id, String psw, String nome, String cognome, int punti) {
		super(id, psw);
		setNome(nome);
		setCognome(cognome);
		setPuntiTotali(punti);
		premiAcquistati = new ArrayList<String>();
	}

	/**
	 * costruisce un Cliente con id password nome cognome punti nome dei premi
	 * acquistati e offerta richiesta
	 * 
	 * @param id
	 *            id dell'Utente
	 * @param psw
	 *            password dell'Utente
	 * @param nome
	 *            nome del Cliente
	 * @param cognome
	 *            cognome del Cliente
	 * @param punti
	 *            punti totali acquisiti dal Cliente
	 * @param premi
	 *            premi acquisiti dal Cliente
	 * @param o
	 *            offertaSpeciale richiesta
	 * @see Premio OffertaSpeciale
	 */
	public Cliente(int id, String psw, String nome, String cognome, int punti,
			ArrayList<String> premi, OffertaSpeciale o) {
		super(id, psw);
		setNome(nome);
		setCognome(cognome);
		setPuntiTotali(punti);
		setPremiAcquistati(premi);
		setOffertaRichiesta(o);
	}

	/**
	 * setta i premi acquistati
	 * 
	 * @param premi
	 *            premi acquistati
	 */
	public void setPremiAcquistati(ArrayList<String> premi) {
		premiAcquistati = premi;
	}

	/**
	 * aggiorna i punti
	 * 
	 * @param saldo
	 *            punti da sommare/sottrarre
	 */
	public void aggiornaPunti(int saldo) {
		this.puntiTotali += saldo;
	}

	/**
	 * ritorna il nome di tutti i premi acquistati
	 * 
	 * @return premi acquistati dal Cliente
	 */
	public ArrayList<String> getPremiAcquistati() {
		return premiAcquistati;
	}

	/**
	 * aggiunge il nome del Premio acquistato
	 * 
	 * @param p
	 *            nome del Premio acquistato
	 */
	public void aggiungiPremio(String p) {
		premiAcquistati.add(p);
	}

	/**
	 * ritorna i punti del Cliente
	 * 
	 * @return punti totali del Cliente
	 */
	public int getPuntiTotali() {
		return puntiTotali;
	}

	/**
	 * setta i punti del Cliente
	 * 
	 * @param puntiTotali
	 */
	public void setPuntiTotali(int puntiTotali) {
		if (puntiTotali > 0)
			this.puntiTotali = puntiTotali;
	}

	/**
	 * ritorna il nome del Cliente
	 * 
	 * @return nome del Cliente
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * setta il nome del Cliente
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * ritorna il cognome
	 * 
	 * @return cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * setta il cognome
	 * 
	 * @param cognome
	 *            cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * ritorna l'ultima offerta richiesta
	 * 
	 * @return l'offertaSpeciale richiesta
	 * @see OffertaSpeciale
	 */
	public OffertaSpeciale getOffertaRichiesta() {
		return offertaRichiesta;
	}

	/**
	 * setta l'offerta richiesta
	 * 
	 * @param offertaRichiesta
	 *            offerta richiesta
	 * @see OffertaSpeciale
	 */
	public void setOffertaRichiesta(OffertaSpeciale offertaRichiesta) {
		this.offertaRichiesta = offertaRichiesta;
	}

	/**
	 * ritorna la rappresentazione in formato stringa dell'oggetto
	 * 
	 * @Overrides toString nella classe Utente
	 * @return la String dell'oggetto
	 */
	@Override
	public String toString() {
		String temp = String.format(
				"Cliente:\n%snome:%s\ncognome:%s\npunti totali:%d\n\n"
						+ "Premi acquistati:\n", super.toString(), this.nome,
				this.cognome, this.puntiTotali);

		for (int i = 0; i < this.premiAcquistati.size(); i++) {
			temp += premiAcquistati.get(i);
			temp += "\n";
		}
		temp += "Ultima offerta richiesta:\n";
		if (offertaRichiesta != null)
			temp += offertaRichiesta;
		else
			temp += "";
		return temp;
	}

}