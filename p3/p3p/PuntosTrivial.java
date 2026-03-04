package p3p;

import java.io.*;
import java.util.*;

public class PuntosTrivial {
    public static class Punto {
        public double x, y;
        public Punto(double x, double y) { this.x = x; this.y = y; }
    }

    // Variables para que el main pueda acceder a los puntos encontrados
    public static Punto p1Min, p2Min;

    public static double distancia(Punto p1, Punto p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double calcularTrivial(List<Punto> puntos) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < puntos.size(); i++) {
            for (int j = i + 1; j < puntos.size(); j++) {
                double d = distancia(puntos.get(i), puntos.get(j));
                if (d < minDist) {
                    minDist = d;
                    p1Min = puntos.get(i);
                    p2Min = puntos.get(j);
                }
            }
        }
        return minDist;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) return;
        List<Punto> puntos = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        br.readLine(); // Salta n
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] c = linea.trim().split(",");
            if (c.length >= 2) puntos.add(new Punto(Double.parseDouble(c[0]), Double.parseDouble(c[1])));
        }
        br.close();

        double min = calcularTrivial(puntos);
        
        System.out.printf("PUNTOS MÁS CERCANOS: [%.6f, %.6f] [%.6f, %.6f]%n", p1Min.x, p1Min.y, p2Min.x, p2Min.y);
        System.out.printf("SU DISTANCIA MÍNIMA = %.6f%n", min);
    }
}
