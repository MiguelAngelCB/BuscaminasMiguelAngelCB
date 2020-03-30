package modelonovalido;

public class Tablero {
/*
 * El modelo de datos aqui expresado es malo porque lo teneis que hacer bien vosotros
 */
	private int [][] casillas;
	private boolean [][] veladas;
	public Tablero() {
		super();
	}
	public void setCasilas(int[][] casilas) {
		this.casillas = casilas;
	}
	public int[][] getCasilas() {
		return casillas;
	}

	
	public boolean[][] getVeladas() {
		return veladas;
	}
	public void setVeladas(boolean[][] veladas) {
		this.veladas = veladas;
	}
	public boolean desvelarCasilla(int x,int y) {
		assert inRango(0,casillas.length,x)&&inRango(0, casillas[0].length, y);
		return false;
	}
	
	private boolean inRango(int min,int max, int value) {
		return value>min&&value<max;
	}
}
