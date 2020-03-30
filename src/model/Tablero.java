package model;

import utiles.Utiles;

public class Tablero {

	private Casilla[][] casillas;

	public Tablero(int lado, int numeroBombas) {
		super();
		crearTablero(lado);
		colocarMinas(lado, numeroBombas);
	}

	private void establecerMinasAlrededor(Coordenada posicionMinaCoordenada) {
		int x = posicionMinaCoordenada.getPosX();
		int y = posicionMinaCoordenada.getPosY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				Coordenada alrededor = new Coordenada(i, j);
				// No tengo en cuenta la posicion que estoy comprobando
				if (!alrededor.equals(posicionMinaCoordenada)) {
					if (isDentroLimites(alrededor) && getCasilla(alrededor).isMina()) {
						getCasilla(posicionMinaCoordenada)
								.setMinasAlrededor(getCasilla(posicionMinaCoordenada).getMinasAlrededor() + 1);
					}
				}
			}
		}
	}

	private boolean isDentroLimites(Coordenada alrededor) {
		int tamannoTablero = this.casillas.length;
		return (alrededor.getPosX() >= 0 && alrededor.getPosX() <= tamannoTablero)
				&& (alrededor.getPosY() >= 0 && alrededor.getPosY() <= tamannoTablero);
	}

	private void colocarMinas(int lado, int numeroBombas) {
		// TODO Auto-generated method stub
		// Una vez colocada la mina
		// Tendremos una posicion que yo llamo 0,0 pero que será aleatoria
		// en vuestro programa
		Coordenada posicionMinaCoordenada;
		int numeroFila;
		int numeroColumna;
		do {
			numeroFila = Utiles.dameNumero(lado);
			numeroColumna = Utiles.dameNumero(lado);
			posicionMinaCoordenada = new Coordenada(numeroFila, numeroColumna);
			if (!isMina(posicionMinaCoordenada)) {
				setMina(posicionMinaCoordenada, true);
				establecerMinasAlrededor(posicionMinaCoordenada);
				numeroBombas--;
			}
		} while (numeroBombas > 0);
	}

	private void crearTablero(int lado) {
		this.casillas = new Casilla[lado][lado];
		// ahora hay que crear los objetos casilla
	}

	// TODO antes todo esto era private
	public Casilla getCasilla(Coordenada posicion) {
		return casillas[posicion.getPosX()][posicion.getPosY()];
	}

	private void setMina(Coordenada posicion, boolean bandera) {
		getCasilla(posicion).setMina(bandera);
	}

	private boolean isMina(Coordenada posicion) {
		return getCasilla(posicion).isMina();
	}

	public boolean desvelarCasilla(Coordenada coordenada) {
		boolean respuesta = false;
		if (!getCasilla(coordenada).isMarcada() && getCasilla(coordenada).isVelada()) {
			getCasilla(coordenada).setVelada(false);
			respuesta = true;
		}
		if (getCasilla(coordenada).getMinasAlrededor() == 0 && respuesta) {
			desvelarCasillasAlrededor(coordenada);
		}
		return respuesta;
	}

	public void desvelarCasillasAlrededor(Coordenada coordenada) {
		int x = coordenada.getPosX();
		int y = coordenada.getPosY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				Coordenada alrededor = new Coordenada(i, j);
				if (!alrededor.equals(coordenada) && isDentroLimites(alrededor)) {
					desvelarCasilla(alrededor);
				}
			}
		}
	}

	public boolean marcarCasilla(Coordenada coordenada) {
		return getCasilla(coordenada).marcar();

	}

	public Casilla[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(Casilla[][] casillas) {
		this.casillas = casillas;
	}

}
