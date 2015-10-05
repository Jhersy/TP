package tp.pr4.control;

import tp.pr4.logic.Ficha;
import tp.pr4.logic.Movimiento;
import tp.pr4.logic.MovimientoInvalido;
import tp.pr4.logic.Partida;

public class ControladorVista extends Controlador {

	/**
	 * Constructor del Controlador Vista
	 * @param factory
	 * @param game
	 */
	public ControladorVista(FactoriaTipoJuego factory,
			Partida game) {
		super(factory, game);
	}
	
	public Ficha getTurno(){
		return game.getTurno();
	}

	/**
	 * Crea un movimiento y lo ejecuta
	 * @param fila
	 * @param columna
	 * @throws MovimientoInvalido
	 */
	public void move(int fila, int columna) throws MovimientoInvalido {
		Movimiento m = factory.creaMovimiento(columna, fila, game.getTurno());
		game.ejecutaMovimiento(m);
	
	}

	/**
	 * Método que cambia al juego del parámetro factory
	 * @param factory
	 */
	public void cambioJuego(FactoriaTipoJuego factory) {
		this.factory = factory;
		game.cambioJuego(factory.creaReglas());
	}

	/**
	 * Método que resetea la partida
	 */
	public void reset() {
		game.reset(factory.creaReglas());
	}

	/**
	 * Método para deshacer un movimiento en la partida
	 */
	public void undo() {
		game.undo();
	}

	
	/**
	 * Método ayuda que llama al método ayuda de partida
	 */
	public void ayuda(){
		game.ayuda();
	}

	/**
	 * Ejecuta un movimiento aleatorio en la partida
	 * @throws MovimientoInvalido
	 */
	public void aleatorio() throws MovimientoInvalido{
		
		game.ejecutaMovimiento(factory.creaJugadorAleatorio().getMovimiento(game.getTablero(), game.getTurno()));
		
	}
	

}
