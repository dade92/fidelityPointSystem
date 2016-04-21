package progettogruppo.file;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * main che attiva il server per le società offerte speciali
 */
public class AvviaServerOfferte {

	// il main avvia il server creando il regitro e facendo il binding
	// dell'oggetto
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Registry registry;
		try {
			registry = LocateRegistry.createRegistry(4000);
			registry.rebind("GestoreFileOfferte",
					UnicastRemoteObject
							.exportObject(new GestoreFileOfferteSpeciali(
									"offerte.dat"), 4000));
			System.out.println("server avviato e pronto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
