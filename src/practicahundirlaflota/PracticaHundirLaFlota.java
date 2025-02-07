package practicahundirlaflota;

import java.util.Scanner;

public class PracticaHundirLaFlota {

    public static void main(String[] args) {

        Scanner datos = new Scanner(System.in);

        boolean jugarOtraPartida = true;

        while (jugarOtraPartida) {
            Tablero tableroPadre = new Tablero();
            Jugador miTablero = new Jugador();
            IA tableroIA = new IA();
            IA tableroIAOculto = new IA();

            tableroIA.inicializarTablero();
            tableroIAOculto.inicializarTablero();

            tableroIA.colocarBarcoRandom(tableroIA.getTablero(), 1, 'V');
            tableroIA.colocarBarcoRandom(tableroIA.getTablero(), 1, 'V');
            tableroIA.colocarBarcoRandom(tableroIA.getTablero(), 1, 'V');
            tableroIA.colocarBarcoRandom(tableroIA.getTablero(), 1, 'V');
            tableroIA.colocarBarcoRandom(tableroIA.getTablero(), 2, 'F');
            tableroIA.colocarBarcoRandom(tableroIA.getTablero(), 2, 'F');
            tableroIA.colocarBarcoRandom(tableroIA.getTablero(), 2, 'F');
            tableroIA.colocarBarcoRandom(tableroIA.getTablero(), 3, 'B');
            tableroIA.colocarBarcoRandom(tableroIA.getTablero(), 3, 'B');
            tableroIA.colocarBarcoRandom(tableroIA.getTablero(), 4, 'P');

            //tableroIA.mostrarTablero();
            tableroIAOculto.mostrarTablero();

            miTablero.inicializarTablero();
            miTablero.mostrarTablero();

            miTablero.colocarBarco(miTablero.getTablero(), 1, 'V', "Velero 1");
            //tableroIA.mostrarTablero();
            tableroIAOculto.mostrarTablero();
            miTablero.mostrarTablero();
            miTablero.colocarBarco(miTablero.getTablero(), 1, 'V', "Velero 2");
            //tableroIA.mostrarTablero();
            tableroIAOculto.mostrarTablero();
            miTablero.mostrarTablero();
            miTablero.colocarBarco(miTablero.getTablero(), 1, 'V', "Velero 3");
            //tableroIA.mostrarTablero();
            tableroIAOculto.mostrarTablero();
            miTablero.mostrarTablero();
            miTablero.colocarBarco(miTablero.getTablero(), 1, 'V', "Velero 4");
            //tableroIA.mostrarTablero();
            tableroIAOculto.mostrarTablero();
            miTablero.mostrarTablero();
            miTablero.colocarBarco(miTablero.getTablero(), 2, 'F', "Fragata 1");
            //tableroIA.mostrarTablero();
            tableroIAOculto.mostrarTablero();
            miTablero.mostrarTablero();
            miTablero.colocarBarco(miTablero.getTablero(), 2, 'F', "Fragata 2");
            //tableroIA.mostrarTablero();
            tableroIAOculto.mostrarTablero();
            miTablero.mostrarTablero();
            miTablero.colocarBarco(miTablero.getTablero(), 2, 'F', "Fragata 3");
            //tableroIA.mostrarTablero();
            tableroIAOculto.mostrarTablero();
            miTablero.mostrarTablero();
            miTablero.colocarBarco(miTablero.getTablero(), 3, 'B', "Buque 1");
            //tableroIA.mostrarTablero();
            tableroIAOculto.mostrarTablero();
            miTablero.mostrarTablero();
            miTablero.colocarBarco(miTablero.getTablero(), 3, 'B', "Buque 2");
            //tableroIA.mostrarTablero();
            tableroIAOculto.mostrarTablero();
            miTablero.mostrarTablero();
            miTablero.colocarBarco(miTablero.getTablero(), 4, 'P', "Portaviones");
            //tableroIA.mostrarTablero();
            tableroIAOculto.mostrarTablero();
            miTablero.mostrarTablero();

            int turno = 0;
            int ganador = 0;
            boolean victoria = false;
            boolean repetirTurno = false;

            while (!victoria) {
                if (turno == 0) {
                    //tableroIA.mostrarTablero();
                    tableroIAOculto.mostrarTablero();
                    miTablero.mostrarTablero();
                    if (repetirTurno == false) {
                        System.out.println("TU TURNO:");
                    } else {
                        System.out.println("REPITES TURNO:");
                    }
                    if (miTablero.dispararBarcos(tableroIA.getTablero(), tableroIAOculto.getTablero())) {
                        turno = 0;
                        repetirTurno = true;
                        ganador = 0;
                    } else {
                        repetirTurno = false;
                        turno = 1;
                    }
                    victoria = tableroPadre.comprobarVictoria(tableroIA.getTablero());
                } else {
                    if (repetirTurno == false) {
                        System.out.println("TURNO DE LA IA:");
                    } else {
                        System.out.println("LA IA REPITE TURNO:");
                    }
                    if (tableroIA.dispararBarcosRandom(miTablero.getTablero())) {
                        turno = 1;
                        repetirTurno = true;
                        ganador = 1;
                    } else {
                        repetirTurno = false;
                        turno = 0;
                    }
                    victoria = tableroPadre.comprobarVictoria(miTablero.getTablero());
                }
            }
            if (ganador == 0) {
                tableroIAOculto.mostrarTablero();
                miTablero.mostrarTablero();
                System.out.println("HAS GANADO!!");
            } else {
                tableroIAOculto.mostrarTablero();
                miTablero.mostrarTablero();
                System.out.println("TE HA GANADO LA IA :(");
            }

            String respuesta;
            boolean respuestaValida = false;
            do {
                System.out.println("Quieres jugar otra partida? (s/n)");
                respuesta = datos.nextLine();
                if (respuesta.equalsIgnoreCase("s") || respuesta.equalsIgnoreCase("S")) {
                    jugarOtraPartida = true;
                    respuestaValida = true;
                } else if (respuesta.equalsIgnoreCase("n") || respuesta.equalsIgnoreCase("N")) {
                    jugarOtraPartida = false;
                    respuestaValida = true;
                } else {
                    System.out.println("Respuesta no valida, prueba otra vez.");
                    respuestaValida = false;
                }
            } while (respuestaValida == false);
        }
        System.out.println("Gracias por jugar! Hasta luego!");
    }
}
//Espero que te guste mi proyecto
