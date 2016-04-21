package progettogruppo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import progettogruppo.file.GestoreFileUtenti;
import progettogruppo.file.UtenteInesistenteException;
import progettogruppo.utenti.Cliente;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * JFrame che si apre al momento del login di un addetto al supporto
 * 
 * @see AddettoAlSupporto
 */
@SuppressWarnings("serial")
public class AddettoAlSupportoFrame extends GeneralAbstractFrame {

	private JPanel contentPane;
	private JTextField textField;
	JTextArea textArea;
	GestoreFileUtenti database;

	/**
	 * costruisce la gui
	 * 
	 * @param nome
	 *            titolo della finestra
	 */
	public AddettoAlSupportoFrame(String nome) {
		super(nome);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				database.chiudiConnessione();
				sistema.logoutUtente();
			}
		});
		try {
			database = new GestoreFileUtenti("Utenti.dat");
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

		JLabel lblId = new JLabel("ID UTENTE:");
		lblId.setBounds(36, 24, 61, 14);
		contentPane.add(lblId);

		textField = new JTextField();
		textField.setBounds(107, 21, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		// ActionListener per cercare un Cliente
		JButton btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// ritorna il Cliente richiesto
					Cliente cliente = database.retrieveCliente(Integer
							.parseInt(textField.getText()));
					textArea.setText(""); // resetta la JTextArea
					textArea.setText(cliente.toString()); // stampa l'oggetto
															// Cliente
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(contentPane,
							"Controlla i campi", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (UtenteInesistenteException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(AddettoAlSupportoFrame.this,
							"Utente non trovato!", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCerca.setBounds(236, 20, 89, 23);
		contentPane.add(btnCerca);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(36, 110, 296, 141);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}
}
