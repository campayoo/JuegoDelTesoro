# 🏝️ Juego de Aventura: Buscar el Tesoro en Java

¡Bienvenido/a al **Juego de Aventura**!  
Este proyecto es un **juego de consola en Java** donde el jugador debe encontrar un **tesoro** moviéndose sobre un mapa de **agua y tierra**.

---

## 🎮 Descripción del juego

- El mapa es una cuadrícula de tamaño `TAM x TAM`.
- Cada casilla puede ser:
  - 🌊 **Agua (`MAR`)** – No transitable.
  - 🟫 **Tierra (`TIERRA`)** – Transitable.
  - 🧑 **Jugador (`JUGADOR`)** – Tu posición actual.
  - 🏆 **Tesoro (`TESORO`)** – Objetivo del juego.
- El **60% del mapa** es agua y el **40% tierra**.
- Se garantiza un **camino de tierra** entre el jugador y el tesoro.
- Movimiento con las teclas:
  - `W` → Arriba  
  - `S` → Abajo  
  - `A` → Izquierda  
  - `D` → Derecha  
  - `Q` → Rendirse

---

## ⚙️ Cómo jugar

1. Clona o descarga el repositorio.
2. Compila y ejecuta el programa:

```bash
javac Main.java
java Main
Observa el mapa generado en la consola.

Ingresa un movimiento (W, A, S, D) para avanzar.

Encuentra el tesoro antes de rendirte (Q para salir).

El juego mostrará la cantidad de movimientos realizados al encontrar el tesoro.

# 🖼️ Ejemplo de mapa en consola
Copiar código
🌊 🌊 🌊 🟫 🟫 🧑 🌊 🌊
🌊 🟫 🌊 🌊 🟫 🟫 🌊 🌊
🌊 🟫 🌊 🌊 🌊 🟫 🌊 🌊
🟫 🟫 🟫 🌊 🟫 🌊 🌊 🏆
🌊 🌊 🟫 🌊 🟫 🌊 🌊 🌊
🧑: Jugador
🏆: Tesoro
🌊: Agua
🟫: Tierra

# 🧩 Funcionalidades
Mapa aleatorio: Cada partida genera un mapa diferente.

Camino garantizado: Siempre existe un camino de tierra entre el jugador y el tesoro.

Colores en consola: Diferenciación visual de agua, tierra, jugador y tesoro con ANSI.

Contador de movimientos: Para medir tu eficiencia.

Posiciones aleatorias: Jugador y tesoro se colocan en tierra.

# 📂 Estructura del proyecto
graphql
Copiar código
/JuegoTesoroJava
│
├── Main.java          # Lógica principal del juego
├── Terrenos.java      # Constantes de los terrenos y entidades
├── Colores.java       # Colores ANSI para la consola
└── README.md          # Este archivo
🔧 Tecnologías
Java 17+

Consola / Terminal

Uso de matrices y arrays

Números aleatorios con Random

💡 Posibles mejoras
Niveles de dificultad (mapas más grandes o más agua)

Obstáculos adicionales (enemigos o trampas)

Guardar récord de movimientos

Interfaz gráfica con Swing o JavaFX
