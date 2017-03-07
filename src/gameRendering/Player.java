package gameRendering;

import java.awt.image.BufferedImage;

import main.MusicHandler;

/**
 * Denna klass sköter all logik som härrör till spelaren. Om spelaren tryckt på
 * sapce kommer metoder här i att anropas, vilket sedan avgör om hoppet är
 * tillåtet och därefter utför hoppet. Det finns även en metod för byte av
 * spelarens bild beroende på om den hoppar eller springer. Slutligen lagrar
 * spelarklassen spelarens poäng under den nuvarande sessionen.
 * 
 * @author frebo147
 *
 */
public class Player extends Sprite {
	// private Sprite playerActiveSprite;
	private BufferedImage[] playerFlappy = new BufferedImage[4];
	private BufferedImage[] playerRunning = new BufferedImage[8];
	private BufferedImage playerJump, playerDoubleJump;
	private int activeRunning = 0;
	private int activeFlappy = 0;
	private int iterationsSinceStep = 0;

	private int score = 0;
	private boolean onGround = true;
	private boolean jumpPressed = false;
	private boolean doubleJump = false;
	private final int jumpMaxHeight = 64;
	private int jumpHeightRemaining;
	private int heightFallen;
	private boolean isAlive = true;
	private boolean flappyActive = false;
	private MusicHandler musicHandler;

	public Player(BufferedImage image, int id, MusicHandler musicHandler) {
		super(image, id);
		this.musicHandler = musicHandler;
	}

	public void addRunningSprites(BufferedImage spriteImage, int x) {
		playerRunning[x] = spriteImage;
	}

	public void addJumpingSprites(BufferedImage jumpSpriteImage,
			BufferedImage doubleJumpSpriteImage) {
		playerJump = jumpSpriteImage;
		playerDoubleJump = doubleJumpSpriteImage;
	}

	public void addFlappySprites(BufferedImage flappySpriteImage, int x) {
		playerFlappy[x] = flappySpriteImage;
	}

	public void tryJump() {
		if (flappyActive) {
			jumpHeightRemaining += jumpMaxHeight / 2;
		} else if (!jumpPressed && onGround) {
			musicHandler.playClipFX("Jump");
			jumpPressed = true;
			doubleJump = false;
			jumpHeightRemaining += jumpMaxHeight;
		} else if (!onGround && !doubleJump) {
			doubleJump = true;
			musicHandler.playClipFX("Jump");
			jumpHeightRemaining += jumpMaxHeight;
		}
	}

	public void doJump() {
		if (jumpHeightRemaining > 0) {
			setY(getY() + jumpSpeed());
			if (jumpHeightRemaining == 0) {
				heightFallen = 0;
			}
		} else if (!onGround) {
			setY(getY() - fallSpeed());
		}
	}

	private int jumpSpeed() {
		int factor;
		factor = (int) Math.round((1.0 + (double) (jumpHeightRemaining / 10)));
		jumpHeightRemaining -= factor;
		return factor;
	}

	private int fallSpeed() {
		int factor;
		factor = (int) Math.round((1.0 + (double) (heightFallen / 10)));
		heightFallen += factor;
		return factor;
	}

	public void addScore(int point) {
		this.score += point;
	}

	public int getScore() {
		return score;
	}

	public void resetJumpsOnGround() {
		jumpPressed = false;
		doubleJump = false;
		heightFallen = 0;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean getIsAlive() {
		return isAlive;
	}

	public void setFlappyActive(boolean flappyActive) {
		this.flappyActive = flappyActive;
	}

	public boolean getFlappyActive() {
		return flappyActive;
	}

	public void updateSprites() {
		if (flappyActive) {
			if (iterationsSinceStep == 3) {
				if (activeFlappy == playerFlappy.length - 1) {
					activeFlappy = 0;
					setImage(playerFlappy[0]);
				} else {
					activeFlappy++;
					setImage(playerFlappy[activeFlappy]);
				}
				iterationsSinceStep = 0;
			} else {
				iterationsSinceStep++;
			}
		} else if (doubleJump) {
			setImage(playerDoubleJump);
			activeRunning = 0;
		} else if (!onGround && !doubleJump) {
			setImage(playerJump);
			activeRunning = 0;
		} else {
			if (iterationsSinceStep == 3) {
				if (activeRunning == playerRunning.length - 1) {
					activeRunning = 0;
					setImage(playerRunning[0]);
				} else {
					activeRunning++;
					setImage(playerRunning[activeRunning]);
				}
				iterationsSinceStep = 0;
			} else {
				iterationsSinceStep++;
			}
		}
	}
}