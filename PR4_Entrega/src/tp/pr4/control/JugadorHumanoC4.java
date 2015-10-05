package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.Ficha;
import tp.pr4.logic.Movimiento;
import tp.pr4.logic.MovimientoConecta4;
import tp.pr4.logic.MovimientoInvalido;
import tp.pr4.logic.Tablero;

/**
 * Implementacion del jugador humano del juego conecta 4.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class JugadorHumanoC4 implements Jugador {
	private Scanner in;
	private int col;

	public JugadorHumanoC4(Scanner in) {
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

		return new MovimientoConecta4(col, color);
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
