package p3.p3p;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PuntosTrivial
{
	public static void main (String arg []) {
		String nombreFichero = "..\\p3\\p3p\\" + arg[0];
		String datosFichero = "";
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(nombreFichero));
			while (in.ready()) {
				datosFichero += in.readLine() + "\n";
			}

		} catch (FileNotFoundException e) {
			System.err.println("Fichero de datos no encontrado (LOS .CLASS DEBEN ESTAR EN p3(practica)/p3(contenedor .classes)/p3p)");
			return;

		} catch (Exception e) {
			System.err.println(e);
			return;
		}

		String[] datos = datosFichero.split("\n");
		new PuntosTrivial().resolverProblema(datos, true);
	}

	public void resolverProblema(String[] datos, boolean imprimirResultados) {

		int tamaño = Integer.parseInt(datos[0]);
		Punto[] puntos = new Punto[tamaño];
		
		// Creacion del array de datos con todos los puntos

		for (int i=0; i<tamaño; i++) {
			String lineaPunto = datos[i+1];
			String[] coords = lineaPunto.split(",");
			double x = Double.parseDouble(coords[0]);
			double y = Double.parseDouble(coords[1]);
			puntos[i] = new Punto(x, y);
		}

		// Resolucion del algoritmo de forma TRIVIAL

		double menorDistancia = Double.POSITIVE_INFINITY;
		Punto punto1 = null;
		Punto punto2 = null;

		for (int i=0; i<tamaño; i++) {
			for (int j=i; j<tamaño; j++) {
				if (i==j) continue;
				double distancia = puntos[i].calcularDistancia(puntos[j]);
				if (distancia < menorDistancia) {
					menorDistancia = distancia;
					punto1 = puntos[i];
					punto2 = puntos[j];
				}
			}
		}

		if (!imprimirResultados) return;

		System.out.println(String.format("PUNTOS MÁS CERCANOS: [%f, %f] [%f, %f]", 
										punto1.getX(), punto1.getY(), 
										punto2.getX(), punto2.getY()));

		System.out.println(String.format("SU DISTANCIA MÍNIMA = %.6f", menorDistancia)); 
	}

	class Punto {

		private double x;
		private double y;

		public Punto(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getX() {return x;}
		public double getY() {return y;}

		public double calcularDistancia(Punto otro) {
			double dx = Math.abs(this.getX() - otro.getX());
			double dy = Math.abs(this.getY() - otro.getY());
			return Math.sqrt(dx*dx + dy*dy);

		}

	}
}