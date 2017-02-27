package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Player extends JPanel {
	// HANTERAR player-sprite, dess rörelser samt poäng
	private static final long serialVersionUID = 1L;

	private Sprite playerSprite;

	private int score;
	private boolean onGround = true;
	private boolean jumpPressed = false;
	private boolean doubleJump = false;
	private final int jumpMaxHeight = 35;
	private int jumpHeightRemaining;
	private int heightFallen;
	private boolean isAlive;

	public Player() {
		score = 0;
		
		playerSprite = new Sprite("./src/assets/png/mainchar/mainchar1.png", 5);

		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_SPACE) {
					tryJump();
				}
				if (key == KeyEvent.VK_ESCAPE) {
					// setFullscreen(false);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	}

	public void doJump() {
		if (jumpHeightRemaining > 0) {
			playerSprite.setY(playerSprite.getY() + jumpSpeed());
			if (jumpHeightRemaining == 0) {
				heightFallen = 0;
			}
		} else if (!onGround) {
			playerSprite.setY(playerSprite.getY() - fallSpeed());
		}
	}

	private void tryJump() {
		if (!jumpPressed && !doubleJump) {
			System.out.println("Jump pressed!");
			jumpPressed = true;
			jumpHeightRemaining += jumpMaxHeight;
		}
		if (jumpPressed && !doubleJump
				&& jumpHeightRemaining < (jumpMaxHeight - 25)) {
			jumpHeightRemaining += jumpMaxHeight;
			System.out.println("Double jump!");
			doubleJump = true;
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
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	public void setPlayerSprite(Sprite newPlayerSprite) {
		this.playerSprite = newPlayerSprite;
	}

	public Sprite getPlayerSprite() {
		return playerSprite;
	}

	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean getIsAlive() {
		return isAlive;
	}

}