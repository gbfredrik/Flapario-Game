package sceneAreas;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import main.Sprite;

public class Scene1 extends RenderArea {
	private static final long serialVersionUID = 1L;

	private Sprite mainCharacter;
	private boolean jumpPressed = false;
	
	private boolean onGround = true;

	public Scene1(JFrame frame, int actualWidth, int actualHeight, int simulatedHeight) {
		super(frame, actualWidth, actualHeight, simulatedHeight);

		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_SPACE) {
					jumpPressed = true;
				}
				if (key == KeyEvent.VK_ESCAPE) {
					//setFullscreen(false);
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

		mainCharacter = new Sprite("./src/assets/png/mainchar/mainchar1.png", 5);
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
		
		Sprite box = new Sprite("./src/assets/png/Untitled.png");
		addSprite(box);
		box.setPosition(0, -getGameHeight() / 2 + 16);
	}
	
	private void checkCollision() {
		for (Sprite sprite : sprites) {
			if (mainCharacter.getCollisionbox().intersects(sprite.getCollisionbox())) { 
				if(sprite.getId() != mainCharacter.getId()) {
					onGround = true;
					break;
				}
			} else {
				onGround = false;
			}
		}
		
	}

	@Override
	public void start() {

		rescale();
		// Get focus for keyevents
		setFocusable(true);
		requestFocusInWindow();
		startLoop();
	}

	@Override
	protected void run() {

		// System.out.println("running");

		int platformLevel = -50;
		if (jumpPressed && onGround) {
			mainCharacter.setY(mainCharacter.getY() + 20);
		} else if (!onGround) {
			mainCharacter.setY(mainCharacter.getY() - 1);
		}

		checkCollision();
		repaint();

	}
}
