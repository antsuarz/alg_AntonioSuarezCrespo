package p3.p3p;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class PuntosDyVTiempos {

    public static List<Punto> generarPuntos(int n) {
        Random r = new Random();
        List<Punto> puntos = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            double x = r.nextDouble(1, 100);
			double y = r.nextDouble(1, 100);
            x = Math.round(x * 1000000) / 1000000;
            y = Math.round(y * 1000000) / 1000000;
            puntos.add(new Punto(x, y));
        }

        return puntos;
    }

    public static void main(String arg[]) {

        long t1, t2;
        int nVeces = Integer.parseInt(arg[0]);

        for (int n = 1024; n <= 100000000; n *= 2) {
            List<Punto> puntos = generarPuntos(n);
            puntos.sort(Comparator.comparingDouble(Punto::getCoordX));
            t1 = System.currentTimeMillis();

            for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
                PuntosDyV.calculaDistanciaMin(puntos);
            }
            t2 = System.currentTimeMillis();
            long tiempo = t2 - t1;
            System.out.println("n=" + n + " **TIEMPO=" + tiempo + "** nVeces=" + nVeces);
        }
    }
}