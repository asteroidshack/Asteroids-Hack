package game;

import java.awt.Graphics;
import java.util.Random;


public class Asteroid extends Shape {

	//step size of asteroid
	int stepSize = 1;
	int collide = 0;	

	//constructor from Polygon superclass
	public Asteroid(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}


	/**
	 * Makes asteroid visible
	 * <p>
	 * Uses object's parameter to get required x, y point arrays
	 * Draws asteroid using those arrays
	 * </p>
	 * @param brush is a Graphics brush
	 */
	public void paint(Graphics brush){
		//declarations of points
		Point[] points = this.getPoints();
		int n = points.length;
		int[] xPoints = new int[n];
		int[] yPoints = new int[n];

		//creates x and y points
		for (int i = 0; i < n; i ++){
			xPoints[i] = (int) (points[i].getX());
			yPoints[i] = (int) (points[i].getY());			
		}
		//draws the asteroid
		brush.drawPolygon(xPoints, yPoints, n);

		//if it collides with a bullet
		if (collide == 2){
			System.out.println("Collide");
			for (int i = 0; i < n; i++){
				points[i].setX(points[i].getX() / 2);
				points[i].setY(points[i].getY() / 2);
			}
		}
	}

	public void paint2(Graphics brush){
		//declarations of points
		Point[] points = this.getPoints();
		int n = points.length;
		int[] xPoints = new int[n];
		int[] yPoints = new int[n];

		//creates x and y points
		for (int i = 0; i < n; i ++){
			xPoints[i] = (int) (points[i].getX());
			yPoints[i] = (int) (points[i].getY());			
		}
		//draws the asteroid
		brush.fillPolygon(xPoints, yPoints, n);

		//if it collides with a bullet
		if (collide == 2){
			System.out.println("Collide");
			for (int i = 0; i < n; i++){
				points[i].setX(points[i].getX() / 2);
				points[i].setY(points[i].getY() / 2);
			}
		}
	}

	//moves asteroid 
	public void move(){
		//moves asteroid forward
		Point center = this.position;
		double rotation = this.rotation;
		//if it goes off top or left
		if (center.getX() < 0 || center.getY() < 0){
			//only left
			if (center.getX() < 0 && center.getY() > 0){
				center.setX(800 - center.getX() + stepSize * Math.cos(Math.toRadians(rotation)) % 800);
			}	
			//only top
			else if (center.getX() > 0 && center.getY() < 0){
				center.setY(600 - center.getY() + stepSize * Math.sin(Math.toRadians(rotation)) % 600);	
			} 
			//both
			else{
				center.setX(800 - (center.getX()) + stepSize * Math.cos(Math.toRadians(rotation)) % 800);
				center.setY(600 - (center.getY()) + stepSize * Math.sin(Math.toRadians(rotation)) % 600);	
			}
		}
		//going off bottom or right
		else{
			center.setX((center.getX() + stepSize * Math.cos(Math.toRadians(rotation))) % 800);
			center.setY((center.getY() + stepSize * Math.sin(Math.toRadians(rotation))) % 600);	
		}
	}

}
