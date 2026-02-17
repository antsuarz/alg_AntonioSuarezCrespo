package p12;

public class Bucle7 
{
public static long bucle7(long n)
{
	long cont = 0;

        for (long i = 1; i <= n; i++)
            for (long j = 1; j <= n; j++)
                for (long k = 1; k <= n; k++)
                    for (long m = 1; m <= n; m++)
                        cont++;

        return cont;
}

public static void main(String arg[]) 
{
	long t1, t2;
	int nVeces = Integer.parseInt(arg[0]);

	System.out.println("n\ttiempo\trepeticiones\tcontador");

	for (long n = 100; n <= 819200; n *= 2) 
            {
		long c = 0;
		t1 = System.currentTimeMillis();

		for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) 
			c = bucle7(n);

		t2 = System.currentTimeMillis();
		System.out.println(n+"\t"+(t2-t1)+"\t"+nVeces+"\t\t"+c);
		
            } // for
} // main
} // class