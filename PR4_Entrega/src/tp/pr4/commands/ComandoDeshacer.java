package tp.pr4.commands;

import tp.pr4.control.ControladorConsola;

/**
 * Implementacion del comando para la opcion Deshacer.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */

public class ComandoDeshacer implements Comando {
	private ControladorConsola ctrl;

	public ComandoDeshacer(ControladorConsola ctrl) {
		this.ctrl = ctrl;
	}

	@Override
	public boolean analiza(String cmd) {
		return cmd.equals("DESHACER");
	}

	@Override
	public void ejecuta() {
		ctrl.undo();

	}

}
