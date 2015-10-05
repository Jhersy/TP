package tp.pr4.view.windows;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import tp.pr4.control.ControladorVista;
import tp.pr4.logic.Ficha;
import tp.pr4.logic.Observable;
import tp.pr4.logic.Partida;
import tp.pr4.logic.PartidaObserver;
import tp.pr4.logic.Tablero;

/**
 * Panel que forman el tablero de fichas y el letrero del turno
 * @author Jhersy y Roberto
 *
 */
public class TableroPanel extends JPanel implements PartidaObserver {

	private static final long serialVersionUID = 1L;

	private ControladorVista ctrl;
	private TableroBasePanel tablero;
	private Observable<PartidaObserver> game;
	private JTextField turnoJugador;

	/**
	 * Constructor del panel
	 * @param ctrl
	 * @param game
	 */
	public TableroPanel(ControladorVista ctrl,
			Observable<PartidaObserver> game) {
		this.ctrl = ctrl;
		this.game = game;
		initGUI();
		game.addObserver(this);
	}

	/**
	 * Inicializar el panel tablero
	 */
	private void initGUI() {
		this.setLayout(new FlowLayout());

		tablero = new TableroBasePanel(ctrl, game);

		turnoJugador = new JTextField("Juegan blancas");
		turnoJugador.setPreferredSize(new Dimension(300, 50));
		turnoJugador.setHorizontalAlignment(JTextField.CENTER);
		setPreferredSize(new Dimension(300, 300));
		this.add(tablero);
		this.add(turnoJugador);

	}

	@Override
	public void onReset(Tablero tab, Ficha turno) {
		turnoJugador.setEnabled(true);
		turnoJugador.setText("Juegan " + turno.toString());

	}

	@Override
	public void onMove(Partida game) {

	}

	@Override
	public void onUndo(Partida game) {
		if (game.getTurno().equals(Ficha.BLANCA))
			turnoJugador.setText("Juegan blancas");
		else
			turnoJugador.setText("Juegan negras");

	}

	@Override
	public void onPartidaEnd(Partida game) {
		turnoJugador.setEnabled(false);

	}

	@Override
	public void onChangeGame(Tablero tab) {
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
		if (game.getTurno().equals(Ficha.BLANCA))
			turnoJugador.setText("Juegan negras");
		else
			turnoJugador.setText("Juegan blancas");
	}

}
