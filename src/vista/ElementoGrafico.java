package vista;

import javax.swing.Icon;

public class ElementoGrafico {
	private boolean ocultado;
	private boolean senalado;
	private int valor;
	
	public ElementoGrafico(boolean mostrada, boolean senalada, int valor) {
		super();
		this.ocultado = mostrada;
		this.senalado = senalada;
		this.valor = valor;
	}
	public boolean isOcultado() {
		return ocultado;
	}
	public boolean isSenalada() {
		return senalado;
	}
	public int getValor() {
		return valor;
	}
	
	
}
