package progettogruppo.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import progettogruppo.utenti.UserType;
import progettogruppo.utenti.Utente;
import progettogruppo.utenti.UtenteFactory;

public class UtenteFactoryTest {

	UtenteFactory factory;

	@Before
	public void setUp() {
		factory = new UtenteFactory();
	}

	@Test
	public void testCreaUtente() {
		Utente utente = factory.creaUtente(UserType.AMMINISTRATORE, 100,
				"prova");
		assertEquals(100, utente.getId());
		assertEquals("prova", utente.getPassword());
	}

}
