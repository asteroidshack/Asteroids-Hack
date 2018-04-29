package game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;  
import java.io.IOException;  
import java.util.Random;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Controller;
import game.Avatar;
import java.io.*;
import javazoom.jl.decoder.*;
import javazoom.jl.player.*;


class Processor extends Game {

	static int counter = 0;
	static boolean gameOver = false;
	//ships
	Point p1 = new Point (25,0);
	Point p2 = new Point (0,15);
	Point p3 = new Point (15,0);
	Point p4 = new Point (0,-15);
	Point center = new Point(200,200);

	Point[] shipPoints = {p1, p2, p3, p4};
	Avatar s = new Avatar(shipPoints, center, 0);

	//alien ship
	Point p100 = new Point (-15,0);
	Point p101 = new Point (-7,15);
	Point p102 = new Point (8,15);
	Point p103 = new Point (15,0);
	Point p104 = new Point (8,-15);
	Point p105 = new Point (-7,-15);
	Point center1 = new Point(100,100);
	Point[] alienShipPoints = {p100, p101, p102, p103, p104, p105};
	AlienShip as = new AlienShip (alienShipPoints, center1, 0);

	//asteroids
	Random gen = new Random();
	Point p5 = new Point(0, 0);
	Point p6 = new Point(60, 0);
	Point p7 = new Point(50, 30);
	Point p8 = new Point(60, 50);
	Point p9 = new Point(30, 60);
	Point p10 = new Point(20, 50);
	Point p11 = new Point(10, 60);
	Point p12 = new Point(-10, 50);
	Point[] aPoints = {p5, p6, p7, p8, p9, p10, p11, p12};
	Point[] aPoints2 = {p5, p7, p8, p11, p12};
	static Asteroid[] asteroids = new Asteroid[10];
	static Bullet[] bullets = new Bullet[10];

	Point p14 = new Point(0, 5);
	Point p15 = new Point(.5, .5);
	Point p16 = new Point(5, 0);
	Point p17 = new Point(.5, -.5);
	Point p18 = new Point(0, -5);
	Point p19 = new Point(-.5, -.5);
	Point p20 = new Point(-5, 0);
	Point p21 = new Point(-.5, .5);
	Point p22 = new Point(-1.5, 0);
	Point p23 = new Point(-10, 0);
	Point p24 = new Point(-1.5, 0);
	Point p25 = new Point(0, 1.5);
	Point[] sPoints = {p14, p15, p16, p17, p18,p19,p20,p21};
	static Asteroid[] asteroids1 = new Asteroid[20];
	//respawn
	Point p13 = new Point(100, 100);

	//creates 3 bullets
	Bullet b1 = new Bullet(s.position); 
	Bullet b2 = new Bullet(as.position);

	static //lives and score
	int lives = 1;
	int score = 0;
	/**
	 * Constructor for Processor
	 * <p>
	 * The constructor starts the setup of the game
	 * It also creates 10 asteroids
	 * </p>
	 */
	public Processor() {
		//game superclass
		super("Processor!",800,600);
		this.setFocusable(true);
		this.requestFocus();
		Random gen = new Random();

		//stars 
		for (int i = 0; i<20; i++) {
			//generates coordinate values
			int x = gen.nextInt(800);
			int y = gen.nextInt(600);

			while (x > 180 && x < 220)
				x = gen.nextInt(800);
			while (y > 180 && y < 220)
				y = gen.nextInt(800);

			//center
			Point p1 = new Point(x, y);
			//generates roation
			double r1 = gen.nextDouble() * 360;
			Asteroid s = new Asteroid(sPoints, p1, r1);
			asteroids1[i] = s;

		}
		//creates 10 asteroids
		for (int i = 0; i < 10; i++){
			//generates coordinate values
			int x = gen.nextInt(800);
			int y = gen.nextInt(600);

			while (x > 180 && x < 220)
				x = gen.nextInt(800);
			while (y > 180 && y < 220)
				y = gen.nextInt(800);

			//center
			Point p = new Point(x, y);
			//generates roation
			double r = gen.nextDouble() * 360;
			//instantiates asteroids and puts them into an array of asteroids
			int b = gen.nextInt(2);
			if (b == 0){
				Asteroid a = new Asteroid(aPoints, p, r);
				asteroids[i] = a;
			}
			else {
				Asteroid a = new Asteroid(aPoints2, p, r);
				asteroids[i] = a;
			}
		}
	}


