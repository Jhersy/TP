package tp.pr4.logic;

import java.util.ArrayList;

public class MovimientoReversi extends Movimiento {
	private boolean haFlanqueado;
	
	private ArrayList<Coordenada> flanqueadas = new ArrayList<Coordenada>();

	public MovimientoReversi(int fila, int col, Ficha color) {
		this.fila = fila;
		this.columna = col;
		this.color = color;
		haFlanqueado = false;
	}

	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if (columna > 0 && columna <= tab.getAncho()) {
			if (fila > 0 && fila <= tab.getAlto()) {
				if (tab.getCasilla(columna, fila) == Ficha.VACIA) {
					/*if (direccionFlanquear(tab))
						tab.setCasilla(columna, fila, color);*/
					if (UtilsReversi.puedeFlanquear(tab, columna, fila, color)){
						flanqueaFichas(tab);
						for(Coordenada c: flanqueadas)
							tab.setCasilla(c.y, c.x, color);
						tab.setCasilla(columna, fila, color);
					} else {
						throw new MovimientoInvalido(
								"No se puede colocar en esta casilla.");
					}
				} else
					throw new MovimientoInvalido("Casilla ocupada.");
			} else
				throw new MovimientoInvalido("Posición incorrecta.");
		} else
			throw new MovimientoInvalido("Posición incorrecta.");

	}

	@Override
	public void undo(Tablero tab) {
		/*
		 * tiene que guardar de alguna manera las fichas que ha cambiado de
		 * color, para poder ponerlas como estaban antes
		 */
		// NO SE SI ESTA BIEN ASI
		/*for (int i = 0; i < j; i++) {
			tab.setCasilla(arrayFlanqueados[i][0], arrayFlanqueados[i][1], tab
					.getCasilla(arrayFlanqueados[i][0], arrayFlanqueados[i][1])
					.opuesto());
		}*/
		for(Coordenada c: flanqueadas){
			tab.setCasilla(c.x, c.y, color.opuesto());
		}

		tab.setCasilla(columna, fila, Ficha.VACIA);
	}

	private boolean flanquea(Tablero tab, int dx, int dy) {
		int i = 0;
		int col = columna, fil = fila;
		boolean salir = false;
		boolean puede = false;

		while (!salir && ! puede) {
			if (tab.siguienteFicha(col, fil, dx, dy).equals(color.opuesto())) {
				col += dx;
				fil += dy;
				i++;
			} else if (tab.siguienteFicha(col, fil, dx, dy).equals(color)) {
				puede = true;
				haFlanqueado = true;
			} else
				salir = true;
		}

		if (puede) {
			col = columna + dx;
			fil = fila + dy;
			while (i > 0) {
				//tab.setCasilla(col, fil, color);
				//arrayFlanqueados[this.j][0] = col;
				//arrayFlanqueados[this.j][1] = fil;
				//	j++;
				flanqueadas.add(new Coordenada(fil, col));
				System.out.println("Flanqueo en la fila:" + col + " col:" + fil);
				col += dx;
				fil += dy;
				i--;
			}
		}
		return haFlanqueado;
	}
	
	public boolean flanqueaFichas(Tablero tab){
		int dx = -1;
		int dy = -1;
		boolean flanquea = false;

		while (dx <= 1 && dy <= 1) {
			if (tab.getCasilla(this.columna + dx, this.fila + dy).equals(
					this.color.opuesto())) 
				flanquea = flanquea(tab, dx, dy);
			
			if (dy == 1) {
				dx++;
				dy = -1;
			} else 
				dy++;
			
		}
		return flanquea;

	}
	
	/*
	public static void main(String[] args) {
		Tablero t = new Tablero (8,8);
		
	}*/

}
