package progettogruppo.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import progettogruppo.premieofferte.Premio;

/**
 * 
 * @author davide
 * 
 */
public class PremioTest {

	Premio premio = null;

	@Before
	public void setUp() {
		this.premio = new Premio(10, "premio prova", "", 10, 10);
	}

	@Test
	public void testSetIdSociet�() {
		premio.setIdSociet�(456);
		assertEquals(456, premio.getIdSociet�());
	}

	@Test
	public void testGetIdSociet�() {
		assertEquals(10, premio.getIdSociet�());
	}

	@Test
	public void testSetDescrizione() {
		premio.setDescrizione("descrizione di prova");
		assertEquals("descrizione di prova", premio.getDescrizione());
	}

	@Test
	public void testGetDescrizione() {
		assertEquals("", premio.getDescrizione());
	}

	@Test
	public void testSetNome() {
		premio.setNome("nome di prova");
		assertEquals("nome di prova", premio.getNome());
	}

	@Test
	public void testGetNome() {
		assertEquals("premio prova", premio.getNome());
	}

	@Test
	public void testSetQuantit�() {
		premio.setQuantit�(1);
		assertEquals(1, premio.getQuantit�());
	}

	@Test
	public void testGetQuantit�() {
		assertEquals(10, premio.getQuantit�());
	}

	@Test
	public void testDecrementaQuantit�() {
		premio.decrementaQuantit�();
		assertEquals(9, premio.getQuantit�());
	}

	@Test
	public void testEqualsShouldWorkObject() {
		Premio p = new Premio(10, "premio prova", "", 10, 10);
		assertEquals(true, premio.equals(p));
	}

	@Test
	public void testEqualsShouldNotWork() {
		Premio p2 = new Premio(10, "premio prova", "", 10, 9);
		assertEquals(false, premio.equals(p2));
	}

	@Test
	public void testGetCosto() {
		assertEquals(10, premio.getCosto());
	}

	@Test
	public void testSetCosto() {
		premio.setCosto(2);
		assertEquals(2, premio.getCosto());
	}

}
