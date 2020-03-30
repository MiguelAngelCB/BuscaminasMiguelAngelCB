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
}
