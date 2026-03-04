package p3p;

public class PuntosTrivial {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Falta el nombre del fichero. Ejemplo: java p3p.PuntosTrivial datos32.txt");
            return;
        }

        try {
            double[][] puntos = LectorFicheros.leerFichero("p3p/" + args[0]);

            trivial(puntos);


        } catch (Exception e) {
            System.out.println("Error al procesar el fichero: " + e.getMessage());
        }
    }

    public static void trivial(double[][] puntos) {
        int n = puntos.length;
        double distMinima = Double.MAX_VALUE;
        double[] p1Min = null;
        double[] p2Min = null;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                
                double d = calcularDistancia(puntos[i], puntos[j]);
                
                if (d < distMinima) {
                    distMinima = d;
                    p1Min = puntos[i];
                    p2Min = puntos[j];
                }
            }
        }

       //RESULTADOS
       //System.out.println("PUNTOS MÁS CERCANOS: [" + p1Min[0] + ", " + p1Min[1] + "] [" + p2Min[0] + ", " + p2Min[1] + "]");
       //System.out.println("SU DISTANCIA MÍNIMA= " + String.format("%.6f", distMinima));
    }

    public static double calcularDistancia(double[] p1, double[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }
}