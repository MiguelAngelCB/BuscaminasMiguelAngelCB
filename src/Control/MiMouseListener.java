package Control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import vista.Botonera;

public class MiMouseListener extends MouseAdapter {
	private DesveladorController desveladorController;
	private MarcadorController marcadorController;

	public MiMouseListener(DesveladorController desveladorController, MarcadorController marcadorController) {
		super();
		this.desveladorController = desveladorController;
		this.marcadorController = marcadorController;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		JButton boton = ((JButton) e.getSource());

		if (SwingUtilities.isLeftMouseButton(e)) {
			desveladorController.desvelarCasilla(boton.getName());
		}
		if (SwingUtilities.isRightMouseButton(e)) {
			// queremos marcar
			marcadorController.marcarCasilla(boton.getName());
		}
		Botonera botonera = ((Botonera) boton.getParent());
		botonera.actualizaBotonera(desveladorController.getEntornoGrafico());
	}
}
