package main;

import java.awt.Rectangle;
//import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private BufferedImage image;
	public Rectangle collisionbox;
	private int x, y = 0;
	// private int width, height;
	private int id = 0;

	public Sprite(BufferedImage image, int id) {
		this.image = image;
		this.id = id;
		createCollisionbox();
	}

	public int getId() {
		return id;
	}

	public Rectangle getCollisionbox() {
		return collisionbox;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		updateCollisionBox();
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		updateCollisionBox();
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		updateCollisionBox();
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

	public void createCollisionbox() {
		collisionbox = new Rectangle(getX() - image.getWidth() / 2, getY()
				+ image.getHeight() / 2, image.getWidth(), image.getHeight());
	}

	/*
	 * public Sprite(String path, int id) { this(path); this.id = id; }
	 */

	public void updateCollisionBox() {
		collisionbox.setLocation(getX() - image.getWidth() / 2,
				getY() + image.getHeight() / 2);
		System.out.println("Updated collision box! ");
	}

	public BufferedImage getImage() {
		return image;
	}

}