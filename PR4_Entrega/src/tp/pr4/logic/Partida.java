package tp.pr4.logic;

import java.util.ArrayList;
import java.util.Collection;
import tp.pr4.logic.Ficha;

/**
 * Clase para representar la informacion de una partida. Se encarga de almacenar
 * el estado del tablero, a quien le toca, si ya hay un ganador, etc., asi como
 * la lista de movimientos que se han ido realizando para poder deshacerlos. La
 * partida guarda al menos los 10 ultimos movimientos.
 * 
 * @author Jhersy Andres Nolasco y Roberto Barrasus
 */
public class Partida implements Observable<PartidaObserver> {

	public Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;
	private PilaMovimientos pila;
	private ReglasJuego reg;
	private boolean tablas;

	private Collection<PartidaObserver> obs;

	/**
	 * Construye una partida nueva.
	 * 
	 * @param reglas
	 *            Reglas del juego que se utilizar�n durante la partida (al
	 *            menos hasta que se ejecute reset).
	 */
	public Partida(ReglasJuego reglas) {
		this.obs = new ArrayList<PartidaObserver>();
		iniciaJuego(reglas);

		for (PartidaObserver o : obs) {
			o.onReset(tablero, turno);	
		}
	}

	/**
	 * Reinicia la partida en curso. Esta operaci�n no puede deshacerse. Gracias
	 * al parámetro, permite cambiar el tipo de juego al que se juega.
	 * 
	 * @param reglas
	 *            Las reglas del juego a utilizar a partir de ahora.
	 */
	public void reset(ReglasJuego reglas) {

		iniciaJuego(reglas);

		for (PartidaObserver o : obs) {
			o.onReset(tablero, turno);
			o.onTurno(this);
		}
	}

	public void cambioJuego(ReglasJuego reglas) {
		iniciaJuego(reglas);
		for (PartidaObserver o : obs) {
			o.onChangeGame(tablero);
			o.onReset(tablero, turno);
		}
	}
	
	public void cambiaJugador(){
		
	}

	/**
	 * Ejecuta el movimiento indicado.
	 * 
	 * @param mov
	 *            Movimiento a ejecutar. Se asume que el movimiento es
	 *            compatible
	 * @return True si el movimiento se ha realizado correctamente
	 * @throws MovimientoInvalido
	 *             si no se puede efectuar el movimiento. Es un error intentar
	 *             colocar una ficha del jugador que no tiene el turno, cuando
	 *             la partida está terminada, columna llena, etc.
	 */

	public void ejecutaMovimiento(Movimiento mov) throws MovimientoInvalido {
		if (!terminada) {
			if (!reg.tablas(turno, tablero)) {
				if (turno == mov.getJugador()) {
					mov.ejecutaMovimiento(tablero);
					pila.apilar(mov);
					ganador = reg.hayGanador(mov, tablero);

					for (PartidaObserver o : obs){
						o.onMove(this);
					}

					if (ganador != Ficha.VACIA) {
						terminada = true;
						for (PartidaObserver o : obs)
							o.onPartidaEnd(this);
					} else if (reg.tablas(turno, tablero)) {
						terminada = true;
						tablas = true;

						for (PartidaObserver o : obs)
							o.onPartidaEnd(this);
					}
							
					turno = reg.siguienteTurno(turno, tablero);					
				
				} else
					throw new MovimientoInvalido(
							"La ficha es distinto al turno");
			} else
				throw new MovimientoInvalido(
						"No se debe poner despues de acabar la partida en tablas");
		} else {
			throw new MovimientoInvalido(
					"No se debe poner despues de acabar la partida");
		}
	}

	/**
	 * Deshace el ultimo movimiento ejecutado.
	 * 
	 * @return true si se pudo deshacer.
	 */
	public void undo() {
		if (!pila.esVacia()) {
			// Utilizamos cima para saber el ultimo movimento que hay en la pila
			(pila.cima()).undo(tablero);
			pila.desapilar();
			turno = reg.siguienteTurno(turno, tablero);

			for (PartidaObserver o : obs)
				o.onUndo(this);
		} else {
			for (PartidaObserver o : obs)
				o.onUndoImposible(tablero);
		}
	}

	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * 
	 * @return Color del jugador, o Ficha.VACIA si la partida ha terminado.
	 */
	public Ficha getTurno() {
		if (!terminada)
			return turno;
		return Ficha.VACIA;
	}

	/**
	 * Metodo set del atributo terminada, utilizado en el método salir.
	 */
	public void setTerminada() {
		terminada = true;
	}

	/**
	 * Metodo para saber si la partida ha concluido ya.
	 * 
	 * @return true si la partida ha acabado.
	 */
	public boolean isTerminada() {
		return terminada;
	}

	/**
	 * Devuelve el color del ganador. Solo valido si la partida ya ha terminado
	 * 
	 * @return Color del ganador. Si la partida termino en tablas, Ficha.VACIA.
	 *         Si la partida no ha terminado aun, el resultado es indeterminado.
	 */
	public Ficha getGanador() {
		return ganador;
	}

	/**
	 * Metodo de acceso al tablero. Dependiendo de como se haga la
	 * implementacion del resto de clases (principalmente de la clase
	 * Controlador), es posible que no se utilice para nada en la practica. Sin
	 * embargo, es necesario implementarlo para poder ejecutar los test de
	 * unidad.
	 * 
	 * @return Estado del tablero actual.
	 */
	public Tablero getTablero() {
		return tablero;
	}

	/**
	 * Metodo get que se utiliza para saber si hay tablas o no en la partida.
	 * 
	 * @return Estado del atributo tablas.
	 */
	public boolean getTablas() {
		return tablas;
	}

	@Override
	public void addObserver(PartidaObserver o) {
		obs.add(o);
		o.onReset(tablero, turno);
	}

	@Override
	public void removeObserver(PartidaObserver o) {
		obs.remove(o);
	}

	/**
	 * Da valores a los atributos para iniciar el juego
	 * @param reglas
	 */
	private void iniciaJuego(ReglasJuego reglas) {
		tablero = reglas.iniciaTablero();
		pila = new PilaMovimientos();
		turno = reglas.jugadorInicial();
		reg = reglas;
		ganador = Ficha.VACIA;
		terminada = false;

	}

	/**
	 * Recorre los observadores cuando se cambia el Jugador
	 */
	public void cambioJugador() {
		for (PartidaObserver o : obs){
			o.onChangePlayer(tablero);
			o.onTurno(this);
		}
	}

	/**
	 * Método que recorre los observadores cuando se ejecuta la ayuda
	 */
	public void ayuda() {
		for (PartidaObserver o : obs)
			o.onAyuda(tablero);
	}
	/**
	 * Método que recorre los observadores cuando se ejecuta un error
	 */
	public void error() {
		for (PartidaObserver o : obs)
			o.onError(tablero);
	}

}