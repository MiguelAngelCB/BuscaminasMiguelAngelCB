package utiles;

public class Utiles {

	public static int dameNumero(int limite) {
		assert limite > 0;
		return (int) (Math.random() * (limite));
	}

	public static void main(String[] args) {
		System.out.println(Utiles.generaCoordenadaAleatorio(1));
	}

	public static int generaCoordenadaAleatorio(int maximo) {
		int min = 0;
		maximo -= 1;
		int num = (int) Math.floor(Math.random() * (maximo - min + 1) + (min));
		return num;
	}

	public static int calculaMinas(int lado, int porcentaje) {
		assert lado > 0 && porcentaje >= 0 && porcentaje <= 100;
		return lado * lado * porcentaje / 100;
	}
}
