package progettogruppo.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import progettogruppo.file.GestoreFileUtenti;
import progettogruppo.file.UtenteInesistenteException;
import progettogruppo.utenti.Utente;

public class GestoreFileUtentiTest {

	GestoreFileUtenti gestore;

	@Before
	public void setUp() throws FileNotFoundException, ClassNotFoundException,
			IOException {
		gestore = new GestoreFileUtenti("utenti.dat");
	}

	@Test
	public void testEliminaInt() {
		fail("Not yet implemented");
	}

	@Test(expected = FileNotFoundException.class)
	public void testGestoreFileUtenti() throws FileNotFoundException,
			ClassNotFoundException, IOException {
		gestore = new GestoreFileUtenti("utens.dat");
	}

	@Test(expected = UtenteInesistenteException.class)
	public void testCheckEsistenzaUtenteDontWork()
			throws UtenteInesistenteException {
		gestore.checkEsistenzaUtente(100, "");
	}

	@Test
	public void testCheckEsistenzaUtenteWork()
			throws UtenteInesistenteException {
		Utente user = gestore.checkEsistenzaUtente(143, "password");
		assertEquals(143, user.getId());
	}

	@Test(expected = UtenteInesistenteException.class)
	public void testRetrieveClienteDontWork() throws UtenteInesistenteException {
		gestore.retrieveCliente(123);
	}

	@Test
	public void testRetrieveClienteWork() throws UtenteInesistenteException {
		Utente user = gestore.retrieveCliente(144);
		assertEquals(144, user.getId());
	}

}
