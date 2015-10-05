package tp.pr4.commands;

import java.util.Scanner;

import tp.pr4.control.ControladorConsola;
import tp.pr4.control.Jugador;

/**
 * Implementacion del comando para la opcion TipoJugador.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class ComandoTipoJugador implements Comando {
	private Jugador jugadorBlancas;
	private Jugador jugadorNegras;
	private boolean tipoJugador;

	private ControladorConsola c;
	private Scanner in;

	public ComandoTipoJugador(ControladorConsola c, Scanner in) {
		this.c = c;
		this.in = in;
	}

	@Override
	public boolean analiza(String cmd) {
		boolean correct = true;
		String[] parts = cmd.split(" ");
		String part1 = parts[0];

		if (part1.toUpperCase().equals("JUGADOR")) {
			String part2 = parts[1];
			if (part2.toUpperCase().equals("NEGRAS")) {
				String part3 = parts[2];
				tipoJugador = true;
				if (part3.toUpperCase().equals("ALEATORIO"))
					jugadorNegras = c.getFactoria().creaJugadorAleatorio();
				else if (part3.toUpperCase().equals("HUMANO"))
					jugadorNegras = c.getFactoria().creaJugadorHumanoConsola(in);
				else
					correct = false;
			} else if (part2.toUpperCase().equals("BLANCAS")) {
				String part3 = parts[2];
				tipoJugador = false;
				if (part3.toUpperCase().equals("ALEATORIO"))
					jugadorBlancas = c.getFactoria().creaJugadorAleatorio();
				else if (part3.toUpperCase().equals("HUMANO"))
					jugadorBlancas = c.getFactoria().creaJugadorHumanoConsola(in);
				else
					correct = false;
			} else
				correct = false;
		} else
			correct = false;

		return correct;
	}

	@Override
	public void ejecuta() {
		if (tipoJugador)
			c.setJugadorNegras(jugadorNegras);
		else
			c.setJugadorBlancas(jugadorBlancas);

	}

}
