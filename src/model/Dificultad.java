package model;

public enum Dificultad {
	pequeno(5), medio(8), grande(12);

	private int lado;

	private Dificultad(int lado) {
		this.lado = lado;
	}

	public int getLado() {
		return lado;
	}

}
