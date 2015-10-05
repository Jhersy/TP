package tp.pr4.logic;

/**
 * Clase que representa el movimiento de un jugador. Tiene un m�todo para
 * ejecutar el movimiento sobre la partida, y otro para deshacerlo. Es una clase
 * abstracta; habr� una clase no abstracta por cada tipo de juego soportado.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */

public abstract class Movimiento {
	protected int columna;
	protected Ficha color;
	protected int fila;
	protected boolean complica;
	/**
	 * Atributos utilizados para almacenar las coordenadas definitivas de la
	 * ficha colocada
	 */
	protected int columnaFinal;
	protected int filaFinal;

	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parametro. Se
	 * puede dar por cierto que tablero recibido sigue las reglas del tipo de
	 * juego al que pertenece el movimiento. En caso contrario, el
	 * comportamiento es indeterminado.
	 * 
	 * @param tab
	 *            Tablero sobre el que ejecutar el movimiento
	 * @return true si todo fue bien. Se devuelve false si el movimiento no
	 *         puede ejecutarse sobre el tablero.
	 * @throws MovimientoInvalido
	 *             si hay algún problema que hizo que el movimiento no se haya
	 *             podido ejecutar. El mensaje de la excepción contendrá una
	 *             explicación del tipo de error.
	 */
	public abstract void ejecutaMovimiento(Tablero tab)
			throws MovimientoInvalido;

	/**
	 * Devuelve el color del jugador al que pertenece el movimiento. (Puede
	 * hacerse abstracto)
	 * 
	 * @return Color del jugador (coincide con el pasado al constructor).
	 */
	public Ficha getJugador() {
		return color;
	}

	/**
	 * Deshace el movimiento en el tablero recibido como parametro. Se puede dar
	 * por cierto que el movimiento se ejecuto sobre ese tablero; en caso
	 * contrario, el comportamiento es indeterminado. Por lo tanto, es de
	 * suponer que el metodo siempre funcionara correctamente.
	 * 
	 * @param tab
	 *            Tablero de donde deshacer el movimiento.
	 */
	public abstract void undo(Tablero tab);

}
