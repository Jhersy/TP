package tp.pr4.logic;

/**
 * Implementacion de las reglas del Complica. Se deben implementar todos los
 * m�todos del interfaz, adem�s del constructor.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus Orza
 * 
 */

public class ReglasComplica implements ReglasJuego {

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		return cuatroEnLinea(ultimoMovimiento.color, ultimoMovimiento.columna,
				ultimoMovimiento.fila, t);
	}

	@Override
	public Tablero iniciaTablero() {

		return new Tablero(4, 7);
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	/**
	 * Devuelve el color del jugador al que le toca poner.
	 */
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {

		if (ultimoEnPoner == Ficha.NEGRA)
			return Ficha.BLANCA;
		else
			return Ficha.NEGRA;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {

		return false;
	}

	public Ficha cuatroEnLinea(Ficha turno, int col, int fila, Tablero tablero) {

		// dx y dy se utilizan para indicar la direccion por la que tiene que
		// mirar
		// -1,-1 = diagonal izquierda arriba
		// -1, 0 = horizontal derecha
		// -1, 1 = diagonal izquierda arriba
		// 0, 1 = vertical abajo
		// 1, 1 = diagonal derecha abajo
		// 1, 0 = horizontal derecha
		// 1,-1 = diagonal derecha arriba
		// 0,-1 = vertical arriba

		int dx = -1, dy = -1, seguidas = 0, cont = 0, f = tablero.getAlto();
		boolean gananBlancas = false, gananNegras = false;
		Ficha fichaPorVer = tablero.getCasilla(col, f), fichaGanadora = Ficha.VACIA;

		while (f > 0 && fichaPorVer != Ficha.VACIA) {
			// COMPRUEBA TODAS LAS FORMAS EN LAS QUE SE PUEDA ENCONTRAR 4 EN
			// LINEA
			while (cont < 4 && seguidas < 4) {
				seguidas = tablero.comprobarFichas(col, f, fichaPorVer, dx, dy);
				if (dy == 1) {
					dx++;
					dy = -1;
				} else {
					dy++;
				}
				cont++;
			}
			if (seguidas >= 4) {
				if (fichaPorVer == Ficha.BLANCA)
					gananBlancas = true;
				else if (fichaPorVer == Ficha.NEGRA)
					gananNegras = true;

				seguidas = 0;
			}
			cont = 0;
			dx = -1;
			dy = -1;
			f--;
			fichaPorVer = tablero.getCasilla(col, f);
		}

		if (gananBlancas && !gananNegras)
			fichaGanadora = Ficha.BLANCA;
		else if (!gananBlancas && gananNegras)
			fichaGanadora = Ficha.NEGRA;

		return fichaGanadora;
	}


}
