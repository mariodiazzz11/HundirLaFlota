package practicahundirlaflota;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Jugador extends Tablero {

    public static Scanner datos = new Scanner(System.in);

    private int partesTocadas;

    public Jugador() {
        super();
    }

    @Override
    public void inicializarTablero() {
        super.inicializarTablero();
    }

    @Override
    public void mostrarTablero() {
        System.out.println("TU TABLERO:");
        super.mostrarTablero();
    }

    public void colocarBarco(char[][] tablero, int longitud, char letraBarco, String nombreBarco) {
        int fila;
        int columna;
        int orientacion;
        boolean orientacionValida = false;

        try {
            if (letraBarco == 'V') {
                do {
                    orientacion = (int) (Math.random() * 2) + 1;
                    System.out.println("Introduce las coordenadas para colocar tu " + nombreBarco + ", fila y columna separadas por espacio");
                    fila = datos.nextInt();
                    columna = datos.nextInt();
                    if (fila == 0) {
                        fila = 10;
                    }
                    if (columna == 0) {
                        columna = 10;
                    }
                    fila = fila - 1;
                    columna = columna - 1;
                    if (!esPosicionValida(tablero, fila, columna, longitud, orientacion)) {
                        System.out.println("Coordenadas no validas, prueba otra vez");
                    }
                } while (!esPosicionValida(tablero, fila, columna, longitud, orientacion));
            } else {
                do {
                    do {
                        System.out.println("Orientacion de tu " + nombreBarco + " (1- Horizontal 2.- Vertical)");
                        orientacion = datos.nextInt();
                        if (orientacion == 1 || orientacion == 2) {
                            orientacionValida = true;
                        } else {
                            System.out.println("ERROR! 1-Horizontal, 2-Vertical.");
                            orientacionValida = false;
                        }
                    } while (orientacionValida == false);
                    System.out.println("Introduce las coordenadas para colocar tu " + nombreBarco + ", fila y columna separadas por espacio");
                    fila = datos.nextInt();
                    columna = datos.nextInt();
                    if (fila == 0) {
                        fila = 10;
                    }
                    if (columna == 0) {
                        columna = 10;
                    }
                    fila = fila - 1;
                    columna = columna - 1;

                    if (!esPosicionValida(tablero, fila, columna, longitud, orientacion)) {
                        System.out.println("No se puede colocar un barco ahi, prueba otra vez");
                    }
                } while (!esPosicionValida(tablero, fila, columna, longitud, orientacion));
            }

            if (orientacion == 1) { //Horizontal
                for (int i = 0; i < longitud; i++) {
                    tablero[fila][columna + i] = letraBarco;
                }
            } else { // Vertical
                for (int i = 0; i < longitud; i++) {
                    tablero[fila + i][columna] = letraBarco;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("ERROR! Introduce numeros enteros");
            datos.nextLine();
            colocarBarco(tablero, longitud, letraBarco, nombreBarco);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR! Coordenadas fuera del tablero");
            colocarBarco(tablero, longitud, letraBarco, nombreBarco);
        }
    }

    public boolean esPosicionValida(char[][] tablero, int fila, int columna, int longitud, int orientacion) {
        if (orientacion == 1) { //Horizontal
            if (columna + longitud > 10) {
                return false;
            }
            for (int x = fila - 1; x <= fila + 1; x++) {
                for (int y = columna - 1; y <= columna + longitud; y++) {
                    if (x >= 0 && x < 10 && y >= 0 && y < 10 && tablero[x][y] != '*') {
                        return false;
                    }
                }
            }
        } else { //Vertical
            if (fila + longitud > 10) {
                return false;
            }
            for (int x = fila - 1; x <= fila + longitud; x++) {
                for (int y = columna - 1; y <= columna + 1; y++) {
                    if (x >= 0 && x < 10 && y >= 0 && y < 10 && tablero[x][y] != '*') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean dispararBarcos(char[][] tableroIA, char[][] tableroIAOculto) {
        int fila;
        int columna;

        try {
            do {
                System.out.println("Introduce las coordenadas del disparo (fila y columna separadas por espacio)");
                fila = datos.nextInt();
                columna = datos.nextInt();

                if (fila == 0) {
                    fila = 10;
                }
                if (columna == 0) {
                    columna = 10;
                }
                fila = fila - 1;
                columna = columna - 1;

                if (tableroIA[fila][columna] == 'X' || tableroIA[fila][columna] == 'O') {
                    System.out.println("Ya has disparado ahi, prueba otra vez");
                }
            } while (tableroIA[fila][columna] == 'X' || tableroIA[fila][columna] == 'O');

            if (tableroIA[fila][columna] == 'V') {
                tableroIA[fila][columna] = 'X';
                tableroIAOculto[fila][columna] = 'X';
                System.out.println("Barco hundido!");
            } else if (tableroIA[fila][columna] == 'F') {
                tableroIA[fila][columna] = 'X';
                tableroIAOculto[fila][columna] = 'X';
                if (barcoHundidoF(fila, columna, tableroIA)) {
                    System.out.println("Barco hundido!");
                } else {
                    System.out.println("Barco tocado!");
                }
            } else if (tableroIA[fila][columna] == 'B') {
                tableroIA[fila][columna] = 'X';
                tableroIAOculto[fila][columna] = 'X';
                if (barcoHundidoB(fila, columna, tableroIA)) {
                    System.out.println("Barco hundido!");
                } else {
                    System.out.println("Barco tocado!");
                }
            } else if (tableroIA[fila][columna] == 'P') {
                tableroIA[fila][columna] = 'X';
                tableroIAOculto[fila][columna] = 'X';
                if (barcoHundidoP(fila, columna, tableroIA)) {
                    System.out.println("Barco hundido!");
                } else {
                    System.out.println("Barco tocado!");
                }
            } else {
                tableroIA[fila][columna] = 'O';
                tableroIAOculto[fila][columna] = 'O';
                System.out.println("Agua");
                return false;
            }
            return true;
        } catch (InputMismatchException e) {
            System.out.println("ERROR! Introduce numeros enteros");
            datos.nextLine();
            dispararBarcos(tableroIA, tableroIAOculto);
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR! Coordenadas fuera del tablero, prueba otra vez");
            dispararBarcos(tableroIA, tableroIAOculto);
            return false;
        }
    }

    private boolean barcoHundidoF(int fila, int columna, char[][] tableroIA) {
        if (fila > 0 && tableroIA[fila - 1][columna] == 'F') {
            return false; //No hundido arriba
        }
        if (fila < tableroIA.length - 1 && tableroIA[fila + 1][columna] == 'F') {
            return false; //No hundido abajo
        }
        if (columna > 0 && tableroIA[fila][columna - 1] == 'F') {
            return false; //No hundido izquierda
        }
        if (columna < tableroIA.length - 1 && tableroIA[fila][columna + 1] == 'F') {
            return false; //No hundido derecha
        }
        return true; //Hundido
    }

    private boolean barcoHundidoB(int fila, int columna, char[][] tableroIA) {
        if (fila > 0 && tableroIA[fila - 1][columna] == 'B') {
            return false; //No hundido arriba
        }
        if (fila < tableroIA.length - 1 && tableroIA[fila + 1][columna] == 'B') {
            return false; //No hundido abajo
        }
        if (columna > 0 && tableroIA[fila][columna - 1] == 'B') {
            return false; //No hundido izquierda
        }
        if (columna < tableroIA.length - 1 && tableroIA[fila][columna + 1] == 'B') {
            return false; //No hundido derecha
        }
        return true; //Hundido
    }

    private boolean barcoHundidoP(int fila, int columna, char[][] tableroIA) {
        if (fila > 0 && tableroIA[fila - 1][columna] == 'P') {
            return false; //No hundido arriba
        }
        if (fila < tableroIA.length - 1 && tableroIA[fila + 1][columna] == 'P') {
            return false; //No hundido abajo
        }
        if (columna > 0 && tableroIA[fila][columna - 1] == 'P') {
            return false; //No hundido izquierda
        }
        if (columna < tableroIA.length - 1 && tableroIA[fila][columna + 1] == 'P') {
            return false; //No hundido derecha
        }
        return true; //Hundido
    }

    public char[][] getTablero() {
        return tablero;
    }

}
