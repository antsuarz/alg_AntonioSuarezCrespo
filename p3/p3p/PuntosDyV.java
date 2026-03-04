package p3p;

import java.io.*;
import java.util.*;

public class PuntosDyV {
    public static class Punto {
        public double x, y;
        public Punto(double x, double y) { this.x = x; this.y = y; }
    }

    public static Punto p1Min, p2Min;

    public static double distancia(Punto p1, Punto p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static double calcularDyV(List<Punto> puntos) {
        puntos.sort(Comparator.comparingDouble(p -> p.x));
        return calcularRecursivo(puntos);
    }

    private static double calcularRecursivo(List<Punto> puntos) {
        int n = puntos.size();
        if (n <= 3) {
            double min = Double.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    double d = distancia(puntos.get(i), puntos.get(j));
                    if (d < min) {
                        min = d; p1Min = puntos.get(i); p2Min = puntos.get(j);
                    }
                }
            }
            return min;
        }

        int m = n / 2;
        double dMin = Math.min(calcularRecursivo(puntos.subList(0, m)), calcularRecursivo(puntos.subList(m, n)));

        List<Punto> franja = new ArrayList<>();
        for (Punto p : puntos) if (Math.abs(p.x - puntos.get(m).x) < dMin) franja.add(p);
        franja.sort(Comparator.comparingDouble(p -> p.y));

        for (int i = 0; i < franja.size(); i++) {
            for (int j = i + 1; j < franja.size() && (franja.get(j).y - franja.get(i).y) < dMin; j++) {
                double d = distancia(franja.get(i), franja.get(j));
                if (d < dMin) {
                    dMin = d; p1Min = franja.get(i); p2Min = franja.get(j);
                }
            }
        }
        return dMin;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) return;
        List<Punto> puntos = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        br.readLine();
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] c = linea.trim().split(",");
            if (c.length >= 2) puntos.add(new Punto(Double.parseDouble(c[0]), Double.parseDouble(c[1])));
        }
        br.close();

        double min = calcularDyV(puntos);
        System.out.printf("PUNTOS MÁS CERCANOS: [%.6f, %.6f] [%.6f, %.6f]%n", p1Min.x, p1Min.y, p2Min.x, p2Min.y);
        System.out.printf("SU DISTANCIA MÍNIMA = %.6f%n", min);
    }
}