package progettogruppo.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import progettogruppo.sistema.Memento;
import progettogruppo.utenti.Cliente;

public class MementoTest {

	Memento memento;

	@Before
	public void setUp() throws IOException {
		memento = new Memento("log di prova", new Cliente(100, "", "", "", 10));
	}

	@Test
	public void testMemento() throws IOException {
		memento = new Memento("", null);
	}

	@Test
	public void testUpdate() throws IOException {
		memento.update();
	}

	@Test
	public void testGetLog() {
		assertEquals("log di prova", memento.getLog());
	}

	@Test
	public void testSetLog() {
		memento.setLog("prova");
		assertEquals(memento.getLog(), "prova");
	}

	@Test
	public void testGetUtenteCorrente() {
		assertEquals(memento.getUtenteCorrente(), new Cliente(100, "", "", "",
				10));
	}

}