	/**
	 * Paints everything
	 * <p>
	 * Main setup of displaying things
	 * </p>
	 */
	public void paint(Graphics brush)  {
		playMusic();
		System.out.println ("hands: " + SampleListener.numHands + "fingers: " + SampleListener.numFingers);

		//paints the screen
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);
		brush.setColor(Color.white);
		brush.drawString("Lives: " + lives, 10, 11);
		brush.drawString("Score: " + score, 10, 24); 

		//bullet
		if (b1.shoot)
			b1.i++;
		else{
			b1.rotation = s.rotation;
			b1.position = new Point(s.position.getX(), s.position.getY());
		}
		b1.activate();
		b1.move();
		b1.paint(brush);

		//bullet2
		if (b2.shoot)
			b2.i++;
		else{
			b2.rotation = as.rotation;
			b2.position = new Point(as.position.getX(), as.position.getY());
		}
		b2.activate2();
		b2.move2();
		b2.paint(brush);

		//checks for collisions
		for (int i = 0; i < 10; i++){
			if (asteroids[i] != null && (asteroids[i].collides(b1))){
				asteroids[i].collide++;
				score += 25;
			}
		}

		if (s.collides(b2)) {
			lives--;
		}
		//paints the ship
		brush.setColor(Color.blue);
		s.paint(brush);
		s.setDirection();
		s.setForward();
		s.move();

		brush.setColor(Color.green);
		as.paint(brush);;
		as.move();

		//paints the stars
		brush.setColor(Color.white);
		for (int i = 0; i < 20; i++){
			asteroids1[i].paint2(brush);
		}

		//paints the asteroids
		for (int i = 0; i < 10; i++){
			if (asteroids[i] != null && asteroids[i].collides(s) && Avatar.stepSize <= 5 && score != 0){
				lives--;
				s.position = p13;
			}
			if (asteroids[i] != null && asteroids[i].collide < 2){
				asteroids[i].paint(brush);
				asteroids[i].move();
			}
			else if (asteroids[i] != null && asteroids[i].collide > 2){
				asteroids[i] = null;
			}
		}	


		if (lives < 1){
			brush.setColor(Color.black);
			brush.fillRect(0, 0, width, height);
			brush.setColor(Color.red);
			Font font = new Font("Courier New",Font.BOLD,200);
			brush.setFont(font);
			brush.drawString("Game", 75, 240);
			brush.drawString("Over", 250, 410);

		}





		if (score == 750){
			brush.setColor(Color.black);
			brush.fillRect(0, 0, width, height);
			brush.setColor(Color.pink);
			Font font3 = new Font("Courier New",Font.BOLD,50);
			brush.setFont(font3);
			brush.drawString("Congratulations!", 150, 275);
			brush.drawString("You won!", 300, 375);

		}

	}
	public void playMusic() {

		if(score == 750) {
			try
			{
				File file = new File ("C:\\\\Users\\\\ryanh\\\\OneDrive\\\\Desktop\\\\02 DJ Khaled ft various All I Do Is Win (Remix).mp3");
				FileInputStream music = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(music);
				try {
					Player player = new Player (bis);
					player.play();
				}
				catch (JavaLayerException ex) {

				}
			}
			catch (IOException e)
			{
			}
		}
		if(lives < 1) {

			try
			{
				File file = new File ("C:\\\\Users\\\\ryanh\\\\OneDrive\\\\Desktop\\\\Sad Trombone Wah Wah Wah Fail Sound Effect.mp3");
				FileInputStream music = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(music);
				try {
					Player player = new Player (bis);
					player.play();
				}
				catch (JavaLayerException ex) {

				}
			}
			catch (IOException e)
			{
			}
		}
	}
	public static void playMusic(String filepath) {

		try
		{
			File file = new File (filepath);
			FileInputStream music = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(music);
			try {
				Player player = new Player (bis);
				player.play();

			}
			catch (JavaLayerException ex) {

			}
		}
		catch (IOException e)
		{
		}
	}

	/**
	 * Starts the actual program 
	 * @param args
	 */
	public static void main (String[] args) {
		//declarations
		Processor a = new Processor();
		a.repaint();



		// Create a sample listener and controller
		SampleListener listener = new SampleListener();
		Controller controller = new Controller();

		// Have the sample listener receive events from the controller
		controller.addListener(listener);


		// Keep this process running until Enter is pressed
		System.out.println("Press Enter to quit...");
		playMusic("C:\\\\Users\\\\ryanh\\\\OneDrive\\\\Desktop\\\\Doctor P - Tetris.mp3");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Remove the sample listener when done
		controller.removeListener(listener);


	}
}
