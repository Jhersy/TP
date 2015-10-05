package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.*;

/**
 * Implementación de la factoría para el juego del Conecta 4. Los métodos
 * devuelven los objetos capaces de jugar a ese juego.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class FactoriaConecta4 implements FactoriaTipoJuego {

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4();
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoC4(in);
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoConecta4(col, color);
	}

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasConecta4();
	}

}
