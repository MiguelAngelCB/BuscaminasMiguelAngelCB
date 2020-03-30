package model;

public class Coordenada {
	// clases de apoyo
	private int posX, posY;

	public Coordenada(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj != null) {
			if (obj instanceof Coordenada) {
				Coordenada coordenada = (Coordenada) obj;
				return this.getPosX() == coordenada.getPosX() && this.getPosY() == coordenada.getPosY();
			}
		}
		return false;
	}

}
