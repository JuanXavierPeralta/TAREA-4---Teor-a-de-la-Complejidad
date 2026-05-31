public class DetectorSobrecorriente {

    public static boolean detectarNoOptimizado(int[] arreglo, int tamanoVentana, int umbral) {
        for (int i = 0; i <= arreglo.length - tamanoVentana; i++) {
            int suma = 0;

            for (int j = i; j < i + tamanoVentana; j++) {
                suma += arreglo[j];
            }

            if (suma > umbral) {
                return true;
            }
        }

        return false;
    }

    public static boolean detectarOptimizado(int[] arreglo, int tamanoVentana, int umbral) {
        if (tamanoVentana > arreglo.length) {
            return false;
        }

        int sumaVentana = 0;

        for (int i = 0; i < tamanoVentana; i++) {
            sumaVentana += arreglo[i];
        }

        if (sumaVentana > umbral) {
            return true;
        }

        for (int i = tamanoVentana; i < arreglo.length; i++) {
            sumaVentana = sumaVentana - arreglo[i - tamanoVentana] + arreglo[i];

            if (sumaVentana > umbral) {
                return true;
            }
        }

        return false;
    }
}