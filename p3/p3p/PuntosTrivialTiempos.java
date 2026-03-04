package p3p;

import java.util.*;

public class PuntosTrivialTiempos {
    public static void main(String[] args) {
        System.out.println("n\t\ttiempo (ms)");

        Random rnd = new Random();
        
        for (int n = 1024; ; n *= 2) {
            List<PuntosTrivial.Punto> listaPuntos = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                listaPuntos.add(new PuntosTrivial.Punto(rnd.nextDouble() * 100, rnd.nextDouble() * 100));
            }

            long t1 = System.currentTimeMillis();
            PuntosTrivial.calcularTrivial(listaPuntos); 
            long t2 = System.currentTimeMillis();
            
            long tiempo = t2 - t1;

            
            System.out.println(n + "\t\t" + tiempo);
        }
    }
}

