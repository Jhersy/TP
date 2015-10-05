package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.Ficha;
import tp.pr4.logic.Movimiento;
import tp.pr4.logic.MovimientoReversi;
import tp.pr4.logic.ReglasJuego;
import tp.pr4.logic.ReglasReversi;

public class FactoriaReversi implements FactoriaTipoJuego {

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioReversi();
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoReversi(in);
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoReversi(fila, col, color);
	}

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasReversi();
	}

}
