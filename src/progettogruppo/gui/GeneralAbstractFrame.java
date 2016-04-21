package progettogruppo.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import progettogruppo.sistema.SistemaGestioneUtenti;

/**
 * JFrame che dichiara attributi e caratterictiche comuni ai JFrame del sistema
 */
@SuppressWarnings("serial")
public abstract class GeneralAbstractFrame extends JFrame {
	/*
	 * hanno tutti un attributo SistemaGestionePuntiFedelt‡Utente, tutte alla
	 * chiusura chiamano il metodo logoutUtente(),tutte hanno un nome della
	 * finestra passato al costruttore,tutte sono visibili e tutte alla chiusura
	 * non terminano il programma
	 */
	protected SistemaGestioneUtenti sistema;

	/**
	 * aggiunge il windowListener e inizializza il sistema
	 * 
	 * @param nome
	 *            titolo della finestra
	 */
	public GeneralAbstractFrame(String nome) {
		super(nome);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				sistema.logoutUtente();
			}
		});
		try {
			sistema = SistemaGestioneUtenti.getInstance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
