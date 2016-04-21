package progettogruppo.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import progettogruppo.file.GestoreFileOfferteSpeciali;
import progettogruppo.file.RicercaFallitaException;
import progettogruppo.premieofferte.OffertaSpeciale;

public class GestoreFileOfferteSpecialiTest {

	GestoreFileOfferteSpeciali gestore;

	@Before
	public void setUp() throws FileNotFoundException, ClassNotFoundException,
			IOException {
		gestore = new GestoreFileOfferteSpeciali("offerte.dat");
	}

	@Test(expected = FileNotFoundException.class)
	public void testGestoreFileOfferteSpeciali() throws FileNotFoundException,
			ClassNotFoundException, IOException {
		gestore = new GestoreFileOfferteSpeciali("offer.dat");
	}

	@Test(expected = RicercaFallitaException.class)
	public void testRecuperaInt() throws RicercaFallitaException {
		@SuppressWarnings("unused")
		ArrayList<OffertaSpeciale> list = gestore.recupera(22);
	}

}
