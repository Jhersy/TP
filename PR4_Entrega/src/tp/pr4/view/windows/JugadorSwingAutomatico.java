package tp.pr4.view.windows;

import javax.swing.SwingUtilities;

import tp.pr4.control.ControladorVista;
import tp.pr4.logic.MovimientoInvalido;

public class JugadorSwingAutomatico implements JugadorSwing {

	private ControladorVista ctrl;
	private HebraJugadorAutomatico hebra;

	public JugadorSwingAutomatico(ControladorVista ctrl) {
		this.ctrl = ctrl;
	}

	@Override
	public void recibeTurno() {
		hebra = new HebraJugadorAutomatico(2000);
		hebra.start();
	}

	@Override
	public void pierdeTurno() {
		hebra.interrupt();

	}

	/*
	 * Clase que implementara la hebra para el jugador automatico
	 */
	private class HebraJugadorAutomatico extends Thread {
		private int tiempo;

		public HebraJugadorAutomatico(int tiempo) {
			this.tiempo = tiempo;

		}

		// implementacion de la hebra
		public void run() {
			try {
				Thread.sleep(tiempo);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							ctrl.aleatorio();
						} catch (MovimientoInvalido e) {
							e.printStackTrace();
						}
					}
				});

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
