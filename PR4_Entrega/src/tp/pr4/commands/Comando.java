package tp.pr4.commands;

import tp.pr4.logic.MovimientoInvalido;

/**
 * Interfaz que agrupa todas los posibles opciones del juego
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */

public interface Comando {
	/**
	 * Compara si la cadena introducida es algún comando
	 * 
	 * @param cmd
	 *            Cadena introducida
	 * @return True si es válida
	 */
	public boolean analiza(String cmd);

	/**
	 * Una vez que ha encontrado el comando, este método realiza la función de
	 * ese comando
	 * 
	 * @throws MovimientoInvalido
	 */
	public void ejecuta() throws MovimientoInvalido;
}
