package practicahundirlaflota;

import java.util.Scanner;

public class Tablero {

    protected static Scanner datos = new Scanner(System.in);

    public char[][] tablero;

    public Tablero() {
        this.tablero = new char[10][10];
    }

    public void inicializarTablero() {
        for (int x = 0; x < tablero.length; x++) {
            for (int y = 0; y < tablero[x].length; y++) {
                this.tablero[x][y] = '*';
            }
        }
    }

    public void mostrarTablero() {
        String letras = "1234567890";
        System.out.println("  1 2 3 4 5 6 7 8 9 0");
        for (int x = 0; x < tablero.length; x++) {
            System.out.print(letras.charAt(x) + " ");
            for (int y = 0; y < tablero[x].length; y++) {
                System.out.print(tablero[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean comprobarVictoria(char[][] tablero) {
        boolean victoria = false;
        int contador = 0;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (tablero[x][y] == 'X') {
                    contador++;
                }
            }
        }
        if (contador == 20) {
            victoria = true;
        }
        return victoria;
    }

}
