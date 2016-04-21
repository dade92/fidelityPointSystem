package progettogruppo.gui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import progettogruppo.file.GestoreFileOfferteSpeciali;
import progettogruppo.file.GestoreFilePremi;
import progettogruppo.file.RicercaFallitaException;
import progettogruppo.premieofferte.OffertaSpeciale;
import progettogruppo.premieofferte.Premio;
import progettogruppo.sistema.Memento;
import progettogruppo.sistema.OperazioneNonConsentitaException;
import progettogruppo.sistema.SaldoInsufficenteException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * JFrame che si apre al momento del login di un Cliente
 * 
 * @see Cliente
 */
@SuppressWarnings("serial")
public class ClienteFrame extends GeneralAbstractFrame {

	private JPanel contentPane;
	private GestoreFilePremi dataPremi;
	private GestoreFileOfferteSpeciali dataOfferte;
	private JList<OffertaSpeciale> offerteList;
	private JList<Premio> premiList;
	private JTextArea textArea;
	private OffertaSpeciale[] o;
	private Premio[] p;

	/**
	 * costruisce la finestra
	 * 
	 * @param nome
	 *            titolo
	 */
	public ClienteFrame(String nome) {
		super(nome);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dataPremi.chiudiConnessione();
				dataOfferte.chiudiConnessione();
				sistema.logoutUtente();
			}
		});
		try {
			dataPremi = new GestoreFilePremi("premi.dat");
			dataOfferte = new GestoreFileOfferteSpeciali("offerte.dat");
			// arrray utilizzati nei metodi toArray() della classe ArrayList
			// (altrimenti non fa il cast all'oggetto che voglio)
			o = new OffertaSpeciale[10];
			p = new Premio[10];
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
		setBounds(100, 100, 587, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 272, 113);
		contentPane.add(scrollPane);
		// ActionListener per il JButton che permette di comprare un premio
		JButton btnCompraPremio = new JButton("Compra Premio");
		btnCompraPremio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Premio premio = premiList.getSelectedValue();
					sistema.compraPremio(premio);
					// se non c'è più nessun premio
					if (premio.getQuantità() == 0)
						dataPremi.elimina(premio);// elimina il riferimento
					premiList.setListData(dataPremi.recupera().toArray(p));
					// recupera il Memento
					Memento memento = sistema.creaMemento("acquisto Premio");
					memento.update();
				} catch (SaldoInsufficenteException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(ClienteFrame.this,
							"Saldo insufficiente!", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (OperazioneNonConsentitaException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (NullPointerException e3) { // se non seleziona nessun
													// Premio
					JOptionPane.showMessageDialog(contentPane,
							"Seleziona un premio", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				} catch (IOException e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				} catch (RicercaFallitaException e6) {
					// TODO Auto-generated catch block
					e6.printStackTrace();
				}

			}
		});
		btnCompraPremio.setBounds(134, 11, 142, 23);
		contentPane.add(btnCompraPremio);
		// ActionListener per richiedere un' OffertaSpeciale
		JButton btnRichiediOfferta = new JButton("Richiedi offerta");
		btnRichiediOfferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// prende l'OffertaSpeciale che vuole l'Utente
					sistema.richiediOfferta(offerteList.getSelectedValue());
					// la elimina dal file
					dataOfferte.elimina(offerteList.getSelectedValue());
					// inizializza la JList con le offerte rimanenti
					offerteList.setListData(dataOfferte.recupera().toArray(o));
					// recupera il Memento
					Memento memento = sistema.creaMemento("richiesta offerta");
					memento.update();
				} catch (OperazioneNonConsentitaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RicercaFallitaException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(contentPane,
							"Seleziona una offerta", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException e3) { // se non seleziona nessuna
													// OffertaSpeciale
					JOptionPane.showMessageDialog(contentPane,
							"Seleziona una offerta", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnRichiediOfferta.setBounds(427, 11, 134, 23);
		contentPane.add(btnRichiediOfferta);
		// l'Utente vede il suo rendiconto
		JButton btnVediRendiconto = new JButton("Vedi Rendiconto");
		btnVediRendiconto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				try {
					// richiede al sistema l'Utente correntemente loggato
					textArea.setText(sistema.getUtenteCorrente().toString());
				} catch (OperazioneNonConsentitaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVediRendiconto.setBounds(381, 165, 148, 23);
		contentPane.add(btnVediRendiconto);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 163, 272, 88);
		contentPane.add(scrollPane_2);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_2.setViewportView(textArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(308, 36, 253, 113);
		contentPane.add(scrollPane_1);

		try {
			offerteList = new JList<OffertaSpeciale>(dataOfferte.recupera()
					.toArray(o));
			scrollPane_1.setViewportView(offerteList);
			premiList = new JList<Premio>(dataPremi.recupera().toArray(p));
			scrollPane.setViewportView(premiList);
			// ActionListener per quando il Cliente richiede un BigliettoAereo
			JButton btnAcquistaBiglietto = new JButton("Acquista biglietto");
			btnAcquistaBiglietto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// recupera il Memento
					Memento memento;
					try {
						// visualizza la finestra
						new ClienteBigliettoFrame("BigliettoPremio");
						memento = sistema
								.creaMemento("acquisto BigliettoPremio");
						memento.update();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnAcquistaBiglietto.setBounds(381, 199, 148, 23);
			contentPane.add(btnAcquistaBiglietto);

			JLabel lblPremi = new JLabel("PREMI:");
			lblPremi.setBounds(10, 15, 46, 14);
			contentPane.add(lblPremi);

			JLabel lblOfferte = new JLabel("OFFERTE:");
			lblOfferte.setBounds(312, 15, 63, 14);
			contentPane.add(lblOfferte);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
