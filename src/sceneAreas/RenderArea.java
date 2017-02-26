package sceneAreas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Player;
import main.Sprite;
import menus.MenuHandler;

public class RenderArea extends JPanel {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private MenuHandler menuHandler;
	// private Scene1 scene1;
	private float scaleFactor;
	private int gameHeight;
	private int gameWidth;
	protected boolean run = true;
	private Player player;
	private ArrayList<Sprite> allSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> movingSprites = new ArrayList<Sprite>();
	private Sprite platformTwo, platformThree, platformFour, platformFive;
	
	public RenderArea(JFrame frame, int actualWidth, int actualHeight,
			int simulatedHeight, MenuHandler menuHandler) {
		this.frame = frame;
		this.gameHeight = simulatedHeight;
		scaleFactor = (float) actualHeight / gameHeight;
		this.gameWidth = Math.round(actualWidth / scaleFactor);
		this.menuHandler = menuHandler;
		this.setPreferredSize(new Dimension(actualWidth, actualHeight));
		
		System.out.println("1");
		player = new Player();
		System.out.println("2");
		allSprites.add(player.getPlayerSprite());
		System.out.println("3");
		
		getAndSetSprites();
		rescale();
		// Get focus for keyevents
		setFocusable(true);
		requestFocusInWindow();
		startLoop();
	}

	private void getAndSetSprites() {
		player.setPlayerSprite(menuHandler.getSprite(400));
	}

	public int getGameHeight() {
		return gameHeight;
	}

	public int getGameWidth() {
		return gameWidth;
	}

	public void rescale() {
		scaleFactor = (float) frame.getContentPane().getBounds().height
				/ gameHeight;
		gameWidth = Math.round(frame.getContentPane().getBounds().width
				/ scaleFactor);
		repaint();
	}

	public void addSprite(Sprite sprite) {
		allSprites.add(sprite);
		movingSprites.add(sprite);
		repaint();
	}

	public void addSprite(Sprite sprite, int x, int y) {
		sprite.setX(x);
		sprite.setY(y);
		allSprites.add(sprite);
		movingSprites.add(sprite);
		repaint();
	}

	public void clear() {
		allSprites.clear();
		movingSprites.clear();
		// liveScreenSprites = new ArrayList<Sprite>(); // Varför så? dafuq
	}

	// public abstract void start();

	public void stop() {
		run = false;
		clear();
	}

	public void startLoop() {
		run = true;

		// final int fps = 60;
		// final long optimalTime = 1000 / fps;
		addPlatforms();
		// Run loop in new thread so it doesn't block everything
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int x = 0;
				// addPlatforms();
				while (player.getIsAlive()) {
					try {
						Thread.sleep(sleepTime(x));
						// ^^^ Bör förmodligen göras om för att optimera fps
						// till 60.

					} catch (Exception e) {
						e.printStackTrace();
					}
					player.doJump();
					updateX();
					checkCollision();
					repaint();
					if (x < 18999) { // Förhindrar
						x++;
					}
				}
			}

		});
		thread.start();
	}

	protected void addPlatforms() {
		Sprite box = new Sprite("./src/assets/png/Untitled.png");
		addSprite(box);
		box.setPosition(-getGameWidth() / 4, -getGameHeight() / 2 + 16);
	}

	private void checkCollision() {
		int spriteID;
		for (Sprite sprite : movingSprites) {
			if (player.getPlayerSprite().getCollisionbox()
					.intersects(sprite.getCollisionbox())) {
				spriteID = sprite.getId();
				if (400 <= spriteID && spriteID <= 499) {// PROBLEEEEEEEEEEEEEM
					player.setOnGround(true);
					player.resetJumpsOnGround();

					System.out.println("TRIGGERED");
					break;
				}
			} else {
				player.setOnGround(false);
			}
		}
	}

	public void checkAlive() {
		if (player.getPlayerSprite().getY() < -getGameHeight() / 2) {
			player.setIsAlive(false);
		} else {
			player.setIsAlive(true);
		}
	}

	private void updateX() {
		Sprite removeSprite = null;
		for (Sprite sp : movingSprites) {
			sp.setX(sp.getX() - 1);
			if ((sp.getX() + sp.getWidth()) <= -getGameWidth() / 2) {
				removeSprite = sp;
			}
		}
		if (removeSprite != null) {
			movingSprites.remove(removeSprite);
			System.out.println("Removed platform @left. @id"
					+ removeSprite.getId());
		}
	}

	private long sleepTime(int x) {
		return (20 - (long) (0.001 * x + 1));
	}

	private void runrun() { // Ugly
		// run();
	}

	protected void stopLoop() {
		run = false;
	}

	// protected abstract void run();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int x, y, spriteWidth, spriteHeigth;

		for (Sprite sprite : allSprites) {
			// Get sprite dimensions and location
			spriteWidth = sprite.getWidth();
			spriteHeigth = sprite.getHeight();
			x = sprite.getX();
			y = sprite.getY();
			// Change coordinate system
			x = x + gameWidth / 2;
			y = -y + gameHeight / 2;

			// Draw sprite
			g.drawImage(sprite.getImage(), Math.round(x * scaleFactor
					- spriteWidth * scaleFactor / 2), Math.round(y
					* scaleFactor - spriteHeigth * scaleFactor / 2), Math
					.round(spriteWidth * scaleFactor), Math.round(spriteHeigth
					* scaleFactor), null);

			g.setColor(Color.GREEN);

			// Draw collisionbox
			g.drawRect(
					Math.round((sprite.collisionbox.x + gameWidth / 2)
							* scaleFactor),
					Math.round((-sprite.collisionbox.y + gameHeight / 2)
							* scaleFactor),
					Math.round(sprite.collisionbox.width * scaleFactor),
					Math.round(sprite.collisionbox.height * scaleFactor));
		}
	}
}
