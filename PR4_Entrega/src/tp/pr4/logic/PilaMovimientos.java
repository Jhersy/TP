package tp.pr4.logic;

/**
 * Clase que va almacenando mediante una pila circular, todos los movimientos
 * que se van produciendo en la partida
 * 
 * @author Jhersy Nolasco Arevalo y Roberto Barrasus Orza
 * 
 */
public class PilaMovimientos {
	private Movimiento[] undoStack;
	private int numUndo;
	private int ini;
	private int fin;
	// Longitud de la pila circular
	private final int LONG = 10;

	/**
	 * Constructor de la pila de 10 movimientos
	 */
	public PilaMovimientos() {
		vacia();
		undoStack = new Movimiento[LONG];
	}

	/**
	 * Metodo que calcula el indice anterior del array circular
	 * 
	 * @param n
	 *            indice a calcular
	 * @return indice anterior
	 */
	private int anterior(int n) {
		if (n > 0 && n < LONG)
			return n - 1;
		return LONG - 1;
	}

	/**
	 * Metodo que calcula el indice siguiente del array circular
	 * 
	 * @param m
	 *            indice a calcular
	 * @return indice siguiente
	 */
	private int siguiente(int m) {
		if (m >= 0 && m < (LONG - 1))
			return m + 1;
		return 0;
	}

	/**
	 * Apila movimientos en la pila
	 * 
	 * @param nuevoMov
	 *            Es el nuevo movimento a poner en la pila
	 */
	public void apilar(Movimiento nuevoMov) {

		if (ini == fin && numUndo > 0) {
			ini = siguiente(ini);
		} else {
			numUndo++;
		}
		undoStack[fin] = nuevoMov;
		fin = siguiente(fin);
	}

	/**
	 * Metodo para quitar el ultimo movimiento de la pila
	 * 
	 * @return True si hay elemento para desapilar
	 */
	public boolean desapilar() {

		boolean ok = false;
		if (numUndo > 0) {
			ok = true;
			numUndo--;
			fin = anterior(fin);
		}
		return ok;
	}

	/**
	 * Metodo para saber el ultimo movimiento que se ha almacenado en la pila
	 * 
	 * @return Devuelve la cima de la pila
	 */
	public Movimiento cima() {
		Movimiento mov;
		mov = undoStack[anterior(fin)];
		return mov;
	}

	/**
	 * Vacia la pila
	 */
	public void vacia() {
		numUndo = 0;
		ini = 0;
		fin = 0;
	}

	/**
	 * Comprueba si la pila esta vacia
	 * 
	 * @return true si esta vacia
	 */
	public boolean esVacia() {
		return numUndo == 0;
	}
}