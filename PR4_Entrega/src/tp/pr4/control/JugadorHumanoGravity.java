package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.*;

/**
 * Implementacion del jugador humano del juego gravity.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class JugadorHumanoGravity implements Jugador {
	private Scanner in;
	private int fila, col;

	public JugadorHumanoGravity(Scanner in) {
		this.in = in;
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color)
			throws MovimientoInvalido {

		String opFila, opCol;

		System.out.print("Introduce columna: ");
		opCol = in.nextLine().trim();

		System.out.print("Introduce fila: ");
		opFila = in.nextLine().trim();

		if (!isNumeric(opCol) || !isNumeric(opFila))
			throw new MovimientoInvalido(
					"Se deben introducir valores numéricos.");
		else {
			col = Integer.parseInt(opCol);
			fila = Integer.parseInt(opFila);
		}

		return new MovimientoGravity(col, fila, color);
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
