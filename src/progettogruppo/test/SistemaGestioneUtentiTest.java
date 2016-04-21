package progettogruppo.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import progettogruppo.file.UtenteInesistenteException;
import progettogruppo.sistema.OperazioneNonConsentitaException;
import progettogruppo.sistema.SistemaGestioneUtenti;

public class SistemaGestioneUtentiTest {

	SistemaGestioneUtenti sistema;

	@Before
	public void setUp() throws FileNotFoundException, ClassNotFoundException,
			IOException {
		sistema = SistemaGestioneUtenti.getInstance();
	}

	@Test(expected = UtenteInesistenteException.class)
	public void testLoginUtente() throws UtenteInesistenteException,
			OperazioneNonConsentitaException {
		sistema.loginUtente(111, "");
	}

	@Test
	public void testLogoutUtente() {
		sistema.logoutUtente();
	}

	@Test
	public void testGeneraId() throws ClassNotFoundException, IOException {
		System.out.println(sistema.generaId());
	}

	@Test
	public void testCreaMemento() throws IOException {
		sistema.creaMemento("log di prova");
	}

}
