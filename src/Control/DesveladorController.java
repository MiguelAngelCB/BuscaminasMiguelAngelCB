package Control;

import model.Coordenada;
import model.Tablero;
import utiles.ConversorGrafico;
import vista.Botonera;
import vista.ElementoGrafico;

public class DesveladorController {

	Tablero tablero;

	public Tablero getTablero() {
		return tablero;
	}

	public DesveladorController(Tablero tablero) {
		super();
		this.tablero = tablero;
	}

	public void desvelarCasilla(String name) {
		// Este pequeno detalle de convertir el nombre en coordenada
		// hace que estemos ante un adapter
		// porque adecua API´s
		Coordenada obtenCoordenada = Botonera.obtenCoordenada(name);
		tablero.desvelarCasilla(obtenCoordenada);
	}

	public ElementoGrafico[][] getEntornoGrafico() {
		return ConversorGrafico.convertir(tablero.getCasillas());
	}

	public boolean comprobarAcabarPartida() {
		return comprobarGanador() || comprobarPerdedor();
	}

	public boolean comprobarGanador() {
		int i = 0;
		do {
			int j = 0;
			do {
				Coordenada posicion = new Coordenada(i, j);
				if (tablero.isMina(posicion) && !tablero.getCasilla(posicion).isMarcada()) {
					return false;
				}
				j++;
			} while (j < tablero.getCasillas()[i].length);
			i++;
		} while (i < tablero.getCasillas().length);
		return true;
	}

	public boolean comprobarPerdedor() {
		int i = 0;
		do {
			int j = 0;
			do {
				Coordenada posicion = new Coordenada(i, j);
				if (tablero.isMina(posicion) && !tablero.isVelada(posicion)) {
					return true;
				}
				j++;
			} while (j < tablero.getCasillas()[i].length);
			i++;
		} while (i < tablero.getCasillas().length);
		return false;
	}

}
