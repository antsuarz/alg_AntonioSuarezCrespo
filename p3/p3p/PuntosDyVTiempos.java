package p3p;

import java.util.*;

public class PuntosDyVTiempos {
    public static void main(String[] args) {
        System.out.println("n\t\ttiempo (ms)");
        Random rnd = new Random();
        for (int n = 1024; ; n *= 2) {
            List<PuntosDyV.Punto> lista = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                lista.add(new PuntosDyV.Punto(rnd.nextDouble() * 100, rnd.nextDouble() * 100));
            }

            long t1 = System.currentTimeMillis();
            PuntosDyV.calcularDyV(lista);
            long t2 = System.currentTimeMillis();
            
            long tiempo = t2 - t1;

            System.out.println(n + "\t\t" + tiempo);
        }
    }
}

