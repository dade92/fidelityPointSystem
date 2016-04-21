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
	public void testSetIdSocietà() {
		premio.setIdSocietà(456);
		assertEquals(456, premio.getIdSocietà());
	}

	@Test
	public void testGetIdSocietà() {
		assertEquals(10, premio.getIdSocietà());
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
	public void testSetQuantità() {
		premio.setQuantità(1);
		assertEquals(1, premio.getQuantità());
	}

	@Test
	public void testGetQuantità() {
		assertEquals(10, premio.getQuantità());
	}

	@Test
	public void testDecrementaQuantità() {
		premio.decrementaQuantità();
		assertEquals(9, premio.getQuantità());
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
