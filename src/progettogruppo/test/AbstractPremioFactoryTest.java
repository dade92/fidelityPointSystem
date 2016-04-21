package progettogruppo.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import progettogruppo.premieofferte.AbstractPremio;
import progettogruppo.premieofferte.AbstractPremioFactory;
import progettogruppo.utenti.UserType;

public class AbstractPremioFactoryTest {

	AbstractPremioFactory factory;
	
	@Before
	public void setUp() {
		factory=new AbstractPremioFactory();
	}
	
	@Test
	public void testCreaPremio() {
		AbstractPremio premio=factory.creaPremio(UserType.CLIENTE, 100);
		assertEquals(100,premio.getCosto());
	}

}
