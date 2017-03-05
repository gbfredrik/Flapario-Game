package gameRendering;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Highscore;
import main.MusicHandler;
import menu.MenuHandler;

public class RenderArea extends JPanel {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private MenuHandler menuHandler;
	private MusicHandler musicHandler;
	private Font font;
	private float scaleFactor;
	private int gameHeight;
	private int gameWidth;
	protected boolean run = true;
	private Player player;
	private ArrayList<Sprite> allSprites;
	private ArrayList<Sprite> movingSprites;
	private Sprite backgroundImage;
	private Sprite[][] platforms;

	private ArrayList<Coin> coins;

	private boolean genNew;
	private int nextPlatform = 0;

	public RenderArea(JFrame frame, int actualWidth, int actualHeight, int simulatedHeight, MenuHandler menuHandler,
			MusicHandler musicHandler, Font font) {

		// Initialize variables
		platforms = new Sprite[3][5];
		allSprites = new ArrayList<Sprite>();
		movingSprites = new ArrayList<Sprite>();
		coins = new ArrayList<Coin>();

		this.frame = frame;
		this.menuHandler = menuHandler;
		this.musicHandler = musicHandler;
		this.font = font;

		this.gameHeight = simulatedHeight;
		scaleFactor = (float) actualHeight / gameHeight;
		this.gameWidth = Math.round(actualWidth / scaleFactor);
		this.setPreferredSize(new Dimension(actualWidth, actualHeight));

		player = new Player(menuHandler.getSprite(300).getImage(), 300, musicHandler);
		player.setPosition(-getGameWidth() / 3, getGameHeight() / 4);

		initSprites();

		// Add keylistener
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_SPACE) {
					System.out.println("Jump pressed!");
					player.tryJump();
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

	private void initSprites() {
		allSprites.add(player);
		for (int x = 0; x < 8; x++) {
			player.addRunningSprites(menuHandler.getSprite(300 + x).getImage(), x);
			System.out.println("Added: x = " + x);
		}
		player.addJumpingSprites(menuHandler.getSprite(308).getImage(), menuHandler.getSprite(309).getImage());
		for (int x = 0; x < platforms.length; x++) {
			platforms[x][0] = new Sprite(menuHandler.getSprite(200 + 10 * x).getImage(), 200 + 10 * x);
			platforms[x][1] = new Sprite(menuHandler.getSprite(201 + 10 * x).getImage(), 201 + 10 * x);
			platforms[x][2] = new Sprite(menuHandler.getSprite(202 + 10 * x).getImage(), 202 + 10 * x);
			platforms[x][3] = new Sprite(menuHandler.getSprite(203 + 10 * x).getImage(), 203 + 10 * x);
			platforms[x][4] = new Sprite(menuHandler.getSprite(204 + 10 * x).getImage(), 204 + 10 * x);
		}
		backgroundImage = new Sprite(menuHandler.getSprite(400).getImage(), 400);
	}

	public void addMovingSprite(Sprite sprite) {
		allSprites.add(sprite);
		movingSprites.add(sprite);
		repaint();
	}

	public void addMovingSprite(Sprite sprite, int x, int y) {
		sprite.setX(x);
		sprite.setY(y);
		allSprites.add(sprite);
		movingSprites.add(sprite);
		repaint();
	}

	public void checkAlive() {
		if (player.getY() < -getGameHeight() / 2) {
			player.setIsAlive(false);
		} else {
			player.setIsAlive(true);
		}
	}

	private void checkCollision() {
		int spriteID;
		if (movingSprites != null) {
			boolean intersected = false;
			for (Sprite sprite : movingSprites) {
				if (player.getCollisionbox().intersects(sprite.getCollisionbox())) {
					spriteID = sprite.getId();
					if (200 <= spriteID && spriteID <= 299) {//
						player.setOnGround(true);
						player.resetJumpsOnGround();
						intersected = true;
						break;
					} else if (sprite instanceof Coin) {
						if (!((Coin) sprite).isTaken()) {
							((Coin) sprite).setTaken(true);
							player.addScore(1);
							musicHandler.playClipFX("PickCoin");
						}
					}
				}
			}
			if (!intersected) {
				player.setOnGround(false);
			} else {
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
			if (sprite instanceof Coin) {
				if (((Coin) sprite).isTaken()) {
					continue;
				}
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
			g.drawImage(sprite.getImage(), Math.round(x * scaleFactor - spriteWidth * scaleFactor / 2),
					Math.round(y * scaleFactor - spriteHeight * scaleFactor / 2), Math.round(spriteWidth * scaleFactor),
					Math.round(spriteHeight * scaleFactor), null);
			// drawCollision(g, sprite);
		}
	}

	private void drawCollision(Graphics g, Sprite sprite) {
		g.setColor(Color.GREEN);

		// Draw collisionbox
		// g.drawRect(Math.round((sprite.collisionbox.x + gameWidth / 2) *
		// scaleFactor),
		// Math.round((-sprite.collisionbox.y + gameHeight / 2) * scaleFactor),
		// Math.round(sprite.collisionbox.width * scaleFactor),
		// Math.round(sprite.collisionbox.height * scaleFactor));

		Graphics2D g2 = (Graphics2D) g;
		Sprite tempSprite = (Sprite) sprite.clone();
		// OBS: Detta klonar inte trots att jag skrivit clone();
		// tempSprite.getCollisionbox().setLocation((int)
		// Math.round(tempSprite.getCollisionbox().getX() + gameWidth / 2),
		// (int) Math.round((-tempSprite.getCollisionbox().getY()) + gameHeight
		// / 2));
		g2.draw(tempSprite.getCollisionbox());
	}

	private void drawBackground(Graphics g) {
		// Draw sprite
		g.drawImage(backgroundImage.getImage(),
				Math.round((gameWidth / 2) * scaleFactor - (backgroundImage.getWidth() * scaleFactor / 2)),
				Math.round((gameHeight / 2) * scaleFactor - (backgroundImage.getHeight() * scaleFactor / 2)),
				Math.round(backgroundImage.getWidth() * scaleFactor),
				Math.round(backgroundImage.getHeight() * scaleFactor), null);
	}

	public void rescale() {
		scaleFactor = (float) frame.getContentPane().getBounds().height / gameHeight;
		gameWidth = Math.round(frame.getContentPane().getBounds().width / scaleFactor);
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
		return ThreadLocalRandom.current().nextInt(-2 * getGameHeight() / 5, 2 * getGameHeight() / 5);
	}

	private int getRightmostX() {
		int spriteID, rightmostX = 0, tempX = 0;
		for (Sprite sprite : movingSprites) {
			spriteID = sprite.getId();
			if (200 <= spriteID && spriteID <= 299) {
				tempX = sprite.getX() + getHalfPlatformWidth(sprite);
				if (tempX > rightmostX) {
					rightmostX = tempX;
				}
			}
		}
		return rightmostX;
	}

	public int getHalfPlatformWidth(Sprite platform) {
		return (platform.getImage().getWidth() / 2);
	}

	private void addBasePlatforms() {
		int x = randomizePlatformColor();
		addMovingSprite(new Sprite(platforms[x][4].getImage(), platforms[x][4].getId()), 0, -getGameHeight() / 2 + 32);
		x = randomizePlatformColor();
		addMovingSprite(new Sprite(platforms[x][4].getImage(), platforms[x][4].getId()),
				getRightmostX() + getHalfPlatformWidth(platforms[x][4]) + getGameWidth() / 8,
				-getGameHeight() / 2 + 32);
	}

	private void addPlatforms() {
		if (genNew) {
			nextPlatform = ThreadLocalRandom.current().nextInt(getGameWidth() / 9, getGameWidth() / 4);
			System.out.println("NEW PLATFORM!");
			genNew = false;
		}
		if ((getGameWidth() - getRightmostX()) >= nextPlatform && !genNew) {
			int color = randomizePlatformColor();
			int length = randomizePlatformLength();
			int x = getGameWidth() + getHalfPlatformWidth(platforms[color][length]);
			int y = randomizePlatformY();
			addMovingSprite(new Sprite(platforms[color][length].getImage(), platforms[color][length].getId()), x, y);

			addCoin(x, y + 32);

			genNew = true;
		}

	}

	private void addCoin(int x, int y) {

		Coin coin = new Coin(menuHandler.getSprite(600).getImage(), (600), x, y);

		for (int i = 0; i < coin.getAnimationLenght(); i++) {
			coin.addSprites(menuHandler.getSprite(600 + i).getImage(), i);
		}
		
		coins.add(coin);
		addMovingSprite(coin);
	}

	public void startLoop() {
		run = true;
		setFocusable(true);
		requestFocusInWindow();
		rescale();
		// Run loop in new thread so it doesn't block everything
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int x = 0;
				addBasePlatforms();

				setFocusable(true);
				while (player.getIsAlive()) {
					try {
						Thread.sleep(sleepTime(x));
					} catch (Exception e) {
						e.printStackTrace();
					}
					requestFocusInWindow();
					checkAlive();
					addPlatforms();
					player.updateSprites();
					player.doJump();

					for (Coin coin : coins) {
						coin.updateSprites();
					}

					updateX();
					checkCollision();
					repaint();
					if (x <= 18000) { // Förhindrar negativ sleep. Max 19000?
						x++;
					}
				}
				if (!player.getIsAlive()) {
					deathScene();
				}
			}
		});
		thread.start();
	}

	private void deathScene() {
		musicHandler.stopAll();
		musicHandler.playClipFX("GameOver");

		Highscore.addHighScore(player.getScore());
		Highscore.saveHighscores();
		add(Box.createRigidArea(new Dimension(0, 60)));
		JLabel titleText = new JLabel("GAME OVER!", JLabel.CENTER);
		titleText.setFont(font);
		titleText.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(titleText);
		revalidate(); // Ser till att texten ritas ut
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		musicHandler.playSongClip("ChibiNinja");
		menuHandler.onPressShow("deathMenu", false);
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
			
			if(removeSprite instanceof Coin) {
				coins.remove(removeSprite);
			}
			
			movingSprites.remove(removeSprite);
			allSprites.remove(removeSprite);
			System.out.println("Removed platform @left. @id" + removeSprite.getId());
		}
	}
}
