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

import progettogruppo.file.GestoreFilePremi;
import progettogruppo.file.RicercaFallitaException;
import progettogruppo.premieofferte.AbstractPremioFactory;
import progettogruppo.premieofferte.Premio;
import progettogruppo.sistema.OperazioneNonConsentitaException;
import progettogruppo.utenti.UserType;

/**
 * JFrame utilizzato dalle societ‡ partner premi
 * 
 * @see Societ‡PartnerPremi
 */
@SuppressWarnings("serial")
public class Societ‡PartnerPremiFrame extends GeneralAbstractFrame {

	private JPanel contentPane;
	private JTextField nomeField;
	private JTextField quantit‡Field;
	private JTextField descField;
	private JTextField costoField;
	private JTextArea textArea;
	private GestoreFilePremi database;

	/**
	 * costruisce la finestra
	 * 
	 * @param nome
	 *            titolo
	 */
	public Societ‡PartnerPremiFrame(String nome) {
		super(nome);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) { // quando chiudo
															// finestra
				database.chiudiConnessione(); // chiudo connessione con database
				sistema.logoutUtente(); // faccio il logout dell'Utente
			}
		});
		try {
			database = new GestoreFilePremi("premi.dat");
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
		nomeField.setBounds(96, 11, 86, 20);
		contentPane.add(nomeField);
		nomeField.setColumns(10);

		quantit‡Field = new JTextField();
		quantit‡Field.setBounds(96, 42, 86, 20);
		contentPane.add(quantit‡Field);
		quantit‡Field.setColumns(10);

		descField = new JTextField();
		descField.setBounds(317, 11, 86, 20);
		contentPane.add(descField);
		descField.setColumns(10);

		costoField = new JTextField();
		costoField.setBounds(317, 42, 86, 20);
		contentPane.add(costoField);
		costoField.setColumns(10);

		JLabel lblNome = new JLabel("NOME:");
		lblNome.setBounds(40, 14, 46, 14);
		contentPane.add(lblNome);

		JLabel lblQuantita = new JLabel("QUANTITA:");
		lblQuantita.setBounds(20, 45, 66, 14);
		contentPane.add(lblQuantita);

		JLabel lblDescrizione = new JLabel("DESCRIZIONE:");
		lblDescrizione.setBounds(224, 14, 83, 14);
		contentPane.add(lblDescrizione);

		JLabel lblCosto = new JLabel("COSTO:");
		lblCosto.setBounds(261, 45, 46, 14);
		contentPane.add(lblCosto);
		// JButton per aggiungere un Premio
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AbstractPremioFactory factory = new AbstractPremioFactory();
					Premio premio = (Premio) factory.creaPremio(
							UserType.SOCIETA_PARTNER_PREMI,
							Integer.parseInt(costoField.getText()));
					premio.setNome(nomeField.getText());
					premio.setQuantit‡(Integer.parseInt(quantit‡Field.getText()));
					premio.setDescrizione(descField.getText());

					premio.setIdSociet‡(sistema.getUtenteCorrente().getId());
					database.aggiungi(premio);
				} catch (OperazioneNonConsentitaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(contentPane,
							"Controlla i campi", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAggiungi.setBounds(20, 100, 89, 23);
		contentPane.add(btnAggiungi);
		// JButton per eliminare un Premio
		JButton btnElimina = new JButton("Elimina");
		btnElimina
				.setToolTipText("per eliminare inserire i campi nome,quantit\u00E0 e costo corretti");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Premio premio = new Premio(Integer.parseInt(costoField
							.getText()), nomeField.getText(), "", Integer
							.parseInt(quantit‡Field.getText()), sistema
							.getUtenteCorrente().getId());

					database.elimina(premio);
				} catch (RicercaFallitaException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(
							Societ‡PartnerPremiFrame.this,
							"Premio non trovato", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(contentPane,
							"Controlla i campi", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (OperazioneNonConsentitaException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		btnElimina.setBounds(314, 100, 89, 23);
		contentPane.add(btnElimina);
		// Button per vedere i premi della societ‡
		JButton btnVediPremi = new JButton("Vedi Premi");
		btnVediPremi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Premio> premi = new ArrayList<Premio>();
				// recupera i premi della societ‡ loggata al momento
				try {
					premi = database.recupera(sistema.getUtenteCorrente()
							.getId());
					textArea.setText("");
					for (int i = 0; i < premi.size(); i++)
						textArea.append(premi.get(i).toString() + "\n");
				} catch (OperazioneNonConsentitaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RicercaFallitaException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(
							Societ‡PartnerPremiFrame.this, "Nessun premio",
							"Errore", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnVediPremi.setBounds(148, 100, 128, 23);
		contentPane.add(btnVediPremi);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 134, 388, 117);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}

}
