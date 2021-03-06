package gameRendering;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * En klass som innehåller bild, id och koordinater för varje sprite. Sköter
 * även spritens collisionbox med en rektangel runt bildobjektet.
 * 
 * @author frebo147
 *
 */
public class Sprite {
	private BufferedImage image;
	private Rectangle collisionbox;
	private int x, y = 0;
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
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage rescaledImage) {
		this.image = rescaledImage;
		// updateCollisionBox();
	}
}