package tp.pr4.view.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr4.control.ControladorVista;
import tp.pr4.control.FactoriaComplica;
import tp.pr4.control.FactoriaConecta4;
import tp.pr4.control.FactoriaGravity;
import tp.pr4.control.FactoriaReversi;
import tp.pr4.logic.Ficha;
import tp.pr4.logic.Observable;
import tp.pr4.logic.Partida;
import tp.pr4.logic.PartidaObserver;
import tp.pr4.logic.Tablero;

public class JuegoPanel extends JPanel implements PartidaObserver {

	private static final long serialVersionUID = 1L;
	private JPanel opcionJuego;
	private JPanel coordenadas;
	private JButton cambiar;
	private JComboBox<String> combo;
	private JTextField filas;
	private JTextField columnas;
	private JLabel etFila;
	private JLabel etColumnas;
	private ControladorVista ctrl;

	public JuegoPanel(ControladorVista ctrl, Observable<PartidaObserver> game) {
		this.ctrl = ctrl;
		initGUI();
		game.addObserver(this);
	}

	public void initGUI() {
		this.setBorder(new TitledBorder("Cambio de Juego"));
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(300, 150));

		// BOTON CAMBIAR
		cambiar = new JButton("Cambiar");
		cambiar.setIcon(new ImageIcon(new ImageIcon("cambiar.png").getImage()
				.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		cambiar.setBackground(Color.GREEN);

		// Para dividir si la opcion del juego es gravity
		opcionJuego = new JPanel();
		opcionJuego.setLayout(new BorderLayout(2, 1));
		// Panel para las coordenadas
		coordenadas = new JPanel();
		coordenadas.setLayout(new FlowLayout());

		// Creacion del JComboBox
		combo = new JComboBox<String>();
		combo.addItem("CONECTA 4");
		combo.addItem("COMPLICA");
		combo.addItem("GRAVITY");
		combo.addItem("REVERSI");

		opcionJuego.add(combo, BorderLayout.NORTH);
		// Accion a realizar cuando el JCombox cambia de item seleccionado.

		etFila = new JLabel("Filas");
		filas = new JTextField(2);
		filas.setPreferredSize(new Dimension(50, 22));
		etColumnas = new JLabel("Columnas");
		columnas = new JTextField(2);
		columnas.setPreferredSize(new Dimension(50, 22));

		coordenadas.add(etFila);
		coordenadas.add(filas);
		coordenadas.add(etColumnas);
		coordenadas.add(columnas);

		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Dependiendo de la opcion deberia crear una nueva vi
				if (combo.getSelectedItem().equals("CONECTA 4")) {
					// SE QUEDA COMO ESTABA AL PRINCIPIO
					opcionJuego.remove(coordenadas);

				} else if (combo.getSelectedItem().equals("COMPLICA")) {
					// SE QUEDA COMO ESTABA AL PRINCIPIO
					opcionJuego.remove(coordenadas);

				} else if (combo.getSelectedItem().equals("GRAVITY")) {
					// DA LA OPCION DE PONER FILA Y COLUMNA
					opcionJuego.add(coordenadas, BorderLayout.CENTER);
					opcionJuego.setVisible(false);
					opcionJuego.setVisible(true);
				} else {
					// SE QUEDA COMO ESTABA AL PRINCIPIO
					opcionJuego.remove(coordenadas);
				}

			}
		});

		cambiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (combo.getSelectedItem().equals("CONECTA 4")) {
					ctrl.cambioJuego(new FactoriaConecta4());

				} else if (combo.getSelectedItem().equals("COMPLICA")) {
					opcionJuego.remove(coordenadas);
					ctrl.cambioJuego(new FactoriaComplica());

				} else if (combo.getSelectedItem().equals("GRAVITY")) {
					// recoge la informacion de fila y columna
					// si el ususario introduce algo distinto a un numero.
					if (filas.getText().isEmpty()
							|| columnas.getText().isEmpty())
						ctrl.cambioJuego(new FactoriaGravity(10, 10));
					else {
						if (!isNumeric(filas.getText().toString())
								|| !isNumeric(columnas.getText().toString())) {
							JOptionPane.showMessageDialog(null,
									"Se deben introducir valores enteros",
									"Error", 2);
						} else {
							int filasOpcion = Integer.parseInt(filas.getText()
									.toString());
							int colOpcion = Integer.parseInt(columnas.getText()
									.toString());
							if (filasOpcion < 4 || colOpcion < 4)
								JOptionPane
										.showMessageDialog(
												null,
												"Se deben introducir valores enteros mayores que 4.",
												"Error", 2);
							else
								ctrl.cambioJuego(new FactoriaGravity(
										filasOpcion, colOpcion));
						}
					}
				} else {
					opcionJuego.remove(coordenadas);
					ctrl.cambioJuego(new FactoriaReversi());
				}
			}
		});

		this.add(opcionJuego, BorderLayout.NORTH);
		this.add(cambiar, BorderLayout.SOUTH);

	}

	@Override
	public void onReset(Tablero tab, Ficha turno) {
		combo.setEnabled(true);
		cambiar.setEnabled(true);
	}

	@Override
	public void onMove(Partida game) {
		combo.setEnabled(true);
		cambiar.setEnabled(true);
	}

	@Override
	public void onUndo(Partida game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPartidaEnd(Partida game) {
		combo.setEnabled(false);
		cambiar.setEnabled(false);
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

	private boolean isNumeric(String opcion) {
		try {
			Integer.parseInt(opcion);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}

	}

	@Override
	public void onTurno(Partida game) {
		// TODO Auto-generated method stub
		
	}
}
