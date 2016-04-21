package progettogruppo.sistematicketing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import progettogruppo.premieofferte.BigliettoPremio;

/**
 * interfaccia RMI del sistema di ticketing
 */

public interface SistemaTicketing extends Remote {

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
	BigliettoPremio creaBigliettoPremio(String data, String partenza,
			String arrivo, int fasciaDistanza, int classe)
			throws RemoteException;

	void salvaCosti(int[][] costi) throws FileNotFoundException, IOException,
			RemoteException;

	/**
	 * setta ma matrice dei costi
	 * 
	 * @param costi
	 *            costi da impostare
	 */
	void setCosti(int[][] costi) throws RemoteException;

	/**
	 * ritorna le fascie di distanza
	 * 
	 * @return fascie di distanza
	 */
	String[] getFascie() throws RemoteException;

	/**
	 * ritorna i tipi di classe
	 * 
	 * @return le classi
	 */
	String[] getClassi() throws RemoteException;

	/**
	 * ritorna la matrice dei costi
	 * 
	 * @return matrice dei costi
	 */
	int[][] getCosti() throws RemoteException;

	/**
	 * calcola il costo dati la classe e la fascia
	 * 
	 * @param classe
	 *            rappresenta la classe
	 * @param la
	 *            fascia
	 * @return costo
	 */
	int calcolaCosto(int classe, int fascia) throws RemoteException;

}
