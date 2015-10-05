package tp.pr4.control;

import tp.pr4.logic.Ficha;
import tp.pr4.logic.Movimiento;
import tp.pr4.logic.MovimientoReversi;
import tp.pr4.logic.Tablero;
import tp.pr4.logic.UtilsReversi;

public class JugadorAleatorioReversi implements Jugador {

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		boolean colocada = false;
		int col = 0;
		int fila = 0;
		// Hay que comprobar que la casilla que genere aleatoriamente este vacía
		while (!colocada) {

			col = (int) (Math.random() * tab.getAncho() + 1);
			fila = (int) (Math.random() * tab.getAlto() + 1);

			if (tab.getCasilla(col, fila) == Ficha.VACIA) {

				System.out.println("He probado colocar en la casilla columna: " + col
						+ " fila: " + fila);
				if(fila==6){
					if(col == 5 || col == 3)
						System.out.println("Soy retrasado.");
				}
				if (UtilsReversi.puedeFlanquear(tab, col, fila, color)) {

					System.out.println("he entrado");
					System.out.println(tab.toString());
					colocada = true;
				}
			}
		}

		return new MovimientoReversi(fila, col, color);
	}

}
