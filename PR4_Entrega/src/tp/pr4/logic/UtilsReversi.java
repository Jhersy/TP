package tp.pr4.logic;

public class UtilsReversi {
	
	public static boolean puedeFlanquear (Tablero tab, int columna, int fila, Ficha color){
		int dx, dy;
		int c=columna, f=fila;
		boolean flanquea = false;
		
		if(!tab.getCasilla(columna, fila).equals(Ficha.VACIA))
			return false;
		
		for(dx=-1; dx<=1; dx++){
			for(dy=-1; dy<=1; dy++){
				if(tab.comprobarPosicion(columna + dx, fila + dy)){
					//System.out.println(tab.getCasilla(columna+dx, fila+dy).toChar());
					//System.out.println(color.opuesto());
					c = columna+dx;
					f = fila+dy;
					if(tab.getCasilla(c, f).equals(color.opuesto())){
						while(tab.getCasilla(c, f).equals(color.opuesto())){
							c += dx;
							f += dy;
						}
						if(tab.getCasilla(c, f).equals(color))
							flanquea = true;
					}
				}
			}
		}
		return flanquea;
	}
	
	public static boolean sePuedePoner (Tablero tab, Ficha color){
		boolean ret = false;
		
		for(int i = 1; i <= tab.getAlto() && !ret; i++){
			for (int j = 1; j <= tab.getAncho() && !ret; j++){
				if(puedeFlanquear(tab, j, i, color))
					ret = true;
			}
		}
		return ret;
	}
	

	/*public static boolean puedeFlanquear(Tablero tab, int columna, int fila, Ficha color){
		int dx = -1;
		int dy = -1;
		int col = columna , fil = fila;
		
		boolean salir = false;
		boolean puede = false;

		while (dx <= 1 && dy <= 1 && !puede) {
			
			System.out.println("intento poner en col: " + col + " fil: " + fil);
			System.out.println("hay una " + tab.getCasilla(col, fil));
			System.out.println();
			System.out.println("ficha en la casilla " + (col + dx) + ", " +(fil+dy)+ ": " + tab.getCasilla(col+dx, fil+dy).toString());
			System.out.println("color: " + color);
			System.out.println(tab.toString());
			System.out.println();
			
			System.out.println("_________________________________________________________");
			
			
			if (tab.getCasilla(columna + dx, fila + dy).equals(
					color.opuesto())){
				while (!salir && !puede) {
					if (tab.siguienteFicha(col, fil, dx, dy).equals(color.opuesto())) {
						col += dx;
						fil += dy;
					} else if (tab.siguienteFicha(col, fil, dx, dy).equals(color)) {
						puede = true;
					} else
						salir = true;
				}	
			}
			if (dy == 1) {
				dx++;
				dy = -1;
			} else 
				dy++;
			
		}
		return puede;

	}*/
}
