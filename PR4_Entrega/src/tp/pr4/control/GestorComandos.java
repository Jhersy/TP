package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.commands.*;
import tp.pr4.logic.MovimientoInvalido;

/**
 * Clase utilizada para administrar todos los comandos que se pueden usar en la
 * aplicación
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class GestorComandos {
	private final int MAX_COMANDOS = 7;
	private Comando[] arrayComandos;
	private ControladorConsola c;

	public GestorComandos(ControladorConsola c, Scanner in) {
		this.c = c;
		arrayComandos = new Comando[MAX_COMANDOS];

		arrayComandos[0] = new ComandoPoner(c);
		arrayComandos[1] = new ComandoDeshacer(c);
		arrayComandos[2] = new ComandoReiniciar(c);
		arrayComandos[3] = new ComandoJugar(c);
		arrayComandos[4] = new ComandoTipoJugador(c, in);
		arrayComandos[5] = new ComandoAyuda(c);
		arrayComandos[6] = new ComandoSalir(c);

	}

	/**
	 * Método que analiza la cadena que introduce el usuario y la compara con
	 * los posibles comandos del juego
	 * 
	 * @param cmd
	 *            cadena escrita por el usuario
	 * @return True si es un comando válido
	 * @throws MovimientoInvalido
	 *             si no se puede efectuar el movimiento. Es un error intentar
	 *             colocar una ficha del jugador que no tiene el turno, cuando
	 *             la partida está terminada, columna llena, etc.
	 */
	public boolean compruebaComando(String cmd) throws MovimientoInvalido {
		boolean comandoEncontrado = false;
		for (int i = 0; i < MAX_COMANDOS && !comandoEncontrado; i++) {
			if (arrayComandos[i].analiza(cmd)) {
				arrayComandos[i].ejecuta();
				comandoEncontrado = true;
			}
		}
		if (!comandoEncontrado) {
			System.err.print("No te entiendo.");
			System.out.println();
			c.error();
		}
		return comandoEncontrado;
	}
}
