public class Busquedas {

    //BUSQUEDA SECUENCIAL
    public static int busquedaSecuencial(int[] arreglo, int valorBuscado) {
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == valorBuscado) {
                return i;
            }
        }

        return -1;
    }

    //BUSQUEDA BINARIA
    public static int busquedaBinaria(int[] arreglo, int valorBuscado) {
        int inicio = 0;
        int fin = arreglo.length - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;

            if (arreglo[medio] == valorBuscado) {
                return medio;
            }

            if (arreglo[medio] < valorBuscado) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return -1;
    }

    //BUSQUEDA INTERPOLACION
    public static int busquedaInterpolacion(int[] arreglo, int valorBuscado) {
        int inicio = 0;
        int fin = arreglo.length - 1;

        while (inicio <= fin
                && valorBuscado >= arreglo[inicio]
                && valorBuscado <= arreglo[fin]) {

            if (arreglo[inicio] == arreglo[fin]) {
                if (arreglo[inicio] == valorBuscado) {
                    return inicio;
                } else {
                    return -1;
                }
            }

            int posicion = inicio + (int) (((long) (valorBuscado - arreglo[inicio]) * (fin - inicio))
                    / (arreglo[fin] - arreglo[inicio]));

            if (posicion < inicio || posicion > fin) {
                return -1;
            }

            if (arreglo[posicion] == valorBuscado) {
                return posicion;
            }

            if (arreglo[posicion] < valorBuscado) {
                inicio = posicion + 1;
            } else {
                fin = posicion - 1;
            }
        }

        return -1;
    }
}