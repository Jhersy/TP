package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.*;

/**
 * Implementacion del jugador humano del juego complica.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class JugadorHumanoCo implements Jugador {

	private Scanner in;
	private int col;

	public JugadorHumanoCo(Scanner in) {
		this.in = in;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color)
			throws MovimientoInvalido {

		String op;
		System.out.print("Introduce columna: ");
		op = in.nextLine().trim();
		if (!isNumeric(op))
			throw new MovimientoInvalido(
					"Se deben introducir valores numéricos.");
		else
			col = Integer.parseInt(op);

		return new MovimientoComplica(col, color);
	}

	private boolean isNumeric(String opcion) {
		try {
			Integer.parseInt(opcion);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
