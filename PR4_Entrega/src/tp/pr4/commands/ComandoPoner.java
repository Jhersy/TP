package tp.pr4.commands;

import tp.pr4.control.ControladorConsola;
import tp.pr4.logic.MovimientoInvalido;
/**
 * Implementacion del comando para la opcion Poner.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class ComandoPoner implements Comando {
	private ControladorConsola c;

	public ComandoPoner(ControladorConsola c) {
		this.c = c;
	}

	@Override
	public boolean analiza(String cmd) {
		return cmd.equals("PONER");
	}

	@Override
	public void ejecuta() throws MovimientoInvalido{
		c.poner();

	}

}
