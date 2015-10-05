package tp.pr4.control;

import tp.pr4.logic.Ficha;
import tp.pr4.logic.Movimiento;
import tp.pr4.logic.MovimientoComplica;
import tp.pr4.logic.Tablero;

/**
 * Jugador que juega de forma aleatoria a Complica. En este caso cualquier
 * columna es válida, pues si está llena, se hará hueco.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class JugadorAleatorioComplica implements Jugador {

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		// Hay que generar unicamente una columna que esté en el rango del
		// tablero
		int col = (int) (Math.random() * tab.getAncho() + 1);
		return new MovimientoComplica(col, color);
	}

}
