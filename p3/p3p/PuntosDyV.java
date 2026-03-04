package p3p;

public class PuntosDyV {

    // Variables globales
    static double distanciaMinimaGlobal = Double.MAX_VALUE;
    static double[] mejorPunto1 = null;
    static double[] mejorPunto2 = null;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Falta el nombre del fichero.");
            return;
        }

        try {
            String rutaFichero = args[0];
            double[][] puntos = LectorFicheros.leerFichero(rutaFichero);

            rapirec(puntos, 0, puntos.length - 1, 1);

            recursivoDyV(puntos, 0, puntos.length - 1);

            System.out.println("PUNTOS MÁS CERCANOS: [" + mejorPunto1[0] + ", " + mejorPunto1[1] + "] [" + mejorPunto2[0] + ", " + mejorPunto2[1] + "]");
            System.out.println("SU DISTANCIA MÍNIMA= " + String.format("%.6f", distanciaMinimaGlobal));

        } catch (Exception e) {
            System.out.println("Error al procesar el fichero: " + e.getMessage());
        }
    }

    public static double recursivoDyV(double[][] puntos, int izq, int der) {
        
        if (der - izq <= 2) {
            return trivial(puntos, izq, der);
        }

        int mitad = (izq + der) / 2;
        double coordenadaXCorte = puntos[mitad][0];

        double minIzq = recursivoDyV(puntos, izq, mitad);
        double minDer = recursivoDyV(puntos, mitad + 1, der);
        double d = Math.min(minIzq, minDer);

        double[][] franja = new double[der - izq + 1][2];
        int numPuntosFranja = 0;
        
        for (int i = izq; i <= der; i++) {
            if (Math.abs(puntos[i][0] - coordenadaXCorte) < d) {
                franja[numPuntosFranja] = puntos[i];
                numPuntosFranja++;
            }
        }

        if (numPuntosFranja > 1) {
            rapirec(franja, 0, numPuntosFranja - 1, 0);
        }

        for (int i = 0; i < numPuntosFranja; i++) {
            for (int j = i + 1; j < numPuntosFranja; j++) {
                
                if ((franja[j][1] - franja[i][1]) >= d) {
                    break; 
                }
                
                double distancia = calcularDistancia(franja[i], franja[j]);
                if (distancia < d) {
                    d = distancia;
                    actualizarMejores(franja[i], franja[j], distancia);
                }
            }
        }

        return d;
    }
    
    // MÉTODO DE ORDENACIÓN (QUICKSORT DE P2) 
    private static void intercambiar(double[][] v, int i, int j) {
        double[] t;
        t = v[i];
        v[i] = v[j];
        v[j] = t;
    }

    private static void rapirec(double[][] v, int iz, int de, int eje) {
        int m;
        if (de > iz) {
            m = particion(v, iz, de, eje);
            rapirec(v, iz, m - 1, eje);
            rapirec(v, m + 1, de, eje);
        }
    }

    private static int particion(double[][] v, int iz, int de, int eje) {
        int i;
        double pivote;
        intercambiar(v, (iz + de) / 2, iz);
        
        pivote = v[iz][eje]; 
        
        i = iz;
        for (int s = iz + 1; s <= de; s++) {
            if (v[s][eje] <= pivote) {
                i++;
                intercambiar(v, i, s);
            }
        }
        intercambiar(v, iz, i);
        return i; 
    }

    //AUXILIARES
    private static double trivial(double[][] puntos, int izq, int der) {
        double minLocal = Double.MAX_VALUE;
        for (int i = izq; i < der; i++) {
            for (int j = i + 1; j <= der; j++) {
                double dist = calcularDistancia(puntos[i], puntos[j]);
                if (dist < minLocal) {
                    minLocal = dist;
                    actualizarMejores(puntos[i], puntos[j], dist);
                }
            }
        }
        return minLocal;
    }

    private static double calcularDistancia(double[] p1, double[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }

    private static void actualizarMejores(double[] p1, double[] p2, double dist) {
        if (dist < distanciaMinimaGlobal) {
            distanciaMinimaGlobal = dist;
            mejorPunto1 = p1;
            mejorPunto2 = p2;
        }
    }
}