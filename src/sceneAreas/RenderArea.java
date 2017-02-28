package sceneAreas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Player;
import main.Sprite;
import menus.MenuHandler;

public class RenderArea extends JPanel {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private MenuHandler menuHandler;
	private float scaleFactor;
	private int gameHeight;
	private int gameWidth;
	protected boolean run = true;
	private Player player;
	private ArrayList<Sprite> allSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> movingSprites = new ArrayList<Sprite>();
	private Sprite[] platforms = new Sprite[5];

	public RenderArea(JFrame frame, int actualWidth, int actualHeight,
			int simulatedHeight, MenuHandler menuHandler) {
		this.frame = frame;
		this.gameHeight = simulatedHeight;
		scaleFactor = (float) actualHeight / gameHeight;
		this.gameWidth = Math.round(actualWidth / scaleFactor);
		this.menuHandler = menuHandler;
		this.setPreferredSize(new Dimension(actualWidth, actualHeight));

		player = new Player(menuHandler.getSprite(300).getImage(), 300);
		// allSprites.add(player.getPlayerSprite());
		// addSprite(player.getPlayerSprite());
		player.getPlayerSprite().setPosition(-getGameWidth() / 3,
				getGameHeight() / 4);
		getAndSetSprites();
		// Get focus for keyevents
		// setFocusable(true);
		// requestFocusInWindow();

		this.addKeyListener(new KeyListener() { // Hopp fungerar ej!
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_SPACE) {
					player.tryJump();
				}
				if (key == KeyEvent.VK_ESCAPE) {
					// setFullscreen(false);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		// this.setBackground(Color.BLUE);
	}

	private void getAndSetSprites() {
		allSprites.add(player.getPlayerSprite());

		platforms[0] = new Sprite(menuHandler.getSprite(200).getImage(), 200);
		platforms[1] = new Sprite(menuHandler.getSprite(201).getImage(), 201);
		platforms[2] = new Sprite(menuHandler.getSprite(202).getImage(), 202);
		platforms[3] = new Sprite(menuHandler.getSprite(203).getImage(), 203);
		platforms[4] = new Sprite(menuHandler.getSprite(204).getImage(), 204);
	}

	protected void addPlatforms() {
		int spriteID, rightmostX = 0;
		
		//int distance = ThreadLocalRandom.current().nextInt(min, max + 1);
		
		for (Sprite sprite : movingSprites) {
			spriteID = sprite.getId();
			if (200 <= spriteID && spriteID <= 299) {
				rightmostX = sprite.getX()+sprite.getImage().getWidth();
			}
		}
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

	public void checkAlive() {
		if (player.getPlayerSprite().getY() < -getGameHeight() / 2) {
			player.setIsAlive(false);
		} else {
			player.setIsAlive(true);
		}
	}

	private void checkCollision() {
		int spriteID;

		Sprite mainchar = player.getPlayerSprite();
		
		player.setOnGround(false);

		if (movingSprites != null) {
			for (Sprite sprite : movingSprites) {
				
				player.setOnGround(false);

				Line2D.Float line = new Line2D.Float(mainchar.getX(),
						mainchar.getY(), mainchar.getX(), mainchar.getY() - 100);
				// if (rect1.intersects(line)) {
				// // linjen beskär rektangeln.
				// }

				if (player.getPlayerSprite().getCollisionbox()
						.intersects(sprite.getCollisionbox())) {
					spriteID = sprite.getId();
					if (200 <= spriteID && spriteID <= 299) {// PROBLEEEEEEEEEEEEEM
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
	}

	public void clear() {
		allSprites.clear();
		movingSprites.clear();
	}

	public int getGameHeight() {
		return gameHeight;
	}

	public int getGameWidth() {
		return gameWidth;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x, y, spriteWidth, spriteHeigth;

		for (Sprite sprite : allSprites) {
			if (sprite == null) {
				System.err.println("Sprite is null!");
				continue;
			}

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
		
		// TODO
		Sprite mainchar = player.getPlayerSprite();
		x = mainchar.getX();
		y = mainchar.getY();
		// Change coordinate system
		x = x + gameWidth / 2;
		y = -y + gameHeight / 2;
		g.drawLine(mainchar.getX(), mainchar.getY(), mainchar.getX(),
				mainchar.getY() - 100);
	}

	public void rescale() {
		scaleFactor = (float) frame.getContentPane().getBounds().height
				/ gameHeight;
		gameWidth = Math.round(frame.getContentPane().getBounds().width
				/ scaleFactor);
		repaint();
	}

	private long sleepTime(int x) {
		return (20 - (long) (0.001 * x + 1));
	}

	public void startLoop() {
		run = true;
		setFocusable(true);
		requestFocusInWindow();
		// final int fps = 60;
		// final long optimalTime = 1000 / fps;
		rescale();
		
		// Run loop in new thread so it doesn't block everything
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int x = 0;
				addSprite(platforms[4], getGameWidth() / 4, -getGameHeight() / 2 + 16);

				while (player.getIsAlive()) {
					addPlatforms();

					checkAlive();
					try {
						Thread.sleep(sleepTime(x));

					} catch (Exception e) {
						e.printStackTrace();
					}
					player.doJump();
					updateX();
					checkCollision();
					repaint();
					if (x < 18000) { // Förhindrar negativ sleep. Max 19000?
						x++;
					}
				}
			}

		});
		thread.start();
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
}
