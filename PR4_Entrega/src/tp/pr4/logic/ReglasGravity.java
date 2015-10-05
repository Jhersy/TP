package tp.pr4.logic;

/**
 * Implementacion de las reglas del Gravity. Se deben implementar todos los
 * métodos del interfaz, además del constructor.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus Orza
 * 
 */
public class ReglasGravity implements ReglasJuego {
	private int numCols = 10;
	private int numFilas = 10;

	public ReglasGravity(int numCols, int numFilas) {
		this.numCols = numCols;
		this.numFilas = numFilas;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {

		return cuatroEnLinea(ultimoMovimiento.color, t.getColumnaFinal(),
				t.getFilaFinal(), t);
	}

	@Override
	public Tablero iniciaTablero() {
		return new Tablero(numCols, numFilas);
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		if (ultimoEnPoner == Ficha.NEGRA)
			return Ficha.BLANCA;
		else
			return Ficha.NEGRA;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return tableroLleno(t);
	}

	@Override
	public Ficha cuatroEnLinea(Ficha turno, int col, int fila, Tablero tablero) {

		int dx = -1, dy = -1, seguidas = 0, cont = 0;
		Ficha fichaGanadora = Ficha.VACIA;

		while (cont < 4 && seguidas < 4) {
			seguidas = tablero
					.comprobarFichas(col + 1, fila + 1, turno, dx, dy);
			if (dy == 1) {
				dx++;
				dy = -1;
			} else {
				dy++;
			}
			cont++;
		}
		if (seguidas > 3) {
			fichaGanadora = turno;
		}
		return fichaGanadora;

	}

	private boolean tableroLleno(Tablero t){
		boolean lleno = true;
		for (int i = 1; i <= t.getAncho() && lleno; i++) {
			for (int j = 1; j <= t.getAlto() && lleno; j++) {
				if (t.getCasilla(i, j) == Ficha.VACIA)
					lleno = false;
			}
		}
		return lleno;
	}
}
