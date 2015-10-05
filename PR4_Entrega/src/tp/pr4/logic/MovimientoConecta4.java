package tp.pr4.logic;


/**
 * Clase que implementa el movimiento para el juego del Conecta 4. Se deben
 * implementar los metodos abstractos de la clase padre.
 * 
 * @author Jhersy Nolasco Arevalo y Roberto Barrasus Orza
 * 
 */
public class MovimientoConecta4 extends Movimiento {

	/**
	 * Constructor de la clase
	 * 
	 * @param donde
	 *            Columna en la que se colocara la ficha
	 * @param color
	 *            Color de la ficha que se pondra (o jugador que pone).
	 */
	public MovimientoConecta4(int donde, Ficha color) {
		columna = donde;
		this.color = color;
	}

	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		// Solo se ejecuta el movimiento si la ficha que se desea poner no se
		// sale del ancho del tablero o la columna no esta llena

		if (columna > 0 && columna <= tab.getAncho()) {
			if (tab.getCasilla(columna, 1) == Ficha.VACIA) {
				tab.coloca(columna, color);
				
			} else {
				throw new MovimientoInvalido("Columna llena.");			
			}
		} else {
			throw new MovimientoInvalido(
					"Columna incorrecta. Debe estar entre 1 y "
							+ tab.getAncho()+ ".");
		}
	}

	@Override
	public void undo(Tablero tab) {
		// Calcula la ultima ficha puesta en la columna y en su lugar pone una
		// ficha vac�a
		int fila = tab.hueco(columna) + 1;
		tab.setCasilla(columna, fila, Ficha.VACIA);
	}

}
