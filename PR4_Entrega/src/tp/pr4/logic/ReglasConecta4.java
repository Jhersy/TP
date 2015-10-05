package tp.pr4.logic;

/**
 * Implementacion de las reglas del Conecta 4.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus Orza
 * 
 */
public class ReglasConecta4 implements ReglasJuego {

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		return cuatroEnLinea(ultimoMovimiento.color, ultimoMovimiento.columna,
				ultimoMovimiento.fila, t);
	}

	@Override
	public Tablero iniciaTablero() {

		return new Tablero(7, 6);
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

		int i = 1, ancho = t.getAncho();
		boolean lleno = true;
		Ficha f;

		while (i <= ancho && lleno) {
			f = t.getCasilla(i, 1);
			if (f == Ficha.VACIA)
				lleno = false;
			i++;
		}
		return lleno;
	}

	@Override
	public Ficha cuatroEnLinea(Ficha turno, int col, int fila, Tablero tablero) {

		int dx = -1, dy = -1, seguidas = 0, cont = 0, fil = tablero.hueco(col) + 1;
		Ficha fichaGanadora = Ficha.VACIA;

		while (cont < 4 && seguidas < 4) {
			seguidas = tablero.comprobarFichas(col, fil, turno, dx, dy);
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


}
