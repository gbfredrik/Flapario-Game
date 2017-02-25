package sceneAreas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import main.GameWindow;
import main.Sprite;

public class Scene1 extends RenderArea {
	private static final long serialVersionUID = 1L;

	private Sprite playerCharacter;
	private boolean jumpPressed = false;
	private boolean doubleJump = false;
	private boolean onGround = true;
	private final int jumpMaxHeight = 35;
	private int jumpHeightRemaining;
	private int heightFallen;

	private ArrayList<Sprite> livePlatforms = new ArrayList<Sprite>();

	public Scene1(JFrame frame, int actualWidth, int actualHeight,
			int simulatedHeight) {
		super(frame, actualWidth, actualHeight, simulatedHeight);

		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_SPACE) {
					System.out.println("\n\nFöre: jumpPressed value: "
							+ jumpPressed);
					System.out.println("Före: doubleJump value: " + doubleJump);

					if (!jumpPressed && !doubleJump) {
						System.out.println("Jump pressed!");
						jumpPressed = true;
						jumpHeightRemaining += jumpMaxHeight;
					}
					if (jumpPressed && !doubleJump
							&& jumpHeightRemaining < (jumpMaxHeight - 25)) {
						jumpHeightRemaining += jumpMaxHeight;
						System.out.println("Double jump!");
						doubleJump = true;
					}

					System.out.println("\nEfter: jumpPressed value: "
							+ jumpPressed);
					System.out
							.println("Efter: doubleJump value: " + doubleJump);
				}
				if (key == KeyEvent.VK_ESCAPE) {
					// setFullscreen(false);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				/*
				 * int key = e.getKeyCode(); if (key == KeyEvent.VK_SPACE) {
				 * jumpPressed = false; }
				 */
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

		playerCharacter = new Sprite("./src/assets/png/mainchar/mainchar1.png",
				5);
		addSprite(playerCharacter);
		playerCharacter.setPosition(-getGameWidth() / 5, 0);

		// Sprite mainCharacter2 = new Sprite(
		// "./src/assets/png/mainchar/mainchar1.png");
		// addSprite(mainCharacter2);
		// mainCharacter2.setPosition(-getGameWidth() / 2, getGameHeight() / 2);
		//
		// Sprite mainCharacter3 = new Sprite(
		// "./src/assets/png/mainchar/mainchar1.png");
		// addSprite(mainCharacter3);
		// mainCharacter3.setPosition(-getGameWidth() / 2, -getGameHeight() /
		// 2);
		//
		// Sprite mainCharacter4 = new Sprite(
		// "./src/assets/png/mainchar/mainchar1.png");
		// addSprite(mainCharacter4);
		// mainCharacter4.setPosition(getGameWidth() / 2, getGameHeight() / 2);
		//
		// Sprite mainCharacter5 = new Sprite(
		// "./src/assets/png/mainchar/mainchar1.png");
		// addSprite(mainCharacter5);
		// mainCharacter5.setPosition(getGameWidth() / 2, -getGameHeight() / 2);

		Sprite box = new Sprite("./src/assets/png/Untitled.png");
		addSprite(box);
		box.setPosition(-getGameWidth() / 4, -getGameHeight() / 2 + 16);
		// platform1 = new Sprite();
	}

	private void checkCollision() {
		for (Sprite sprite : livePlatforms) {
			if (playerCharacter.getCollisionbox().intersects(
					sprite.getCollisionbox())) {
				if (sprite.getId() != playerCharacter.getId()) {// PROBLEEEEEEEEEEEEEM
					onGround = true;
					// doubleJump = false;
					// jumpPressed = false;
					// System.out.println("TRIGGERED");
					break;
				}
			} else {
				onGround = false;
			}
		}
		if (onGround) {
			doubleJump = false;
			jumpPressed = false;
			System.out.println("VARFÖR I HELVETE TRIGGAS DETTA");
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
		// if (jumpPressed && onGround) {
		// mainCharacter.setY(mainCharacter.getY() + 20);
		// } else
		if (jumpHeightRemaining > 0) {
			playerCharacter.setY(playerCharacter.getY() + jumpSpeed());
			if (jumpHeightRemaining == 0) {
				heightFallen = 0;
			}
		} else if (!onGround) {
			playerCharacter.setY(playerCharacter.getY() - fallSpeed());
		}
		updateX();
		checkCollision();
		repaint();
	}

	private int jumpSpeed() {
		int factor;
		factor = (int) Math.round((1.0 + (double) (jumpHeightRemaining / 10)));
		jumpHeightRemaining -= factor;
		return factor;
	}

	private int fallSpeed() {
		int factor;
		factor = (int) Math.round((1.0 + (double) (heightFallen / 10)));
		heightFallen += factor;
		return factor;
	}

	private void updateX() {
		Sprite removeSprite = null;
		for (Sprite sp : livePlatforms) {
			sp.setX(sp.getX() - 1);
			if ((sp.getX() + sp.getWidth()) <= -getGameWidth() / 2) {
				removeSprite = sp;
			}
		}
		if (removeSprite != null) {
			livePlatforms.remove(removeSprite);
			System.out.println("Removed platform @left");
		}
	}

	public boolean getAlive() {
		return (playerCharacter.getY() < -getGameHeight() / 2);
	}
}
