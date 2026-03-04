package p3.p3p;

public class Punto
{
	private double coordX;
	private double coordY;

	public Punto(double coordX, double coordY){
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public double calculaDistancia(Punto p2){
		double distX = p2.getCoordX() - this.getCoordX();
		double distY = p2.getCoordY() - this.getCoordY();
		double distancia = Math.sqrt((distX*distX) + (distY*distY));
		return distancia;
	}

	public double getCoordX(){
		return coordX;
	}

	public double getCoordY(){
		return coordY;
	}

	@Override
	public String toString() {
    return "[" + coordX + ", " + coordY + "]";
	}
} 