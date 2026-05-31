public class MedicionTiempo {

    public static long iniciar() {
        return System.nanoTime();
    }

    public static double finalizarEnMilisegundos(long tiempoInicio) {
        long tiempoFin = System.nanoTime();
        return (tiempoFin - tiempoInicio) / 1_000_000.0;
    }

    public static void mostrarTiempo(String nombre, double tiempoMs) {
        System.out.printf("%-22s %.4f ms%n", nombre, tiempoMs);
    }
}