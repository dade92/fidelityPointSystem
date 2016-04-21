package progettogruppo.sistema;

import java.awt.EventQueue;

import progettogruppo.gui.GeneralFrame;

public class AvviaSistema {

	/**
	 * classe che si occupa dell'avvio del sistema
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GeneralFrame("SistemaPuntiFedeltà");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
