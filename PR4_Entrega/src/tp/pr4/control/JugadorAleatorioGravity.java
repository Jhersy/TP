package tp.pr4.control;

import tp.pr4.logic.Ficha;
import tp.pr4.logic.Movimiento;
import tp.pr4.logic.MovimientoGravity;
import tp.pr4.logic.Tablero;

/**
 * Jugador que juega de forma aleatoria a Gravity. Simplemente elige una
 * posición aleatoria en el tablero, comprobando antes que esa casilla está
 * vacía (se podrá poner).
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class JugadorAleatorioGravity implements Jugador {

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		boolean colocada = false;
		int col = 0;
		int fila = 0;
		// Hay que comprobar que la casilla que genere aleatoriamente este vacía
		while (!colocada) {
			col = (int) (Math.random() * tab.getAncho() + 1);
			fila = (int) (Math.random() * tab.getAlto() + 1);

			if (tab.getCasilla(col, fila) == Ficha.VACIA)
				colocada = true;
		}

		return new MovimientoGravity(col, fila, color);
	}

}
