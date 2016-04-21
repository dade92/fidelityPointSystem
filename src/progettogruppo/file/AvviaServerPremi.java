package progettogruppo.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * main che avvia il server per la gestione dei premi da remoto
 */
public class AvviaServerPremi {
	// il main avvia il server creando il regitro e facendo il binding
	// dell'oggetto
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(4001);
			registry.rebind("DatabasePremi", UnicastRemoteObject.exportObject(
					new GestoreFilePremi("premi.dat"), 4001));
			System.out.println("Server avviato e pronto");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
