package progettogruppo.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import progettogruppo.file.GestoreFilePremi;
import progettogruppo.file.RicercaFallitaException;
import progettogruppo.premieofferte.Premio;

public class GestoreFilePremiTest {

	GestoreFilePremi gestore;

	@Before
	public void setUp() throws FileNotFoundException, ClassNotFoundException,
			IOException {
		gestore = new GestoreFilePremi("premi.dat");
	}

	@Test(expected = FileNotFoundException.class)
	public void testGestoreFilePremi() throws FileNotFoundException,
			ClassNotFoundException, IOException {
		gestore = new GestoreFilePremi("premio.dat");
	}

	@Test(expected = RicercaFallitaException.class)
	public void testRecuperaInt() throws RicercaFallitaException {
		gestore.recupera(1);
	}

	@Test
	public void testAggiorna() throws IOException {
		gestore.aggiorna();
	}

	@Test
	public void testSetBuffer() {
		ArrayList<Premio> list = new ArrayList<>(2);
		gestore.setBuffer(list);
	}

	@Test
	public void testGetBuffer() {
		ArrayList<Premio> list = gestore.getBuffer();
		System.out.println(list.get(0));
	}

	@Test
	public void testChiudiConnessione() {
		gestore.chiudiConnessione();
	}

	@Test
	public void testGetFileName() {
		assertEquals("premi.dat", gestore.getFileName());
	}

	@Test
	public void testSetFileName() {
		gestore.setFileName("prova");
		assertEquals("prova", gestore.getFileName());
	}

	@Test
	public void testRecupera() throws ClassNotFoundException, IOException {
		gestore.recupera();
	}

	@Test
	public void testAggiungi() {
		gestore.aggiungi(new Premio(10, "prova", "", 10, 10));
	}

	@Test(expected = RicercaFallitaException.class)
	public void testEliminaInt() throws RicercaFallitaException {
		gestore.elimina(100);
	}

	@Test(expected = RicercaFallitaException.class)
	public void testEliminaObject() throws RicercaFallitaException {
		gestore.elimina(new Premio(1, "", "", 1, 1));
	}

}
