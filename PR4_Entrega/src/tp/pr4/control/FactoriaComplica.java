package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.*;

/**
 * Implementación de la factoría para el juego del Complica. Los métodos
 * devuelven los objetos capaces de jugar a ese juego.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class FactoriaComplica implements FactoriaTipoJuego {
	/**
	 * Construye el objeto Jugador capaz de jugar al juego concreto de forma
	 * aleatoria.
	 */
	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}

	/**
	 * Construye el objeto Jugador que se encarga de preguntar al usuario por
	 * consola el siguiente movimiento a realizar.
	 */
	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoCo(in);
	}

	/**
	 * Construye un movimiento para el juego concreto. Es posible que la
	 * implementaci�n no utilice todos los par�metros.
	 * 
	 */
	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoComplica(col, color);
	}

	/**
	 * Construye las reglas del juego concreto.
	 */
	@Override
	public ReglasJuego creaReglas() {
		return new ReglasComplica();
	}

}
