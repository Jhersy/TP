package tp.pr4.commands;

import tp.pr4.control.ControladorConsola;

/**
 * Implementacion del comando para la opcion Reiniciar.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class ComandoReiniciar implements Comando {

	private ControladorConsola c;

	public ComandoReiniciar(ControladorConsola c) {
		this.c = c;
	}

	@Override
	public boolean analiza(String cmd) {
		return cmd.equals("REINICIAR");
	}

	@Override
	public void ejecuta() {
		c.reset(c.getFactoria());
		////////////////////////
		// System.out.println("Partida reiniciada.");
	}

}
