package practicaDamas;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

public class Interfaz {
	final static String VACIO = "·";
	final static String BLANCA = "b";
	final static String BLANCA_REINA = "B";
	final static String NEGRA = "n";
	final static String NEGRA_REINA = "N";

	static String coordenadaOrigen;
	static String coordenadaDestino;

	static boolean se_mueve = false;

	static Scanner tec = new Scanner(System.in);

	public static void main(String[] args) {

		String[][] tablero = { { " ", " ", " ", " ", " ", " ", " ", " ", " " },
				{ " ", BLANCA, VACIO, BLANCA, VACIO, BLANCA, VACIO, BLANCA, VACIO },
				{ " ", VACIO, BLANCA, VACIO, BLANCA, VACIO, BLANCA, VACIO, BLANCA },
				{ " ", BLANCA, VACIO, BLANCA, VACIO, BLANCA, VACIO, BLANCA, VACIO },
				{ " ", VACIO, NEGRA, VACIO, VACIO, VACIO, VACIO, VACIO, VACIO },
				{ " ", VACIO, VACIO, BLANCA, VACIO, VACIO, VACIO, VACIO, VACIO },
				{ " ", VACIO, NEGRA, VACIO, NEGRA, VACIO, NEGRA, VACIO, NEGRA },
				{ " ", NEGRA, VACIO, NEGRA, VACIO, NEGRA, VACIO, NEGRA, VACIO },
				{ " ", VACIO, NEGRA, VACIO, NEGRA, VACIO, NEGRA, VACIO, NEGRA } };

		boolean juegoTerminado = Terminado(tablero);

		Tablero(tablero);
		int jugador = 1;
		do {
			if (jugador == 1) {
				JugadorActualB(jugador, tablero);

				jugador = 2;
				JugadorActualN(jugador, tablero);
			} else {
				jugador = 1;
			}

		} while (juegoTerminado == true);

		System.out.println("El juego ha termindo");
	}

