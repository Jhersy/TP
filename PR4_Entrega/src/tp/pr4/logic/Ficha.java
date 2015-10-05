package tp.pr4.logic;

import java.awt.Color;

/**
 * Repesenta la informacion del color de una ficha. El enumerado es utilizado
 * para guardar la informacion de cada posicion de los tableros, por lo que
 * contiene tambien un simbolo para indicar la ausencia de ficha en esa
 * posicion. Tambien se utiliza para el color de un jugador.
 * 
 * @author Jhersy Andres Nolasco y Roberto Barrasus
 * 
 */

public enum Ficha {
	VACIA, BLANCA, NEGRA;

	/**
	 * Metodo para pintar la ficha en el tablero
	 * 
	 * @return un char para usarlo cuando se pinta el tablero
	 */
	public char toChar() {
		if (this == NEGRA)
			return 'X';
		if (this == BLANCA)
			return 'O';
		else
			return ' ';
	}

	/**
	 * Metodo para imprimir por pantalla si ganan "negras" o "blancas"
	 */

	public String toString() {
		if (this == NEGRA)
			return "negras";
		else
			return "blancas";
	}
	
	public Color color(){
		if(this == Ficha.BLANCA){
			return Color.WHITE;
		}else if (this == Ficha.NEGRA)
			return Color.BLACK;
		else 
			return Color.LIGHT_GRAY;
	}
	
	public Ficha opuesto(){
		if(this == Ficha.BLANCA){
			return Ficha.NEGRA;
		}else if (this == Ficha.NEGRA)
			return Ficha.BLANCA;
		else 
			return Ficha.VACIA;
	}
}
