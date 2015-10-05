package tp.pr4.logic;

/**
 * Clase que implementa el movimiento para el juego del Complica. Se deben
 * implementar los m�todos abstractos de la clase padre.
 * 
 * @author Jhersy Nolasco Arevalo y Roberto Barrasus Orza
 * 
 */

public class MovimientoComplica extends Movimiento {

	private Ficha fichaEscondida = Ficha.VACIA;

	/**
	 * Constructor del objeto.
	 * 
	 * @param donde
	 *            Columna en la que se colocar� la ficha
	 * @param color
	 *            Color de la ficha que se pondr� (o jugador que pone).
	 */
	public MovimientoComplica(int donde, Ficha color) {
		columna = donde;
		this.color = color;
	}

	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		// El movimiento solo será incorrecto si la columna a poner no esta
		// dentro del tablero
		if (columna > 0 && columna <= tab.getAncho()) {
			// si la columna esta llena, guarda la ficha de abajo del todo y
			// desplaza toda la columna hacia abajo
			if (tab.getCasilla(columna, 1) == Ficha.VACIA) {
			} else {
				complica = true;
				fichaEscondida = tab.desplazaColumnaAbajo(columna);
			}
			tab.coloca(columna, color);
			
		} else {
			throw new MovimientoInvalido(
					"Columna incorrecta. Debe estar entre 1 y "
							+ tab.getAncho() + ".");
		}

	}

	@Override
	public void undo(Tablero tab) {

		int fila;
		// Si ha escondido una ficha, desplaza la columna hacia arriba y poner
		// la ficha escondida abajo del todo del tablero
		// Si no ha escondido ninguna ficha, calcula la ultima ficha puesta en
		// la columna y en su lugar pone una
		// ficha vacía
		if (fichaEscondida != Ficha.VACIA) {
			tab.desplazaColumnaArriba(columna);
			tab.setCasilla(columna, tab.getAlto(), fichaEscondida);
		} else {
			fila = tab.hueco(columna) + 1;
			tab.setCasilla(columna, fila, Ficha.VACIA);
		}

	}

}
