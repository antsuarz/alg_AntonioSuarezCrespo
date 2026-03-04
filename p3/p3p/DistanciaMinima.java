package p3.p3p;

public class DistanciaMinima
{
	Punto p1;
    Punto p2;
    double dist;

    public DistanciaMinima(Punto a, Punto b, double d) {
    	p1 = a;
    	p2 = b;
	    dist = d;
    }

    public double getDist(){
        return dist;
    }

    public Punto getP1(){
        return p1;
    }

    public Punto getP2(){
        return p2;
    }
} 