package tp.pr4.commands;

import tp.pr4.control.ControladorConsola;
import tp.pr4.control.FactoriaComplica;
import tp.pr4.control.FactoriaConecta4;
import tp.pr4.control.FactoriaGravity;
import tp.pr4.control.FactoriaReversi;
import tp.pr4.control.FactoriaTipoJuego;

/**
 * Implementacion del comando para la opcion Jugar.
 * 
 * @author Jhersy Nolasco y Roberto Barrasus
 * 
 */
public class ComandoJugar implements Comando {
	private ControladorConsola c;
	private FactoriaTipoJuego f;
	private boolean correct;

	public ComandoJugar(ControladorConsola c) {
		this.c = c;
	}

	@Override
	public boolean analiza(String cmd) {

		String[] parts = cmd.split(" ");
		String part1 = parts[0];
		correct = true;
		
		if (parts.length > 1) {
			if (part1.toUpperCase().equals("JUGAR")) {
				String part2 = parts[1];
				
				if (part2.toUpperCase().equals("C4")) {
					f = new FactoriaConecta4();
				} else if (part2.toUpperCase().equals("CO")) {
					f = new FactoriaComplica();
				} else if (part2.toUpperCase().equals("RV")) {
					f = new FactoriaReversi();
				}else if (part2.toUpperCase().equals("GR")) {
					if (parts.length == 4) {
						int part3 = Integer.parseInt(parts[2]);
						int part4 = Integer.parseInt(parts[3]);
						
						f = new FactoriaGravity(part3, part4);
						
					} else
						correct = false;
				} else
					correct = false;
			} else
				correct = false;
		} else
			correct = false;

		return correct;
	}

	@Override
	public void ejecuta() {
		c.reset(f);
	}

}
