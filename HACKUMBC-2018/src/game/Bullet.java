package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game.SampleListener;
public class Bullet extends Shape{

	//for shoot
	boolean shoot = false;//, shoot2 = false;

	//attributes of all bullets
	int stepSize = 7;
	int radius = 3;
	int i = 0;

	static Point p1 = new Point(1, 0);
	static Point p2 = new Point(0, 1);
	static Point p3 = new Point(-1, 0);
	static Point p4 = new Point(0, -1);
	static double inRotation = 0;

	static Point[] inShape = {p1, p2, p3, p4};

	public void activate() {
		if(SampleListener.numFingers == 3) {
			shoot = true;
		} else shoot = false;
	}
	public void activate2() {
		shoot = true;
	}
	Bullet(Point inPosition){
		super(inShape, inPosition, inRotation);
	}

	/**
	 * Draws bullet
	 * <p>
	 * This makes the bullet visible
	 * The bullets are meant to appear as boogers
	 * </p>
	 * @param brush
	 */
	public void paint(Graphics brush){
		//declarations
		Point center = this.position;

		//paint portion
		//gets coordinates
		int x = (int) center.getX();
		int y = (int) center.getY();
		//gets radius
		int width = (int) this.radius;
		int height = (int) this.radius;
		//draws circle
		brush.setColor(Color.green);
		brush.drawOval(x, y, width, height);
	}		

	/**
	 * Moves bullet
	 * <p> 
	 * This method uses the instance variables in Polygon to move the bullet.
	 * The bullet only moves forward
	 * </p>
	 */
	public void move(){
		//declarations
		Point center = this.position;
		double rotation = this.rotation;
		//if shooting
		if (shoot){
			if (i < 300){
				//if bullet goes left or down
				if (center.getX() < 800 && center.getY() < 600 && center.getX() > 0 && center.getY() > 0){
					center.setX(center.getX() + stepSize * Math.cos(Math.toRadians(rotation)));
					center.setY(center.getY() + stepSize * Math.sin(Math.toRadians(rotation)));	
				}
				else{
					//ends shooting
					shoot = false;
					i = 0;
				}
			}
			else{
				//ends shooting
				shoot = false;
				i = 0;
			}
		}
	}
	public void move2(){
		//declarations
		Point center = this.position;
		double rotation = this.rotation;
		//if shooting
		if (shoot){
			if (i < 300){
				//if bullet goes left or down
				if (center.getX() < 800 && center.getY() < 600 && center.getX() > 0 && center.getY() > 0){

					center.setY(center.getY() + 1);	
				}
				else{
					//ends shooting
					shoot = false;
					i = 0;
				}
			}
			else{
				//ends shooting
				shoot = false;
				i = 0;
			}
		}
	}
}