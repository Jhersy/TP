package tp.pr4.commands;

import tp.pr4.control.ControladorConsola;

/**
 * Implementacion del comando para la opcion Ayuda.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class ComandoAyuda implements Comando {
	private ControladorConsola c;

	public ComandoAyuda(ControladorConsola c) {
		this.c = c;
	}

	@Override
	public boolean analiza(String cmd) {

		return cmd.equals("AYUDA");
	}

	@Override
	public void ejecuta() {
		System.out.println();
		System.out.println("PONER: utilízalo para poner la siguiente ficha.");
		System.out
				.println("DESHACER: deshace el último movimiento hecho en la partida.");
		System.out.println("REINICIAR: reinicia la partida.");
		System.out
				.println("JUGAR [c4|co|gr] [tamX tamY]: cambia el tipo de juego");
		System.out
				.println("JUGADOR [blancas|negras][humano|aleatorio]: cambia el tipo de jugador");
		System.out.println("SALIR: termina la aplicacion.");
		System.out.println("AYUDA: muestra esta ayuda.");
		System.out.println();

		c.ayuda();
	}

}
