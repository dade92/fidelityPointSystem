package progettogruppo.sistematicketing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import progettogruppo.premieofferte.AbstractPremioFactory;
import progettogruppo.premieofferte.BigliettoPremio;
import progettogruppo.utenti.UserType;

/**
 * Sistema esterno che gestisce la creazione
 * di Biglietti aerei e la gestione dei costi
 * recupera da file una matrice contenente i costi di ogni
 * categoria di biglietto premio.dichiara anche un array di stringhe
 * costanti,uno per i nomi delle classi di biglietto e uno per le fascie di
 * distanza.invocabile da remoto
 */
@SuppressWarnings("serial")
public class SistemaTicketingImpl extends UnicastRemoteObject implements
		SistemaTicketing {

	private static final String classi[] = { "Economy", "EconomyPlus", "Prima",
			"Businnes" };
	private static final String fascie[] = { "Nazionale", "Continentale",
			"Intercontinentale" };
	private int costi[][];

	/**
	 * costruisce un nuovo sistema di ticketing
	 * 
	 * @throws FileNotFoundException
	 *             se non trova il file dei costi
	 * @throws IOException
	 *             se non trova il file dei costi
	 * @throws ClassNotFoundException
	 *             se non trova il file dei costi
	 * @throws RemoteException
	 *             se il client non riesce a invocare il metodo
	 */
	public SistemaTicketingImpl() throws FileNotFoundException, IOException,
			ClassNotFoundException, RemoteException {
		costi = new int[classi.length][3];
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(
				"costiBiglietto.dat"));
		costi = (int[][]) input.readObject();
	}

	/**
	 * genera un BigliettoPremio
	 * 
	 * @param data
	 *            data del BigliettoPremio
	 * @param partenza
	 *            luogo di partenza
	 * @param arrivo
	 *            luogo di arrivo
	 * @param classe
	 * @param fasciaDistanza
	 * @see BigliettoPremio
	 * @return BigliettoPremio richiesto
	 */
	public BigliettoPremio creaBigliettoPremio(String data, String partenza,
			String arrivo, int classe, int fasciaDistanza) {
		AbstractPremioFactory factory = new AbstractPremioFactory();
		// la factory crea il BigliettoPremo
		BigliettoPremio biglietto = (BigliettoPremio) factory.creaPremio(
				UserType.CLIENTE, costi[classe][fasciaDistanza]);
		// setto i parametri del BigliettoPremio che la factory non ha settato
		// perchè crea oggetti AbstractPremio generici
		biglietto.setData(data);
		biglietto.setArrivo(arrivo);
		biglietto.setPartenza(partenza);
		biglietto.setClasse(classi[classe]);
		biglietto.setFasciaDistanza(fascie[fasciaDistanza]);
		return biglietto;
	}

	/**
	 * setta la matrice dei costi
	 * 
	 * @param costi
	 *            costi da impostare
	 */
	public void setCosti(int[][] costi) throws RemoteException {
		this.costi = costi;
	}

	/**
	 * ritorna la matrice dei costi
	 * 
	 * @return matrice dei costi
	 */
	public int[][] getCosti() {
		return costi;
	}

	/**
	 * ritorna i tipi di classe
	 * 
	 * @return le classi dei biglietti
	 */
	public String[] getClassi() throws RemoteException {
		return classi;
	}

	/**
	 * salva i costi impostati
	 * 
	 * @param costi
	 *            matrice di costi
	 * @throws FileNotFoundException
	 *             se non trova il file dei costi
	 * @throws IOException
	 *             se
	 * @throws RemoteException
	 *             se non si riesce a invocare il metodo
	 */
	public void salvaCosti(int[][] costi) throws FileNotFoundException,
			IOException, RemoteException {
		setCosti(costi); // cambia i costi con quelli nuovi inseriti
		ObjectOutputStream output = new ObjectOutputStream(
				new FileOutputStream("costiBiglietti.dat"));
		output.writeObject(costi); // scrive l'oggetto su file
	}

	/**
	 * ritorna le fascie di distanza
	 * 
	 * @return fascie di distanza
	 */
	public String[] getFascie() throws RemoteException {
		return fascie;
	}

	/**
	 * calcola il costo dati la classe e la fascia
	 * 
	 * @param classe
	 *            rappresenta la classe
	 * @param la
	 *            fascia
	 * @return costo
	 */
	public int calcolaCosto(int classe, int fascia) {
		return costi[classe][fascia];
	}

}