package p3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random; 
import java.io.IOException;
import java.io.FileNotFoundException;

/* El sencillo problema de sumar los elementos de un vector
   resuelto mediante 3 algoritmos diferentes.
   Medicion de tiempos para un n ejemplo */

public class PuntosTrivial
{
static int []v;
	
// main para probar funcionamiento y medir tiempos
public static void main (String arg []) 
{
	List<double[]> lista = new ArrayList<double[]>();
	try {
		lista = leerArchivo(arg[0]);
	}
	catch (Exception e) {

	}
	int pos1 = 0;
	int pos2 = 0;
	double distancia = Double.POSITIVE_INFINITY;

	for (int i = 0; i<lista.size(); i++) {
		for (int j = 0; j<lista.size();j++) {
			double distanciaLocal = compararPos(lista.get(i), lista.get(j));
			if (distanciaLocal<distancia) {
				pos1 = i;
				pos2 = j;
				distancia = distanciaLocal;
			}
		}
	}

	System.out.print("PUNTOS MÁS CERCANOS: "+lista.get(pos1)+" "+lista.get(pos2));
	System.out.print("SU DISTANCIA MÍNIMA: "+distancia);
}  // main 
	

public static double compararPos(double[] pos1, double[] pos2) {
	return (Math.max(Math.abs(pos1[0]-pos2[0]), Math.abs(pos1[1]-pos2[1])));
}

public static List<double[]> leerArchivo(String filename) throws FileNotFoundException, IOException {
	List<double[]> list
            = new ArrayList<double[]>();
      
        // load data from file
        BufferedReader bf = new BufferedReader(
            new FileReader(filename));
      
        // read entire line as string
        String line = bf.readLine();
      
        // checking for end of file
        while (line != null) {
			String[] split = line.split(",");
			double[] tupla = {Double.valueOf(split[0]), Double.valueOf(split[1])};
            list.add(tupla);
            line = bf.readLine();
        }
      
        // closing bufferreader object
        bf.close();
		return list;
}   
	


}  //  clase 
