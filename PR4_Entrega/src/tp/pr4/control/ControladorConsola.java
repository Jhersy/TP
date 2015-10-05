package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.*;

/**
 * Clase que controla la ejecución de la partida, pidiendo al usuario qué quiere
 * ir haciendo, hasta que la partida termina.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class ControladorConsola extends Controlador {
	final int numJugadores = 2;
	private Partida p;
	private Scanner in;
	// Almacena el tipo de jugador de cada una de las fichas,
	// 0 para blancas,
	// 1 para negras
	private Jugador[] jugador = new Jugador[numJugadores];

	/**
	 * Constructor de la clase.
	 * 
	 * @param f
	 *            Factoría del juego con el que se va a empezar una partida
	 * @param p
	 *            Partida a la que se jugará, configurada con el juego al que
	 *            hay que jugar en la invocación a run().
	 * @param in
	 *            Scanner que hay que utilizar para pedirle la información al
	 *            usuario.
	 */
	public ControladorConsola(FactoriaTipoJuego f, Partida p,
			java.util.Scanner in) {
		super(f, p);
		this.in = in;
		this.p = p;
		reset(f);
	}

	/**
	 * Método para reiniciar un juego
	 * 
	 * @param f
	 *            Tipo de factoría del juego
	 */
	public void reset(FactoriaTipoJuego f) {
		// Se empieza con dos jugadores humanos por defecto.
		jugador[0] = f.creaJugadorHumanoConsola(in);
		jugador[1] = f.creaJugadorHumanoConsola(in);
		p.reset(f.creaReglas());
	}

	/**
	 * Método para actualizar el atributo jugador blancas
	 */
	public void setJugadorBlancas(Jugador j) {
		jugador[0] = j;
		p.cambioJugador();
	}

	/**
	 * Método para actualizar el atributo jugador blancas
	 */
	public void setJugadorNegras(Jugador j) {
		jugador[1] = j;
		p.cambioJugador();
	}

	/**
	 * Método para devolver el valor del atributo jugador blancas
	 */
	public Jugador getJugadorBlancas() {
		return jugador[0];
	}

	/**
	 * Método para devolver el valor del atributo jugador negras
	 */
	public Jugador getJugadorNegras() {
		return jugador[1];
	}

	/**
	 * Método partida terminada
	 * 
	 * @return true si la partida ha acabado
	 */
	public boolean partidaTerminada() {
		return p.isTerminada();
	}

	/**
	 * GetTurno
	 * 
	 * @return turno
	 */
	public Ficha getTurno() {
		return p.getTurno();
	}

	/**
	 * getTablero
	 * 
	 * @return tablero
	 */
	public Tablero getTablero() {
		return p.getTablero();
	}

	/**
	 * Método poner un movimiento
	 * 
	 * @throws MovimientoInvalido
	 */
	public void poner() throws MovimientoInvalido {

		if (p.getTurno() == Ficha.BLANCA)
			p.ejecutaMovimiento(jugador[0].getMovimiento(p.getTablero(),
					p.getTurno()));
		else
			p.ejecutaMovimiento(jugador[1].getMovimiento(p.getTablero(),
					p.getTurno()));
	}

	/**
	 * Método para deshacer un movimiento en la partida
	 */
	public void undo() {
		p.undo();
	}

	/**
	 * Modificar el atributo terminada de partida
	 */
	public void setTerminada() {
		p.setTerminada();
	}

	/**
	 * Método ayuda que llama al método ayuda de partida
	 */
	public void ayuda() {
		p.ayuda();
	}

	/**
	 * Método error que llama al método error de la partida
	 */
	public void error() {
		p.error();
	}

}
