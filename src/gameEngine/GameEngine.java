package gameEngine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.MusicHandler;
import main.Player;
import main.Sprite;
import menus.MenuHandler;

public class GameEngine extends JPanel {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private MenuHandler menuHandler;
	private MusicHandler musicHandler;
	private float scaleFactor;
	private int gameHeight;
	private int gameWidth;
	protected boolean run = true;
	private Player player;
	private ArrayList<Sprite> allSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> movingSprites = new ArrayList<Sprite>();
	private Sprite backgroundImage;
	private Sprite[][] platforms = new Sprite[3][5];

	private boolean genNew;
	private int nextPlatform = 0;

	public GameEngine(JFrame frame, int actualWidth, int actualHeight,
			int simulatedHeight, MenuHandler menuHandler,
			MusicHandler musicHandler) {
		this.frame = frame;
		this.gameHeight = simulatedHeight;
		scaleFactor = (float) actualHeight / gameHeight;
		this.gameWidth = Math.round(actualWidth / scaleFactor);
		this.menuHandler = menuHandler;
		this.musicHandler = musicHandler;
		this.setPreferredSize(new Dimension(actualWidth, actualHeight));

		player = new Player(menuHandler.getSprite(300).getImage(), 300,
				musicHandler);
		// player.getPlayerSprite().setPosition(-getGameWidth() / 3,
		// getGameHeight() / 4);
		reset();
		getAndSetSprites();

		this.addKeyListener(new KeyListener() {
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

		// Add window resize listener
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				rescale();
			}
		});
	}

	public void reset() {
		player.getPlayerSprite().setPosition(-getGameWidth() / 3,
				getGameHeight() / 4);
	}

	private void getAndSetSprites() {
		allSprites.add(player.getPlayerSprite());
		for (int x = 0; x < 8; x++) {
			player.addRunningSprites(menuHandler.getSprite(300 + x).getImage(),
					x);
			System.out.println("Added: x = " + x);
		}
		player.addJumpingSprites(menuHandler.getSprite(308).getImage(),
				menuHandler.getSprite(309).getImage());
		for (int x = 0; x < platforms.length; x++) {
			platforms[x][0] = new Sprite(menuHandler.getSprite(200 + 10 * x)
					.getImage(), 200 + 10 * x);
			platforms[x][1] = new Sprite(menuHandler.getSprite(201 + 10 * x)
					.getImage(), 201 + 10 * x);
			platforms[x][2] = new Sprite(menuHandler.getSprite(202 + 10 * x)
					.getImage(), 202 + 10 * x);
			platforms[x][3] = new Sprite(menuHandler.getSprite(203 + 10 * x)
					.getImage(), 203 + 10 * x);
			platforms[x][4] = new Sprite(menuHandler.getSprite(204 + 10 * x)
					.getImage(), 204 + 10 * x);
		}
		backgroundImage = new Sprite(menuHandler.getSprite(400).getImage(), 400);
		// allSprites.add(backgroundImage);

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
		int x = mainchar.getX();
		int y = mainchar.getY();
		// Change coordinate system
		x = x + gameWidth / 2;
		y = -y + gameHeight / 2;

		player.setOnGround(false);

		if (movingSprites != null) {
			for (Sprite sprite : movingSprites) {

				player.setOnGround(false);

				Line2D.Float line = new Line2D.Float(
						Math.round(x * scaleFactor),
						Math.round(y * scaleFactor),
						Math.round(x * scaleFactor), Math.round(y * scaleFactor
								+ gameHeight * scaleFactor));

				// if (rect1.intersects(line)) {
				// // linjen beskär rektangeln.
				// }

				if (player.getPlayerSprite().getCollisionbox()
						.intersects(sprite.getCollisionbox())) {
					spriteID = sprite.getId();
					if (200 <= spriteID && spriteID <= 299) {// PROBLEEEEEEEEEEEEEM
						player.setOnGround(true);
						player.resetJumpsOnGround();
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
		int x, y, spriteWidth, spriteHeight;

		drawBackground(g);

		for (Sprite sprite : allSprites) {
			if (sprite == null) {
				System.err.println("Sprite is null!");
				continue;
			}

			// Get sprite dimensions and location
			spriteWidth = sprite.getWidth();
			spriteHeight = sprite.getHeight();
			x = sprite.getX();
			y = sprite.getY();
			// Change coordinate system
			x = x + gameWidth / 2;
			y = -y + gameHeight / 2;

			// Draw sprite
			g.drawImage(sprite.getImage(), Math.round(x * scaleFactor
					- spriteWidth * scaleFactor / 2), Math.round(y
					* scaleFactor - spriteHeight * scaleFactor / 2), Math
					.round(spriteWidth * scaleFactor), Math.round(spriteHeight
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

		Sprite mainchar = player.getPlayerSprite();
		x = mainchar.getX();
		y = mainchar.getY();
		// Change coordinate system
		x = x + gameWidth / 2;
		y = -y + gameHeight / 2;
		g.drawLine(Math.round(x * scaleFactor), Math.round(y * scaleFactor),
				Math.round(x * scaleFactor),
				Math.round(y * scaleFactor + gameHeight * scaleFactor));
	}

	private void drawBackground(Graphics g) {
		// Draw sprite
		g.drawImage(
				backgroundImage.getImage(),
				Math.round((gameWidth / 2) * scaleFactor
						- (backgroundImage.getWidth() * scaleFactor / 2)),
				Math.round((gameHeight / 2) * scaleFactor
						- (backgroundImage.getHeight() * scaleFactor / 2)),
				Math.round(backgroundImage.getWidth() * scaleFactor),
				Math.round(backgroundImage.getHeight() * scaleFactor), null);
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

	private int randomizePlatformColor() {
		return ThreadLocalRandom.current().nextInt(0, platforms.length);
	}

	private int randomizePlatformLength() {
		return ThreadLocalRandom.current().nextInt(0, platforms[0].length);
	}

	private int randomizePlatformY() {
		return ThreadLocalRandom.current().nextInt(-2 * getGameHeight() / 5,
				2 * getGameHeight() / 5);
	}

	private void addBasePlatform() {
		try {
			int x = randomizePlatformColor();
			addSprite((Sprite) platforms[x][4].clone(), 0,
					-getGameHeight() / 2 + 32);

			x = randomizePlatformColor();
			addSprite((Sprite) platforms[x][4].clone(), getRightmostX() + 32,
					-getGameHeight() / 2 + 32);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getRightmostX() {
		int spriteID, rightmostX = 0, tempX = 0;
		for (Sprite sprite : movingSprites) {
			spriteID = sprite.getId();
			if (200 <= spriteID && spriteID <= 299) {
				tempX = sprite.getX() + sprite.getImage().getWidth();
				if (tempX > rightmostX) {
					rightmostX = tempX;
				}
			}
		}
		return rightmostX;
	}

	private void addPlatforms() {
		if (genNew) {
			nextPlatform = ThreadLocalRandom.current().nextInt(
					getGameWidth() / 8, 2 * getGameWidth() / 7);
			System.out.println(nextPlatform);
			genNew = false;
		}
		if ((getGameWidth() - getRightmostX()) > nextPlatform) {
			try {
				addSprite(
						(Sprite) platforms[randomizePlatformColor()][randomizePlatformLength()]
								.clone(), getGameWidth(), randomizePlatformY());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			genNew = true;
		}

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
				addBasePlatform();

				setFocusable(true);
				while (player.getIsAlive()) {
					rescale();
					requestFocusInWindow();

					addPlatforms();
					player.updateSprites();
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
					if (x <= 18000) { // Förhindrar negativ sleep. Max 19000?
						x++;
					}
				}
				if (!player.getIsAlive()) {
					musicHandler.stopAll();
					musicHandler.playClipFX("GameOver");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					musicHandler.playSongClip("ChibiNinja");
					menuHandler.onPressShow("deathMenu", false);
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
