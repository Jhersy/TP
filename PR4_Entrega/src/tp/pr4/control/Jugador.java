package tp.pr4.control;

import tp.pr4.logic.*;

/**
 * Interfaz que representa un jugador; cuando el controlador quiere saber qu�
 * movimiento ejecutar a continuaci�n, le pregunta al jugador que tiene el
 * turno. NO es un concepto de la partida (que va recibiendo las �rdenes de qu�
 * movimientos ejecutar), sino del que controla el flujo de ejecuci�n de la
 * aplicaci�n. De ah� que est� en el paquete control y no en el paquete logica
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public interface Jugador {

	/**
	 * Devuelve el siguiente movimiento a efectuar por el jugador. Las distintas
	 * implementaciones pueden hacer cosas distintas si se les pide un
	 * movimiento sobre un tablero en el que no se puede colocar ficha del color
	 * indicado, como devolver null, un movimiento incorrecto o incluso no
	 * terminar.
	 * 
	 * @param tab
	 *            Estado del tablero donde poner.
	 * @param color
	 *            Color de la ficha que hay que colocar. Las distintas
	 *            implementaciones no tienen por qu� hacer uso de este
	 *            par�metro, si los objetos han sido configurados previamente en
	 *            el momento de la construcci�n. Se a�ade para facilitar la
	 *            implementaci�n de algunas clases derivadas.
	 * @return Movimiento que se desea ejecutar. Dependiendo de la
	 *         implementaci�n, el movimiento puede ser correcto o no.
	 * @throws MovimientoInvalido
	 */

	public Movimiento getMovimiento(Tablero tab, Ficha color)
			throws MovimientoInvalido;

}
