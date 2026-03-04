package p3.p3p;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PuntosTrivial
{
public static DistanciaMinima calculaDistanciaMin (List<Punto> listaPuntos){
	double min = Double.MAX_VALUE;
	Punto p1 = null;
	Punto p2 = null;

	for(int i = 0; i < listaPuntos.size();i++){
		for(int j = 0; j < listaPuntos.size(); j++){
			if(listaPuntos.get(i).getCoordX() == listaPuntos.get(j).getCoordX()
			&& listaPuntos.get(i).getCoordY() == listaPuntos.get(j).getCoordY()){
			continue;
			}

			double distancia = listaPuntos.get(i).calculaDistancia(listaPuntos.get(j));
			if(distancia < min){
				min = distancia;
				p1 = listaPuntos.get(i);
				p2 = listaPuntos.get(j);
			}
		}
	}

	return new DistanciaMinima(p1,p2,min);
}

public static void loadFilePuntos(String nombreFicheroEntrada, List<Punto> listaPuntos){
	String linea;
	String[] datosPunto = null;

	try {
		BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
		Integer.parseInt(fichero.readLine());
		while(fichero.ready()){
			linea = fichero.readLine();
			datosPunto = linea.split(",");
			listaPuntos.add(new Punto(Double.parseDouble(datosPunto[0]),Double.parseDouble(datosPunto[1])));
		}
		fichero.close();
	} catch (FileNotFoundException fnfe) {
		System.out.println("Archivo no encontrado");
	}catch (IOException ioe){
		new RuntimeException("Error de entrada/salida");
	}
} 

public static void main (String arg []) 
{
	List<Punto> puntos = new ArrayList<>();
	loadFilePuntos(arg[0], puntos);
	System.out.println(calculaDistanciaMin(puntos));
}
}