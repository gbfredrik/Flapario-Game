package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RenderArea extends JPanel {
	
	JFrame frame;
	
	private float scaleFactor;
	private int gameHeight;
	private int gameWidth;
	
	public int getGameHeight() {
		return gameHeight;
	}

	public int getGameWidth() {
		return gameWidth;
	}
	
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public RenderArea(JFrame frame, int actualWidth, int actualHeight, int simulatedHeight) {
		this.frame = frame;
		this.gameHeight = simulatedHeight;
		scaleFactor = (float) actualHeight/gameHeight;
		this.gameWidth = Math.round(actualWidth/scaleFactor);
		
		this.setPreferredSize(new Dimension(actualWidth, actualHeight));

		setBackground(Color.getHSBColor(175, 50, 75));
	}
	
	public void rescale() {
		scaleFactor = (float) frame.getContentPane().getBounds().height/gameHeight;
		gameWidth = Math.round(frame.getContentPane().getBounds().width/scaleFactor);
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
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(Sprite sprite : sprites) {
			
			int spriteWidth = sprite.getWidth();
			int spriteHeigth = sprite.getHeight();
			int x = sprite.getX();
			int y = sprite.getY();
			x = x + gameWidth/2;
			y = -y + gameHeight/2;
			
			g.drawImage(sprite.getImage(), 
					Math.round(x * scaleFactor - spriteWidth * scaleFactor / 2),
					Math.round(y * scaleFactor - spriteHeigth * scaleFactor / 2), Math.round(spriteWidth * scaleFactor),
					Math.round(spriteHeigth * scaleFactor), null);
		}
	}
}
