package p3.p3p;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

public class PuntosDyV
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
		new PuntosDyV().resolverProblema(datos, true);
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

		// Resolucion del algoritmo por DIVIDE Y VENCERAS
		Arrays.sort(puntos, Comparator.comparingDouble(Punto::getX));
		Resultado r = minimaDistancia(puntos, 0, puntos.length - 1);

		if (!imprimirResultados) return;

		System.out.println(String.format("PUNTOS MÁS CERCANOS: [%f, %f] [%f, %f]", 
										r.getP1().getX(), r.getP1().getY(), 
										r.getP2().getX(), r.getP2().getY()));

		System.out.println(String.format("SU DISTANCIA MÍNIMA = %.6f", r.getDistancia())); 
	}

	
	private Resultado minimaDistancia(Punto[] puntos, int izq, int der) {
		if (der-izq <= 1) {
			return new Resultado(puntos[izq], puntos[der]);
		} else {
			int medio = (izq + der) / 2;
			Resultado resIzq = minimaDistancia(puntos, izq, medio);
			Resultado resDer = minimaDistancia(puntos, medio, der);
			return (resIzq.getDistancia() <= resDer.getDistancia()) ? resIzq : resDer;
		}
	}


	class Resultado {
		private Punto p1;
		private Punto p2;
		private double distancia;

		public Resultado(Punto p1, Punto p2) {
			this.p1 = p1;
			this.p2 = p2;
			this.distancia = p1.calcularDistancia(p2);
		}
		
		public Resultado(Punto p1, Punto p2, double distancia) {
			this.p1 = p1;
			this.p2 = p2;
			this.distancia = distancia;
		}

		public Punto getP1() {return p1;}
		public Punto getP2() {return p2;}
		public double getDistancia() {return distancia;}
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