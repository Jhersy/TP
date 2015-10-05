package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.Ficha;
import tp.pr4.logic.Movimiento;
import tp.pr4.logic.MovimientoGravity;
import tp.pr4.logic.ReglasGravity;
import tp.pr4.logic.ReglasJuego;

/**
 * Implementación de la factoría para el juego del Gravity. Los métodos
 * devuelven los objetos capaces de jugar a ese juego. Dado que el tamaño del
 * tablero de Gravity no está fijo, sino que se puede cambiar en cada partida,
 * la factoría puede configurarse con el tamaño del tablero que se quiere
 * utilizar.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class FactoriaGravity implements FactoriaTipoJuego {
	private int numCols;
	private int numFilas;

	public FactoriaGravity(int numCols, int numFilas) {
		if (numCols > 0 && numFilas > 0) {
			this.numCols = numCols;
			this.numFilas = numFilas;
		} else {
			this.numCols = 1;
			this.numFilas = 1;
		}
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoGravity(in);
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col, fila, color);
	}

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasGravity(numCols, numFilas);
	}

}
