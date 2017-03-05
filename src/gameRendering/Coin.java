package gameRendering;

import java.awt.image.BufferedImage;

public class Coin extends Sprite {

	private BufferedImage[] coins = new BufferedImage[10];

	private int index;
	private int deltaIterations;
	
	boolean taken = false;

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
	
	public int getAnimationLenght() {
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
