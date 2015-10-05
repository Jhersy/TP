package tp.pr4;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import tp.pr4.control.ControladorConsola;
import tp.pr4.control.ControladorVista;
import tp.pr4.control.FactoriaComplica;
import tp.pr4.control.FactoriaConecta4;
import tp.pr4.control.FactoriaGravity;
import tp.pr4.control.FactoriaReversi;
import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.logic.Partida;
import tp.pr4.view.console.ConsoleView;
import tp.pr4.view.windows.WindowsView;

/**
 * 
 * Clase que contiene el punto de entrada a la aplicación.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class Main {
	/**
	 * 
	 * @param args
	 *            Argumentos pasados a la aplicacion.
	 * @throws ParseException
	 *             Excepcion usada para controlar los posibles errores generados
	 *             por los argumentos.
	 */

	@SuppressWarnings("resource")
	public static void main(String[] args) throws ParseException {
		FactoriaTipoJuego f;
		Partida game;
		ControladorConsola ctrlConsole;
		ControladorVista ctrlVista;
		ConsoleView console;
		Scanner in;
		int col = 0, fila = 0;

		try {

			// Definir las opciones
			Options options = new Options();

			options.addOption("g", "game", true,
					"Tipo de juego (c4, co, gr). Por defecto, c4.");
			options.getOption("g").setArgName("game");
			options.addOption("h", "help", false, "Muestra esta ayuda.");
			options.addOption("u", "ui", true,
					"Tipo de interfaz (console, window). Por defecto, console.");
			options.getOption("u").setArgName("tipo");
			options.addOption("x", "tamX", true,
					"Número de columnas del tablero (sólo para Gravity). Por defecto, 10.");
			options.getOption("x").setArgName("columnNumber");
			options.addOption("y", "tamY", true,
					"Número de filas del tablero (sólo para Gravity). Por defecto, 10.");
			options.getOption("y").setArgName("rowNumber");

			CommandLineParser parser = new PosixParser();
			CommandLine cmd = parser.parse(options, args);

			in = new Scanner(System.in);

			if (cmd.hasOption("h")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp(Main.class.getName(), options, true);

			} else if (cmd.hasOption("g")) {
				String tipoJuego = cmd.getOptionValue("g", "c4");
				String tipointerfaz = cmd.getOptionValue("u", "console");
				if (tipoJuego.toUpperCase().equals("C4")) {
					if (cmd.hasOption("u")) {
						// Opción consola
						if (tipointerfaz.toUpperCase().equals("CONSOLE")) {
							// activa la vista consola
							// Console View
							if (cmd.getArgList().size() > 0) {
								List<?> e = cmd.getArgList();
								StringBuilder str = new StringBuilder();
								for (Object o : e) {
									str.append(o.toString() + " ");
								}
								throw new ParseException(
										"Argumentos no entendidos: "
												+ str.toString());
							} else {
								f = new FactoriaConecta4();
								game = new Partida(f.creaReglas());
								ctrlConsole = new ControladorConsola(f, game,
										in);
								console = new ConsoleView(ctrlConsole, game, in);
								console.run();
							}

							// Opción ventana
						} else if (tipointerfaz.toUpperCase().equals("WINDOW")) {
							// activa la vista ventana, Log View
							if (cmd.getArgList().size() > 0) {
								List<?> e = cmd.getArgList();
								StringBuilder str = new StringBuilder();
								for (Object o : e) {
									str.append(o.toString() + " ");
								}
								throw new ParseException(
										"Argumentos no entendidos: "
												+ str.toString());
							} else {
								f = new FactoriaConecta4();
								game = new Partida(f.creaReglas());
								ctrlVista = new ControladorVista(f, game);
								new WindowsView(ctrlVista, game);

							}
						} else
							throw new ParseException("Interfaz'" + tipointerfaz
									+ "' incorrecta.");
					}

				} else if (tipoJuego.toUpperCase().equals("CO")) {
					if (cmd.hasOption("u")) {
						if (tipointerfaz.toUpperCase().equals("CONSOLE")) {
							if (cmd.getArgList().size() > 0) {
								List<?> e = cmd.getArgList();
								StringBuilder str = new StringBuilder();

								for (Object o : e) {
									str.append(o.toString() + " ");
								}
								throw new ParseException(
										"Argumentos no entendidos: "
												+ str.toString());
							} else {
								f = new FactoriaComplica();
								game = new Partida(f.creaReglas());
								ctrlConsole = new ControladorConsola(f, game,
										in);
								console = new ConsoleView(ctrlConsole, game, in);
								console.run();
							}

						} else if (tipointerfaz.toUpperCase().equals("WINDOW")) {
							if (cmd.getArgList().size() > 0) {
								List<?> e = cmd.getArgList();
								StringBuilder str = new StringBuilder();

								for (Object o : e) {
									str.append(o.toString() + " ");
								}
								throw new ParseException(
										"Argumentos no entendidos: "
												+ str.toString());
							} else {
								f = new FactoriaComplica();
								game = new Partida(f.creaReglas());
								ctrlVista = new ControladorVista(f, game);
								new WindowsView(ctrlVista, game);
							}
						} else
							throw new ParseException("Interfaz'" + tipointerfaz
									+ "' incorrecta.");
					}

				} else if (tipoJuego.toUpperCase().equals("GR")) {
					if (cmd.hasOption("x") && cmd.hasOption("y")) {
						col = Integer.parseInt(cmd.getOptionValue("x", "10"));
						fila = Integer.parseInt(cmd.getOptionValue("y", "10"));
						f = new FactoriaGravity(col, fila);
						if (cmd.hasOption("u")) {
							if (tipointerfaz.toUpperCase().equals("CONSOLE")) {
								game = new Partida(f.creaReglas());
								ctrlConsole = new ControladorConsola(f, game,
										in);
								console = new ConsoleView(ctrlConsole, game, in);
								console.run();
							} else if (tipointerfaz.toUpperCase().equals(
									"WINDOW")) {
								// activa la vista ventana
								game = new Partida(f.creaReglas());
								ctrlVista = new ControladorVista(f, game);
								new WindowsView(ctrlVista, game);
							} else
								throw new ParseException("Interfaz'"
										+ tipointerfaz + "' incorrecta.");

						}
					}

				} else if (tipoJuego.toUpperCase().equals("RV")) {
					if (cmd.hasOption("u")) {
						if (tipointerfaz.toUpperCase().equals("CONSOLE")) {
							if (cmd.getArgList().size() > 0) {
								List<?> e = cmd.getArgList();
								StringBuilder str = new StringBuilder();

								for (Object o : e) {
									str.append(o.toString() + " ");
								}
								throw new ParseException(
										"Argumentos no entendidos: "
												+ str.toString());
							} else {
								f = new FactoriaReversi();
								game = new Partida(f.creaReglas());
								ctrlConsole = new ControladorConsola(f, game,
										in);
								console = new ConsoleView(ctrlConsole, game, in);
								console.run();
							}

						} else if (tipointerfaz.toUpperCase().equals("WINDOW")) {
							if (cmd.getArgList().size() > 0) {
								List<?> e = cmd.getArgList();
								StringBuilder str = new StringBuilder();

								for (Object o : e) {
									str.append(o.toString() + " ");
								}
								throw new ParseException(
										"Argumentos no entendidos: "
												+ str.toString());
							} else {
								f = new FactoriaReversi();
								game = new Partida(f.creaReglas());
								ctrlVista = new ControladorVista(f, game);
								new WindowsView(ctrlVista, game);
							}
						} else
							throw new ParseException("Interfaz'" + tipointerfaz
									+ "' incorrecta.");
					}

				} else
					throw new ParseException("Juego '" + tipoJuego
							+ "' incorrecto.");

			} else if (cmd.getArgList().isEmpty()) {
				// Juego por defecto
				f = new FactoriaConecta4();
				game = new Partida(f.creaReglas());
				ctrlVista = new ControladorVista(f, game);
				new WindowsView(ctrlVista, game);

			} else {
				throw new ParseException("Unrecognized option: ");

			}
		} catch (ParseException e) {
			System.err.println("Uso incorrecto: " + e.getMessage()
					+ "\nUse -h|--help para mas detalles.");
			System.exit(1);
		}

	}

}
