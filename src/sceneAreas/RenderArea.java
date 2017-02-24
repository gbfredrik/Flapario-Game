package sceneAreas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Sprite;

public abstract class RenderArea extends JPanel {
	private static final long serialVersionUID = 1L;

	private JFrame frame;

	private float scaleFactor;
	private int gameHeight;
	private int gameWidth;

	protected boolean run = true;

	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public int getGameHeight() {
		return gameHeight;
	}

	public int getGameWidth() {
		return gameWidth;
	}

	public RenderArea(JFrame frame, int actualWidth, int actualHeight, int simulatedHeight) {
		this.frame = frame;
		this.gameHeight = simulatedHeight;
		scaleFactor = (float) actualHeight / gameHeight;
		this.gameWidth = Math.round(actualWidth / scaleFactor);

		this.setPreferredSize(new Dimension(actualWidth, actualHeight));

		// setBackground(Color.getHSBColor(175, 50, 75));

		// Get focus for keyevents
		setFocusable(true);
		requestFocusInWindow();
	}

	public void rescale() {
		scaleFactor = (float) frame.getContentPane().getBounds().height / gameHeight;
		gameWidth = Math.round(frame.getContentPane().getBounds().width / scaleFactor);
		repaint();
	}

	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
		repaint();
	}

	public void addSprite(Sprite sprite, int x, int y) {
		sprites.add(sprite);
		sprite.setX(x);
		sprite.setY(y);
		repaint();
	}

	public void clear() {
		sprites = new ArrayList<Sprite>();
	}

	public abstract void start();

	public void stop() {
		run = false;
		clear();
	}

	protected void startLoop() {
		run = true;

		final int fps = 60;
		final long optimalTime = 1000 / fps;

		// Run loop in new thread so it doesn't block everything
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (run) {
					try {
						Thread.sleep(20);
					} catch (Exception e) {
						e.printStackTrace();
					}

					runrun(); // Ugly
				}
			}

		});
		thread.start();
	}
	
	private void runrun() { // Ugly
		run();
	}

	protected void stopLoop() {
		run = false;
	}

	protected abstract void run();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int x, y, spriteWidth, spriteHeigth;

		for (Sprite sprite : sprites) {
			// Get sprite dimensions and location
			spriteWidth = sprite.getWidth();
			spriteHeigth = sprite.getHeight();
			x = sprite.getX();
			y = sprite.getY();
			// Change coordinate system
			x = x + gameWidth / 2;
			y = -y + gameHeight / 2;

			// Draw sprite
			g.drawImage(sprite.getImage(), Math.round(x * scaleFactor - spriteWidth * scaleFactor / 2),
					Math.round(y * scaleFactor - spriteHeigth * scaleFactor / 2), Math.round(spriteWidth * scaleFactor),
					Math.round(spriteHeigth * scaleFactor), null);
		}
	}
}
