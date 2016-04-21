package progettogruppo.sistemisociet‡;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import progettogruppo.file.RemoteGestoreFilePremi;
import progettogruppo.file.RicercaFallitaException;
import progettogruppo.premieofferte.Premio;

/**
 * classe dummy che si connette al sistema remotamente
 */
public class SistemaSociet‡PartnerPremi {

	public static void main(String args[]) {

		try {
			Registry registry = LocateRegistry.getRegistry(4001); // recupera il
																	// registro
			RemoteGestoreFilePremi remote = (RemoteGestoreFilePremi) registry
					.lookup("DatabasePremi");
			ArrayList<Premio> offerte = remote.recupera(127);
			for (Premio o : offerte)
				System.out.println(o);
			remote.elimina(1);
			remote.chiudiConnessione();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RicercaFallitaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}