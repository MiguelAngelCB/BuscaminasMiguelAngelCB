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
				if (!alrededor.equals(posicionMinaCoordenada) && isDentroLimites(alrededor)) {
					getCasilla(alrededor).setMinasAlrededor(getCasilla(alrededor).getMinasAlrededor() + 1);
				}
			}
		}
	}

	private boolean isDentroLimites(Coordenada alrededor) {
		int tamannoTablero = this.casillas.length - 1;
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
			numeroFila = Utiles.generaCoordenadaAleatorio(lado);
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
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				casillas[i][j] = new Casilla();
			}
		}
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
		respuesta = desvelarCasillaDesvelada(coordenada);
		respuesta = desvelarCasillaVelada(coordenada);
		return respuesta;
	}

	public boolean desvelarCasillaDesvelada(Coordenada coordenada) {
		boolean respuesta = false;
		if (!getCasilla(coordenada).isVelada() && casillasMarcadasAlrededor(coordenada)) {
			respuesta = desvelarCasillasAlrededor(coordenada);
		}
		return respuesta;
	}

	public boolean casillasMarcadasAlrededor(Coordenada coordenada) {
		int contadorMarcadas = 0;
		int x = coordenada.getPosX();
		int y = coordenada.getPosY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				Coordenada alrededor = new Coordenada(i, j);
				if (!alrededor.equals(coordenada) && isDentroLimites(alrededor) && getCasilla(alrededor).isMarcada()) {
					contadorMarcadas++;
				}
			}
		}
		return getCasilla(coordenada).getMinasAlrededor() == contadorMarcadas;
	}

	public boolean desvelarCasillaVelada(Coordenada coordenada) {
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

	public boolean desvelarCasillasAlrededor(Coordenada coordenada) {
		boolean respuesta = false;
		int x = coordenada.getPosX();
		int y = coordenada.getPosY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				Coordenada alrededor = new Coordenada(i, j);
				if (!alrededor.equals(coordenada) && isDentroLimites(alrededor)) {
					respuesta = desvelarCasillaVelada(alrededor);
				}
			}
		}
		return respuesta;
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
