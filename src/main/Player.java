package main;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Player extends JPanel {
	// HANTERAR player-sprite, dess rörelser samt poäng
	private static final long serialVersionUID = 1L;

	private Sprite playerActiveSprite;
	private BufferedImage[] playerRunning = new BufferedImage[8];
	private BufferedImage playerJump, playerDoubleJump;
	private int activeRunning = 0;
	private int iterationsSinceStep = 0;

	private int score = 0;
	private boolean onGround = true;
	private boolean jumpPressed = false;
	private boolean doubleJump = false;
	private final int jumpMaxHeight = 64;
	private int jumpHeightRemaining;
	private int heightFallen;
	private boolean isAlive = true;
	private MusicHandler musicHandler;

	public Player(BufferedImage playerImage, int id, MusicHandler musicHandler) {
		playerActiveSprite = new Sprite(playerImage, id);
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

	public void tryJump() {
		if (!jumpPressed && onGround) {
			System.out.println("Jump pressed!");
			musicHandler.playClipFX("Jump");
			jumpPressed = true;
			doubleJump = false;
			jumpHeightRemaining += jumpMaxHeight;
		} else if (jumpPressed && !doubleJump) {
			/* && jumpHeightRemaining < (jumpMaxHeight - 25) */
			// Villkor eller ej?
			System.out.println("Double jump!\n");
			doubleJump = true;
			musicHandler.playClipFX("Jump");
			jumpHeightRemaining += jumpMaxHeight;
		}
	}

	public void doJump() {
		if (jumpHeightRemaining > 0) {
			playerActiveSprite.setY(playerActiveSprite.getY() + jumpSpeed());
			if (jumpHeightRemaining == 0) {
				heightFallen = 0;
			}
		} else if (!onGround) {
			playerActiveSprite.setY(playerActiveSprite.getY() - fallSpeed());
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

	public void setScore(int point) {
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

	public void setPlayerSprite(Sprite newPlayerSprite) {
		this.playerActiveSprite = newPlayerSprite;
	}

	public Sprite getPlayerSprite() {
		return playerActiveSprite;
	}

	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean getIsAlive() {
		return isAlive;
	}

	public void updateSprites() {
		if (doubleJump) {
			playerActiveSprite.setImage(playerDoubleJump);
			activeRunning = 0;
		} else if (!onGround && !doubleJump) {
			playerActiveSprite.setImage(playerJump);
			activeRunning = 0;
		} else {
			if (iterationsSinceStep == 3) {
				if (activeRunning == playerRunning.length - 1) {
					activeRunning = 0;
					playerActiveSprite.setImage(playerRunning[0]);
				} else {
					activeRunning++;
					playerActiveSprite.setImage(playerRunning[activeRunning]);
				}
				iterationsSinceStep = 0;
			} else {
				iterationsSinceStep++;
			}
		}
	}
}