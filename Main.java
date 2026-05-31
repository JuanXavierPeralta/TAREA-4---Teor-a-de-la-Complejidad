public class Main {

    public static void main(String[] args) {

        int filas = 1000;
        int columnas = 1000;
        int minimo = -1000;
        int maximo = 1000;

        int[][] matriz = MatrizAleatoria.generarMatriz(filas, columnas, minimo, maximo);
        int[] arreglo = MatrizAleatoria.convertirMatrizAArreglo(matriz);

        System.out.println("Matriz generada: " + filas + " x " + columnas);
        System.out.println("Rango de valores: " + minimo + " a " + maximo);
        System.out.println("Total de elementos: " + arreglo.length);

        System.out.println("\nVista parcial de la matriz:");
        MatrizAleatoria.mostrarParteMatriz(matriz, 5, 10);

        int[] muestra = obtenerMuestra(arreglo, 10000);

        System.out.println("\nPrueba de ordenamientos con muestra:");
        System.out.println("Datos evaluados: " + muestra.length);
        System.out.printf("%-18s %-10s %-15s%n", "Algoritmo", "Ordenado", "Tiempo (ms)");
        System.out.println("---------------------------------------------");

        probarOrdenamiento("Bubble Sort", muestra.clone());
        probarOrdenamiento("Insertion Sort", muestra.clone());

        System.out.println("\nPrueba de ordenamientos con arreglo completo:");
        System.out.println("Datos evaluados: " + arreglo.length);
        System.out.printf("%-18s %-10s %-15s%n", "Algoritmo", "Ordenado", "Tiempo (ms)");
        System.out.println("---------------------------------------------");

        int[] arregloOrdenado = probarOrdenamiento("Merge Sort", arreglo.clone());
        probarOrdenamiento("Shell Sort", arreglo.clone());
        probarOrdenamiento("Counting Sort", arreglo.clone());
        probarOrdenamiento("Radix Sort", arreglo.clone());

        System.out.println("\nPrimeros 20 valores del arreglo ordenado:");
        MatrizAleatoria.mostrarPrimerosValores(arregloOrdenado, 20);

        int x = 250;
        int negativoX = -x;

        System.out.println("\nBusqueda de valores:");
        System.out.println("Valores buscados: " + x + " y " + negativoX);

        System.out.println("\nBusqueda secuencial sobre arreglo original:");
        medirBusqueda("Secuencial", arreglo, x, false, false);
        medirBusqueda("Secuencial", arreglo, negativoX, false, false);

        System.out.println("\nBusqueda binaria sobre arreglo ordenado:");
        medirBusqueda("Binaria", arregloOrdenado, x, true, false);
        medirBusqueda("Binaria", arregloOrdenado, negativoX, true, false);

        System.out.println("\nBusqueda por interpolacion sobre arreglo ordenado:");
        medirBusqueda("Interpolacion", arregloOrdenado, x, false, true);
        medirBusqueda("Interpolacion", arregloOrdenado, negativoX, false, true);

        // PROBLEMA PARTE 2
        int tamanoVentana = 100;
        int umbralSobrecorriente = 10000;

        int[] muestraSobrecorriente = obtenerMuestra(arreglo, 10000);

        System.out.println("\nProblema adicional: deteccion de sobrecorriente");
        System.out.println("Tamano de ventana: " + tamanoVentana);
        System.out.println("Umbral de sobrecorriente: " + umbralSobrecorriente);

        long inicioNoOptimizado = MedicionTiempo.iniciar();
        boolean resultadoNoOptimizado = DetectorSobrecorriente.detectarNoOptimizado(
                muestraSobrecorriente,
                tamanoVentana,
                umbralSobrecorriente);
        double tiempoNoOptimizado = MedicionTiempo.finalizarEnMilisegundos(inicioNoOptimizado);

        long inicioOptimizadoMuestra = MedicionTiempo.iniciar();
        boolean resultadoOptimizadoMuestra = DetectorSobrecorriente.detectarOptimizado(
                muestraSobrecorriente,
                tamanoVentana,
                umbralSobrecorriente);
        double tiempoOptimizadoMuestra = MedicionTiempo.finalizarEnMilisegundos(inicioOptimizadoMuestra);

        long inicioOptimizadoCompleto = MedicionTiempo.iniciar();
        boolean resultadoOptimizadoCompleto = DetectorSobrecorriente.detectarOptimizado(
                arreglo,
                tamanoVentana,
                umbralSobrecorriente);
        double tiempoOptimizadoCompleto = MedicionTiempo.finalizarEnMilisegundos(inicioOptimizadoCompleto);

        System.out.println("\nComparacion de soluciones:");
        System.out.printf("%-30s %-18s %-18s %-15s%n", "Solucion", "Datos evaluados", "Resultado", "Tiempo (ms)");
        System.out.println("--------------------------------------------------------------------------------");

        System.out.printf("%-30s %-18d %-18s %-15.4f%n",
                "No optimizada O(n*k)",
                muestraSobrecorriente.length,
                resultadoNoOptimizado,
                tiempoNoOptimizado);

        System.out.printf("%-30s %-18d %-18s %-15.4f%n",
                "Optimizada O(n) - muestra",
                muestraSobrecorriente.length,
                resultadoOptimizadoMuestra,
                tiempoOptimizadoMuestra);

        System.out.printf("%-30s %-18d %-18s %-15.4f%n",
                "Optimizada O(n) - completo",
                arreglo.length,
                resultadoOptimizadoCompleto,
                tiempoOptimizadoCompleto);

    }

    public static int[] probarOrdenamiento(String nombre, int[] arreglo) {
        long inicio = MedicionTiempo.iniciar();

        if (nombre.equals("Bubble Sort")) {
            Ordenamientos.bubbleSort(arreglo);
        } else if (nombre.equals("Insertion Sort")) {
            Ordenamientos.insertionSort(arreglo);
        } else if (nombre.equals("Merge Sort")) {
            Ordenamientos.mergeSort(arreglo);
        } else if (nombre.equals("Shell Sort")) {
            Ordenamientos.shellSort(arreglo);
        } else if (nombre.equals("Counting Sort")) {
            Ordenamientos.countingSort(arreglo);
        } else if (nombre.equals("Radix Sort")) {
            Ordenamientos.radixSort(arreglo);
        }

        double tiempoMs = MedicionTiempo.finalizarEnMilisegundos(inicio);

        System.out.printf("%-18s %-10s %-15.4f%n",
                nombre,
                Ordenamientos.estaOrdenado(arreglo),
                tiempoMs);

        return arreglo;
    }

    public static int[] obtenerMuestra(int[] arreglo, int cantidad) {
        int limite = Math.min(cantidad, arreglo.length);
        int[] muestra = new int[limite];

        for (int i = 0; i < limite; i++) {
            muestra[i] = arreglo[i];
        }

        return muestra;
    }

    public static void medirBusqueda(String metodo, int[] arreglo, int valor, boolean usarBinaria,
            boolean usarInterpolacion) {
        long inicio = MedicionTiempo.iniciar();

        int posicion;

        if (usarBinaria) {
            posicion = Busquedas.busquedaBinaria(arreglo, valor);
        } else if (usarInterpolacion) {
            posicion = Busquedas.busquedaInterpolacion(arreglo, valor);
        } else {
            posicion = Busquedas.busquedaSecuencial(arreglo, valor);
        }

        double tiempoMs = MedicionTiempo.finalizarEnMilisegundos(inicio);

        if (posicion != -1) {
            System.out.printf("%-15s valor %-6d posicion %-10d tiempo %.6f ms%n",
                    metodo, valor, posicion, tiempoMs);
        } else {
            System.out.printf("%-15s valor %-6d no encontrado       tiempo %.6f ms%n",
                    metodo, valor, tiempoMs);
        }
    }
}