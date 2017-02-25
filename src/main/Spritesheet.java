package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Spritesheet {
	private static BufferedImage spritesheet;
	// private static BufferedImage animationSheet;
	private final int spriteSize = 16;
	private ArrayList<Sprite> buttonSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> textSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> platformSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> npcSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> backgroundSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> particleSprites = new ArrayList<Sprite>();

	public Spritesheet(String pathSpritesheet, String pathAnimationSheet) {
		try {
			spritesheet = ImageIO.read(new File(pathSpritesheet));
			// spritesheet = ImageIO.read(new File(pathAnimationSheet));
			createAllSprites();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createAllSprites() {
		// Hex spriteID:
		// 1-99 för knappar
		// 100-199 för bokstäver och text
		// 200-299 för plattformar
		// 300-399 för player och framtida NPC:s
		// 400-499 för bakgrunder
		// 500-599 för partiklar
		// Knappar, 1-99
		createSprite(
				spritesheet.getSubimage(48, 0, 3 * spriteSize, spriteSize), 1); // playButton
		createSprite(
				spritesheet.getSubimage(96, 0, 3 * spriteSize, spriteSize), 2); // scoreButton
		createSprite(
				spritesheet.getSubimage(144, 0, 3 * spriteSize, spriteSize), 3); // backButton
		createSprite(
				spritesheet.getSubimage(192, 0, 3 * spriteSize, spriteSize), 4); // exitButton
		// Bokstäver och text, 100-199

		// Plattformar, 200-299
		createSprite(
				spritesheet.getSubimage(176, 32, 2 * spriteSize, spriteSize),
				200); // Size 2
		createSprite(
				spritesheet.getSubimage(208, 32, 3 * spriteSize, spriteSize),
				201); // Size 3
		createSprite(
				spritesheet.getSubimage(256, 32, 4 * spriteSize, spriteSize),
				202); // Size 4
		createSprite(
				spritesheet.getSubimage(320, 32, 5 * spriteSize, spriteSize),
				203); // Size 5
		// Player och NPC, 300-399

		// Bakgrunder, 400-499
		createSprite(
				spritesheet.getSubimage(0, 48, 9 * spriteSize, 7 * spriteSize),
				400); //

		// Partiklar, 500-599

	}

	private void createSprite(BufferedImage spriteImage, int id) {
		Sprite sprite = new Sprite(spriteImage, id);

		if (0 < id && id < 99) {
			buttonSprites.add(sprite);
		} else if (0 < id && id < 99) {
			textSprites.add(sprite);
		} else if (100 < id && id < 199) {
			platformSprites.add(sprite);
		} else if (200 < id && id < 299) {
			buttonSprites.add(sprite);
		} else if (300 < id && id < 399) {
			npcSprites.add(sprite);
		} else if (400 < id && id < 499) {
			backgroundSprites.add(sprite);
		} else if (500 < id && id < 599) {
			particleSprites.add(sprite);
		} else {
			System.err.println("No such sprite list.");
		}
	}

	public ArrayList<Sprite> getSpriteList(int id) {
		if (0 < id && id < 99) {
			return buttonSprites;
		} else if (0 < id && id < 99) {
			return textSprites;
		} else if (100 < id && id < 199) {
			return platformSprites;
		} else if (200 < id && id < 299) {
			return buttonSprites;
		} else if (300 < id && id < 399) {
			return npcSprites;
		} else if (400 < id && id < 499) {
			return backgroundSprites;
		} else if (500 < id && id < 599) {
			return particleSprites;
		} else {
			System.err.println("No such sprite list.");
			return null;
		}
	}
}
