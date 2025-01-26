package practicahundirlaflota;

public class IA extends Tablero {

    public IA() {
        super();
    }

    @Override
    public void inicializarTablero() {
        super.inicializarTablero();
    }

    @Override
    public void mostrarTablero() {
        System.out.println("TABLERO IA:");
        super.mostrarTablero();
    }

    public void colocarBarcoRandom(char[][] tablero, int longitud, char letraBarco) {
        int orientacion;
        int x;
        int y;

        do {
            orientacion = (int) (Math.random() * 2);
            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);
        } while (!posicionCorrecta(tablero, x, y, longitud, orientacion));

        if (orientacion == 0) { //Horizontal
            for (int i = 0; i < longitud; i++) {
                tablero[x][y + i] = letraBarco;
            }
        } else { //Vertical
            for (int i = 0; i < longitud; i++) {
                tablero[x + i][y] = letraBarco;
            }
        }
    }

    private boolean posicionCorrecta(char[][] tablero, int fila, int columna, int longitud, int orientacion) {
        if (orientacion == 0) { //Horizontal
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

    public boolean dispararBarcosRandom(char[][] miTablero) {
        int fila;
        int columna;
        do {
            fila = (int) (Math.random() * 10);
            columna = (int) (Math.random() * 10);
        } while (miTablero[fila][columna] == 'X' || miTablero[fila][columna] == 'O');

        if (miTablero[fila][columna] == 'V') {
            miTablero[fila][columna] = 'X';
            System.out.println("La IA te ha hundido un barco!");
        } else if (miTablero[fila][columna] == 'F') {
            miTablero[fila][columna] = 'X';
            if (barcoHundidoF(fila, columna, miTablero)) {
                System.out.println("La IA te ha hundido un barco!");
            } else {
                System.out.println("La IA ha tocado un barco!");
            }
        } else if (miTablero[fila][columna] == 'B') {
            miTablero[fila][columna] = 'X';
            if (barcoHundidoB(fila, columna, miTablero)) {
                System.out.println("La IA te ha hundido un barco!");
            } else {
                System.out.println("La IA ha tocado un barco!");
            }
        } else if (miTablero[fila][columna] == 'P') {
            miTablero[fila][columna] = 'X';
            if (barcoHundidoP(fila, columna, miTablero)) {
                System.out.println("La IA te ha hundido un barco!");
            } else {
                System.out.println("La IA ha tocado un barco!");
            }
        } else {
            miTablero[fila][columna] = 'O';
            System.out.println("La IA le ha dado al agua.");
            return false;
        }
        return true;
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
