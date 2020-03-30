package modelonovalido;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TableroTest {

	int[][] casillas = { 
			{ 4, 3, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 }, };;
	boolean[][] veladas = { 
			{ true, false, true, true, true }, 
			{ true, true, true, true, true },
			{ true, true, true, true, true }, 
			{ true, true, true, true, true },
			{ true, true, true, true, true } };

	@Test
	void testDesvelarCasillaBAMayorCero() {
		Tablero tablero=new Tablero();
		tablero.setCasilas(casillas);
		tablero.setVeladas(veladas);
		// Vectores ortogonales
		// Coordenada de la casilla a probar
		int coordenadas[][]= {{0,0},{0,1}};
		//El resultado que debe dar el metodo
		boolean resultados[]= {true,false};
		//la situacion en la que queda la casilla tras el metodo
		boolean modificacion[]= {false,false};
		for (int i = 0; i < modificacion.length; i++) {
			assertEquals(resultados[i],tablero.desvelarCasilla(coordenadas[i][0], coordenadas[i][1]));
			assertEquals(modificacion[i], tablero.getCasilas()[coordenadas[i][0]][coordenadas[i][1]]);
		}
		//Cuando esto de verde, es que funciona, ya sabeis
	}

}
