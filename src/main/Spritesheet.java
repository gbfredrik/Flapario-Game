package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Spritesheet {
	private static BufferedImage spritesheet;
	private static BufferedImage animationSheet;
	private final int spriteSize = 16;
	private ArrayList<Sprite> buttonSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> textSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> platformSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> npcSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> backgroundSprites = new ArrayList<Sprite>();
	private ArrayList<Sprite> particleSprites = new ArrayList<Sprite>();

	public Spritesheet(String pathSpritesheet, String pathAnimationSheet) {
		System.out.println("Spritesheet constructor");
		try {
			spritesheet = ImageIO.read(new File(pathSpritesheet));
			animationSheet = ImageIO.read(new File(pathAnimationSheet));
			System.out.println("Starting to add all sprites");
			createAllSprites();
			System.out.println("Added all sprites");
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
		createSprite(
				animationSheet.getSubimage(208, 32, 3 * spriteSize, spriteSize),
				301);
		// Bakgrunder, 400-499
		createSprite(
				spritesheet.getSubimage(0, 48, 9 * spriteSize, 7 * spriteSize),
				400); //

		// Partiklar, 500-599
		
		System.out.println("Added all sprites!");
	}

	private void createSprite(BufferedImage spriteImage, int id) {
		Sprite sprite = new Sprite(spriteImage, id);

		if (0 <= id && id <= 99) {
			buttonSprites.add(sprite);
		} else if (100 <= id && id <= 199) {
			textSprites.add(sprite);
		} else if (200 <= id && id <= 299) {
			platformSprites.add(sprite);
		} else if (300 <= id && id <= 399) {
			npcSprites.add(sprite);
		} else if (400 <= id && id <= 499) {
			backgroundSprites.add(sprite);
		} else if (500 <= id && id <= 599) {
			particleSprites.add(sprite);
		} else {
			System.err.println("@adding: No such sprite list. @ID: " + id);
			System.err.flush();
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
			System.err.println("@getting: No such sprite list. @ID: " + id);
			System.err.flush();
			return null;
		}
	}

	public Sprite getSprite(int id) {
		System.out.println("Trying to get sprite from spritesheet");
		ArrayList<Sprite> tempGetFromList;
		if (0 < id && id < 99) {
			tempGetFromList = buttonSprites;
		} else if (0 < id && id < 99) {
			tempGetFromList = textSprites;
		} else if (100 < id && id < 199) {
			tempGetFromList = platformSprites;
		} else if (200 < id && id < 299) {
			tempGetFromList = buttonSprites;
		} else if (300 < id && id < 399) {
			tempGetFromList = npcSprites;
		} else if (400 < id && id < 499) {
			tempGetFromList = backgroundSprites;
		} else if (500 < id && id < 599) {
			tempGetFromList = particleSprites;
		} else {
			System.err.println("@getting: No such sprite list. @ID: " + id);
			System.err.flush();
			return null;
		}
		System.out.println("loop");
		for (Sprite sp : tempGetFromList) {
			if (sp.getId() == id) {
				System.out.println("getID = " + sp.getId() + ". id = " + id);
				return sp;
			}
		}
		System.err.println("@getting: No such sprite. @ID: " + id);
		System.err.flush();
		return null;
	}
}
