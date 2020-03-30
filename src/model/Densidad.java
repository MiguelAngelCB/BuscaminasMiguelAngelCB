package model;

public enum Densidad {
	facil(10),dificil(20);
	private int porcentaje;

	private Densidad(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public int getPorcentaje() {
		return porcentaje;
	}
	
}
