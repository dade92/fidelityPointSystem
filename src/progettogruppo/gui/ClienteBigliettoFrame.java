package progettogruppo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import progettogruppo.sistema.Memento;
import progettogruppo.sistema.OperazioneNonConsentitaException;
import progettogruppo.sistema.SistemaGestioneUtenti;
import progettogruppo.sistematicketing.SistemaTicketing;

/**
 * JFrame utilizzato dal Cliente quando deve acquistare un biglietto aereo
 * 
 * @see Cliente BigliettoPremio
 */
@SuppressWarnings("serial")
public class ClienteBigliettoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField partenzaField;
	private JTextField arrivoField;
	private JTextField giornoField;
	private JTextField meseField;
	private JTextField annoField;
	private JComboBox<String> classiComboBox;
	private JComboBox<String> fascieComboBox;
	private SistemaTicketing ticket;
	private JTextField costoField;
	private SistemaGestioneUtenti sistema;

	/**
	 * costruisce la finestra
	 * 
	 * @param nome
	 *            titolo
	 */
	public ClienteBigliettoFrame(String name) {
		super(name);

		String[] classi = null;
		String[] fascie = null;
		try {
			// prende il registro
			Registry registry = LocateRegistry.getRegistry(4321);
			// fa il lookup
			ticket = (SistemaTicketing) registry.lookup("SistemaTicketing");
			// recupera le classi di posti dal SistemaTicketing
			classi = ticket.getClassi();
			// recupera le fascie di distanza
			fascie = ticket.getFascie();
			// recuera l'istanza del SistemaGestioneUtenti
			sistema = SistemaGestioneUtenti.getInstance();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPartenza = new JLabel("PARTENZA:");
		lblPartenza.setBounds(10, 24, 74, 14);
		contentPane.add(lblPartenza);

		JLabel lblArrivo = new JLabel("ARRIVO:");
		lblArrivo.setBounds(10, 60, 57, 14);
		contentPane.add(lblArrivo);

		partenzaField = new JTextField();
		partenzaField.setBounds(79, 21, 86, 20);
		contentPane.add(partenzaField);
		partenzaField.setColumns(10);

		arrivoField = new JTextField();
		arrivoField.setBounds(77, 57, 88, 20);
		contentPane.add(arrivoField);
		arrivoField.setColumns(10);

		giornoField = new JTextField();
		giornoField.setBounds(223, 21, 38, 20);
		contentPane.add(giornoField);
		giornoField.setColumns(10);

		meseField = new JTextField();
		meseField.setBounds(298, 21, 46, 20);
		contentPane.add(meseField);
		meseField.setColumns(10);

		annoField = new JTextField();
		annoField.setBounds(378, 21, 46, 20);
		contentPane.add(annoField);
		annoField.setColumns(10);

		JLabel lblGg = new JLabel("gg");
		lblGg.setBounds(199, 24, 29, 14);
		contentPane.add(lblGg);

		JLabel lblMon = new JLabel("mes:");
		lblMon.setBounds(273, 24, 29, 14);
		contentPane.add(lblMon);

		JLabel lblAn = new JLabel("an:");
		lblAn.setBounds(353, 24, 29, 14);
		contentPane.add(lblAn);
		// ActionListener quando il Cliente acquista un BigliettoPremio
		JButton btnAcquista = new JButton("Acquista");
		btnAcquista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int giorno = Integer.parseInt(giornoField.getText());
					int mese = Integer.parseInt(meseField.getText());
					int anno = Integer.parseInt(annoField.getText());
					// controlla che il giorno e il mese siano validi
					if (giorno < 0 || giorno > 31 || mese < 0 || mese > 12
							|| anno < 2013)
						throw new NumberFormatException();
					// recupera la data
					String data = giornoField.getText() + "/"
							+ meseField.getText() + "/" + annoField.getText();
					// compra il Premio generando il BigliettoPremio e chiamando
					// il metodo della classe SistemaGestionePuntiFedeltàUtente
					sistema.compraPremio(ticket.creaBigliettoPremio(data,
							partenzaField.getText(), arrivoField.getText(),
							classiComboBox.getSelectedIndex(),
							fascieComboBox.getSelectedIndex()));
					Memento memento = sistema
							.creaMemento("acquisto biglietto aereo");
					memento.update();
					JOptionPane.showMessageDialog(contentPane,
							"Biglietto aereo acquistato con successo!", "",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(contentPane,
							"Controlla i campi della data", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (OperazioneNonConsentitaException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(ClienteBigliettoFrame.this,
							"Saldo insufficente", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAcquista.setBounds(10, 100, 89, 23);
		contentPane.add(btnAcquista);

		classiComboBox = new JComboBox<String>(classi);
		// ActionListener:quando la JComboBox viene cambiata,mostra il costo del
		// biglietto
		classiComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					costoField.setText(String.valueOf(ticket.calcolaCosto(
							classiComboBox.getSelectedIndex(),
							fascieComboBox.getSelectedIndex())));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		classiComboBox.setBounds(308, 57, 116, 20);
		contentPane.add(classiComboBox);

		fascieComboBox = new JComboBox<String>(fascie);
		// ActionListener:quando la JComboBox viene cambiata,mostra il costo del
		// biglietto
		fascieComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					costoField.setText(String.valueOf(ticket.calcolaCosto(
							classiComboBox.getSelectedIndex(),
							fascieComboBox.getSelectedIndex())));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		fascieComboBox.setBounds(308, 122, 116, 20);
		contentPane.add(fascieComboBox);

		JLabel lblClasse = new JLabel("CLASSE:");
		lblClasse.setBounds(245, 60, 57, 14);
		contentPane.add(lblClasse);

		JLabel lblFascia = new JLabel("FASCIA:");
		lblFascia.setBounds(256, 125, 46, 14);
		contentPane.add(lblFascia);

		JLabel lblCosto = new JLabel("COSTO:");
		lblCosto.setBounds(10, 148, 46, 14);
		contentPane.add(lblCosto);

		costoField = new JTextField();
		costoField.setEditable(false);
		costoField.setBounds(79, 145, 86, 20);
		contentPane.add(costoField);
		costoField.setColumns(10);

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
