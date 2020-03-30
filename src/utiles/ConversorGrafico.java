package utiles;

import model.Casilla;
import vista.ElementoGrafico;

public class ConversorGrafico {
public static ElementoGrafico[][] convertir(Casilla[][] casilla) {
	ElementoGrafico elementos[][]=new ElementoGrafico[casilla.length][casilla.length];
	for (int i = 0; i < casilla.length; i++) {
		for (int j = 0; j < casilla.length; j++) {
			int valor=casilla[i][j].getMinasAlrededor();
			elementos[i][j]= new ElementoGrafico(casilla[i][j].isVelada(), casilla[i][j].isMarcada(), valor);
		}
	}
	return elementos;
}
}
