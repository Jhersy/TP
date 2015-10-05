package tp.pr4.logic;

/**
 * Clase que implementa el movimiento para el juego del Gravity. Se deben
 * implementar los métodos abstractos de la clase padre.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class MovimientoGravity extends Movimiento {
	public MovimientoGravity(int columna, int fila, Ficha color) {
		this.columna = columna;
		this.fila = fila;
		this.color = color;
	}

	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {

		// Solo se ejecuta el movimiento si la columna y la fila esta dentro del
		// tablero.
		if (columna > 0 && columna <= tab.getAncho()) {
			if (fila > 0 && fila <= tab.getAlto()) {
				if (tab.getCasilla(columna, fila) == Ficha.VACIA) {
					tab.colocaGravity(columna, fila, color);
					columnaFinal = tab.getColumnaFinal() + 1;
					filaFinal = tab.getFilaFinal() + 1;
				} else
					throw new MovimientoInvalido("Casilla ocupada.");
			} else
				throw new MovimientoInvalido("Posición incorrecta.");
		} else
			throw new MovimientoInvalido("Posición incorrecta.");
	}

	@Override
	public void undo(Tablero tab) {
		tab.setCasilla(columnaFinal, filaFinal, Ficha.VACIA);
	}

}
