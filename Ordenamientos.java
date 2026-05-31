public class Ordenamientos {

    //ORDENAMIENTO BUBBLESORT
    public static void bubbleSort(int[] arreglo) {
        int n = arreglo.length;

        for (int i = 0; i < n - 1; i++) {
            boolean huboCambio = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                    huboCambio = true;
                }
            }

            if (!huboCambio) {
                break;
            }
        }
    }

    //ORDENAMIENTO INSERTIONSORT
    public static void insertionSort(int[] arreglo) {
        for (int i = 1; i < arreglo.length; i++) {
            int actual = arreglo[i];
            int j = i - 1;

            while (j >= 0 && arreglo[j] > actual) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }

            arreglo[j + 1] = actual;
        }
    }

    //ORDENAMIENTO MERGESORT
    public static void mergeSort(int[] arreglo) {
        if (arreglo.length < 2) {
            return;
        }

        int[] auxiliar = new int[arreglo.length];
        mergeSort(arreglo, auxiliar, 0, arreglo.length - 1);
    }

    private static void mergeSort(int[] arreglo, int[] auxiliar, int izquierda, int derecha) {
        if (izquierda >= derecha) {
            return;
        }

        int medio = izquierda + (derecha - izquierda) / 2;

        mergeSort(arreglo, auxiliar, izquierda, medio);
        mergeSort(arreglo, auxiliar, medio + 1, derecha);

        mezclar(arreglo, auxiliar, izquierda, medio, derecha);
    }

    private static void mezclar(int[] arreglo, int[] auxiliar, int izquierda, int medio, int derecha) {
        for (int i = izquierda; i <= derecha; i++) {
            auxiliar[i] = arreglo[i];
        }

        int i = izquierda;
        int j = medio + 1;
        int k = izquierda;

        while (i <= medio && j <= derecha) {
            if (auxiliar[i] <= auxiliar[j]) {
                arreglo[k] = auxiliar[i];
                i++;
            } else {
                arreglo[k] = auxiliar[j];
                j++;
            }
            k++;
        }

        while (i <= medio) {
            arreglo[k] = auxiliar[i];
            i++;
            k++;
        }
    }


    //ORDENAMIENTO SHELLSORT
    public static void shellSort(int[] arreglo) {
        int n = arreglo.length;

        for (int intervalo = n / 2; intervalo > 0; intervalo /= 2) {
            for (int i = intervalo; i < n; i++) {
                int temp = arreglo[i];
                int j = i;

                while (j >= intervalo && arreglo[j - intervalo] > temp) {
                    arreglo[j] = arreglo[j - intervalo];
                    j -= intervalo;
                }

                arreglo[j] = temp;
            }
        }
    }


    //ORDENAMIENTO COUNTINGSORT
    public static void countingSort(int[] arreglo) {
        if (arreglo.length == 0) {
            return;
        }

        int minimo = arreglo[0];
        int maximo = arreglo[0];

        for (int valor : arreglo) {
            if (valor < minimo) {
                minimo = valor;
            }
            if (valor > maximo) {
                maximo = valor;
            }
        }

        int rango = maximo - minimo + 1;
        int[] conteo = new int[rango];

        for (int valor : arreglo) {
            conteo[valor - minimo]++;
        }

        int indice = 0;

        for (int i = 0; i < conteo.length; i++) {
            while (conteo[i] > 0) {
                arreglo[indice] = i + minimo;
                indice++;
                conteo[i]--;
            }
        }
    }


    //ORDENAMIENTO RADIXSORT
    public static void radixSort(int[] arreglo) {
        if (arreglo.length == 0) {
            return;
        }

        int minimo = arreglo[0];

        for (int valor : arreglo) {
            if (valor < minimo) {
                minimo = valor;
            }
        }

        if (minimo < 0) {
            for (int i = 0; i < arreglo.length; i++) {
                arreglo[i] = arreglo[i] - minimo;
            }
        }

        int maximo = obtenerMaximo(arreglo);

        for (int exp = 1; maximo / exp > 0; exp *= 10) {
            countingSortPorDigito(arreglo, exp);
        }

        if (minimo < 0) {
            for (int i = 0; i < arreglo.length; i++) {
                arreglo[i] = arreglo[i] + minimo;
            }
        }
    }

    private static int obtenerMaximo(int[] arreglo) {
        int maximo = arreglo[0];

        for (int valor : arreglo) {
            if (valor > maximo) {
                maximo = valor;
            }
        }

        return maximo;
    }

    private static void countingSortPorDigito(int[] arreglo, int exp) {
        int n = arreglo.length;
        int[] salida = new int[n];
        int[] conteo = new int[10];

        for (int i = 0; i < n; i++) {
            int digito = (arreglo[i] / exp) % 10;
            conteo[digito]++;
        }

        for (int i = 1; i < 10; i++) {
            conteo[i] += conteo[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digito = (arreglo[i] / exp) % 10;
            salida[conteo[digito] - 1] = arreglo[i];
            conteo[digito]--;
        }

        for (int i = 0; i < n; i++) {
            arreglo[i] = salida[i];
        }
    }

    public static boolean estaOrdenado(int[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            if (arreglo[i] > arreglo[i + 1]) {
                return false;
            }
        }

        return true;
    }
}