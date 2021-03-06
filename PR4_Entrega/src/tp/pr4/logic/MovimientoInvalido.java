package tp.pr4.logic;

/**
 * Excepción generada cuando se intenta ejecutar un movimiento incorrecto.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class MovimientoInvalido extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor sin parámetros.
	 */
	public MovimientoInvalido() {
	}

	/**
	 * Constructor con un parámetro para el mensaje.
	 */
	public MovimientoInvalido(java.lang.String msg) {
		super(msg);
	}

	/**
	 * Constructor con un parámetro para el mensaje y otro para la causa.
	 */
	public MovimientoInvalido(java.lang.String msg, java.lang.Throwable arg) {
		super(msg, arg);
	}

	/**
	 * Constructor con un parámetro para la causa inicial que provocó la
	 * excepción
	 */
	public MovimientoInvalido(java.lang.Throwable arg) {
		super(arg);
	}

}
