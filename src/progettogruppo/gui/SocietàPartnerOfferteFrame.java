package progettogruppo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import progettogruppo.file.GestoreFileOfferteSpeciali;
import progettogruppo.file.RicercaFallitaException;
import progettogruppo.premieofferte.OffertaSpeciale;
import progettogruppo.sistema.OperazioneNonConsentitaException;

/**
 * JFrame utilizzato dalle societ‡ partner offerte
 * 
 * @see Societ‡PartnerOfferte
 */
@SuppressWarnings("serial")
public class Societ‡PartnerOfferteFrame extends GeneralAbstractFrame {

	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField descField;
	private JTextArea textArea;
	private GestoreFileOfferteSpeciali database;

	/**
	 * costruisce la finestra
	 * 
	 * @param nome
	 *            titolo
	 */
	public Societ‡PartnerOfferteFrame(String nome) {
		super(nome);
		addWindowListener(new WindowAdapter() { // quando chiudo la finestra
												// chiudo anche la connessione
												// con il database delle offerte
			@Override
			public void windowClosed(WindowEvent e) {
				database.chiudiConnessione();
				sistema.logoutUtente(); // faccio il logout dell'Utente
			}
		});
		try {
			database = new GestoreFileOfferteSpeciali("offerte.dat");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		nomeField = new JTextField();
		nomeField.setBounds(85, 23, 86, 20);
		contentPane.add(nomeField);
		nomeField.setColumns(10);

		descField = new JTextField();
		descField.setBounds(85, 70, 86, 20);
		contentPane.add(descField);
		descField.setColumns(10);

		JLabel lblNome = new JLabel("NOME:");
		lblNome.setBounds(39, 26, 46, 14);
		contentPane.add(lblNome);

		JLabel lblDescrizione = new JLabel("DESCRIZIONE:");
		lblDescrizione.setBounds(3, 73, 72, 14);
		contentPane.add(lblDescrizione);
		// ActionListener:aggiungere un'OffertaSpeciale
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OffertaSpeciale o = null;
				try {
					o = new OffertaSpeciale(nomeField.getText(), descField
							.getText(), sistema.getUtenteCorrente().getId());
				} catch (OperazioneNonConsentitaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				database.aggiungi(o);
			}
		});
		btnAggiungi.setBounds(226, 11, 122, 23);
		contentPane.add(btnAggiungi);
		// ActionListener:eliminare un'OffetaSpeciale
		JButton btnElimina = new JButton("Elimina");
		btnElimina
				.setToolTipText("per eliminare inserire solo il nome dell'offerta");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					OffertaSpeciale o = new OffertaSpeciale(
							nomeField.getText(), "", sistema
									.getUtenteCorrente().getId());
					database.elimina(o);
				} catch (RicercaFallitaException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(
							Societ‡PartnerOfferteFrame.this,
							"offerta non trovata", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (OperazioneNonConsentitaException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnElimina.setBounds(226, 82, 122, 23);
		contentPane.add(btnElimina);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 129, 391, 122);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		// ActionListener:vedere Offerte della societ‡ partner
		JButton btnVediOfferte = new JButton("Vedi Offerte");
		btnVediOfferte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<OffertaSpeciale> offerte = new ArrayList<OffertaSpeciale>();
				try {
					offerte = database.recupera(sistema.getUtenteCorrente()
							.getId());
				} catch (OperazioneNonConsentitaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RicercaFallitaException e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(
							Societ‡PartnerOfferteFrame.this, "Nessuna offerta",
							"Errore", JOptionPane.INFORMATION_MESSAGE);
				}
				textArea.setText(""); // pulisce la JTextArea
				for (int i = 0; i < offerte.size(); i++)
					// appende tutte le offerte
					textArea.append(offerte.get(i).toString() + "\n");
			}
		});
		btnVediOfferte.setBounds(226, 45, 122, 23);
		contentPane.add(btnVediOfferte);
	}
}
