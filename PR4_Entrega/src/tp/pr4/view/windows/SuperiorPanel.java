package tp.pr4.view.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr4.logic.Observable;
import tp.pr4.control.ControladorVista;
import tp.pr4.logic.Ficha;
import tp.pr4.logic.Partida;
import tp.pr4.logic.PartidaObserver;
import tp.pr4.logic.Tablero;

/**
 * Clase que implementa los paneles deshacer y reiniciar
 */
public class SuperiorPanel extends JPanel implements PartidaObserver {
	private static final long serialVersionUID = 1L;
	private JButton deshacerButton;
	private JButton reiniciarButton;
	private ControladorVista ctrl;

	/**
	 * Constructor del panel
	 * @param ctrl
	 * @param game
	 */
	public SuperiorPanel(ControladorVista ctrl, Observable<PartidaObserver> game) {
		this.ctrl = ctrl;
		initGUI();
		game.addObserver(this);
	}

	/**
	 * Inicializar panel
	 */
	private void initGUI() {
		this.setBorder(new TitledBorder("Partida"));
		this.setSize(new Dimension(200, 50));
		// BOTON DESHACER
		deshacerButton = new JButton("Deshacer");
		deshacerButton.setIcon(new ImageIcon( new ImageIcon("deshacer.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));


		// ACCION
		deshacerButton.setBackground(Color.YELLOW);
		this.add(deshacerButton);
		deshacerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.undo();
			}
		});

		// BOTON REINICIAR
		reiniciarButton = new JButton("Reiniciar");
		reiniciarButton.setIcon(new ImageIcon( new ImageIcon("reiniciar.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
		reiniciarButton.setToolTipText("Reiniciar");


		// ACCION
		reiniciarButton.setBackground(Color.BLUE);
		this.add(reiniciarButton);

		reiniciarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.reset();
			}
		});

	}

	@Override
	public void onReset(Tablero tab, Ficha turno) {
		deshacerButton.setEnabled(false);
		reiniciarButton.setEnabled(true);
	}

	@Override
	public void onUndo(Partida game) {

	}

	@Override
	public void onMove(Partida game) {
		deshacerButton.setEnabled(true);

	}

	@Override
	public void onPartidaEnd(Partida game) {
		deshacerButton.setEnabled(false);

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
	public void onUndoImposible(Tablero tab){
		deshacerButton.setEnabled(false);

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
		// TODO Auto-generated method stub
		
	}


}
