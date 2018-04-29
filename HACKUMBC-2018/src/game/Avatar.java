package game;

import java.awt.Graphics;
import java.io.IOException;
import java.lang.Math;
import game.SampleListener;

public class Avatar extends Shape{

	//movement
	boolean left;
	boolean right;
	boolean forward;
	boolean isBoosted;
	static double stepSize = 0;

	/**
	 * Constructor
	 * <p>
	 * Constructs a ship using superclass's constructor
	 * </p>
	 * @param inShape is an array of points
	 * @param inPosition is a point
	 * @param inRotation is a double
	 */
	public Avatar(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}

	/**
	 * Makes ship visible
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
	public void setDirection() {
		if(SampleListener.numFingers == 1) {
			right = true;
			left = false;
		}
		else if(SampleListener.numFingers == 2) {
			left = true;	
			right = false;
		} else if(SampleListener.numFingers == 4 && stepSize <=4){
			isBoosted = true;
		}
		else {
			isBoosted = false;
			left = false;
			right = false;
		}
	}
	public void setForward() {
		if(SampleListener.numHands==2) {
			forward = true;
		} else forward = false;
	}

	/**
	 * Moves ship
	 * <p> 
	 * This method uses the instance variables in Polygon to move the ship.
	 * The ship moves forward when the UP arrow key is hit
	 * The ship turns left and right when the LEFT and RIGHT arrow keys are hit, respectively
	 * </p>
	 */
	public void move(){
		//gravity
		if(isBoosted) {
			stepSize = 10;
		}
		if(forward == true && stepSize < 1) {
			stepSize += .05;
		} else if(forward == true && stepSize < 2)
			stepSize += .1;
		else if(forward == true && stepSize < 4)
			stepSize += .2;
		else if(forward == false && stepSize > 5)
			stepSize -= .1;
		else if(forward == false && stepSize > 2)
			stepSize -= .01;
		else if(forward == false && stepSize > 0)
			stepSize -= .05;

		//moves ship forward

		Point center = this.position;
		double rotation = this.rotation;
		//if ship goes left or down
		if (center.getX() < 0 || center.getY() < 0){
			//if ship goes off the left
			if (center.getX() < 0 && center.getY() < 600){
				center.setX(800 - center.getX() + stepSize * Math.cos(Math.toRadians(rotation)) % 800);
			}	
			//if the ship goes downwards
			if (center.getX() > 0 && center.getY() < 0){
				center.setY(600 - center.getY() + stepSize * Math.sin(Math.toRadians(rotation)) % 600);	
			} 
		}
		//if ship goes up or right

		center.setX((center.getX() + stepSize * Math.cos(Math.toRadians(rotation))) % 800);
		center.setY((center.getY() + stepSize * Math.sin(Math.toRadians(rotation))) % 600);	

		//turns ship left
		if (left){
			this.rotate(-2);
		}
		//turns ship right
		if (right){
			this.rotate(2);
		}
	}
}