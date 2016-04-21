package progettogruppo.sistema;

import java.io.FileWriter;
import java.io.IOException;

import progettogruppo.utenti.Utente;

/**
 * classe Memento che scrive i log di sistema senza violare l'incapsulamento
 */
public class Memento {

	private String log;
	private Utente utenteCorrente;
	private FileWriter writer;

	/**
	 * costruisce un nuovo oggetto Memento con stringa da scrivere,Utente
	 * loggato
	 * 
	 * @param log
	 *            String da scrivere
	 * @param utente
	 *            Utente corrente
	 * @throws IOException
	 *             se non trova il file dei log
	 */
	public Memento(String log, Utente utente) throws IOException {
		setLog(log);
		setUtenteCorrente(utente);
		writer = new FileWriter("log.txt", true);
	}

	/**
	 * appende al file di testo la stringa log
	 * 
	 * @throws IOException
	 *             se non trova il file dei log
	 */
	public void update() throws IOException {
		writer.append(String.format("utente:%d, operazione:%s",
				utenteCorrente.getId(), log + "\n"));
		writer.close();
	}

	/**
	 * restituisce la stringa
	 * 
	 * @return la stringa
	 */
	public String getLog() {
		return log;
	}

	/**
	 * setta la stringa da scrivere
	 * 
	 * @param log
	 */
	public void setLog(String log) {
		this.log = log;
	}

	/**
	 * ritorna l'Utente corrente
	 * 
	 * @return l'Utente
	 */
	public Utente getUtenteCorrente() {
		return utenteCorrente;
	}

	/**
	 * setta l'Utente corrente
	 * 
	 * @param utenteCorrente
	 *            Utente loggato
	 */
	public void setUtenteCorrente(Utente utenteCorrente) {
		this.utenteCorrente = utenteCorrente;
	}

}
