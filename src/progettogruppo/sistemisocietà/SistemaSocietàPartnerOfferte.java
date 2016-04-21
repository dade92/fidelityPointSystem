package progettogruppo.sistemisociet‡;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import progettogruppo.file.RemoteGestoreFileOfferte;
import progettogruppo.file.RicercaFallitaException;
import progettogruppo.premieofferte.OffertaSpeciale;

/**
 * classe dummy che invoca da remoto i metodi del GestorefileOfferte
 */
public class SistemaSociet‡PartnerOfferte {

	public static void main(String args[]) {

		try {
			// recupera il registro
			Registry registry = LocateRegistry.getRegistry(4000);
			// fa il lookup dell'oggetto e restituisce l'oggetto Remote
			RemoteGestoreFileOfferte remote = (RemoteGestoreFileOfferte) registry
					.lookup("GestoreFileOfferte");
			// chiama i metodi dell'oggetto RemoteDatabaseOfferte come se fosse
			// un oggetto della classe
			ArrayList<OffertaSpeciale> offerte = remote.recupera(138);
			for (OffertaSpeciale o : offerte)
				System.out.println(o);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RicercaFallitaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}