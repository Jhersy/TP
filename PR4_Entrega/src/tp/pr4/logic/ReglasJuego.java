package tp.pr4.logic;

/**
 * Interfaz que representa las reglas de un juego concreto. La partida delega en
 * un objeto de este interfaz para hacer avanzar la partida.
 * 
 * @author Jhersy Andres Nolasco y Roberto Barrasus
 * 
 */
public interface ReglasJuego {
	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no. Devuelve
	 * el color del ganador (si lo hay).
	 * 
	 * @param ultimoMovimiento
	 *            Ultimo movimiento realizado. Las distintas implementaciones
	 *            pueden utilizar este par�metro para optimizar la b�squeda
	 *            del ganador.
	 * @param t
	 *            Estado del tablero.
	 * @return color del ganador, si lo hay. Si no lo hay, devuelve Ficha.VACIA
	 *         (eso NO significa necesariamente que la partida haya terminado en
	 *         tablas).
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t);

	/**
	 * Construye el tablero que hay que utilizar para la partida, seg�n las
	 * reglas del juego
	 * 
	 * @return Tablero a utilizar. El estado del tablero ser� el de inicio de
	 *         la partida.
	 */
	public Tablero iniciaTablero();

	/**
	 * Devuelve el color del jugador que comienza la partida.
	 * 
	 * @return Color del primer jugador en colocar ficha.
	 */
	public Ficha jugadorInicial();

	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * 
	 * @param ultimoEnPoner
	 *            �ltimo jugador en poner ficha
	 * @param t
	 *            Estado del tablero.
	 * @return Siguiente jugador que debe poner ficha.
	 */

	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t);

	/**
	 * Devuelve true si, con el estado del tablero dado, la partida ha terminado
	 * en tablas.
	 * 
	 * @param ultimoEnPoner
	 *            Jugador que acaba de poner ficha
	 * @param t
	 *            Estado del tablero
	 * @return true si la partida ha terminado sin ganador.
	 */
	public boolean tablas(Ficha ultimoEnPoner, Tablero t);

	/**
	 * Metodo que determina las direcciones en las que buscar fichas alineadas
	 * 
	 * @param turno
	 *            color de la ficha a buscar
	 * @param col
	 * @param tablero
	 * @return color de la ficha si hay 4 en linea o vacia si no hay
	 */
	public Ficha cuatroEnLinea(Ficha turno, int col, int fila, Tablero tablero);
	

}
