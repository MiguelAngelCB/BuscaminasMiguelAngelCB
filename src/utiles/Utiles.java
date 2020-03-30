package utiles;

public class Utiles {

	public static int dameNumero(int limite) {
		assert limite > 0;
		return (int) (Math.random() * (limite));
	}

	public static int calculaMinas(int lado, int porcentaje) {
		assert lado > 0 && porcentaje >= 0 && porcentaje <= 100;
		return lado * lado * porcentaje / 100;
	}
}
