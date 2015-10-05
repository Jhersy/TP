package tp.pr4.view.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr4.control.ControladorVista;
import tp.pr4.logic.Ficha;
import tp.pr4.logic.Observable;
import tp.pr4.logic.Partida;
import tp.pr4.logic.PartidaObserver;
import tp.pr4.logic.Tablero;

/**
 * Clase ventana principal
 * 
 * @author Jhersy Nolasco y Rober Barrasus
 * 
 */
public class WindowsView extends JFrame implements PartidaObserver {


	private static final long serialVersionUID = 1L;

	private Observable<PartidaObserver> game;
	private ControladorVista ctrl;


	/**
	 * Constructor panel principal
	 * 
	 * @param ctrl
	 * @param game
	 */
	public WindowsView(ControladorVista ctrl, Observable<PartidaObserver> game) {

		super("PRACTICA 4 - TP");
		this.game = game;
		this.ctrl = ctrl;
		this.setResizable(true);
		initGUI();
		game.addObserver(this);
	}

	/**
	 * Inicializar la vista ventana
	 */
	private void initGUI() {
		//inicio();
		JMenuBar barraMenu = new JMenuBar();
		JMenu ayuda = new JMenu("Ayuda");
		JMenuItem verAyuda = new JMenuItem("Ver ayuda");
		JMenuItem aCercaDe = new JMenuItem("A cerca de");
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);

		JPanel panelDerecho = new JPanel(new GridLayout(3, 1));

		JPanel partidaPanel = new SuperiorPanel(ctrl, game);
		JPanel inferiorPanel = new InferiorPanel(ctrl, game);
		JPanel tableroPanel = new TableroPanel(ctrl, game);
		JPanel juegoPanel = new JuegoPanel(ctrl, game);
		JPanel jugadores = new GestionJugadores(ctrl, game);

		panelDerecho.add(partidaPanel);
		panelDerecho.add(jugadores);
		panelDerecho.add(juegoPanel);

		ayuda.add(verAyuda);
		verAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.ayuda();
			}
		});

		ayuda.add(aCercaDe);
		aCercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ac = new String(
						"<html><body>Práctica de Tecnología de la Programación.<br>"
								+ "Jhersy Andrés Nolasco Arévalo<br>Roberto Barrasús Orza");
				JLabel aCLabel = new JLabel(ac);
				JOptionPane.showMessageDialog(null, aCLabel);
			}
		});

		barraMenu.add(ayuda);

		mainPanel.add(barraMenu, BorderLayout.NORTH);
		mainPanel.add(panelDerecho, BorderLayout.EAST);

		mainPanel.add(inferiorPanel, BorderLayout.SOUTH);

		mainPanel.add(tableroPanel, BorderLayout.WEST);

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				salir();
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

		});
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.pack();
		Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((pantallaTamano.width / 2) - (this.getWidth() / 2),
				(pantallaTamano.height / 2) - (this.getHeight() / 2));
		this.setVisible(true);

	}

	
	/**
	 * Panel de la imagen del principio de la ejecución en ventana
	 */
	private void inicio() {
		JFrame ventana = new JFrame();
		ventana.setSize(656, 479);
		Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
		ventana.setLocation((pantallaTamano.width / 2)
				- (ventana.getWidth() / 2), (pantallaTamano.height / 2)
				- (ventana.getHeight() / 2));
		ventana.setUndecorated(true);
		JPanel inicio = new JPanel();
		JLabel i = new JLabel(new ImageIcon("inicio.jpg"));
		inicio.add(i);
		ventana.add(inicio);
		ventana.setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		ventana.setVisible(false);
		ventana.removeAll();

	}

	@Override
	public void onReset(Tablero tab, Ficha turno) {
	}

	@Override
	public void onUndo(Partida game) {
	}

	/**
	 * Método que lanza una ventana para confirmar la salida
	 */
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
		if (game.getTablas()) {
			JOptionPane.showMessageDialog(null, "Partida terminada en tablas",
					"Fin de la partida", 1);
		} else {
			ImageIcon i = new ImageIcon("win.gif");
			JOptionPane.showMessageDialog(null, "Ganan las "
					+ game.getGanador().toString() + "!", "Fin de la partida",
					1, i);
		}
	}

	@Override
	public void onChangeGame(Tablero tab) {
	}

	@Override
	public void onMoveIncorrect(Partida game, String error) {
		JOptionPane.showMessageDialog(null, error, "Error", 2);

	}

	@Override
	public void onCambioJugador(Partida game) {
	}

	@Override
	public void onUndoImposible(Tablero tab) {
		JOptionPane.showMessageDialog(null, "Imposible deshacer", "Error", 2);
	}

	@Override
	public void onChangePlayer(Tablero tab) {
	}

	@Override
	public void onAyuda(Tablero tab) {
		String s = new String(
				"<html><body>Pulse en una casilla del tablero para poner su ficha.<br>"
						+ "Botón 'Reiniciar': Reinicia la partida.<br> Botón 'Deshacer': Deshace "
						+ "el último movimiento hecho en la partida.<br> Botón 'Poner Aleatorio': Pone una ficha al "
						+ "azar.<br> Botón 'Salir': Sale del programa. <br> Botón 'Cambiar': Cambia el tipo de juego dependiendo de la casilla de selección.</body></html>");
		JLabel ay = new JLabel(s);
		JOptionPane.showMessageDialog(null, ay, "Ayuda", 1);
	}

	@Override
	public void onError(Tablero tablero) {
	}

	@Override
	public void onTurno(Partida game) {
		// TODO Auto-generated method stub
		
	}

}
