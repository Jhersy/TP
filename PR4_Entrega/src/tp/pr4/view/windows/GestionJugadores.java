package tp.pr4.view.windows;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr4.control.ControladorVista;
import tp.pr4.logic.Ficha;
import tp.pr4.logic.Observable;
import tp.pr4.logic.Partida;
import tp.pr4.logic.PartidaObserver;
import tp.pr4.logic.Tablero;

public class GestionJugadores extends JPanel implements PartidaObserver {

	private static final long serialVersionUID = 1L;
	private ControladorVista ctrl;
	private JComboBox<String> comboJugadorBlancas;
	private JComboBox<String> comboJugadorNegras;

	final int numJugadores = 2;
	JugadorSwing[] jugadorSwing = new JugadorSwing[numJugadores];
	private Jugadores jugador;

	public GestionJugadores(ControladorVista ctrl,
			Observable<PartidaObserver> game) {

		jugadorSwing[0] = new JugadorSwingHumano(ctrl);
		jugadorSwing[1] = new JugadorSwingHumano(ctrl);
		this.ctrl = ctrl;
		initGUI();
		game.addObserver(this);
	}

	public enum Jugadores {
		jugadorBlancasAutomatico, jugadorBlancasHumano, jugadorNegrasAutomatico, jugadorNegrasHumano;
	}

	private void initGUI() {

		this.setBorder(new TitledBorder("Gestion de jugadores"));

		this.setLayout(new GridLayout(2, 1));

		comboJugadorBlancas = new JComboBox<String>();
		comboJugadorBlancas.setBorder(new TitledBorder("Jugador de blancas"));
		comboJugadorBlancas.setPreferredSize(new Dimension(50, 50));
		comboJugadorBlancas.addItem("HUMANO");
		comboJugadorBlancas.addItem("AUTOMATICO");
		comboJugadorNegras = new JComboBox<String>();

		comboJugadorNegras.setPreferredSize(new Dimension(50, 10));
		comboJugadorNegras.setBorder(new TitledBorder("Jugador de negras"));
		comboJugadorNegras.addItem("HUMANO");
		comboJugadorNegras.addItem("AUTOMATICO");

		this.add(comboJugadorBlancas);
		this.add(comboJugadorNegras);

		comboJugadorBlancas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (comboJugadorBlancas.getSelectedItem().equals("HUMANO")) {
					// deberia poner el array jugador[0] a jugadorSwingHumano
					// llamada al setJugador
					setJugadorSwing(Ficha.BLANCA, new JugadorSwingHumano(ctrl));
					jugador = Jugadores.jugadorBlancasHumano;

				} else if (comboJugadorBlancas.getSelectedItem().equals(
						"AUTOMATICO")) {
					setJugadorSwing(Ficha.BLANCA, new JugadorSwingAutomatico(
							ctrl));

					jugador = Jugadores.jugadorBlancasAutomatico;
					// deberia poner el array jugador[1] a
					// jugadorSwingAutomatico

				}
			}
		});

		comboJugadorNegras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboJugadorNegras.getSelectedItem().equals("HUMANO")) {
					setJugadorSwing(Ficha.NEGRA, new JugadorSwingHumano(ctrl));
					jugador = Jugadores.jugadorNegrasHumano;
				} else if (comboJugadorNegras.getSelectedItem().equals(
						"AUTOMATICO")) {
					// deberia poner el array jugador[1] a
					// jugadorSwingAutomatico
					setJugadorSwing(Ficha.NEGRA, new JugadorSwingAutomatico(
							ctrl));
					
					jugador = Jugadores.jugadorNegrasAutomatico;
				}

			}
		});

	}

	private void setJugador2(Ficha ficha, JugadorSwing jugador){
		if (ficha.equals(Ficha.BLANCA)) {
			jugadorSwing[0].pierdeTurno();
			jugadorSwing[0] = jugador;

		} else {
			jugadorSwing[1].pierdeTurno();
			jugadorSwing[1] = jugador;
		}
		
	}
	
	
	
	
	private void setJugadorSwing(Ficha ficha, JugadorSwing jugador) {

		if (ficha.equals(Ficha.BLANCA)) {
			jugadorSwing[0].pierdeTurno();
			jugadorSwing[0] = jugador;

		} else {
			jugadorSwing[1].pierdeTurno();
			jugadorSwing[1] = jugador;
		}

		if (ctrl.getTurno().equals(Ficha.BLANCA)) {
			jugadorSwing[0].recibeTurno();
		} else {
			jugadorSwing[1].recibeTurno();
		}

	}

	@Override
	public void onReset(Tablero tab, Ficha turno) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMove(Partida game) {
	}

	@Override
	public void onUndo(Partida game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPartidaEnd(Partida game) {
		jugadorSwing[0].pierdeTurno();
		jugadorSwing[1].pierdeTurno();

	}

	@Override
	public void onChangeGame(Tablero tab) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMoveIncorrect(Partida game, String error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCambioJugador(Partida game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUndoImposible(Tablero tab) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChangePlayer(Tablero tab) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAyuda(Tablero tab) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(Tablero tablero) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTurno(Partida game) {

		if (game.getTurno() == Ficha.BLANCA) {
				jugadorSwing[0].recibeTurno();
		} else {
			jugadorSwing[1].recibeTurno();
		}

	}

}
