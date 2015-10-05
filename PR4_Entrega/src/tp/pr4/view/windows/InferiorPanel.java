package tp.pr4.view.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr4.control.ControladorVista;
import tp.pr4.logic.Ficha;
import tp.pr4.logic.MovimientoInvalido;
import tp.pr4.logic.Observable;
import tp.pr4.logic.Partida;
import tp.pr4.logic.PartidaObserver;
import tp.pr4.logic.Tablero;

/**
 * Clase que implementara los paneles de salir y aleatorio
 */
public class InferiorPanel extends JPanel implements PartidaObserver {

	private static final long serialVersionUID = 1L;

	private ControladorVista ctrl;
	private JButton aleatorioButton;
	private JButton salirButton;

	/**
	 * Constructor
	 * @param ctrl
	 * @param game
	 */
	public InferiorPanel(ControladorVista ctrl, Observable<PartidaObserver> game) {
		this.ctrl = ctrl;
		initGUI();
		game.addObserver(this);

	}

	/**
	 * Inicializar panel
	 */
	private void initGUI() {
		this.setLayout(new BorderLayout());

		// BOTON ALEATORIO
		aleatorioButton = new JButton("Poner Aleatorio");
		aleatorioButton.setSize(10, 10);
		aleatorioButton.setIcon(new ImageIcon( new ImageIcon("aleatorio.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));

		this.add(aleatorioButton);
		aleatorioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * tiene que saber que jugador tiene el turno, tiene que despues
				 * de ejecutar el aleatorio, ejecutar el comando poner
				 */			
				try {
					ctrl.aleatorio();
				} catch (MovimientoInvalido e) {
					e.printStackTrace();
				}
			}
		});

		salirButton = new JButton("Salir");
		salirButton.setIcon(new ImageIcon( new ImageIcon("salir.png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));

		this.add(salirButton);
		salirButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				salir();
			}
		});
		aleatorioButton.setBackground(Color.lightGray);
		salirButton.setBackground(Color.RED);
		
		this.add(aleatorioButton, BorderLayout.WEST);
		this.add(salirButton, BorderLayout.EAST);

	}

	@Override
	public void onReset(Tablero tab, Ficha turno) {	
		aleatorioButton.setEnabled(true);
	}


	@Override
	public void onUndo(Partida game) {
	}

	private void salir() {
		int n = JOptionPane.showOptionDialog(new JFrame(),
				"¿Desea salir de la aplicación?", "Salir",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);

		if (n == 0) {
			System.exit(0);
		}

	}

	@Override
	public void onMove(Partida game) {
	}

	@Override
	public void onPartidaEnd(Partida game) {
		aleatorioButton.setEnabled(false);
	}

	@Override
	public void onChangeGame(Tablero tab) {
	}

	@Override
	public void onMoveIncorrect(Partida game, String error) {
	}

	@Override
	public void onCambioJugador(Partida game) {
	}

	@Override
	public void onUndoImposible(Tablero tab) {
	}

	@Override
	public void onChangePlayer(Tablero tab) {
	}

	@Override
	public void onAyuda(Tablero tab) {	
	}

	@Override
	public void onError(Tablero tablero) {	
	}

	@Override
	public void onTurno(Partida game) {
		// TODO Auto-generated method stub
		
	}


}
