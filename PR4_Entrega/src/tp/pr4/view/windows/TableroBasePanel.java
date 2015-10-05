package tp.pr4.view.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
 * Panel que forma el tablero de fichas
 * 
 * @author Jhersy y Rober
 *
 */
public class TableroBasePanel extends JPanel implements PartidaObserver {

	private static final long serialVersionUID = 1L;

	private JButton[][] buttons;
	private GridBagConstraints c;
	private ControladorVista ctrl;

	/**
	 * Constructor del panel
	 * 
	 * @param ctrl
	 * @param game
	 */
	public TableroBasePanel(ControladorVista ctrl,
			Observable<PartidaObserver> game) {
		this.ctrl = ctrl;
		initGUI();
		game.addObserver(this);
	}

	/**
	 * Inicializar panel
	 */
	private void initGUI() {
		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		this.setPreferredSize(new Dimension(400, 200));

	}

	@Override
	public void onReset(Tablero tab, Ficha turno) {
		int rows = tab.getAncho();
		int cols = tab.getAlto();
		this.removeAll();
		buttons = new JButton[cols][rows];

		for (int i = 0; i < cols; i++)
			for (int j = 0; j < rows; j++) {
				buttons[i][j] = createButton(i, j);
				buttons[i][j].setBackground(Color.LIGHT_GRAY);
				c.gridy = i;
				c.gridx = j;
				this.add(buttons[i][j], c);
			}
		refrescaTablero(tab);
		this.revalidate();

	}

	/**
	 * Método que crea los botones de las fichas del tablero
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private JButton createButton(final int i, final int j) {
		JButton x = new JButton();
		setPreferredSize(new Dimension(300, 300));
		x.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ctrl.move(i + 1, j + 1);
				} catch (MovimientoInvalido e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Error", 2);
				}
			}

		});
		return x;
	}

	@Override
	public void onMove(Partida game) {
		refrescaTablero(game.getTablero());
	}

	/**
	 * Refrescar la vista del tablero
	 * 
	 * @param tab
	 */
	private void refrescaTablero(Tablero tab) {
		for (int i = 1; i <= tab.getAncho(); i++)
			for (int j = 1; j <= tab.getAlto(); j++) {
				Ficha f = tab.getCasilla(i, j);
				buttons[j - 1][i - 1].setBackground(f.color());
			}
	}

	@Override
	public void onUndo(Partida game) {
		refrescaTablero(game.getTablero());
	}

	@Override
	public void onPartidaEnd(Partida game) {
		for (int i = 1; i <= game.getTablero().getAncho(); i++)
			for (int j = 1; j <= game.getTablero().getAlto(); j++) 
				buttons[j - 1][i - 1].setEnabled(false);
			
	}

	@Override
	public void onChangeGame(Tablero tab) {
		int rows = tab.getAncho();
		int cols = tab.getAlto();
		this.removeAll();
		this.setVisible(false);
		buttons = new JButton[cols][rows];

		for (int i = 0; i < cols; i++)
			for (int j = 0; j < rows; j++) {
				buttons[i][j] = createButton(i, j);
				buttons[i][j].setBackground(Color.LIGHT_GRAY);
				c.gridy = i;
				c.gridx = j;
				this.add(buttons[i][j], c);
			}
		refrescaTablero(tab);

		this.setVisible(true);
		this.revalidate();
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
		
	}

}
