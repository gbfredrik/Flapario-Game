package sceneAreas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import main.Sprite;

public class Scene1 extends RenderArea {
	private static final long serialVersionUID = 1L;
	
	private Sprite mainCharacter;
	private boolean run = true;
	public boolean jumpPressed = false;

	public Scene1(JFrame frame, int actualWidth, int actualHeight, int simulatedHeight) {
		super(frame, actualWidth, actualHeight, simulatedHeight);

		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_SPACE) {
					jumpPressed = true;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_SPACE) {
					jumpPressed = false;
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	}

	public void start() {
		run = true;

		mainCharacter = new Sprite("./src/assets/png/mainchar/mainchar1.png");
		addSprite(mainCharacter);
		mainCharacter.setPosition(0, 0);

		Sprite mainCharacter2 = new Sprite("./src/assets/png/mainchar/mainchar1.png");
		addSprite(mainCharacter2);
		mainCharacter2.setPosition(-getGameWidth() / 2, getGameHeight() / 2);

		Sprite mainCharacter3 = new Sprite("./src/assets/png/mainchar/mainchar1.png");
		addSprite(mainCharacter3);
		mainCharacter3.setPosition(-getGameWidth() / 2, -getGameHeight() / 2);

		Sprite mainCharacter4 = new Sprite("./src/assets/png/mainchar/mainchar1.png");
		addSprite(mainCharacter4);
		mainCharacter4.setPosition(getGameWidth() / 2, getGameHeight() / 2);

		Sprite mainCharacter5 = new Sprite("./src/assets/png/mainchar/mainchar1.png");
		addSprite(mainCharacter5);
		mainCharacter5.setPosition(getGameWidth() / 2, -getGameHeight() / 2);

		run();
	}

	public void stopScene() {
		run = false;
		clear();
	}

	// TODO: B�ttre loop
	private void run() {

		final int fps = 60;
		final long optimalTime = 1000 / fps;

		//System.out.println(optimalTime);

		/*
		long oldTime = System.currentTimeMillis();
		long newTime = System.currentTimeMillis();
		long deltaTime;
		long delta = 1;
		*/

		while (run) {
			// System.out.println("running");
			int platformLevel = -50;
			if (jumpPressed) {
				mainCharacter.setY(mainCharacter.getY() + 5);
			} else if (mainCharacter.getY() > platformLevel) {
				mainCharacter.setY(mainCharacter.getY() - 2);
			}
			
			repaint();

			/*
			newTime = System.currentTimeMillis();
			deltaTime = newTime - oldTime;
			oldTime = newTime;
			
			delta = deltaTime/optimalTime;
			*/
			
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
