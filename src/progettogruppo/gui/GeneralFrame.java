package progettogruppo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import progettogruppo.file.GestoreFileUtenti;
import progettogruppo.file.UtenteInesistenteException;
import progettogruppo.sistema.Memento;
import progettogruppo.sistema.OperazioneNonConsentitaException;
import progettogruppo.utenti.Cliente;
import progettogruppo.utenti.UserType;
import progettogruppo.utenti.UtenteFactory;

/**
 * frame generale che si apre all'avvio consente all'Utente di fare il login o
 * di registrarsi nel sistema
 */
@SuppressWarnings("serial")
public class GeneralFrame extends GeneralAbstractFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField nomeField;
	private JTextField cognomeField;
	private JTextField newPasswordField;
	private JPasswordField passwordField;
	private GestoreFileUtenti file;

	/**
	 * costruisce la finestra passandogli il titolo
	 * 
	 * @param nome
	 *            titolo del JFrame
	 */
	public GeneralFrame(String nome) {
		super(nome);
		try {
			file=new GestoreFileUtenti("utenti.dat");
			sistema.setGestoreFileUtenti(file);
		} catch (FileNotFoundException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		} catch (ClassNotFoundException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		} catch (IOException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(69, 94, 23, 14);
		contentPane.add(lblId);

		idField = new JTextField();
		idField.setBounds(102, 91, 86, 20);
		contentPane.add(idField);
		idField.setColumns(10);

		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setBounds(20, 145, 97, 14);
		contentPane.add(lblPassword);
		// crea il JButton per il login
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() { // gli aggiunge un
															// ActionListener
			public void actionPerformed(ActionEvent arg0) {
				try {
					String password = new String(passwordField.getPassword());
					// fa il login dell'utente nel sistema
					UserType type = sistema.loginUtente(
							Integer.parseInt(idField.getText()), password);
					// sceglie a seconda del tipo di Utente quale JFrame
					// visualizzare
					// recupera il Memento
					Memento memento = sistema.creaMemento("login dell'Utente");
					memento.update();
					if (type == UserType.CLIENTE)
						new ClienteFrame("Cliente");
					else if (type == UserType.SOCIETA_PARTNER_PREMI)
						new Societ‡PartnerPremiFrame("Societ‡PartnerPremi");
					else if (type == UserType.SOCIETA_PARTNER_OFFERTE)
						new Societ‡PartnerOfferteFrame("Societ‡PartnerOfferte");
					else if (type == UserType.ADDETTO_AL_SUPPORTO)
						new AddettoAlSupportoFrame("AddettoAlSupporto");
					else if (type == UserType.AMMINISTRATORE)
						new AmministratoreFrame("Amministratore");
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(GeneralFrame.this,
							"Controlla i campi!", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (UtenteInesistenteException e) { // se l'Utente non
															// esiste
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(GeneralFrame.this,
							"Credenziali errate.Riprova", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (OperazioneNonConsentitaException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(contentPane, e.getMessage(),
							"Errore", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(99, 191, 89, 23);
		contentPane.add(btnLogin);

		nomeField = new JTextField();
		nomeField.setBounds(314, 80, 86, 20);
		contentPane.add(nomeField);
		nomeField.setColumns(10);

		cognomeField = new JTextField();
		cognomeField.setBounds(314, 111, 86, 20);
		contentPane.add(cognomeField);
		cognomeField.setColumns(10);

		newPasswordField = new JTextField();
		newPasswordField.setBounds(314, 142, 86, 20);
		contentPane.add(newPasswordField);
		newPasswordField.setColumns(10);
		// JButton che registra il Cliente nel sistema
		JButton btnRegistrati = new JButton("Registrati");
		btnRegistrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int nuovoId = sistema.generaId(); // ottiene l'id nuovo
					// crea il Cliente
					UtenteFactory factory = new UtenteFactory();
					Cliente nuovoCliente = (Cliente) factory.creaUtente(
							UserType.CLIENTE, nuovoId,
							newPasswordField.getText());// la factory crea il
														// Cliente
					// setta i parametri del Cliente
					nuovoCliente.setNome(nomeField.getText());
					nuovoCliente.setCognome(cognomeField.getText());
					nuovoCliente.setPuntiTotali(10000); // setta di default
														// 10000
														// punti
					// il sistema aggiunge il Cliente
					file.aggiungi(nuovoCliente); // il sistema aggiunge
															// il Cliente
					file.aggiorna();
					sistema.setGestoreFileUtenti(file);
					JOptionPane.showMessageDialog(GeneralFrame.this, String
							.format("il tuo id Ë %d. Non dimenticarlo!",
									nuovoId),
							"Registrazione avvenuta con successo",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (NumberFormatException e4) {
					JOptionPane.showMessageDialog(GeneralFrame.this,
							"Controlla i campi!", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRegistrati.setBounds(311, 191, 89, 23);
		contentPane.add(btnRegistrati);

		JLabel lblNome = new JLabel("NOME:");
		lblNome.setBounds(268, 83, 46, 14);
		contentPane.add(lblNome);

		JLabel lblCognome = new JLabel("COGNOME:");
		lblCognome.setBounds(248, 114, 66, 14);
		contentPane.add(lblCognome);

		JLabel lblPassword_1 = new JLabel("PASSWORD:");
		lblPassword_1.setBounds(237, 145, 86, 14);
		contentPane.add(lblPassword_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(102, 142, 86, 20);
		contentPane.add(passwordField);

	}
}
