package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.Ficha;
import tp.pr4.logic.Movimiento;
import tp.pr4.logic.MovimientoInvalido;
import tp.pr4.logic.MovimientoReversi;
import tp.pr4.logic.Tablero;

public class JugadorHumanoReversi implements Jugador {
	private Scanner in;
	private int col, fila;

	public JugadorHumanoReversi(Scanner in) {
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

		return new MovimientoReversi(fila, col, color);
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
