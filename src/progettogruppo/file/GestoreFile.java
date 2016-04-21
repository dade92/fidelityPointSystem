package progettogruppo.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * superclasse delle classi che gestiscono i file dei premi delle offerte e
 * degli utenti memorizzati nel sistema.è generico: non sa a priori quali
 * oggetti dovrà gestire.invocabile da remoto
 */
public abstract class GestoreFile<T> {

	// oggetti che gestiscono la scrittura/lettura su/da file
	protected ObjectInputStream input;
	protected ObjectOutputStream output;
	private String fileName;
	protected ArrayList<T> buffer;

	/**
	 * costruisce l'oggetto aprendo il file specificato
	 * 
	 * @param fileName
	 *            nome dei file
	 * @throws FileNotFoundException
	 *             se non trova il file
	 * @throws IOException
	 *             se non riesce a aprire il file
	 * @throws ClassNotFoundException
	 *             se non trova le classi per la deserializzazione
	 */
	@SuppressWarnings("unchecked")
	public GestoreFile(String fileName) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		setFileName(fileName);
		input = new ObjectInputStream(new FileInputStream(fileName));
		buffer = (ArrayList<T>) input.readObject(); // legge l'ArrayList
													// memorizzato
	}

	/**
	 * aggiorna il flle aperto
	 * 
	 * @throws IOException
	 *             se non riesce a scrivere su file
	 */
	public void aggiorna() throws IOException {
		output = new ObjectOutputStream(new FileOutputStream(fileName));
		output.writeObject(buffer);
	}

	/**
	 * setta il buffer da scrivere
	 * 
	 * @param nuovo
	 *            buffer aggiornato da scrivere su file
	 */
	public void setBuffer(ArrayList<T> nuovo) {
		this.buffer = nuovo;
	}

	/**
	 * ritorna il buffer
	 * 
	 * @return buffer
	 */
	public ArrayList<T> getBuffer() {
		return buffer;
	}

	/**
	 * aggiorna il file e chiude gli stream associati
	 */
	public void chiudiConnessione() {
		try {
			aggiorna();
			input.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ritorna il nome del file aperto
	 * 
	 * @return nome del file
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * setta il nome del file
	 * 
	 * @param fileName
	 *            nome del file
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * recupera il buffer
	 * 
	 * @return buffer letto dal file
	 * @throws ClassNotFoundException
	 *             se non riesce a deserializzare
	 * @throws IOException
	 *             se non trova il file
	 */
	public ArrayList<T> recupera() throws ClassNotFoundException, IOException {
		return getBuffer();
	}

	/**
	 * aggiunge un oggetto T al buffer
	 * 
	 * @param object
	 *            l'oggetto
	 */
	public void aggiungi(T object) {
		buffer.add(object);
	}

	/**
	 * elimina l'oggetto i-esimo dal buffer
	 * 
	 * @param index
	 *            indice dell'oggetto
	 * @throws RicercaFallitaException
	 *             se non trova l'oggetto da eliminare
	 */
	public void elimina(int index) throws RicercaFallitaException {
		try {
			buffer.remove(index);
		} catch (IndexOutOfBoundsException e) { // se l'indice è errato
			throw new RicercaFallitaException("indice sbagliato");
		}
	}

	/**
	 * elimina l'oggetto specificato
	 * 
	 * @param o
	 *            oggetto da eliminare
	 * @throws RicercaFallitaException
	 *             se non trova l'oggetto
	 */
	public void elimina(Object o) throws RicercaFallitaException {
		if (!buffer.remove(o))
			throw new RicercaFallitaException("non trovato");
	}

}