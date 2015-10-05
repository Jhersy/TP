package tp.pr4.logic;


/**
 * Almacena la información de un tablero rectangular. El tamaño se fija en el
 * momento de la construcción, y contiene métodos para acceder a la información
 * de cada celda y para colocar fichas.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class Tablero {

	private Ficha[][] tablero;
	private int ancho;
	private int alto;
	private int colFinal;
	private int filaFinal;


	/**
	 * Construye un tablero vacio.
	 * 
	 * @param ancho
	 *            Longitud en la coordenada x (o numero de columnas).
	 * @param alto
	 *            Longitud en la coordenada y (o numero de filas).
	 */
	public Tablero(int ancho, int alto) {
		reset(ancho, alto);
	}

	private void reset(int ancho, int alto) {

		if (alto < 1 || ancho < 1) {
			this.tablero = new Ficha[1][1];
			this.ancho = 1;
			this.alto = 1;
		} else {
			this.ancho = ancho;
			this.alto = alto;
			this.tablero = new Ficha[ancho][alto];
		}
		for (int j = 1; j <= alto; j++) {
			for (int i = 1; i <= ancho; i++) {
				setCasilla(i, j, Ficha.VACIA);
			}
		}

	}

	/**
	 * Metodo para obtener el alto del tablero.
	 * 
	 * @return Numero de filas del tablero.
	 */
	public int getAlto() {
		return this.alto;
	}

	/**
	 * Metodo para obtener el ancho del tablero.
	 * 
	 * @return Numero de columnas del tablero.
	 */
	public int getAncho() {
		return this.ancho;
	}

	/**
	 * Metodo para acceder a la informacion de una casilla o celda concreta.
	 * 
	 * @param x
	 *            Numero de columna (1 ... ancho)
	 * @param y
	 *            Numero de fila (1 ... alto)
	 * @return Informacion de la casilla. Si la casilla no es valida, devuelve
	 *         Ficha.VACIA
	 */
	public Ficha getCasilla(int x, int y) {
		if (!comprobarPosicion(x, y)) {
			return Ficha.VACIA;
		} else
			return tablero[x - 1][y - 1];
	}

	/**
	 * Metodo para comprobar si es correcta la posicion
	 * 
	 * @param columna
	 * @param fila
	 * @return true si es correcta
	 */
	public boolean comprobarPosicion(int columna, int fila) {
		if (columna - 1 < 0 || columna > ancho || fila - 1 < 0 || fila > alto)
			return false;
		else
			return true;
	}

	/**
	 * Permite especificar el nuevo contenido de una casilla. Tambien puede
	 * utilizarse para quitar una ficha.
	 * 
	 * @param columna
	 *            Numero de la columna (1 ... ancho)
	 * @param fila
	 *            Numero de la fila (1 ... alto)
	 * @param color
	 *            Color de la casilla. Si se indica Ficha.VACIA, la celda
	 *            quedara sin ficha
	 */
	public void setCasilla(int columna, int fila, Ficha color) {
		if (comprobarPosicion(columna, fila))
			tablero[columna - 1][fila - 1] = color;

		colFinal = columna - 1;
		filaFinal = fila - 1;
	}

	/**
	 * Transforma un objeto de la clase tablero a un String para mostrar el
	 * tablero por pantalla.
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		int k = 0;

		// Dibuja tablero y lo rellena

		for (int j = 0; j < alto; j++) {
			str.append('|');
			// cada fila del tablero
			for (int i = 0; i < ancho; i++) {
				// concatenamos a str la ficha correspondiente
				str.append(tablero[i][j].toChar());
			}
			str.append('|');
			str.append('\n');
		}

		// Dibuja linea +-------------+

		str.append('+');
		for (int i = 0; i < ancho; i++)
			str.append('-');
		str.append('+');
		str.append('\n');
		str.append(' ');

		// Dibuja numeros
		for (int i = 1; i <= ancho; i++) {
			k = i;
			while (k >= 10) {
				k = k % 10;
			}
			str.append(k);
		}

		return str.toString();
	}

	/**
	 * Metodo para poner una ficha en el tablero
	 * 
	 * @param columna
	 * @param color
	 * @return true si ha colocado la ficha correctamente
	 */
	public boolean coloca(int columna, Ficha color) {
		boolean ok = false;
		int y = hueco(columna);
		if (y > 0) {
			ok = true;
			setCasilla(columna, y, color);
		}
		return ok;
	}

	/**
	 * Metodo para buscar la primera posicion libre de una columna
	 * 
	 * @param columna
	 * @return coordenada de fila
	 */
	public int hueco(int columna) {
		int j = alto;

		while (getCasilla(columna, j) != Ficha.VACIA) {
			j--;
		}
		return j;
	}

	/**
	 * Desplaza la columna hacia abajo para colocar una ficha en Complica cuando
	 * la columna esta llena
	 * 
	 * @param columna
	 * @return Ficha que se esconde al desplazar toda la columna
	 */
	public Ficha desplazaColumnaAbajo(int columna) {

		Ficha fichaOculta;
		int i = alto;
		// Guarda en fichaOculta la ficha que va a esconder del tablero
		fichaOculta = getCasilla(columna, i);
		// Mueve hacia abajo toda la columna
		while (i > 0) {
			setCasilla(columna, i, getCasilla(columna, i - 1));
			i--;
		}

		return fichaOculta;

	}

	/**
	 * Para deshacer un movimiento en Complica
	 * 
	 * @param columna
	 */
	public void desplazaColumnaArriba(int columna) {

		int i = 2;
		// Mueve toda la columna hacia arriba
		while (i <= alto) {
			setCasilla(columna, i - 1, getCasilla(columna, i));
			i++;
		}

	}

	/**
	 * Comprueba el numero de fichas que hay alineadas dada una posicion
	 * 
	 * @param col
	 *            coordenada x de la ficha sobre la que se desea comprobar
	 * @param fil
	 *            coordenada y de la ficha sobre la que se desea comprobar
	 * @param turno
	 *            color de la ficha a comprobar
	 * @param dx
	 *            direccion coordenada x (-1: hacia la izquierda, ..)
	 * @param dy
	 *            direccion sobre la coordenada y
	 * @return numero maximo de fichas que se han econtrado alineadas
	 */
	public int comprobarFichas(int col, int fil, Ficha turno, int dx, int dy) {

		int fx = dx, fy = dy, n = 1;

		// Verifica si la casilla siguiente es igual al turno actual
		while (getCasilla(col + fx, fil + fy) == turno && n < 5) {
			n++;
			// Comprueba la siguiente posicion
			fx = fx + dx;
			fy = fy + dy;
		}
		// invierte el sentido en el que comprueba
		fx = -dx;
		fy = -dy;
		// Vuelve a verificar hacia la direccion opuesta respecto la posicion
		// dada
		while (getCasilla(col + fx, fil + fy) == turno && n < 5) {
			n++;
			// Comprueba la siguiente posicion
			fx = fx - dx;
			fy = fy - dy;
		}

		return n;
	}

	/**
	 * Calcula la distancia de una ficha al borde lateral mas cercano
	 * 
	 * @param columna
	 *            de la ficha cuya distancia queremos hallar
	 * 
	 * @return casillas entre la ficha y el borde lateral mas cercano
	 */
	public int distanciaHorizontal(int columna) {
		int distancia;
		// Como necesitamos hallar la mitad, en el caso en el que una dimensión
		// sea impar, la mitad es un número decimal

		float mitad = ((float) ancho + 1) / 2;

		if (ancho == 1)
			distancia = -1;
		else if (columna < mitad)
			distancia = -columna;
		else if (columna > mitad)
			distancia = ancho - (columna - 1);
		else
			distancia = 0;
		return distancia;
	}

	/**
	 * Calcula la distancia de una ficha al borde superior o inferior (el mas
	 * cercano)
	 * 
	 * @param fila
	 *            de la ficha cuya distancia queremos hallar
	 * 
	 * @return casillas entre la ficha y el borde mas cercano
	 */
	public int distanciaVertical(int fila) {
		int distancia;
		// Como necesitamos hallar la mitad, en el caso en el que una dimensión
		// sea impar, la mitad es un número decimal

		float mitad = ((float) alto + 1) / 2;

		if (alto == 1)
			distancia = -1;
		else if (fila < mitad)
			distancia = -fila;
		else if (fila > mitad)
			distancia = alto - (fila - 1);
		else
			distancia = 0;
		return distancia;
	}

	/**
	 * Método que dada una posición en el tablero y la distancia a los bordes
	 * mas cercanos devuelve la dirección a la que ir
	 * 
	 * En las condiciones de los if else se calcula la dirección sabiendo la
	 * distancia a los bordes
	 * 
	 * @param x
	 *            coordenada x de la ficha inicialmente
	 * @param y
	 *            coordenada y de la ficha inicialmente
	 * @param cx
	 *            distancia al borde lateral mas cercano
	 * @param cy
	 *            distancia al borde superior o inferior (el mas cercano)
	 * @param color
	 *            color de la ficha
	 * 
	 */
	public void combinaResultado(int x, int y, int cx, int cy, Ficha color) {
		if (x == -1 || y == -1) {
			// La ficha está ya pegando al borde
			setCasilla(cx, cy, color);
		} else if (x < 0 && y < 0 && x == y) {
			// -1, -1 porque va hacia la izquierda y hacia arriba
			mueveFicha(cx, cy, -1, -1, color);
		} else if (x < 0 && y > 0 && Math.abs(x) == y) {
			// -1, 1 porque va hacia la izquierda y hacia abajo
			mueveFicha(cx, cy, -1, 1, color);
		} else if (x > 0 && y < 0 && x == Math.abs(y)) {
			// 1, -1 porque va hacia la derecha y hacia arriba
			mueveFicha(cx, cy, 1, -1, color);
		} else if (x > 0 && y > 0 && x == y) {
			// 1, 1 porque va hacia la derecha y hacia abajo
			mueveFicha(cx, cy, 1, 1, color);
		} else if ((Math.abs(x) < Math.abs(y) && x < 0) || x < 0 && y == 0) {
			// -1, 0 porque va hacia la izquierda
			mueveFicha(cx, cy, -1, 0, color);
		} else if ((Math.abs(x) > Math.abs(y) && y < 0) || x == 0 && y < 0) {
			// 0, -1 porque va hacia arriba
			mueveFicha(cx, cy, 0, -1, color);
		} else if ((Math.abs(x) < Math.abs(y) && x > 0) || y == 0 && x > 0) {
			// 1, 0 porque va hacia la derecha
			mueveFicha(cx, cy, 1, 0, color);
		} else if ((Math.abs(x) > Math.abs(y) && y > 0) || x == 0 && y > 0) {
			// 0, 1 porque va hacia abajo
			mueveFicha(cx, cy, 0, 1, color);
		} else {
			// La ficha se mantiene en equilibrio
			setCasilla(cx, cy, color);
		}
	}

	/**
	 * Metodo para mover la ficha en el gravity hasta que choque con un borde o
	 * con otra ficha
	 * 
	 * @param x
	 *            coordenada x original de la ficha a mover
	 * @param y
	 *            coordenada y original de la ficha a mover
	 * @param dx
	 *            desplazamiento horizontal de la ficha a mover (-1 izquierda, 1
	 *            derecha, 0 sin movimiento horizontal)
	 * @param dy
	 *            desplazamiento vertical de la ficha a mover (-1 arriba, 1
	 *            abajo, 0 sin desplazamiento vertical)
	 * @param color
	 *            color de la ficha a mover
	 */
	public void mueveFicha(int x, int y, int dx, int dy, Ficha color) {
		while (siguienteLibre(x, y, dx, dy)) {
			x += dx;
			y += dy;
		}
		setCasilla(x, y, color);
	}

	/**
	 * Método get de la columna definitiva
	 * 
	 * @return columna definitiva
	 */
	public int getColumnaFinal() {
		return colFinal;
	}

	/**
	 * Método get de la fila definitiva
	 * 
	 * @return fila definitiva
	 */
	public int getFilaFinal() {
		return filaFinal;
	}

	/**
	 * Método para saber si la siguiente casilla en una dirección dada está
	 * libre o no
	 * 
	 * @param x
	 *            coordenada x
	 * @param y
	 *            coordenada y
	 * @param dx
	 *            desplazamiento x
	 * @param dy
	 *            desplazamiento y
	 * @return true si la casilla siguiente está vacia
	 */
	public boolean siguienteLibre(int x, int y, int dx, int dy) {
		x += dx;
		y += dy;
		return (comprobarPosicion(x, y) && getCasilla(x, y) == Ficha.VACIA);
	}
	
	public Ficha siguienteFicha(int x, int y, int dx, int dy) {
		x += dx;
		y += dy;
		if (comprobarPosicion(x, y))
			return getCasilla(x, y);
		else return Ficha.VACIA;
	}

	/**
	 * Método que coloca una ficha en el juego gravity
	 * 
	 * @param columna
	 *            coordenada horizontal de la ficha a colocar
	 * @param fila
	 *            coordenada vertical de la ficha a colocar
	 * @color color de la ficha a colocar
	 */
	public void colocaGravity(int columna, int fila, Ficha color) {
		int dH = distanciaHorizontal(columna);
		int dV = distanciaVertical(fila);
		combinaResultado(dH, dV, columna, fila, color);
	}
	

}
