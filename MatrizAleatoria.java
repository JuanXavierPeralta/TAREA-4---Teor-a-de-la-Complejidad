import java.util.Random;

public class MatrizAleatoria {

    public static int[][] generarMatriz(int filas, int columnas, int minimo, int maximo) {
        int[][] matriz = new int[filas][columnas];
        Random random = new Random();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = random.nextInt(maximo - minimo + 1) + minimo;
            }
        }

        return matriz;
    }

    public static int[] convertirMatrizAArreglo(int[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int[] arreglo = new int[filas * columnas];

        int indice = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                arreglo[indice] = matriz[i][j];
                indice++;
            }
        }

        return arreglo;
    }

    public static void mostrarParteMatriz(int[][] matriz, int filasMostrar, int columnasMostrar) {
        for (int i = 0; i < filasMostrar && i < matriz.length; i++) {
            for (int j = 0; j < columnasMostrar && j < matriz[i].length; j++) {
                System.out.printf("%8d", matriz[i][j]);
            }
            System.out.println();
        }
    }

    public static void mostrarPrimerosValores(int[] arreglo, int cantidad) {
        for (int i = 0; i < cantidad && i < arreglo.length; i++) {
            System.out.print(arreglo[i] + " ");
        }
        System.out.println();
    }
}