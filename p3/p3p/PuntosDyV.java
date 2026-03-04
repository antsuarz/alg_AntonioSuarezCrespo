package p3.p3p;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PuntosDyV
{
public static DistanciaMinima calculaDistanciaMin (List<Punto> listaPuntos){
	int n = listaPuntos.size();

	if(n<=1)
	return new DistanciaMinima(null,null,Double.MAX_VALUE);

	if(n ==2){
	double dist = listaPuntos.get(0).calculaDistancia(listaPuntos.get(1));
	return new DistanciaMinima(listaPuntos.get(0),listaPuntos.get(1),dist);
	}
	int mitad = n/2;

	List<Punto> izq = listaPuntos.subList(0, mitad);
	List<Punto> dcha = listaPuntos.subList(mitad, n);

	DistanciaMinima dIzq = calculaDistanciaMin(new ArrayList<Punto>(izq));
	DistanciaMinima dDcha = calculaDistanciaMin(new ArrayList<Punto>(dcha));

	DistanciaMinima dMin = dIzq.getDist() < dDcha.getDist() ? dIzq : dDcha;
	double linea = listaPuntos.get(mitad).getCoordX();

	List<Punto> cercaIzq = new ArrayList<>();
	List<Punto> cercaDcha = new ArrayList<>();

	for(int i = izq.size() -1 ; i>=0;i--){
		if(Math.abs(izq.get(i).getCoordX() - linea) < dMin.getDist())
			cercaIzq.add(izq.get(i));
		else
			break;
	}

	for(int i = 0;i < dcha.size(); i++){
		if(Math.abs(dcha.get(i).getCoordX() - linea) < dMin.getDist())
			cercaDcha.add(dcha.get(i));
		else
			break;
	}

	for(int i = 0; i < cercaIzq.size(); i++){
		for(int j = 0; j < cercaDcha.size(); j++){
			Punto a = cercaIzq.get(i);
			Punto b = cercaDcha.get(j);
			double dist = a.calculaDistancia(b);

			if(dist < dMin.dist){
				dMin = new DistanciaMinima(a,b,dist);
			}

		}
	}

	return dMin;
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
	puntos.sort(Comparator.comparingDouble(Punto::getCoordX));
	DistanciaMinima dist = calculaDistanciaMin(puntos);
	
	System.out.println("Puntos mas cercanos: " + dist.getP1() + " " + dist.getP2());
	System.out.println("Distancia minima: " + dist.getDist());
}
}

