package tp.pr4.logic;

/**
 * Clase Observador partida
 * @author Jhersy Nolasco y Roberto Barrasus
 *
 */
public interface PartidaObserver {

	public void onReset(Tablero tab, Ficha turno);

	public void onMove(Partida game);

	public void onUndo(Partida game);

	public void onPartidaEnd(Partida game);

	public void onChangeGame(Tablero tab);

	public void onMoveIncorrect(Partida game, String error);

	public void onCambioJugador(Partida game);

	public void onUndoImposible(Tablero tab);
	
	public void onChangePlayer(Tablero tab);
	
	public void onAyuda(Tablero tab);

	public void onError(Tablero tablero);
	
	public void onTurno(Partida game);

}
