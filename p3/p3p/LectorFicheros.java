package p3p;

import java.io.BufferedReader;
import java.io.FileReader;

public class LectorFicheros {

    public static double[][] leerFichero(String nombreFichero) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(nombreFichero));
        
        int n = Integer.parseInt(br.readLine().trim());
        double[][] puntos = new double[n][2];
        
        for (int i = 0; i < n; i++) {
            String linea = br.readLine();
            String[] coordenadas = linea.split(","); 
            
            puntos[i][0] = Double.parseDouble(coordenadas[0]); 
            puntos[i][1] = Double.parseDouble(coordenadas[1]); 
        }
        
        br.close();
        return puntos;
    }
}