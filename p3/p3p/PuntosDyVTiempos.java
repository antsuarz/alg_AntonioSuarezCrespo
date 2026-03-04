package p3p;

import java.util.Random;

/* Mide los tiempos del algoritmo Trivial O(n^2) (Fuerza Bruta)
Se ejecuta pasándole solo el número de repeticiones */

public class PuntosDyVTiempos {

    public static void main(String arg[]) {
        int repeticiones = Integer.parseInt(arg[0]);
        Random rnd = new Random();
        long t1, t2;

        System.out.println("--- TIEMPOS ALGORITMO DIVIDE Y VENCERÁS O(n log n) ---");

        // Cortamos en n=64000 porque tardará muchísimo
        for (int n = 1000; n <= 64000; n *= 2) {
            
            double[][] puntos = new double[n][2];
            long t = 0;

            for (int i = 1; i <= repeticiones; i++) {
                
                // Generamos puntos aleatorios
                for (int j = 0; j < n; j++) {
                    puntos[j][0] = rnd.nextDouble() * 10000;
                    puntos[j][1] = rnd.nextDouble() * 10000;
                }

                t1 = System.currentTimeMillis();
                
                PuntosDyV.recursivoDyV(puntos, 0, n - 1); 
                
                t2 = System.currentTimeMillis();
                t = t + (t2 - t1);
            }

            System.out.println("n=" + n + "\tTiempo=" + t + "  \tRepeticiones=" + repeticiones);
        }
    }
}