package gameRendering;

import java.awt.image.BufferedImage;

/**
 * Klassen coin hanterar den lilla logik som rör mynten på spelbanan. Den
 * innehåller metoder för om myntet är taget eller ej, samt uppdatering av dess
 * bildens rotering.
 * 
 * @author frebo147
 *
 */
public class Coin extends Sprite {

	private BufferedImage[] coins = new BufferedImage[10];

	private int index;
	private int deltaIterations;

	private boolean taken = false;

	public Coin(BufferedImage image, int id, int x, int y) {
		super(image, id);
		setPosition(x, y);
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

	public int getAnimationLength() {
		return coins.length;
	}

	public void addSprites(BufferedImage spriteImage, int index) {
		coins[index] = spriteImage;
	}

	public void updateSprites() {
		if (deltaIterations == 3) {
			if (index == coins.length - 1) {
				index = 0;
				setImage(coins[0]);
			} else {
				index++;
				setImage(coins[index]);
			}
			deltaIterations = 0;
		} else {
			deltaIterations++;
		}
	}
}
