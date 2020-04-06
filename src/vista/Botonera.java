package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Control.DesveladorController;
import Control.MarcadorController;
import model.Coordenada;

public class Botonera extends JPanel {
	DesveladorController desveladorController;
	MarcadorController marcadorController;

	MouseAdapter miMouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			JButton boton = ((JButton) e.getSource());

			if (SwingUtilities.isLeftMouseButton(e)) {
				desveladorController.desvelarCasilla(boton.getName());
			}
			if (SwingUtilities.isRightMouseButton(e)) {
				marcadorController.marcarCasilla(boton.getName());
//				actualizaBotonera(marcadorController.getEntornoGrafico());
//				comprobarAcabarPartida();
			}
			actualizaBotonera(desveladorController.getEntornoGrafico());
			comprobarAcabarPartida();
			// Al estar dentro de la botonera (el objeto)
		}

	};

	private void desabilitarBotonera() {
		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++) {
			JButton boton = (JButton) components[i];
			boton.setEnabled(false);
		}
	}

	public Botonera(int lado, DesveladorController desveladorController, MarcadorController marcadorController) {
		this.desveladorController = desveladorController;
		this.marcadorController = marcadorController;
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
				// Esta linea ahora usar el adapter interno
				boton.addMouseListener(miMouseAdapter);
			}
		}
	}

	public void actualizaBotonera(ElementoGrafico[][] elementos) {
		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++) {
			JButton boton = (JButton) components[i];
			Coordenada coordenada = obtenCoordenada(boton.getName());
			ElementoGrafico elementoGrafico = elementos[coordenada.getPosX()][coordenada.getPosY()];
			if (!elementoGrafico.isVelada()) {
				boton.setText(String.valueOf(elementoGrafico.getValor()));
				if (elementoGrafico.isMina()) {
					boton.setText("M");
				}
			} else if (elementoGrafico.isMarcada()) {
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

	public boolean comprobarAcabarPartida() {
		ElementoGrafico[][] elementoGrafico = desveladorController.getEntornoGrafico();
		return comprobarGanador(elementoGrafico) || comprobarPerdedor(elementoGrafico);
	}

	public boolean comprobarGanador(ElementoGrafico[][] elementos) {
		int i = 0;
		do {
			int j = 0;
			do {
				if (elementos[i][j].isMina() && !elementos[i][j].isMarcada()) {
					return false;
				}
				j++;
			} while (j < elementos[i].length);
			i++;
		} while (i < elementos.length);
		desabilitarBotonera();
		JOptionPane.showMessageDialog(null, "Felicidades has ganado");
		return true;
	}

	public boolean comprobarPerdedor(ElementoGrafico[][] elementos) {
		Component[] components = getComponents();
		int i = 0;
		do {
			int j = 0;
			do {
				if (elementos[i][j].isMina() && !elementos[i][j].isVelada()) {
					JButton boton = (JButton) components[(i * elementos.length) + j];
					boton.setBackground(Color.RED);
					desabilitarBotonera();
					JOptionPane.showMessageDialog(null, "Has perdido :(");
					return true;
				}
				j++;
			} while (j < elementos[i].length);
			i++;
		} while (i < elementos.length);
		return false;
	}

}
