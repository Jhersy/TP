package tp.pr4.logic;

public class ReglasReversi implements ReglasJuego {

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		/*
		 * Comprueba todo el tablero, y mira que jugador tiene mas fichas.
		 */
		int blancas = 0, negras = 0;
		if (tableroLleno(t)) {
			for (int i = 1; i <= t.getAncho(); i++) {
				for (int j = 1; j <= t.getAlto(); j++) {
					if (t.getCasilla(i, j) == Ficha.BLANCA)
						blancas++;
					else if (t.getCasilla(i, j) == Ficha.NEGRA)
						negras++;
				}
			}
			if (blancas > negras)
				return Ficha.BLANCA;
			else if (negras > blancas)
				return Ficha.NEGRA;
			else
				return Ficha.VACIA;
		} 
		// MIRAR AAAAAA
		else
			return Ficha.VACIA;
	}

	@Override
	public Tablero iniciaTablero() {

		Tablero tablero = new Tablero(8, 8);
		tablero.setCasilla(4, 5, Ficha.NEGRA);
		tablero.setCasilla(4, 4, Ficha.BLANCA);
		tablero.setCasilla(5, 5, Ficha.BLANCA);
		tablero.setCasilla(5, 4, Ficha.NEGRA);

		return tablero;
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.NEGRA;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		if (UtilsReversi.sePuedePoner(t, ultimoEnPoner.opuesto())){
			if (ultimoEnPoner == Ficha.NEGRA)
				return Ficha.BLANCA;
			else
				return Ficha.NEGRA;
		}
		else return ultimoEnPoner;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		/*
		 * Tiene que comprobar todo el tablero, y ver si las blancas y negras
		 * tienen el mismo numero de fichas
		 */
		int blancas = 0, negras = 0;
		for (int i = 1; i <= t.getAncho(); i++) {
			for (int j = 1; j <= t.getAlto(); j++) {
				if (t.getCasilla(i, j) == Ficha.BLANCA)
					blancas++;
				else if (t.getCasilla(i, j) == Ficha.NEGRA)
					negras++;
			}
		}
		return blancas == negras && tableroLleno(t);
	}

	@Override
	public Ficha cuatroEnLinea(Ficha turno, int col, int fila, Tablero tablero) {
		/*
		 * NO SE VA A USAR PARA NADA
		 */
		return null;
	}

	/**
	 * metodo para ver si un tablero esta lleno
	 */

	private boolean tableroLleno(Tablero t) {
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
