import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {

        // Matriz que representa el mapa (agua, tierra, jugador y tesoro)
        char[][] mapa = new char[Terrenos.TAM][Terrenos.TAM];
        // Matriz para marcar el camino garantizado desde jugador hasta tesoro
        boolean[][] camino = new boolean[Terrenos.TAM][Terrenos.TAM];

        // Posiciones del jugador y del tesoro (fila, columna)
        int[] jugador = new int[2];
        int[] tesoro = new int[2];

        boolean jugando = true;  // Controla el bucle principal del juego
        int movimientos = 0;     // Contador de movimientos realizados por el jugador

        // Crearemos el mapa en este orden:

        // 1. Generar mapa aleatorio con 60% agua y 40% tierra
        inicializarMapa(mapa);

        // 2. Crear un camino aleatorio garantizado entre jugador y tesoro
        crearCamino(camino, jugador, tesoro);

        // 3. Forzar que el camino sea de tierra para que sea transitable
        forzarCaminoATierra(mapa, camino);

        // 4. Colocar jugador y tesoro en posiciones de tierra
        colocarJugador(jugador, mapa);
        colocarTesoro(tesoro, mapa);

        while (jugando) {

            // Mostrar el mapa actualizado en consola
            mostrarMapa(mapa);

            // Pedir movimiento al usuario (WASD) o Q para rendirse
            System.out.print("Movimiento (WASD, Q rendirse): ");
            char movimiento = sc.next().toUpperCase().charAt(0);

            // Posiciones temporales para calcular el siguiente movimiento
            int numFila = jugador[0];
            int numColumna = jugador[1];

            // Actualizar la posición según la entrada del usuario
            if (movimiento == 'W') numFila--;  // Arriba
            if (movimiento == 'S') numFila++;  // Abajo
            if (movimiento == 'A') numColumna--;  // Izquierda
            if (movimiento == 'D') numColumna++;  // Derecha
            if (movimiento == 'Q') jugando = false;  // Rendirse

            if (jugando) {

                // Comprobar límites y que no se pueda caminar sobre el agua
                if (numFila >= 0 && numFila < Terrenos.TAM &&
                        numColumna >= 0 && numColumna < Terrenos.TAM &&
                        mapa[numFila][numColumna] != Terrenos.MAR) {

                    // Marcar la posición anterior como tierra
                    mapa[jugador[0]][jugador[1]] = Terrenos.TIERRA;
                    // Actualizar posición del jugador
                    jugador[0] = numFila;
                    jugador[1] = numColumna;
                    movimientos++;

                    // Colocar jugador en la nueva posición
                    mapa[numFila][numColumna] = Terrenos.JUGADOR;

                    // Comprobar si el jugador llegó al tesoro
                    if (jugador[0] == tesoro[0] && jugador[1] == tesoro[1]) {
                        mostrarMapa(mapa);
                        System.out.println("¡HAS ENCONTRADO EL TESORO!");
                        System.out.println("Movimientos realizados: " + movimientos);
                        jugando = false;
                    }
                }
            }
        }

        // Mensaje de fin si el jugador se rinde o no encuentra el tesoro
        if (!(jugador[0] == tesoro[0] && jugador[1] == tesoro[1])) {
            System.out.println(". . . . FIN DE LA AVENTURA . . . .");
        }
    }

    // Inicializa el mapa con agua.
    // Actualmente, se llena de agua y después se forzará el camino a tierra.
    public static void inicializarMapa(char[][] mapa) {
        for (int i = 0; i < Terrenos.TAM; i++) {
            for (int j = 0; j < Terrenos.TAM; j++) {
                mapa[i][j] = Terrenos.MAR;
            }
        }
    }

    // Crea un camino aleatorio entre el jugador y el tesoro.
    // Las posiciones se almacenan en el array booleano 'camino'.
    public static void crearCamino(boolean[][] camino, int[] jugador, int[] tesoro) {
        // Posiciones aleatorias iniciales
        jugador[0] = rand.nextInt(Terrenos.TAM);
        jugador[1] = rand.nextInt(Terrenos.TAM);

        tesoro[0] = rand.nextInt(Terrenos.TAM);
        tesoro[1] = rand.nextInt(Terrenos.TAM);

        // Camino aleatorio desde jugador hasta tesoro
        int fila = jugador[0];
        int columna = jugador[1];
        camino[fila][columna] = true;

        while (fila != tesoro[0] || columna != tesoro[1]) {
            int dir = rand.nextInt(4);
            int numFila = fila;
            int numColumna = columna;

            // Movimiento aleatorio en una de las 4 direcciones
            if (dir == 0) numFila--; // Arriba
            if (dir == 1) numFila++; // Abajo
            if (dir == 2) numColumna--; // Izquierda
            if (dir == 3) numColumna++; // Derecha

            // Comprobar límites
            if (numFila >= 0 && numFila < Terrenos.TAM && numColumna >= 0 && numColumna < Terrenos.TAM) {
                fila = numFila;
                columna = numColumna;
                camino[fila][columna] = true; // Marcar la casilla como parte del camino
            }
        }
    }

    // Convierte todas las casillas marcadas en "camino" a tierra
    public static void forzarCaminoATierra(char[][] mapa, boolean[][] camino) {
        for (int i = 0; i < Terrenos.TAM; i++) {
            for (int j = 0; j < Terrenos.TAM; j++) {
                if (camino[i][j]) {
                    mapa[i][j] = Terrenos.TIERRA;
                }
            }
        }
    }

    // Coloca al jugador en una posición aleatoria de tierra
    public static void colocarJugador(int[] jugador, char[][] mapa) {
        boolean colocado = false;
        while (!colocado) {
            int f = rand.nextInt(Terrenos.TAM);
            int c = rand.nextInt(Terrenos.TAM);
            if (mapa[f][c] == Terrenos.TIERRA) {
                jugador[0] = f;
                jugador[1] = c;
                mapa[f][c] = Terrenos.JUGADOR;
                colocado = true;
            }
        }
    }

    // Coloca el tesoro en una posición aleatoria de tierra
    public static void colocarTesoro(int[] tesoro, char[][] mapa) {
        boolean colocado = false;
        while (!colocado) {
            int f = rand.nextInt(Terrenos.TAM);
            int c = rand.nextInt(Terrenos.TAM);
            if (mapa[f][c] == Terrenos.TIERRA) {
                tesoro[0] = f;
                tesoro[1] = c;
                mapa[f][c] = Terrenos.TESORO;
                colocado = true;
            }
        }
    }

    // Muestra el mapa en consola con colores según el tipo de terreno
    public static void mostrarMapa(char[][] mapa) {
        System.out.println();
        for (int i = 0; i < Terrenos.TAM; i++) {
            for (int j = 0; j < Terrenos.TAM; j++) {
                if (mapa[i][j] == Terrenos.TIERRA)
                    System.out.print(Colores.TIERRA + mapa[i][j] + Colores.RESET + "\t");
                if (mapa[i][j] == Terrenos.MAR)
                    System.out.print(Colores.MAR + mapa[i][j] + Colores.RESET + "\t");
                if (mapa[i][j] == Terrenos.JUGADOR)
                    System.out.print(Colores.JUGADOR + mapa[i][j] + Colores.RESET + "\t");
                if (mapa[i][j] == Terrenos.TESORO)
                    System.out.print(Colores.TESORO + mapa[i][j] + Colores.RESET + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
