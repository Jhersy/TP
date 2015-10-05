package tp.pr4.commands;

import tp.pr4.control.ControladorConsola;


/**
 * Implementacion del comando para la opcion Salir.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class ComandoSalir implements Comando {

	private ControladorConsola ctrl;

	public ComandoSalir(ControladorConsola ctrl) {
		this.ctrl = ctrl;
	}

	@Override
	public boolean analiza(String cmd) {
		return cmd.equals("SALIR");
	}

	@Override
	public void ejecuta() {
		ctrl.setTerminada();
	}

}