	public static void Tablero(String[][] tablero) { // mostrar el tablero

		System.out.println("    A B C D E F G H");
		System.out.println("    ---------------");

		for (int i = 1; i < tablero.length; i++) {
			System.out.print((i) + "|");
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean Terminado(String[][] tablero) { // si gana alguien

		boolean enc = false;
		for (int i = 1; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length && !enc; j++) {
				if (("b").equals(tablero[i][j]) || ("B").equals(tablero[i][j]) && ("n").equals(tablero[i][j])
						|| ("N").equals(tablero[i][j])) {
					enc = true;
				} else {
					enc = false;
				}
			}
		}
		return enc;
	}

	public static int coordenadaAFila(String coord) { // coordenada fila
		int fila = 0;
		try {
			fila = Integer.parseInt(coord.substring(0, 1));
			if (fila >= 9) {
				fila = 10;
			}

			if (coord.length() < 2 || coord.length() > 2) {
				fila = 10;
			}

		} catch (NumberFormatException e) {
			fila = 10;
		}
		return fila;
	}

	public static int coordenadaACol(String coord) { // coordenada colu

		String letras = " ABCDEFGH";

		int columna = letras.indexOf(coord.substring(1));

		if (letras.contains(coord.substring(1)) == true) {
			return columna;
		} else {
			return 10;
		}

	}

	public static void JugadorActualB(int jugador, String[][] tablero) { // mover
																			// fichas
		do {
			System.out.println("Que ficha blanca eliges: ");
			coordenadaOrigen = tec.nextLine().toUpperCase();

			System.out.println("A donde la llevas: ");
			coordenadaDestino = tec.nextLine().toUpperCase();

			while ((coordenadaAFila(coordenadaOrigen) == 10) || (coordenadaAFila(coordenadaDestino) == 10)
					|| (coordenadaACol(coordenadaDestino) == 10) || (coordenadaACol(coordenadaOrigen) == 10)) {

				System.out.println("No has introducido bien las coordenadas");
				System.out.println("-----------------------------------------");
				System.out.println("Que ficha blanca eliges: ");
				coordenadaOrigen = tec.nextLine().toUpperCase();

				System.out.println("A donde la llevas: ");
				coordenadaDestino = tec.nextLine().toUpperCase();
			}

		} while (SePuedeMover(coordenadaOrigen, coordenadaDestino, tablero, jugador) == false);

	}

	public static void JugadorActualN(int jugador, String[][] tablero) {

		do {
			System.out.println("Que ficha negra eliges: ");
			coordenadaOrigen = tec.nextLine().toUpperCase();

			System.out.println("A donde la quieres llevar: ");
			coordenadaDestino = tec.nextLine().toUpperCase();

			while ((coordenadaAFila(coordenadaOrigen) == 10) || (coordenadaAFila(coordenadaDestino) == 10)
					|| (coordenadaACol(coordenadaDestino) == 10) || (coordenadaACol(coordenadaOrigen) == 10)) {

				System.out.println("No has introducido bien las coordenadas");
				System.out.println("-----------------------------------------");
				System.out.println("Que ficha negra eliges: ");
				coordenadaOrigen = tec.nextLine().toUpperCase();

				System.out.println("A donde la quieres llevar: ");
				coordenadaDestino = tec.nextLine().toUpperCase();
			}

		} while (SePuedeMover(coordenadaOrigen, coordenadaDestino, tablero, jugador) == false);

		SePuedeMover(coordenadaOrigen, coordenadaDestino, tablero, jugador);

	}

	public static boolean SePuedeMover(String coordenadaOrigen, String coordenadaDestino, String[][] tablero,
			int jugador) {

		int fOrigen = coordenadaAFila(coordenadaOrigen);
		int cOrigen = coordenadaACol(coordenadaOrigen);
		int fDestino = coordenadaAFila(coordenadaDestino);
		int cDestino = coordenadaACol(coordenadaDestino);

		if (tablero[fDestino][cDestino].contains(VACIO)) {

			if (jugador == 1 && tablero[fOrigen][cOrigen].contains(BLANCA)) {
				
				if (SiMata(coordenadaOrigen, coordenadaDestino, tablero,  jugador, fOrigen,  cOrigen,  fDestino, cDestino) == true) {
					se_mueve = true;
					mueve(tablero, jugador, cDestino, cDestino, cDestino, cDestino);
					
				} else {
					se_mueve = false;
				}

				if ((fDestino - fOrigen < 2) && (cDestino - cOrigen < 2) || (cDestino - cOrigen == -1)
						&& tablero[fOrigen][cOrigen].equals(tablero[fDestino][cDestino]) == false) {

					if (fDestino - fOrigen > 0 && cDestino - cOrigen != 0) {

						mueve(tablero, jugador, cDestino, cDestino, cDestino, cDestino);
						se_mueve = true;

					}
				}

			}
			if (jugador == 2 && tablero[fOrigen][cOrigen].contains(NEGRA)) {
				
				

				if ((fOrigen - fDestino < 2) && (cOrigen - cDestino < 2) || (cOrigen - cDestino == -1)
						&& tablero[fOrigen][cOrigen].equals(tablero[fDestino][cDestino]) == false) {
					System.out.println("yee");
					if (fOrigen - fDestino > 0 && cOrigen - cDestino != 0) {

						mueve(tablero, jugador, cDestino, cDestino, cDestino, cDestino);
						se_mueve = true;

					}
				}

			}

			if ((jugador == 1 && tablero[fOrigen][cOrigen].contains(BLANCA_REINA))
					|| (jugador == 2 && tablero[fOrigen][cOrigen].contains(NEGRA_REINA))) {

				if (((jugador == 1) && (fDestino - fOrigen < 2) && (cDestino - cOrigen < 2)
						|| (cDestino - cOrigen == -1))
						|| (jugador == 2 && (fOrigen - fDestino < 2) && (cOrigen - cDestino < 2)
								|| (cOrigen - cDestino == -1))
								&& tablero[fOrigen][cOrigen].equals(tablero[fDestino][cDestino]) == false) {

					if (fDestino - fOrigen > 0 && cDestino - cOrigen != 0) {
						mueve(tablero, jugador, cDestino, cDestino, cDestino, cDestino);
						se_mueve = true;

					} else {
						se_mueve = false;
					}
				} else {
					se_mueve = false;
				}
			}

		} 
		return se_mueve;
	}

	public static boolean SiMata(String coordenadaOrigen, String coordenadaDestino, String[][] tablero, int jugador,
			int fOrigen, int cOrigen, int fDestino, int cDestino) {

		boolean mata = false;

		fOrigen = coordenadaAFila(coordenadaOrigen);
		cOrigen = coordenadaACol(coordenadaOrigen);
		fDestino = coordenadaAFila(coordenadaDestino);
		cDestino = coordenadaACol(coordenadaDestino);

		if (jugador == 1) {

			if ((fDestino - fOrigen == 2) && (cDestino - cOrigen == 2)){
				
				if(tablero[fOrigen + 1][cOrigen + 1].equals(NEGRA)
					|| tablero[fOrigen + 1][cOrigen + 1].equals(NEGRA_REINA)) {
					
					tablero[fOrigen + 1][cOrigen + 1] = VACIO;
				mata = true;
			} 
		} else if ((fDestino - fOrigen == 2) && (cDestino - cOrigen == -2)){
			
				if(tablero[fOrigen + 1][cOrigen - 1].equals(NEGRA)
						|| tablero[fOrigen + 1][cOrigen - 1].equals(NEGRA_REINA)) {
						
						tablero[fOrigen + 1][cOrigen - 1] = VACIO;
					mata = true;
				} 
			}
		
	} else {
		if ((fOrigen - fDestino  == 2) && (fOrigen - fDestino == 2)){
			
			if(tablero[fOrigen - 1][cOrigen + 1].equals(BLANCA)
				|| tablero[fOrigen - 1][cOrigen + 1].equals(BLANCA_REINA)) {
				
				tablero[fOrigen + 1][cOrigen + 1] = VACIO;
			mata = true;
		} 
	} else if ((fOrigen - fDestino == 2) && (fOrigen - fDestino == -2)){
		
			if(tablero[fOrigen - 1][cOrigen - 1].equals(NEGRA)
					|| tablero[fOrigen - 1][cOrigen - 1].equals(NEGRA_REINA)) {
					
					tablero[fOrigen - 1][cOrigen - 1] = VACIO;
				mata = true;
			} 
		}
		
	}
		return mata;
	}

	public static void mueve(String[][] tablero, int jugador, int fOrigen, int cOrigen, int fDestino, int cDestino) { // el
																														// //
																														// movimiento
		fOrigen = coordenadaAFila(coordenadaOrigen);
		cOrigen = coordenadaACol(coordenadaOrigen);
		fDestino = coordenadaAFila(coordenadaDestino);
		cDestino = coordenadaACol(coordenadaDestino);

		System.out.println("    A B C D E F G H");
		System.out.println("    ---------------");

		for (int i = 0; i < tablero.length; i++) {
			System.out.print((i) + "|");

			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j] + " ");

				if (jugador == 1) {
					if ((tablero[fOrigen][cOrigen].contains(BLANCA))) {
						System.out.print(tablero[fDestino][cDestino] = BLANCA);
						System.out.print(tablero[fOrigen][cOrigen] = VACIO);

						if (fDestino == 8) {
							System.out.print(tablero[fOrigen][cOrigen] = BLANCA_REINA);
						}

					} else if (tablero[fOrigen][cOrigen].contains(BLANCA_REINA)) {

						tablero[fDestino][cDestino] = BLANCA_REINA;
						tablero[fOrigen][cOrigen] = VACIO;
						;
					}

				} else {
					if ((tablero[fOrigen][cOrigen].contains(NEGRA))) {
						System.out.print(tablero[fDestino][cDestino] = NEGRA);
						System.out.print(tablero[fOrigen][cOrigen] = VACIO);

						if (fDestino == 1) {
							System.out.print(tablero[fOrigen][cOrigen] = NEGRA_REINA);
						}

					} else if (tablero[fOrigen][cOrigen].contains(NEGRA_REINA)) {

						tablero[fDestino][cDestino] = NEGRA_REINA;
						tablero[fOrigen][cOrigen] = VACIO;
					}
				}

			}
			System.out.println();

		}
		System.out.println();
	}

}
