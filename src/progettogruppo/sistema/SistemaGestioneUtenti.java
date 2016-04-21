package progettogruppo.sistema;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import progettogruppo.file.GestoreFileUtenti;
import progettogruppo.file.UtenteInesistenteException;
import progettogruppo.premieofferte.*;
import progettogruppo.utenti.*;

/**
 * classe che rappresenta il gestore degli utenti.si occupa del login
 * dell'utente nel sistema e nella gestione dei suoi acquisti premi e
 * richieste di offerte durante la sua permanenza; è un singleton perchè
 * nel sistema ci deve essere un solo utente loggato alla volta
 */
public class SistemaGestioneUtenti {

	private static SistemaGestioneUtenti sistema;

	private Utente utenteCorrente;

	private GestoreFileUtenti data;

	/**
	 * costruisce un SistemaGestioneUtenti
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private SistemaGestioneUtenti() throws FileNotFoundException,
			ClassNotFoundException, IOException {
		data = new GestoreFileUtenti("utenti.dat");
	}

	/**
	 * permette il login dell'Utente
	 * 
	 * @param id
	 *            id dell'Utente
	 * @param password
	 *            password dell'Utente
	 * @return tipo di Utente loggato
	 * @see UserType Utente
	 * @throws UtenteInesistenteException
	 *             se l'Utente non è registrato
	 * @throws OperazioneNonConsentitaException
	 *             se un Utente è già loggato nel sistema e un altro Utente
	 *             cerca di loggarsi
	 */
	public UserType loginUtente(int id, String password)
			throws UtenteInesistenteException, OperazioneNonConsentitaException {
		// non si può fare il login se un Utente è già presente nel sistema
		if (utenteCorrente != null)
			throw new OperazioneNonConsentitaException(
					"c'è già un Utente loggato nel sistema");
		utenteCorrente = data.checkEsistenzaUtente(id, password); // cerca nel
																	// database
		// ritorna lo UserType corretto cosicchè l'interfaccia possa capire
		// quale
		// Utente ha fatto il login
		if (utenteCorrente instanceof Cliente)
			return UserType.CLIENTE;
		if (utenteCorrente instanceof Amministratore)
			return UserType.AMMINISTRATORE;
		else if (utenteCorrente instanceof AddettoAlSupporto)
			return UserType.ADDETTO_AL_SUPPORTO;
		else if (utenteCorrente instanceof SocietàPartnerPremi)
			return UserType.SOCIETA_PARTNER_PREMI;
		return UserType.SOCIETA_PARTNER_OFFERTE;
	}

	/**
	 * fa il logout dell'Utente
	 */
	public void logoutUtente() {
		data.chiudiConnessione(); // chiude la connessione con il file degli
									// utenti
		utenteCorrente = null;
	}

	/**
	 * ritorna l'unica istanza del Sistema
	 * 
	 * @return l'istanza del SistemaGestioneUtenti
	 * @throws FileNotFoundException
	 *             se non trova il file degli utenti
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static SistemaGestioneUtenti getInstance()
			throws FileNotFoundException, ClassNotFoundException, IOException {
		if (sistema == null)
			sistema = new SistemaGestioneUtenti();
		return sistema;
	}

	/**
	 * ritorna l'Utente correntemente loggato nel Sistema
	 * 
	 * @return l'Utente loggato
	 * @throws OperazioneNonConsentitaException
	 *             se non c'è nessun Utente loggato
	 */
	public Utente getUtenteCorrente() throws OperazioneNonConsentitaException {
		if (utenteCorrente == null)
			throw new OperazioneNonConsentitaException("utente non loggato");
		return utenteCorrente;
	}

	/**
	 * setta l'Utente corrente
	 * 
	 * @param utente
	 *            Utente loggato
	 */
	public void setUtenteCorrente(Utente utente) {
		utenteCorrente = utente;
	}
	
	public void setGestoreFileUtenti(GestoreFileUtenti gestore) {
		data=gestore;
	}

	/**
	 * gestisce l'acquisto di un Premio da parte dell'Utente
	 * 
	 * @param p
	 *            Premio da acquistare
	 * @see Premio
	 * @throws OperazioneNonConsentitaException
	 *             se l'Utente non ha abbastanza punti
	 */
	public void compraPremio(AbstractPremio p)
			throws OperazioneNonConsentitaException {
		if (!(utenteCorrente instanceof Cliente)) // controllla che sia un
													// Cliente
			throw new OperazioneNonConsentitaException("Errore");
		Cliente clienteCorrente = (Cliente) utenteCorrente; // esegue il cast
		int costo = p.getCosto(); // recupera il costo dell'AbstractPremio
		if (clienteCorrente.getPuntiTotali() > costo) { // se il Cliente ha
														// abbastanza soldi
			if (p instanceof Premio) { // controlla se è un Premio
				Premio premio = (Premio) p;
				clienteCorrente.aggiornaPunti(-costo);
				clienteCorrente.aggiungiPremio(premio.toString());
				premio.decrementaQuantità();
			} else if (p instanceof BigliettoPremio) { // controlla se è un
														// BigliettoPremio
				clienteCorrente.aggiornaPunti(-costo);
				clienteCorrente.aggiungiPremio(p.toString());
			}
		} else
			throw new SaldoInsufficenteException("Saldo insufficiente");
	}

	/**
	 * gestisce la richiesta di un OffertaSpeciale da parte dell'Utente
	 * 
	 * @param o
	 *            OffertaSpeciale richiesta
	 * @see OffertaSpeciale
	 * @throws OperazioneNonConsentitaException
	 *             se l'Utente che invoca il metodo non è un Cliente
	 */
	public void richiediOfferta(OffertaSpeciale o)
			throws OperazioneNonConsentitaException {
		if (!(utenteCorrente instanceof Cliente))
			throw new OperazioneNonConsentitaException("Errore");
		Cliente clienteCorrente = (Cliente) utenteCorrente;
		clienteCorrente.setOffertaRichiesta(o);
	}

	/**
	 * genera un id casuale
	 * 
	 * @return l'id generato
	 * @throws ClassNotFoundException
	 * @throws IOException
	 *             se non trova il file degli utenti
	 */
	public int generaId() throws ClassNotFoundException, IOException {
		Random random = new Random();
		// recupera la lista degli Utenti registrati
		ArrayList<Utente> utentiRegistrati = data.recupera();
		// recupera l'ultimo Utente:gli Utenti sono memorizzati per semplicità
		// dall'id piu basso al piu alto
		Utente u = utentiRegistrati.get(utentiRegistrati.size() - 1);
		// prende il suo id
		int idMin = u.getId();
		// genera id casuale,sapendo che deve essere compreso tra idMin e
		// idMin+5
		return (idMin + 1 + random.nextInt(5));
	}

	/**
	 * crea un Memento che si occuperà di aggiornare il file di log
	 * 
	 * @param log
	 *            String da scrivere sul log
	 * @return il Memento
	 * @see Memento
	 * @throws IOException
	 *             se non trova il file dei log
	 */
	public Memento creaMemento(String log) throws IOException {
		return new Memento(log, utenteCorrente);
	}

}