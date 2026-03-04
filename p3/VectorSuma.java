package p3;

import java.util.Random; 

/* El sencillo problema de sumar los elementos de un vector
   resuelto mediante 3 algoritmos diferentes.
   Medicion de tiempos para un n ejemplo */

public class VectorSuma
{
static int []v;
	
// main para probar funcionamiento y medir tiempos
public static void main (String arg []) 
{
	int n = 100;
	v = new int[n];
	rellenar(v);
	 
	System.out.println("METODO1: SUMA= " + sum1(v));
	System.out.println("METODO2: SUMA= " + sum2(v));		
	System.out.println("METODO3: SUMA= " + sum3(v));
        long t1,t2;
        int x;
	
	t1= System.currentTimeMillis();
	for (int nVeces=0; nVeces<1000000; nVeces++)
        	x=sum1(v); 
	t2= System.currentTimeMillis();
	System.out.println ("METODO1 >>> n= "+n+"  **  "+"TIEMPO= "+(t2-t1)+ " NANOSEGUNDOS");
	
	t1= System.currentTimeMillis();
	for (int nVeces=0; nVeces<1000000; nVeces++)
        	x=sum2(v); 
	t2= System.currentTimeMillis();
	System.out.println ("METODO2 >>> n= "+n+"  **  "+"TIEMPO= "+(t2-t1)+ " NANOSEGUNDOS");

	t1= System.currentTimeMillis();
	for (int nVeces=0; nVeces<1000000; nVeces++)
        	x=sum3(v); 
	t2= System.currentTimeMillis();
	System.out.println ("METODO3 >>> n= "+n+"  **  "+"TIEMPO= "+(t2-t1)+ " NANOSEGUNDOS");
}  // main 
	
/* ya conocido para rellenar el vector */
public static void rellenar(int[]a) 
{
	Random r= new Random();
	int n=a.length;
	for(int i=0;i<n;i++)
		a[i]= r.nextInt(199)-99; //entre -99 and 99
}   
	
	
/* iterativo O(n) */
public static int sum1(int[]a) 
{
	int n= a.length;
	int s=0;
	for(int i=0;i<n;i++)
		s=s+a[i];
	return s; 
}    
	
/* recursivo Sustraccion (a=1;b=1;k=0)=>O(n)  */
public static int sum2(int[]a) 
{
	return recSust(0,a);
}    
	
private static int recSust(int i, int[]a) 
{
	if (i==a.length) 
		return 0;
	else 
		return a[i] + recSust(i+1,a);
	} 
	

/* recursivo Division (a=2;b=2;k=0)=>O(n) */
public static int sum3(int[]a) {
	return recDiv(0,a.length-1,a);
}  
	
private static int recDiv(int iz,int de,int[]a) {
	if (iz==de)
		return a[iz];
	else 
	{ 
		int m= (iz+de)/2;
		return recDiv(iz,m,a)+ recDiv(m+1,de,a);
	}
}  

}  //  clase 
