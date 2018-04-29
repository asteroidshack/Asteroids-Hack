package game;

import java.awt.Graphics;

public class AlienShip extends Shape{
	/**
	 * Constructor
	 * <p>
	 * Constructs a ship using superclass's constructor
	 * </p>
	 * @param inShape is an array of points
	 * @param inPosition is a point
	 * @param inRotation is a double
	 */
	public AlienShip(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}

	/**
	 * Makes alien ship visible
	 * <p>
	 * This method makes the ship visible
	 * It fills in the ship with a color and displays it on the screen
	 * </p>
	 * @param brush is a brush :)
	 */
	public void paint(Graphics brush){
		//declarations of points
		Point[] points = this.getPoints();
		int n = points.length;
		int[] xPoints = new int[n];
		int[] yPoints = new int[n];

		//creates x and y points
		for (int i = 0; i < n; i ++){
			xPoints[i] = (int) points[i].getX();
			yPoints[i] = (int) points[i].getY();			
		}
		//fills in the ship
		brush.fillPolygon(xPoints, yPoints, n);
	}
	int stepSize = 1;
	public void move(){
		Point center = this.position;
		center.setX((center.getX() + 1));
		//if ship goes left or down
		//if ship goes off the right
		if (center.getX() > 800){
			center.setX(0);
		}	
	}


}
