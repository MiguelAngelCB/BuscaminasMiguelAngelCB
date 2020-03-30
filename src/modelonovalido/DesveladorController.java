package modelonovalido;

public class DesveladorController {
	//Esto esto hecho sobre la clase Tablero no valida
	Tablero tablero;

	public DesveladorController(Tablero tablero) {
		super();
		this.tablero = tablero;
	}

	public boolean desvelarController(int x, int y) {
		return tablero.desvelarCasilla(x, y);
	}
}
