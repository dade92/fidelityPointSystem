package progettogruppo.sistematicketing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AvviaSistemaTicketing {

	/**
	 * avvia il server del sistema di ticketing
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SistemaTicketingImpl ticket = new SistemaTicketingImpl();
			Registry registry = LocateRegistry.createRegistry(4321);
			registry.rebind("SistemaTicketing", ticket);
			System.out.println("Server sistema di ticketing avviato e pronto ");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
