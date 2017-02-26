/*package sceneAreas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import main.Player;
//import main.GameWindow;
import main.Sprite;

public class Scene1 extends RenderArea {
	private static final long serialVersionUID = 1L;

	private boolean onGround = true;
	private int jumpHeightRemaining;

	private ArrayList<Sprite> livePlatforms = new ArrayList<Sprite>();

	public Scene1(JFrame frame, int actualWidth, int actualHeight,
			int simulatedHeight) {
		//super(frame, actualWidth, actualHeight, simulatedHeight);

		//playerCharacter = new Sprite("./src/assets/png/mainchar/mainchar1.png",5);
		//addSprite(playerCharacter);
		// playerCharacter.setPosition(-getGameWidth() / 5, 0);

		Sprite box = new Sprite("./src/assets/png/Untitled.png");
		addSprite(box);
		box.setPosition(-getGameWidth() / 4, -getGameHeight() / 2 + 16);
	}

	

//	@Override
//	public void start() {
//		rescale();
//		// Get focus for keyevents
//		setFocusable(true);
//		requestFocusInWindow();
//		startLoop();
//	}
}*/