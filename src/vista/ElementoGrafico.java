package vista;

public class ElementoGrafico {
	private boolean velada;
	private boolean marcada;
	private boolean mina;
	private int valor;

	public ElementoGrafico(boolean mostrada, boolean senalada, boolean mina, int valor) {
		super();
		this.velada = mostrada;
		this.marcada = senalada;
		this.mina = mina;
		this.valor = valor;
	}

	public boolean isVelada() {
		return velada;
	}

	public boolean isMarcada() {
		return marcada;
	}

	public boolean isMina() {
		return mina;
	}

	public int getValor() {
		return valor;
	}

}
