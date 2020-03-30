package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import utiles.Utiles;

class UtilesTest {

	@Ignore
	void testDameNumero() {
		// La dificultad de probar lo aleatorio
		// necesito probar un metodo que me da un valor entre 0 y limite (no incluido)
		int max = 10;
		// Habria que calcular la cantidad de casos necesarios para asegurar que salen
		// todos
		// los valores, pero como no somos matematicos vamos a poner un numero alto
		for (int i = 0; i < 1000; i++) {
			int actual = Utiles.dameNumero(max);
			assertTrue(actual < max && actual >= 0);
//			Podemos pensar que estan bien pero no sabemos si los números aletorios
//			sacan resultados que toquen los limites de 0 y max-1
		}

		/*
		 * Pero tambien debemos asegurar que toca los limites, asi que proponemos esto
		 */
		boolean limiteInferior = false, limiteSuperior = false, noLimites = false;
		for (int i = 0; i < 1000; i++) {
			int actual = Utiles.dameNumero(max);
			if (actual == 0) {
				limiteInferior = true;
			} else if (actual == max - 1) {
				limiteSuperior = true;
			} else {
				noLimites = true;
			}

		}
		assertTrue(limiteSuperior);
		assertTrue(limiteInferior);
		assertTrue(noLimites);
		/*
		 * Estas pruebas son mejores que las anteriores porque comprueban si se
		 * comportan bien con los limites
		 */
//		Podemos sacar mas precision
//		max=1;
		max = 3;
		limiteInferior = false;
		limiteSuperior = false;
		noLimites = false;
		for (int i = 0; i < 1000; i++) {
			int actual = Utiles.dameNumero(max);
			if (actual == 0) {
				limiteInferior = true;
			} else if (actual == max - 1) {
				limiteSuperior = true;
			} else {
				noLimites = true;
			}
		}
		// con max=1 o con max=2 debe fallar porque los valores seran siempre 0 o 0 y 1
		// y noLimites debe dar false
		// pero si cambias max=3 los valores posibles son 0 y 2 (limites) y 1 (no
		// limite) y deberia dar todo true
		assertTrue(limiteSuperior);
		assertTrue(limiteInferior);
		assertTrue(noLimites);

	}

	@Test
	void testCalculaPorcentaje() {
		int lado[] = { 4, 5, 6, 8, 9, 12, 13, 4, 5, 6, 8, 9, 12, 13 };
		int porcentaje[] = { 10, 10, 10, 10, 10, 10, 10, 20, 20, 20, 20, 20, 20, 20 };
		int resultados[] = { 1, 2, 3, 6, 8, 14, 16, 3, 5, 7, 12, 16, 28, 33 };
		for (int i = 0; i < resultados.length; i++) {
			assertEquals(resultados[i], Utiles.calculaMinas(lado[i], porcentaje[i]));
		}
	}

	@Test
	void testCalculaPorcentajeComplicao() {
		// Esta segunda forma es mas compleja, vosotros decidis cual os gusta mas
		int lado[] = { 4, 5, 6, 8, 9, 12, 13 };
		int porcentaje[] = { 10, 20 };
//		Los valores estan intercalados par 10, para 20, para 10, etc.
		int resultados[] = { 1, 3, 2, 5, 3, 7, 6, 12, 8, 16, 14, 28, 16, 33 };
		for (int i = 0; i < lado.length; i++) {
			for (int j = 0; j < porcentaje.length; j++) {
				// multiplico por la longitud de la dimension j
				int valor = i * porcentaje.length + j;
				System.out.println("probando resultados:" + valor + " para i:" + i + " y j:" + j);
				assertEquals(resultados[valor], Utiles.calculaMinas(lado[i], porcentaje[j]));
			}
		}
	}
}
