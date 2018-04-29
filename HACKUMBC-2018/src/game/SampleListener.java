package game;

import com.leapmotion.leap.*;

public class SampleListener extends Listener {
	public static int numFingers;
	public static int numHands;

	public void onConnect(Controller controller) {
		System.out.println("Connected");
	}


	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
		FingerList extendedFingerList = frame.fingers().extended();

		Hand hand = new Hand();
		numFingers = extendedFingerList.count();
		numHands = frame.hands().count();        
	}

}