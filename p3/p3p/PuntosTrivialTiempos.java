package p3.p3p;

import java.util.Random;

public class PuntosTrivialTiempos
{

public static String[] generarPuntos(int n) {

    Random r = new Random();
    String[] datos = new String[n + 1];

    datos[0] = n + "";

    for (int i = 0; i < n; i++) {

        double x = 1 + 99 * r.nextDouble();
        double y = 1 + 99 * r.nextDouble();
        x = Math.round(x * 1_000_000.0) / 1_000_000.0;
        y = Math.round(y * 1_000_000.0) / 1_000_000.0;

        datos[i + 1] = x + "," + y + "\n";
    }

    return datos;
}

public static void main (String arg []) 
{
	long t1,t2;
	int nVeces= Integer.parseInt (arg [0]);
	 
	for (int n=1024;n<=100000000;n*=2)
	{	

		String[] datos = generarPuntos(n);

		t1 = System.currentTimeMillis ();

		for (int repeticiones=1; repeticiones<=nVeces;repeticiones++)
		{ 
			new PuntosTrivial().resolverProblema(datos, false);
		} 

		t2 = System.currentTimeMillis ();

		System.out.println (" n="+n+ "**TIEMPO="+(t2-t1)+"**nVeces="+nVeces);
	}  // for
} // main
} //class