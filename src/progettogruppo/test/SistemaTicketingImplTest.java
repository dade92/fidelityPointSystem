package progettogruppo.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import progettogruppo.sistematicketing.SistemaTicketing;
import progettogruppo.sistematicketing.SistemaTicketingImpl;

public class SistemaTicketingImplTest {

	SistemaTicketing ticket;

	@Before
	public void setUp() throws FileNotFoundException, ClassNotFoundException,
			RemoteException, IOException {
		ticket = new SistemaTicketingImpl();
	}

	@Test
	public void testCreaBigliettoPremio() throws RemoteException {
		ticket.creaBigliettoPremio("", "", "", 1, 2);
	}

	@Test
	public void testGetClassi() throws RemoteException {
		ticket.getClassi();
	}

	@Test
	public void testGetFascie() throws RemoteException {
		ticket.getFascie();
	}

	@Test
	public void testCalcolaCosto() throws RemoteException {
		ticket.calcolaCosto(1, 1);
	}

}
