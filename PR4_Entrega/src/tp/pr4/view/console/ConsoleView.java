package tp.pr4.view.console;

import java.util.Scanner;

import tp.pr4.control.ControladorConsola;
import tp.pr4.control.GestorComandos;
import tp.pr4.logic.Ficha;
import tp.pr4.logic.MovimientoInvalido;
import tp.pr4.logic.Observable;
import tp.pr4.logic.Partida;
import tp.pr4.logic.PartidaObserver;
import tp.pr4.logic.Tablero;

public class ConsoleView implements PartidaObserver {

	ControladorConsola ctrl;
	private Scanner in;

	/**
	 * Constructor de la vista consola
	 * @param ctrl
	 * @param game
	 * @param in
	 */
	public ConsoleView(ControladorConsola ctrl,
			Observable<PartidaObserver> game, Scanner in) {
		this.ctrl = ctrl;
		this.in = in;
		game.addObserver(this);
	}

	/**
	 * Método que se ejecuta mientras se está jugando con consola
	 */
	public void run() {

		GestorComandos gc = new GestorComandos(ctrl, in);
		String op = "";
		while (!ctrl.partidaTerminada()) {
			try {
				System.out.println("Juegan " + ctrl.getTurno().toString());
				System.out.print("Qué quieres hacer? ");
				op = in.nextLine().toUpperCase().trim();
				gc.compruebaComando(op);
			} catch (MovimientoInvalido e) {
				System.err.println(e.getMessage());
				ctrl.error();
			}
		}

	}

	@Override
	public void onReset(Tablero tab, Ficha turno) {
		System.out.println(tab.toString());

	}

	@Override
	public void onMove(Partida game) {
		System.out.println(game.getTablero().toString());

	}

	@Override
	public void onUndo(Partida game) {
		System.out.println(game.getTablero().toString());
	}

	@Override
	public void onPartidaEnd(Partida game) {
		if (game.isTerminada()) {
			if (game.getTablas()) {
				System.out.println("Partida terminada en tablas.");
			} else if (game.getGanador() != Ficha.VACIA) {
				System.out.println("ganan las " + game.getGanador().toString());
			}
		}
	}

	@Override
	public void onChangeGame(Tablero tab) {
		System.out.println("Partida Reiniciada.");
		System.out.println(tab.toString());

	}

	@Override
	public void onMoveIncorrect(Partida game, String error) {
		System.err.println(error);
		System.out.println(game.getTablero().toString());

	}

	@Override
	public void onCambioJugador(Partida game) {
		System.out.println(game.getTablero().toString());
	}

	@Override
	public void onUndoImposible(Tablero tab) {
		System.err.println("Imposible deshacer.");
		System.out.println(tab.toString());
	}

	@Override
	public void onChangePlayer(Tablero tab) {
		System.out.println(tab.toString());

	}

	@Override
	public void onAyuda(Tablero tab) {
		System.out.println(tab.toString());

	}

	@Override
	public void onError(Tablero tablero) {
		System.out.println(tablero.toString());
	}

	@Override
	public void onTurno(Partida game) {
		// TODO Auto-generated method stub
		
	}

}
