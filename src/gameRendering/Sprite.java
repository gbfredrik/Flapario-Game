package gameRendering;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Sprite implements Cloneable {
	private BufferedImage image;
	private Rectangle collisionbox;
	private int x, y = 0;
	private int id = 0;

	private SpriteType spriteType;

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error("Uhh...");
		}
	}

	public Sprite(BufferedImage image, int id) {
		this.image = image;
		this.id = id;
		this.spriteType = SpriteType.DEFAULT;
		createCollisionbox();
	}

	public void setSpriteType(SpriteType spriteType) {
		this.spriteType = spriteType;
	}

	public SpriteType getSpriteType() {
		return this.spriteType;
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

	public void updateCollisionBox() {
		collisionbox.setLocation(getX() - image.getWidth() / 2,
				getY() + image.getHeight() / 2);
		// System.out.println("Updating collisionbox. " + id);
		// System.out.println(getX());
		// System.out.println(getY());
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage rescaledImage) {
		this.image = rescaledImage;
		// updateCollisionBox();
	}

}