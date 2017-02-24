package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RenderArea extends JPanel {

	JFrame frame;

	private float scaleFactor;
	private int gameHeight;
	private int gameWidth;
	
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

		//setBackground(Color.getHSBColor(175, 50, 75));
		
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
