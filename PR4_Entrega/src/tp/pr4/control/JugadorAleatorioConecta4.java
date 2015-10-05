package tp.pr4.control;

import tp.pr4.logic.*;

import java.lang.Math;

/**
 * Implementación de la factoría para el juego del Conecta 4. Los métodos
 * devuelven los objetos capaces de jugar a ese juego.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class JugadorAleatorioConecta4 implements Jugador {

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		boolean colocada = false;
		int col = 0;
		// Hay que comprobar que en la columna generada aún haya filas vacías
		// para poder poner
		while (!colocada) {
			col = (int) (Math.random() * tab.getAncho() + 1);
			if (tab.getCasilla(col, 1) == Ficha.VACIA)
				colocada = true;
		}

		return new MovimientoConecta4(col, color);
	}
}
