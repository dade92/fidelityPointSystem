package progettogruppo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import progettogruppo.file.GestoreFileUtenti;
import progettogruppo.file.UtenteInesistenteException;
import progettogruppo.sistema.OperazioneNonConsentitaException;
import progettogruppo.sistematicketing.SistemaTicketing;
import progettogruppo.utenti.UserType;
import progettogruppo.utenti.UtenteFactory;
import javax.swing.JComboBox;

/**
 * JFrame utilizzato dall'admin
 * 
 * @see Amministratore
 */
@SuppressWarnings("serial")
public class AmministratoreFrame extends GeneralAbstractFrame {

	private JPanel contentPane;
	private JTextField passwordField;
	private JTextField eliminaField;
	private JRadioButton rdbtnSocietPartnerPremi;
	private JRadioButton rdbtnSocietPartnerOfferte;
	private JButton btnImpostaCosti;
	private SistemaTicketing ticket;
	private JComboBox<String> classiComboBox;
	private JComboBox<String> fascieComboBox;
	private JTextField costoField;
	private JLabel lblCosto;
	private int costi[][];
	private int costiPrecedenti[][];
	private GestoreFileUtenti file;

	/**
	 * costruisce la finestra
	 * 
	 * @param nome
	 *            titolo
	 */
	public AmministratoreFrame(String nome) {
		super(nome);
		String classi[] = null;
		String fascie[] = null;
		try {
			// recupera il registro
			Registry registry = LocateRegistry.getRegistry(4321);
			// inizializza l'oggetto SistemaTicketing
			ticket = (SistemaTicketing) registry.lookup("SistemaTicketing");
			classi = ticket.getClassi();
			fascie = ticket.getFascie();
			costiPrecedenti = ticket.getCosti();
			file=new GestoreFileUtenti("utenti.dat");
			sistema.setGestoreFileUtenti(file);
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		costi = new int[classi.length][fascie.length];
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setBounds(10, 101, 80, 14);
		contentPane.add(lblPassword);

		passwordField = new JTextField();
		passwordField.setBounds(85, 98, 86, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);

		rdbtnSocietPartnerPremi = new JRadioButton("Societ\u00E0 Partner Premi");
		rdbtnSocietPartnerPremi.setBounds(10, 7, 181, 23);
		contentPane.add(rdbtnSocietPartnerPremi);

		rdbtnSocietPartnerOfferte = new JRadioButton(
				"Societ\u00E0 Partner Offerte");
		rdbtnSocietPartnerOfferte.setBounds(10, 33, 181, 23);
		contentPane.add(rdbtnSocietPartnerOfferte);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnSocietPartnerPremi);
		group.add(rdbtnSocietPartnerOfferte);
		// ActionListener:quando si aggiunge una società
		JButton btnAggungiSociet = new JButton("Aggungi Societ\u00E0");
		btnAggungiSociet
				.setToolTipText("aggiunge una societ\u00E0 partner premi-offerte a seconda del tipo selezionato");
		btnAggungiSociet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = sistema.generaId(); // genera l'id casuale
					UtenteFactory factory = new UtenteFactory();
					if (rdbtnSocietPartnerOfferte.isSelected()) { // controlla
																	// quale
																	// JRadioButton
																	// è
																	// selezionato
						// aggiunge la società creandola con la Factory
						// e invocando il metodo generaId(),per mantenere
						// coerenza con gli id
						// inseriti in precedenza
						file.aggiungi(factory.creaUtente(
								UserType.SOCIETA_PARTNER_OFFERTE, id,
								passwordField.getText()));
					}
					// se invece è selezionato quello per la società partner
					// premi
					else if (rdbtnSocietPartnerPremi.isSelected()) {
						file.aggiungi((factory.creaUtente(
								UserType.SOCIETA_PARTNER_PREMI, id,
								passwordField.getText())));
					}
					// se nessun JRadioButton è stato selezionato sollevo
					// un'eccezione
					else
						throw new Exception();
					JOptionPane.showMessageDialog(AmministratoreFrame.this,
							String.format("l'id è %d", id),
							"Società aggiunta con successo",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException | IOException
						| OperazioneNonConsentitaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(AmministratoreFrame.this,
							"Seleziona il tipo di società", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAggungiSociet.setBounds(10, 63, 147, 23);
		contentPane.add(btnAggungiSociet);

		JLabel lblIdUtente = new JLabel("ID UTENTE:");
		lblIdUtente.setBounds(335, 11, 64, 14);
		contentPane.add(lblIdUtente);

		eliminaField = new JTextField();
		eliminaField.setBounds(335, 32, 86, 20);
		contentPane.add(eliminaField);
		eliminaField.setColumns(10);
		// ActionListener:eliminare un Utente dato l'id
		JButton btnElimina = new JButton("Elimina");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(eliminaField.getText());
					// se l'admin cerca di eliminare il proprio id
					if (id == sistema.getUtenteCorrente().getId())
						throw new OperazioneNonConsentitaException("");
					file.elimina(id); // elimina
					// visualizza un messaggio per dire che l'Utente è stato
					// eliminato
					JOptionPane.showMessageDialog(AmministratoreFrame.this,
							"Utente eliminato con successo", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(contentPane,
							"Controlla i campi", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (UtenteInesistenteException e1) { // se l'id specificato
															// non corrisponde a
															// nessun Utente
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(AmministratoreFrame.this,
							"l'Utente non esiste!", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (OperazioneNonConsentitaException e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(AmministratoreFrame.this,
							"proprio id.impossibile eliminare", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnElimina.setBounds(335, 63, 89, 23);
		contentPane.add(btnElimina);
		// ActionListener:quando clicca sul JButton impostaCosto
		btnImpostaCosti = new JButton("Imposta costo");
		btnImpostaCosti
				.setToolTipText("imposta il costo della combinazione classe-fascia selezionata");
		btnImpostaCosti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// la riga i-esima e la colonna j-esima sono selezionate e viene
				// impostato il valore specificato nel costoField
				costi[classiComboBox.getSelectedIndex()][fascieComboBox
						.getSelectedIndex()] = Integer.parseInt(costoField
						.getText());
			}
		});
		btnImpostaCosti.setBounds(153, 137, 127, 23);
		contentPane.add(btnImpostaCosti);

		classiComboBox = new JComboBox<String>(classi);
		classiComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				costoField.setText(String
						.valueOf(costiPrecedenti[classiComboBox
								.getSelectedIndex()][fascieComboBox
								.getSelectedIndex()]));
			}
		});
		classiComboBox.setBounds(10, 138, 106, 20);
		contentPane.add(classiComboBox);

		fascieComboBox = new JComboBox<String>(fascie);
		fascieComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				costoField.setText(String
						.valueOf(costiPrecedenti[classiComboBox
								.getSelectedIndex()][fascieComboBox
								.getSelectedIndex()]));
			}
		});
		fascieComboBox.setBounds(10, 179, 106, 20);
		contentPane.add(fascieComboBox);

		costoField = new JTextField();
		costoField.setBounds(66, 219, 86, 20);
		contentPane.add(costoField);
		costoField.setColumns(10);

		lblCosto = new JLabel("COSTO:");
		lblCosto.setBounds(10, 222, 46, 14);
		contentPane.add(lblCosto);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ticket.setCosti(costi);
					JOptionPane.showMessageDialog(contentPane,
							"Prezzi impostati con successo", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton
				.setToolTipText("clicca qui per sottomettere i costi impostati");
		btnNewButton.setBounds(153, 178, 127, 23);
		contentPane.add(btnNewButton);
	}
}
