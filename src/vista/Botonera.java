package vista;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import Control.MiMouseListener;
import model.Coordenada;

public class Botonera extends JPanel {
	MiMouseListener miListener;

	public Botonera(int lado, MiMouseListener miListener) {
		this.miListener = miListener;
		// TODO el nombre para cuando hay mas de 10 de lado.
		// debe ser de dos digitos por coordenada aunque el valor<10
		// es decir la coordenada 6:11 debe ser 06:11, por ejemplo.
		setLayout(new GridLayout(lado, lado, 0, 0));
		for (int filas = 0; filas < lado; filas++) {
			for (int columnas = 0; columnas < lado; columnas++) {
				JButton boton = new JButton();
				String[] coordenada = { Integer.toString(filas), Integer.toString(columnas) };
				int i = 0;
				do {
					if (Integer.valueOf(coordenada[i]) < 10) {
						coordenada[i] = "0" + coordenada[i];
					}
					i++;
				} while (i < coordenada.length);
				String nombreBoton = coordenada[0] + coordenada[1];
				boton.setName(nombreBoton);
				add(boton);
				boton.addMouseListener(miListener);
			}
		}
	}

	public static void main(String[] args) {
		for (int filas = 0; filas < 20; filas++) {
			for (int columnas = 0; columnas < 20; columnas++) {
				JButton boton = new JButton();
				String[] coordenada = { Integer.toString(filas), Integer.toString(columnas) };
				int i = 0;
				do {
					if (Integer.valueOf(coordenada[i]) < 10) {
						coordenada[i] = "0" + coordenada[i];
					}
					i++;
				} while (i < coordenada.length);
				String nombreBoton = coordenada[0] + ":" + coordenada[1];
				System.out.println(nombreBoton);
			}
		}

	}

	public void actualizaBotonera(ElementoGrafico[][] elementos) {
		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++) {
			JButton boton = (JButton) components[i];
			Coordenada coordenada = obtenCoordenada(boton.getName());
			ElementoGrafico elementoGrafico = elementos[coordenada.getPosX()][coordenada.getPosY()];
			if (!elementoGrafico.isOcultado()) {
				boton.setText(String.valueOf(elementoGrafico.getValor()));
			} else if (elementoGrafico.isSenalada()) {
				boton.setText("X");
			} else {
				boton.setText("");
			}
		}
	}

	public static Coordenada obtenCoordenada(String name) {
		int pos = name.length() / 2;
		return new Coordenada(Integer.valueOf(name.substring(0, pos)),
				Integer.valueOf(name.substring(pos, name.length())));
	}

}
