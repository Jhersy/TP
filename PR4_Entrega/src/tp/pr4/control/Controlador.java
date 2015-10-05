package tp.pr4.control;

import tp.pr4.logic.Partida;

public abstract class Controlador {
	Jugador player;
	Partida game;
	FactoriaTipoJuego factory;

	public Controlador(FactoriaTipoJuego factory, Partida game) {
		this.game = game;
		this.factory = factory;
	}
	
	/**
	 * Devuelve la factoría
	 * @return factoría
	 */
	public FactoriaTipoJuego getFactoria() {
		return factory;
	}
}
